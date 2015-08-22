package com.niux.spring.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/5.下午7:03
 * 测试cookie
 */
@Path("")
public class TestCookieController {

    private static final Logger logger = LoggerFactory.getLogger(TestCookieController.class);


    @Get("testCookie")
    public String testCookie(Invocation inv) {
        HttpServletRequest request = inv.getRequest();
        HttpServletResponse response = inv.getResponse();
        Cookie[] cookies = request.getCookies();
        logger.info("cookies=" + cookies);

        Cookie namecookie = new Cookie("name", "xiaoming");
        Cookie passwordcookie = new Cookie("password", "123");
        Cookie optioncookie = new Cookie("option", "12");

//生命周期
        namecookie.setMaxAge(60 * 60 * 24 * 365);
        passwordcookie.setMaxAge(60 * 60 * 24 * 365);
        optioncookie.setMaxAge(60 * 60 * 24 * 365);
        namecookie.setDomain(".focus.cn");

        response.addCookie(namecookie);
        response.addCookie(passwordcookie);
        response.addCookie(optioncookie);
        return "@ok";
    }
}
