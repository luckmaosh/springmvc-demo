package com.niux.spring.util;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/22.下午3:25
 */
public class AppClassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }
}
