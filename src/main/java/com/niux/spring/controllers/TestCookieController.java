package com.niux.spring.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/5.下午7:03
 */
@Path("")
public class TestCookieController {


    @Get("testCookie")
    public String testCookie(Invocation inv) {

        return "@ok";
    }
}
