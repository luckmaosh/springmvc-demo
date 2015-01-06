/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package org.format.demo.filter;

import org.apache.log4j.MDC;

import javax.servlet.*;
import java.io.IOException;

/**
 * User: maso [maoshaohui@baidu.com]
 * Date: 15-1-5
 * Time: 下午5:18
 */
public class MDCFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        MDC.put("client", "32323");

        filterChain.doFilter(servletRequest, servletResponse);

        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
