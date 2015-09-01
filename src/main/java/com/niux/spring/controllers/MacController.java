package com.niux.spring.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/9/1.下午2:04
 */
@Path("macbook")
public class MacController {


    @Get("air")
    public String air() {
        return "macbookair";
    }
}
