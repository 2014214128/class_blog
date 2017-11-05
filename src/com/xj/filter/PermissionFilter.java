package com.xj.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by sheeran on 2017/6/5.
 * Talk is cheap show me code.
 */
@WebFilter(filterName = "PermissionFilter",urlPatterns = "/jsps/main.jsp")
public class PermissionFilter implements Filter {
    //保证主页面只能由servlet进行跳转
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("启动过滤器");

        String url = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println("请求的url  =  "+url);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
