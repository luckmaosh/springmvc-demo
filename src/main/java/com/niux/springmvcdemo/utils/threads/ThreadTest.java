package com.niux.springmvcdemo.utils.threads;

/**
 * 线程A： 循环50次后等待并放弃锁，让线程B执行。
 */
class ThreadA extends Thread {
    //线程同步的公共数据区
    Object oa = null;

    ThreadA(Object o) {
        this.oa = o;
    }

    //线程A执行逻辑
    public void run() {
        //线程同步区域，需要申请公共数据的锁
        synchronized (oa) {
            System.out.println("ThreadA is running......");
            for (int i = 0; i < 100; i++) {
                System.out.println("   ThreadA value is " + i);
                if (i == 50) {
                    try {
                        //当前线程等待
                        oa.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }//if(i==50)
            }//for(int i)
        }
    }
}

/**
 * 线程B：等待线程A放弃锁，然后获得锁并执行，完成后唤醒线程A
 */
class ThreadB extends Thread {
    //线程同步的公共数据区
    Object ob = null;

    ThreadB(Object o) {
        this.ob = o;
    }

    //线程B执行逻辑
    public void run() {
        //线程同步区域，需要申请公共数据的锁
        synchronized (ob) {
            System.out.println("ThreadB is running......");
            for (int i = 0; i < 50; i++) {
                System.out.println("   ThreadB value is " + i);
            }
            //唤醒等待的线程
            ob.notify();
        }
    }
}

//测试
public class ThreadTest {
    public static void main(String[] args) {
        Object lock = new Object(); //公共数据区
        ThreadA threada = new ThreadA(lock);
        ThreadB threadb = new ThreadB(lock);
        threada.start(); //线程A执行
        threadb.start(); //线程B执行
    }
}  