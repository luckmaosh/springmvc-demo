package com.niux.springmvcdemo.threadSync;

import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * Created by shaohuimao@sohu-inc.com
 * on 16/8/18. 下午5:26
 */
public class ProducerConsumerTest {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();

        Stack stack = new Stack();

        Producer producer = new Producer(stack);
        Consumer consumer = new Consumer(stack);

        for (int i = 0; i < 20; i++) {

            new Thread(producer).start();
            new Thread(consumer).start();
        }


    }
}


class Producer implements Runnable {

    private Stack stack;

    public Producer(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {

        synchronized (stack) {
            if (stack.size() >= 10) {
                try {
                    //等其他线程消费
                    stack.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String s = "object " + new Random().nextInt(20);
            System.out.println("produce a object " + s);
            stack.add(s);
            stack.notify();
        }
    }
}


class Consumer implements Runnable {

    private Stack stack;

    public Consumer(Stack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {

        synchronized (stack) {
            if (stack != null && stack.size() > 0) {

                Object pop = stack.pop();
                System.out.println("consumer a object  =" + pop.toString());
            } else {
                try {

                    //让生产者生产
                    stack.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}




