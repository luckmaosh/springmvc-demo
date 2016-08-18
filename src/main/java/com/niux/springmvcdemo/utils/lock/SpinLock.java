package com.niux.springmvcdemo.utils.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLock {

    private AtomicReference<Thread> sign = new AtomicReference<Thread>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {
            System.out.println("try again!");
        }


    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current, null);
    }


    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        spinLock.lock();

        spinLock.unlock();
    }
}