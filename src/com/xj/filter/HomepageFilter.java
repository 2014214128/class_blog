package com.xj.filter;

import com.xj.domain.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Asus on 2017/6/14.
 */
@WebFilter(filterName = "HomepageFilter",urlPatterns = "/jsps/background/homepage/*")
public class HomepageFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user != null) {
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            httpServletResponse.sendRedirect("/blog/jsps/background/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
