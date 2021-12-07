package com.egintra.common.exception;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author liushihao
 * @date 2021/11/17
 * 漏洞拦截
 */
@WebFilter
public class CookieHttpOnlyFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest)) {
            chain.doFilter(request, response);
            return;
        }
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpResp = (HttpServletResponse) response;
        Cookie[] cookies = httpReq.getCookies();
        if (cookies != null) {
            Cookie cookie = cookies[0];
            if (cookie != null) {
                HttpSession session = httpReq.getSession();
                if (session != null) {
                    String sessionId = session.getId();
                    // http设置
                    httpResp.addHeader("Set-Cookie", "JSESSIONID=" + sessionId + "; Path=/fis; HttpOnly");
                    httpResp.addHeader("x-frame-options", "SAMEORIGIN");
                    // https设置
//	                    httpResp.addHeader("Set-Cookie", "JSESSIONID=" + sessionId
//	                            + "; Path=/admin;Secure; HttpOnly");
                }
            }
        }
        chain.doFilter(httpReq, httpResp);

    }


    public void destroy() {
    }


    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
