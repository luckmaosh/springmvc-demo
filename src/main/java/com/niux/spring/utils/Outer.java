package com.niux.spring.utils;

/**
 * Created by shaohui.mao
 * on 16/4/5.上午10:09
 */
public class Outer {

    private int age;

    static class Inner {
        private String name;
    }


    public static void main(String[] args) {
        Inner inner = new Inner();
        Inner inner2 = new Inner();

        System.out.println(inner);
        System.out.println(inner2);
        System.out.println(inner.equals(inner2));
    }
}
