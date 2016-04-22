package com.niux.springmvcdemo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by shaohui.mao
 * on 15/12/26.下午2:50
 */
@RequestMapping("/")
@org.springframework.stereotype.Controller
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "hello";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView nothing() {
        logger.info("nothing");
        ModelAndView mv = new ModelAndView("hello");
        return mv;
    }
}
