package com.niux.springmvcdemo.utils;

/**
 * Created by shaohui.mao
 * on 16/4/6.上午11:41
 */
public class Child extends Parent {


    public void lock(){
        System.out.println("lock");
        acquire(1);
    }

    @Override
    protected boolean tryAcquire(int arg) {
        System.out.println("child tryAcquire");
        return true;
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.lock();
    }
}
