package com.niux.spring.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TestPrepoeseService {
    public static final Logger logger = LoggerFactory.getLogger(TestPrepoeseService.class);

    public static final ArrayBlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(300);

    //第一级线程池调大一点
    public static ThreadPoolExecutor serviceExecutor = new ThreadPoolExecutor(8, 300, 2, TimeUnit.HOURS, blockingQueue, new ThreadFactory() {
        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "prepose-execute");
        }
    }, new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            // 记日志，然后直接抛出异常
            logger.error("PreposeServiceThreadPoll is full, executor {}", executor.getQueue().remainingCapacity());
            throw new RejectedExecutionException("Task " + r.toString() +
                    " rejected from " +
                    executor.toString());
        }
    }
    );

    TestHighThreadPool testHighThreadPool = new TestHighThreadPool();

    AtomicInteger success = new AtomicInteger();

    public String execute() {
        long l = System.currentTimeMillis();
        Future<String> submit = serviceExecutor.submit(new PreposeServiceTask(testHighThreadPool));
        String s = null;
        try {
            s = submit.get(100, TimeUnit.MILLISECONDS);
            if ("ok".equals(s)) {
                success.addAndGet(1);
            }
            logger.info("result={} cost={} successCount={} ", s, (System.currentTimeMillis() - l), success.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        return s;

    }


}