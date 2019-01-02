package com.niux.spring.threadpool;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class PreposeServiceTask implements Callable<String> {
    public static final Logger logger = LoggerFactory.getLogger(PreposeServiceTask.class);

    TestHighThreadPool testHighThreadPool;

    public PreposeServiceTask(TestHighThreadPool testHighThreadPool) {
        this.testHighThreadPool = testHighThreadPool;
    }

    @Override
    public String call() throws Exception {
//        Thread.sleep(40);


        Map<String, Object> context = Maps.newHashMap();
        context.put("completeTask", new AtomicInteger(0));
        context.put("createTime", System.currentTimeMillis());//一开始就要确定时间，放在上下文中
        context.put("maxWaitTime", 80L);
        int task = 10;
        long start = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(task);
        //work 代表一个模板
        List<Future> futures = new ArrayList<Future>();
        for (int i = 0; i < task; i++) {
            Future submit = testHighThreadPool.submit(new Worker("tpl" + i, context, latch));
            futures.add(submit);

        }
        boolean success = latch.await(80, TimeUnit.MILLISECONDS);// 指定超时时间
        if (success) {
            return "ok";
        } else {
            for (Future f :futures){
                if (!f.isDone()){
                    f.cancel(true);
                    logger.info("cancel task xx->" + f.isCancelled());
                }
            }
        }

        AtomicInteger complete = (AtomicInteger) context.get("completeTask");
        logger.info("all task {} , complete {} cost={}", task, complete.get(), (System.currentTimeMillis() - start));
        return "fail";
    }


}
