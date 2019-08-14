package com.niux.spring.quartz;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test{

    public static void main(String[] args) throws BeansException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-mvc.xml");  
        QuartzManager quartzManager = (QuartzManager) ctx.getBean("quartzManager");
        try {  
            System.out.println("【系统启动】开始(每1秒输出一次)...");        
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
}