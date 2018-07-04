package com.niux.spring.aop;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class MethodAfterInterceptor implements AfterReturningAdvice {

    public void afterReturning(Object value, Method method, Object[] args,
                               Object instance) throws Throwable {

        System.out.println("method  " + method.getName() + "had finished and return value-" + value);

    }

}