package com.egintra.common.config;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.egintra.common.pointcuts.PassToken;
import com.egintra.common.pointcuts.SysToken;
import com.egintra.common.repository.entity.RightUser;
import com.egintra.common.repository.mapper.LoginMapper;
import com.egintra.common.utils.TokenUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Resource
    private LoginMapper loginMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object object) {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader("token");
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        // 检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        // 检查有没有需要用户权限的注解
        if (method.isAnnotationPresent(SysToken.class)) {
            SysToken sysToken = method.getAnnotation(SysToken.class);
            if (sysToken.required()) {
                // 执行认证
                if (token == null) {
                    throw new RuntimeException("无token，请重新登录");
                }
                // 获取token中的userid
                String userId;
                try {
                    userId = TokenUtils.getTokenUserId();
                } catch (JWTDecodeException j) {
                    throw new RuntimeException("401");
                }
                RightUser userInfo = loginMapper.queryUserInfoById(userId);
                if (userInfo == null) {
                    throw new RuntimeException("用户不存在，请重新登录");
                }

                // 验证token
                TokenUtils.verifyToken(token);
                return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}
