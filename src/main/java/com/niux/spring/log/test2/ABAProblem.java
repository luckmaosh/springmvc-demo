package com.niux.spring.log.test2;

import java.util.concurrent.atomic.AtomicInteger;

public class ABAProblem {

    public static void main(String[] args) throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(100);
        new Thread(() -> {
            atomicInteger.compareAndSet(100, 101);
            atomicInteger.compareAndSet(101, 100);
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            boolean result = atomicInteger.compareAndSet(100, 101);
            System.out.println(String.format("  >>> 修改 atomicInteger :: %s ", result));
        }).start();

        /**
         *   >>> 修改 atomicInteger :: true
         */
    }


}
