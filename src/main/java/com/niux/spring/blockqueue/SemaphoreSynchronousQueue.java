package com.niux.spring.blockqueue;

import java.util.concurrent.Semaphore;

/**
 * 信号量使用
 *
 * @param <E>
 */
public class SemaphoreSynchronousQueue<E> {
    E item = null;
    Semaphore sync = new Semaphore(0);
    Semaphore send = new Semaphore(1);
    Semaphore recv = new Semaphore(0);

    public E take() throws InterruptedException {
        recv.acquire();
        E x = item;
        sync.release();
        send.release();
        return x;
    }

    public void put(E x) throws InterruptedException {
        send.acquire();
        item = x;
        recv.release();
        sync.acquire();
    }

    public static void main(String[] args) {
        SemaphoreSynchronousQueue<Object> queue = new SemaphoreSynchronousQueue<>();

        for (int i = 0; i < 5; i++) {
            Thread t = new SQThread(queue, 1);
            t.start();
        }
        //Thread.sleep(1000);
        for (int i = 0; i < 10; i++)
            try {
                queue.put(new Object());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    public static class SQThread extends Thread {
        private SemaphoreSynchronousQueue<Object> queue;
        int mode;

        SQThread(SemaphoreSynchronousQueue<Object> queue, int mode) {
            this.queue = queue;
            this.mode = mode;
        }

        @Override
        public void run() {
            Object item = null;
            try {
                System.out.println(Thread.currentThread().getId());
                if (mode == 1) {
                    while ((item = queue.take()) != null) {
                        System.out.println(item.toString());
                    }
                } else {
                    //
                }
            } catch (Exception e) {
                //
            }
            System.out.println("end");
        }
    }
}