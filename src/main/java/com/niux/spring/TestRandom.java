package com.niux.spring;

import java.util.Random;

/**
 * @author 矛戈 on 2017/10/16.
 */
public class TestRandom {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println(TestRandom.class.getClassLoader());
        Class<?> aClass = TestRandom.class.getClassLoader().loadClass("com.niux.spring.ByteBuffer2");


        int c = 0;
        int b = 0;
        int a = 0;
        for (int i = 0; i < 100000000; i++) {
            Random r = new Random();
            int n = r.nextInt(10);
            if (n < 2) {
                //System.out.println("C");
                c++;
            } else if (n >= 2 && n < 5) {
                //System.out.println("B");
                b++;
            } else {
                //System.out.println("A");
                a++;
            }

        }

        System.out.println("a ,b,c=" + a + " " + b + " " + c);

    }
}
