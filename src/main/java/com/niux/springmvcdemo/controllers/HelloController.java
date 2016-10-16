package com.niux.springmvcdemo.controllers;

import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shaohui.mao
 * on 15/12/26.下午2:50
 */
@RequestMapping("/")
@org.springframework.stereotype.Controller
@Path("")
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Get
    public String index() {
        return "index";
    }


    @Get("floatlayer")
    public String floatLayer() {

        return "floatlayer";
    }

    @Get("cityLayer")
    public String floatLayer2() {

        return "cityLayer";
    }
//
//    @RequestMapping(value = "/hello", method = RequestMethod.GET)
//    public String index() {
//        return "hello";
//    }
//
//    @RequestMapping(value = "/", method = RequestMethod.GET)
//    public ModelAndView nothing() {
//        logger.info("nothing");
//        ModelAndView mv = new ModelAndView("hello");
//        return mv;
//    }
}
