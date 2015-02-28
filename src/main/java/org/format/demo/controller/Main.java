package org.format.demo.controller;

import org.format.demo.transaction.NoTransactinService;
import org.format.demo.transaction.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by maso on 15/2/28.
 */
public class Main {


    public static void main(String[] args) {
        String name = "sss3";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springConfig/applicationContext.xml");

        //有事务保证
        UserService userService = (UserService) applicationContext.getBean("proxyFactoryBean");
        userService.insert(name);

        //无事务保证
        NoTransactinService noTransactinService = (NoTransactinService) applicationContext.getBean("noTransactinService");
        noTransactinService.insert(name);

    }
}
