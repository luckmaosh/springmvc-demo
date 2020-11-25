package com.niux.spring.log;

public class InterruptDemo {

    public static void main(String[] args) {

        Thread thread = new Thread() {
            @Override
            public void run() {
                if (isInterrupted()) {
                    System.out.println("interrupt");
                }
            }
        };
        thread.start();

        thread.interrupt();

    }
}
