package com.autonavi.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by maso on 15/5/11.
 */

@Controller
@RequestMapping("/test")
public class TestController {


    @ResponseBody
    @RequestMapping("/string")
    public Map<String ,Object> get() {
        ModelAndView modelAndView = new ModelAndView("Hello World");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", "0");
        modelAndView.addObject(result) ;
        return result;
    }
}
