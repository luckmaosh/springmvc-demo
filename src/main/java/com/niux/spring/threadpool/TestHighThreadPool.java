package com.niux.spring.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

public class TestHighThreadPool {
    public static final Logger logger = LoggerFactory.getLogger(TestHighThreadPool.class);

    private ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(10000);

    public static final String HIGH_THROUGHPUT_THREAD_POOL_NAME = "high_throughput_pool";
    public ThreadPoolExecutor executor = new ThreadPoolExecutor(64,
            64,
            10,
            TimeUnit.MINUTES,
            queue,
            new ThreadFactory() {
                @Override
                public Thread newThread(Runnable runnable) {
                    return new Thread(runnable, HIGH_THROUGHPUT_THREAD_POOL_NAME);
                }
            },
            new RejectThreadHandler(HIGH_THROUGHPUT_THREAD_POOL_NAME));

    public Future submit(Worker worker) {
        Future submit = executor.submit(worker);
        return submit;
    }


    public static class RejectThreadHandler implements RejectedExecutionHandler {

        private String name;

        public RejectThreadHandler(String name) {
            this.name = name;
        }

        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            logger.error(String.format("%s EXECUTOR IS FULL", name));
        }
    }

    public void execute(Runnable runnable) {
        executor.execute(runnable);
    }

}
