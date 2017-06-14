package com.niux.spring.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by shaohui.mao
 * on 15/12/26.下午2:48
 */
public class HelloFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init filter ...");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("hello filter");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {
        System.out.println("destroy filter");
    }
}
