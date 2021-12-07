package com.egintra.common.aspects;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.pointcuts.UserContext;
import com.egintra.common.pointcuts.VdException;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.utils.StringUtil;
import com.egintra.common.utils.TokenUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 获取用户信息
 *
 * @author liushihao
 * @date 2021/9/15
 */
@Aspect
@Component
public class RequestAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestAspect.class);
    @Resource
    private LoginMapper loginMapper;

    /**
     * 拦截所有Controller类中所有public方法
     */
    @Pointcut("(@target(org.springframework.web.bind.annotation.RestController)) && (execution(public * com.egintra.feeService..*.*(..)))")
    public void executionService() {
    }

    /**
     * 方法调用之前调用
     */
    @Before("executionService()")
    public void doBefore(JoinPoint joinPoint) {
        LOGGER.info("开始处理请求头部信息！");
        // 初始化用户信息类
        UserContext.init();
        // 取出请求参数
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        // 若请求参数为空,方法结束
        if (requestAttributes == null) {
            LOGGER.info("ServletRequestAttributes is null");
            return;
        }
        // 根据注解设置判断是否需要Token，默认为需要
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method targetMethod = methodSignature.getMethod();
        SysToken tokenRequired = targetMethod.getAnnotation(SysToken.class);
        if (!ObjectUtils.isEmpty(tokenRequired) && !tokenRequired.required()) {
            return;
        }
        // 取出请求request
        HttpServletRequest request = requestAttributes.getRequest();
        LOGGER.info("request URI={}", request.getRequestURI());
        // token
        String token = "";
        // 从 http 请求头中取出 token
        token = request.getHeader("token");
        LOGGER.info("token:{}", token);
        // 若token为空,抛出异常
        if (StringUtil.isEmpty(token)) {
            UserContext.putToken(token);
        }
        // token不为空,查询用户信息
        // 获取token中的userid
        String userId;
        try {
            userId = TokenUtils.getTokenUserId();
        } catch (JWTDecodeException j) {
            throw new VdException("401", "");
        }
        RightUser userInfo = loginMapper.queryUserInfoById(userId);
        if (userInfo == null) {
            throw new VdException("", "获取用户登录信息失败");
        }
        // 取出userId,不为空则记录用到用户信息类
        if (!StringUtil.isEmpty(userId)) {
            UserContext.putUserId(userId);
        }
        // 取出userName,不为空则记录用到用户信息类
        String userNm = userInfo.getName();
        if (!StringUtil.isEmpty(userNm)) {
            UserContext.putUserName(userNm);
        }
        // 取出userCode,不为空则记录用到用户信息类
        String userCode = userInfo.getNo();
        if (!StringUtil.isEmpty(userCode)) {
            UserContext.putUserCode(userCode);
        }

        // 把用户登录信息放到ThreadLocal中
        UserContext.putUserInfo();
    }

    /**
     * 方法之后调用 清楚线程缓存
     */
    @AfterReturning(pointcut = "executionService()")
    public void doAfterReturning() {
        LOGGER.info("clear cache for user login info");
        UserContext.remove();
    }

    /**
     * 方法之后调用耗时打印
     */
    @Around(value = "executionService()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object result;
        StringBuilder sbLog = new StringBuilder();
        try {
            MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
            sbLog.append(String.format("method=%s", methodSignature.getMethod().getName()));
            long startTime = System.currentTimeMillis();
            result = proceedingJoinPoint.proceed();
            long endTime = System.currentTimeMillis();
            sbLog.append(String.format(",cost time=%sms", endTime - startTime));
        } finally {
            LOGGER.info(sbLog.toString());
        }
        return result;
    }
}
