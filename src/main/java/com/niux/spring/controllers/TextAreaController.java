package com.niux.spring.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

/**
 * @author 矛戈 on 2018/3/30.
 */
@Path("textArea")
public class TextAreaController {

    @Get
    public String index() {
        return "textArea";
    }

}
