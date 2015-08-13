package com.niux.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/13.上午10:49
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("MyBeanFactoryPostProcessor ...postProcessBeanFactory ");
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("car");

        beanDefinition.getPropertyValues().add("color", "red");

    }
}
