package com.niux.spring.utils.threads;

/**
 * 测试线程的wait和notify方法
 *
 * @author rey
 */
public class TestThreadWait {

    // 共享资源
    private String[] lockObj = {"true"};

    /**
     * @param args
     */
    public static void main(String[] args) {
        TestThreadWait testMainThread = new TestThreadWait();

// 定义线程
        NotifyThread notifyThread = testMainThread.new NotifyThread("notifyThread");
        WatiThread wait01 = testMainThread.new WatiThread("wait01");
        wait01.setPriority(2);
        WatiThread wait02 = testMainThread.new WatiThread("wait02");
        wait02.setPriority(3);
        WatiThread wait03 = testMainThread.new WatiThread("wait03");
        wait03.setPriority(4);

// 启动线程
        wait01.start();
        wait02.start();
        wait03.start();
        notifyThread.start();

    }

    // 定义通知线程
    class NotifyThread extends Thread {
        public NotifyThread(String _sThreadName) {
            super(_sThreadName);
        }

        public void run() {

// 休眠5秒，给等待线程等待时间
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lockObj) {
                System.out.println("线程[" + getName() + "]开始准备通知.....");
                lockObj[0] = "false";
                lockObj.notifyAll();  //该成lockObj.notify();则运行测试的时候进程没有结束，因为我们还有2个线程没有结束
                System.out.println("线程[" + getName() + "]开始通知结束.....");
            }

            System.out.println("线程[" + getName() + "]运行结束.....");

        }

    }

    // 定义等待的线程类
    class WatiThread extends Thread {
        public WatiThread(String _sThreadName) {
            super(_sThreadName);
        }

        public void run() {

            synchronized (lockObj) {
                while (lockObj[0].equalsIgnoreCase("true")) {
                    System.out.println("线程[" + this.getName() + "]开始等待....");

                    long lWaitTime = System.currentTimeMillis();

                    try {
                        lockObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    lWaitTime = System.currentTimeMillis() - lWaitTime;
                    System.out.println("线程[" + getName() + "]等待时间为：：：" + lWaitTime);
                }
            }

            System.out.println("线程[" + getName() + "]等待结束！！！");
        }
    }

}