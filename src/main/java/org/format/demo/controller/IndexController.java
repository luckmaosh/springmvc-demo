package org.format.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;


@Controller
@RequestMapping("/index")
public class IndexController {

    public static Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping
    public ModelAndView index(@RequestHeader HttpHeaders headers) {
        logger.info("from request:" + headers.getFirst("LOGID"));
        Set<String> keys = headers.keySet();
        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {
                System.out.println("from request " + key + "=" + headers.getFirst(key));
            }
        }

        ModelAndView view = new ModelAndView("index");
        view.addObject("welcome", "hello");
        return view;
    }

}