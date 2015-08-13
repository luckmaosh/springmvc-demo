package com.niux.spring.lifecycle;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/13.上午10:07
 */
public class TestPostProcessor {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:car.xml");

        Car bean = context.getBean(Car.class);

        System.out.println("brand=" +bean.getBrand());
        System.out.println("color=" +bean.getColor());


        AbstractApplicationContext abstractApplicationContext = (AbstractApplicationContext) context;
        abstractApplicationContext.close();

    }
}
