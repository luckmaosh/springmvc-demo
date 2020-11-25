package com.niux.spring.log;

public class Test2 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("interrupt");

            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("interrupt");
        });
        thread.start();
        //第一次中断线程
        thread.interrupt();

        //第二次中断
        Thread.sleep(500);
        thread.interrupt();
    }


}
