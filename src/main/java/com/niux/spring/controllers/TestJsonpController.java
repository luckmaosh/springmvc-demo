package com.niux.spring.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/26.下午2:31
 */
@Path("jsonp")
public class TestJsonpController {

    @Get("")
    public String index(){
        return "jsonp";
    }
}
