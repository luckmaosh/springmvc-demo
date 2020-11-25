package com.niux.spring.log.tets;

public class Main {

    public static void main(String[] args) {
        InnerClass innerClass = new InnerClass();
        Thread thread = new Thread(innerClass);
        thread.start();
        long i = System.currentTimeMillis();
        //10s
        while (System.currentTimeMillis() - i < 10 * 1000) {
            thread.isAlive();
        }
        thread.interrupt();
    }

    static class InnerClass implements Runnable {

        @Override
        public void run() {
            System.err.println("start work");
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("doing work");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
            System.err.println("done work");
        }
    }

}