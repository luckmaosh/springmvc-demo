package com.niux.spring.log;

import java.util.ArrayList;

/**
 * Created by liouville on 3/27/16.
 * 单生产者多消费者,
 *
 * Res.list.size == 1时,只能消费;等于０时，只能生产
 *
 * 两个问题：
 *
 * 1.数组越界:因为有这么一种情况,
 *
 *      由于最开始list.size == 0,如果消费者A得到了执行,进入等待状态.
 *      生产者生产完一个元素以后唤醒消费者A,但是唤醒不一定代表能抢到对象锁,
 *     若消费者B抢到了执行权,此时消费B者顺利消费一个元素,之后唤醒的是消费者A.
 *     由于此时list.size == 0,消费一个元素就会报空指针异常.
 *
 *      解决办法：改if为while
 *
 * 2.所有线程都在等待的问题:因为改if为while,所以可能出现消费者唤醒消费者的情况,使得被唤醒的生产者
 *　继续等待,而生产者也处于等待状态,所以所有线程都处于等待状态,
 *      解决办法：改notify为notifyAll即可
 */
public class PAndCS {

    private static final Object lock = new Object();
    public static void main(String[] args) {
        new ProductThread().start();

        for (int i = 0; i < 3; i++) {
            new ConsumerThread().start();
        }
    }

    private static class ProductThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                synchronized (lock){
                    if (Res.list.size() != 0){

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    Res.list.add("h");
                    System.out.println(getName() + ": 生产者生产一个元素");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }

            }


        }
    }

    private static class ConsumerThread extends Thread{
        @Override
        public void run() {
            super.run();
            while(true){
                synchronized (lock){
                    while(Res.list.size() == 0){

                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Res.list.remove(0);
                    System.out.println(getName() + ": 消费者消费一个元素");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notifyAll();
                }

            }

        }
    }


    private static class Res{
        static ArrayList<String> list = new ArrayList<>();
    }
}
