package com.niux.spring.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Invoker {
    public static final Logger logger = LoggerFactory.getLogger(Invoker.class);

    public static final ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(10);
    public static ThreadPoolExecutor serviceExecutor = new ThreadPoolExecutor(8, 200, 2, TimeUnit.HOURS, blockingQueue, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "invoker");
        }
    }, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记日志，然后直接抛出异常
            logger.error("PreposeServiceThreadPoll is full");
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }
    );

    public static void main(String[] args) {
        AtomicInteger invokeNum = new AtomicInteger(0);

        TestPrepoeseService testPrepoeseService = new TestPrepoeseService();
//        请求并发数
        for (int i = 0; i < 200; i++) {
            serviceExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        testPrepoeseService.execute();
                        logger.info("invoke times={} thread-{}",invokeNum.addAndGet(1), Thread.currentThread().getName() + Thread.currentThread().getId());
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
