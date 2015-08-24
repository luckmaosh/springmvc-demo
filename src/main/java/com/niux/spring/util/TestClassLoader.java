package com.niux.spring.util;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/22.下午3:25
 */
public class TestClassLoader {


    public static void main(String[] args) throws ClassNotFoundException {
        Class<TestClassLoader> loaderClass = TestClassLoader.class;

        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("close .....");
            }
        });
        ClassLoader parent = contextClassLoader.getParent();


        //my classLoader
        AppClassLoader appClassLoader = new AppClassLoader();
        Class<?> aClass = appClassLoader.loadClass("com.niux.spring.util.TestClassLoader");

        System.out.println(aClass == loaderClass); //true

        Thread.currentThread().setContextClassLoader(appClassLoader);
        Class<?> bClass = appClassLoader.loadClass("com.niux.spring.util.TestClassLoader");
        System.out.println(bClass == loaderClass); //true

        System.out.println("finish .....");
    }
}
