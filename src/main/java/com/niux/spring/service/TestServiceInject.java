package com.niux.spring.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/24.下午1:45
 */
public class TestServiceInject {


    public static void main(String[] args) {


        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:springConfig/applicationContext.xml");


        DemoService bean = applicationContext.getBean(DemoService.class);

    }
}
