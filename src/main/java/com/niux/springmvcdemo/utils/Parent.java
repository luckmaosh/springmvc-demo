package com.niux.springmvcdemo.utils;

/**
 * Created by shaohui.mao
 * on 16/4/6.上午11:39
 */
public class Parent {

    protected final void acquire(int a) {
        tryAcquire(a);
    }


    protected boolean tryAcquire(int arg) {
        System.out.println("parent tryAcquire");
        throw new UnsupportedOperationException();
    }
}
