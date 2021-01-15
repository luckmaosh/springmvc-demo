package com.niux.spring.algorithm.ratelimit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 漏桶
 */
public class LeakyBucketLimiter {
    private static final Logger log = LoggerFactory.getLogger(LeakyBucketLimiter.class);
    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);

    // 桶的容量
    public int capacity = 10;
    // 当前水量
    public int water = 0;
    //水流速度/s
    public int rate = 4;
    // 最后一次加水时间
    public long lastTime = System.currentTimeMillis();

    public void acquire() {
        scheduledExecutorService.scheduleWithFixedDelay(() -> {
            long now = System.currentTimeMillis();
            //计算当前水量
            water = Math.max(0, (int) (water - (now - lastTime) * rate / 1000));
            int permits = (int) (Math.random() * 8) + 1;
            log.info("请求数：" + permits + "，当前桶余量：" + (capacity - water));
            lastTime = now;
            if (capacity - water < permits) {
                // 若桶满,则拒绝
                log.info("限流了");
            } else {
                // 还有容量
                water += permits;
                log.info("剩余容量=" + (capacity - water));
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }


    public static void main(String[] args) {
        LeakyBucketLimiter limiter = new LeakyBucketLimiter();
        limiter.acquire();
    }
}