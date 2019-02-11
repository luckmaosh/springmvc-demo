package com.niux.spring.util;

public class ABTest {
    public static void main(String args[]) {
        new Thread() {
            public void run() {
                B.test();
            }
        }.start();

        new Thread() {
            public void run() {
                A.test();
            }
        }.start();
    }
}
