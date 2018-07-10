package com.niux.spring.threadpool;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestTask implements Callable<String> {
    public static final Logger logger = LoggerFactory.getLogger(TestTask.class);

    TestHighThreadPool testHighThreadPool;

    public TestTask(TestHighThreadPool testHighThreadPool) {
        this.testHighThreadPool = testHighThreadPool;
    }

    @Override
    public String call() throws Exception {
//        Thread.sleep(40);


        Map<String, Object> context = Maps.newHashMap();
        context.put("completeTask", new AtomicInteger(0));
        context.put("createTime", System.currentTimeMillis());
        context.put("maxWaitTime", 80L);
        int task = 10;
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(task);
        //work 代表一个模板
        for (int i = 0; i < task; i++) {
            testHighThreadPool.execute(new Worker("tpl" + i, context, latch));
        }
        latch.await(80, TimeUnit.MILLISECONDS); // 指定超时时间

        AtomicInteger complete = (AtomicInteger) context.get("completeTask");
        logger.info("all task {} , complete {} cost={}", task, complete.get(), (System.currentTimeMillis() - start));
        return "ok";
    }


}
