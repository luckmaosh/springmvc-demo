package com.niux.spring.util;

/**
 * Created by maoshaohui [maoshh88@gmail.com]
 * on 15/8/18.下午8:12
 */
public class TestHook {

    public static void main(String[] args) {


        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                long id = currentThread().getId();

                System.out.println("shutting down " + id);
            }
        });


        long id = Thread.currentThread().getId();
        System.out.println("end..." +id);

    }
}
