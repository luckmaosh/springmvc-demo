package org.format.demo.interceptor;

import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 * User: maso [maoshaohui@baidu.com]
 * Date: 15-1-5
 * Time: 上午11:33
 */
public class PublicInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(PublicInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("preHandle " + new Random().nextLong());
        MDC.put("client", "32323");
        return true;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("interceptor");
        logger.info("host:" + request.getHeader("host"));//request.getHeader("host")

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        MDC.remove("client");
    }
}
