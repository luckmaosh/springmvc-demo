package com.niux.spring.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Worker implements Runnable {
    public static final Logger logger = LoggerFactory.getLogger(Worker.class);
    private String tpl;
    private long createTime;
    private Map<String, Object> context;
    private CountDownLatch latch;

    public Worker(String tpl, Map<String, Object> context, CountDownLatch latch) {
        this.tpl = tpl;
        this.latch = latch;
        this.createTime = (long) context.get("createTime");
        this.context = context;

    }

    @Override
    public void run() {
        long l = System.currentTimeMillis();
        long waitTime = l - createTime;
        Long maxWaitTime = (Long) context.get("maxWaitTime");
        if (waitTime > maxWaitTime) {
            logger.info("thread name ={} wait time > max", Thread.currentThread().getName() + Thread.currentThread().getId());
            return;
        }
        try {
            Thread.sleep(new Random().nextInt(25));

            AtomicInteger num = (AtomicInteger) context.get("completeTask");
            num.addAndGet(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            logger.info("worker-{} from creatime ,cost {}", tpl+"-"+Thread.currentThread().getName() + Thread.currentThread().getId(),
                    (System.currentTimeMillis() - createTime));
            latch.countDown();
        }

    }
}
