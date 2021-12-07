package com.egintra.common.exception;

import com.egintra.common.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * @author liushihao
 * @date 2021/11/17
 * 漏洞拦截
 */
@WebFilter
public class SessionFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestStr = getRequestString(request);
        System.out.println("requestStr： ======================== " + requestStr);
        System.out.println("完整的地址是====" + request.getRequestURL().toString());
        System.out.println("提交的方式是========" + request.getMethod());
        LOGGER.info("requestStr： ======================== " + requestStr);
        LOGGER.info("完整的地址是====" + request.getRequestURL().toString());
        LOGGER.info("提交的方式是========" + request.getMethod());

        if ("bingo".equals(guolv2(requestStr)) || "bingo".equals(guolv2(request.getRequestURL().toString()))) {
            System.out.println("======访问地址发现非法字符，已拦截======");
            LOGGER.info("======访问地址发现非法字符，已拦截======其非法地址为：" + guolv2(request.getRequestURL().toString()));
            response.setStatus(403);
            //response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }
        // 主机ip和端口 或 域名和端口
//        String myhosts = request.getHeader("host");
//        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/classes/csrfWhite.txt");
//        ArrayList<String> hosts = readFromTextFile(path);
//        if (!hosts.contains(myhosts)) {
//            System.out.println("======访问host非法，已拦截======其非法host为:" + myhosts);
//            log.info("======访问host非法，已拦截======其非法host为:" + myhosts);
//            response.setStatus(403);
//            return;
//        }

        String currentURL = request.getRequestURI();
        // add by wangsk 过滤请求特殊字符，扫描跨站式漏洞
        Map parameters = request.getParameterMap();
        if (parameters != null && parameters.size() > 0) {
            for (Iterator iter = parameters.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                String[] values = (String[]) parameters.get(key);
                for (int i = 0; i < values.length; i++) {
                    values[i] = guolv(values[i]);
                    System.out.println(values[i]);
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public static String guolv(String a) {
        a = a.replaceAll("%22", "");
        a = a.replaceAll("%27", "");
        a = a.replaceAll("%3E", "");
        a = a.replaceAll("%3e", "");
        a = a.replaceAll("%3C", "");
        a = a.replaceAll("%3c", "");
        a = a.replaceAll("<", "");
        a = a.replaceAll(">", "");
        a = a.replaceAll("\"", "");
        a = a.replaceAll("'", "");
        a = a.replaceAll("\\+", "");
        a = a.replaceAll("\\(", "");
        a = a.replaceAll("\\)", "");
        a = a.replaceAll(" and ", "");
        a = a.replaceAll(" or ", "");
        a = a.replaceAll(" 1=1 ", "");
        return a;
    }

    private String getRequestString(HttpServletRequest req) {
        String requestPath = req.getServletPath().toString();
        String queryString = req.getQueryString();
        if (queryString != null)
            return requestPath + "?" + queryString;
        else
            return requestPath;
    }

    public String guolv2(String a) {
        if (!StringUtil.isEmpty(a)) {
            if (a.contains("%22") || a.contains("%3E") || a.contains("%3e")
                    || a.contains("%3C") || a.contains("%3c")
                    || a.contains("<") || a.contains(">") || a.contains("\"")
                    || a.contains("'") || a.contains("+") ||
                    a.contains(" and ") || a.contains(" or ")
                    || a.contains("1=1") || a.contains("(") || a.contains(")")) {
                return "bingo";
            }
        }
        return a;
    }

    public static ArrayList<String> readFromTextFile(String pathname) throws IOException {
        ArrayList<String> strArray = new ArrayList<String>();
        File filename = new File(pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader br = new BufferedReader(reader);
        String line = "";
        line = br.readLine();
        while (line != null) {
            strArray.add(line);
            line = br.readLine();
        }
        return strArray;
    }

}
