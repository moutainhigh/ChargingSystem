package com.egintra.common.aspects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截统一打印出入参日志和调用方法耗时（单位是毫秒）
 * <p>在方法名上使用@SysLog，就可以以json格式打印出入参数</p>
 *
 * @author liushihao
 * @date 2021/8/27
 */
@Component
@Aspect
public class LogAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper mapper;

    @Autowired
    public LogAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 注释启用
     */
    @Pointcut("@annotation(com.egintra.common.pointcuts.SysLog)")
    private void cut() {
    }

    /**
     * 打印方法返回参数和调用方法耗时，单位毫秒
     */
    @Around("cut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startTime = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        long endTime = System.currentTimeMillis();
        //打印调用方法的返回参数和调用本方法的总耗时
        LOGGER.info("method={}, response params={}, cost time={}ms", methodName, mapper.writeValueAsString(proceed), endTime - startTime);
        return proceed;
    }

    /**
     * 打印方法入参
     */
    @Before("cut()")
    public void before(JoinPoint joinPoint) throws JsonProcessingException {
        StringBuilder param = new StringBuilder();
        for (Object object : joinPoint.getArgs()) {
            if (object instanceof MultipartFile
                    || object instanceof HttpServletRequest
                    || object instanceof HttpServletResponse) {
                continue;
            }
            param.append(mapper.writeValueAsString(object)).append(",");
        }
        String methodName = joinPoint.getSignature().getName();
        //打印方法入参
        LOGGER.info("method={}, request params={}", methodName, param.toString());
    }
}
