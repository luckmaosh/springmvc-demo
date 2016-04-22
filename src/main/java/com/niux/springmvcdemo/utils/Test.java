package com.niux.springmvcdemo.utils;

/**
 * Created by shaohui.mao
 * on 16/4/21.上午9:03
 */
public class Test {


    static class TestThread extends Thread {
        int inner;

        public TestThread(int inner) {
            this.inner = inner;
        }

        @Override
        public synchronized void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inner++;
        }
    }

    public static void main(String[] args) {

        TestThread testThread = new TestThread(0);

        for (int i = 0; i < 1000; i++) {
            new Thread(testThread).start();
        }

        Thread.yield();
        System.out.println(testThread.inner);
    }
}
