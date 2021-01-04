package com.niux.spring;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo {


    public static void main(String[] arg0) {
        System.out.println("main start=====");
        Thread thread1 = new Thread("守护线程") {
            @Override
            public void run() {
                int i = 0;
                while (i <= 4) {
                    i++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：" + i);
                }
                super.run();
            }
        };


        Thread thread2 = new Thread("用户线程") {
            @Override
            public void run() {
                int i = 0;
                while (i <= 2) {
                    i++;
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "：" + i);
                }
                super.run();
            }
        };

        //setDaemon, 不设置则默认false
        thread1.setDaemon(true);//设置thread1为守护线程
        thread2.setDaemon(false);//设置thread2为普通线程

        thread1.start();
        thread2.start();

        System.out.println("main end==");
    }


    public static void main5(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> System.out.println(finalI + "=>xxx"));
            thread.start();
        }

        System.out.println("main thread is running");

        //主线程，main执行结束后，普通线程可以继续执行直至执行完毕；

        //用户线程执行完毕后，守护线程立刻结束；
    }

    public static void main3(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 10, 6, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(finalI + "=>xxx");

                }
            });
        }

//       System.exit(0);

        threadPoolExecutor.shutdown();
    }


    public static void main2(String[] arg) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    System.out.println(i);
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.setDaemon(true);
        thread.start();

        try {
            //用户线程退出就可以结束jvm
            thread.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main1(String[] arg) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("shutdown hook!!!");
            }
        }));


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}

