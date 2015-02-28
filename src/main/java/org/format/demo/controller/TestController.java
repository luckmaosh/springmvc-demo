package org.format.demo.controller;

import org.format.demo.transaction.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by maso on 15/2/28.
 */
@Controller
@RequestMapping("/test/tx")
public class TestController {
    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping("insert")
    public String insert(@RequestParam("name") String name) {

        long insert = userService.insert(name);

        return "ok" + insert;
    }

}
