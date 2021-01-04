package com.niux.spring.log.test2;

import java.util.concurrent.atomic.AtomicStampedReference;

public class ABASolution {
    public static void main(String[] args) throws Exception {
        AtomicStampedReference stampedReference = new AtomicStampedReference(100, 0);

        new Thread(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stampedReference.compareAndSet(100,
                    101,
                    stampedReference.getStamp(),
                    stampedReference.getStamp() + 1);

            stampedReference.compareAndSet(101,
                    100,
                    stampedReference.getStamp(),
                    stampedReference.getStamp() + 1);
        }).start();


        new Thread(() -> {
            int stamp = stampedReference.getStamp();

            System.out.println("stamp=" + stamp);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result = stampedReference.compareAndSet(100, 101, stamp, stamp + 1);
            System.out.println(String.format("  >>> 修改 stampedReference :: %s ", result));
        }).start();

    }
}
