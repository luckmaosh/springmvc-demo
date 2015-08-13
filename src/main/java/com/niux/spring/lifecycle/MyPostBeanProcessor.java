package com.niux.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/13.上午10:06
 */
public class MyPostBeanProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyPostBeanProcessor before initial.....");
        if ("car".equals(beanName)) {
            Car car = (Car) bean;
            System.out.println("postProcessBeforeInitialization =>brand=" + car.getBrand() +  " color=" + car.getColor());
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("MyPostBeanProcessor after initial....");
        return bean;
    }
}
