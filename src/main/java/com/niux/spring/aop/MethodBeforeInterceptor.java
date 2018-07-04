package com.niux.spring.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class MethodBeforeInterceptor implements MethodBeforeAdvice {

    public void before(Method method, Object[] args, Object instance)
            throws Throwable {

        System.out.println("method invoke:" + method.getName());

        if (instance instanceof AopServiceImpl) {

            String name = ((AopServiceImpl) instance).getName();

            if (name == null)
                throw new NullPointerException("name cannot be null");
        }

    }

}