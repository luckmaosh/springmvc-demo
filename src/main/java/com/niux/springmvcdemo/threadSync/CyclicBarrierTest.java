package com.niux.springmvcdemo.threadSync;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    System.out.println("finish new thread");
                    c.await();
                } catch (Exception e) {

                }
                System.out.println(1);
            }
        }).start();

        try {
            System.out.println("wait   for  new thread");
            c.await();

            System.out.println(" continue ");
        } catch (Exception e) {

        }
        System.out.println(2);
    }
}