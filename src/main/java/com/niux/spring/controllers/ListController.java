package com.niux.spring.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/22.下午1:28
 */
@Path("list")
public class ListController {


    @Get("")
    public String list(net.paoding.rose.web.Invocation inv) {


        return "";
    }
}
