//package com.niux.spring.algorithm.ratelimit;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.atomic.AtomicInteger;
//
//public class TokenRateLimiter {
//
//
//    private Integer qps;
//    private AtomicInteger token;
//    private final Object lock = new Object();
//
//    public TokenRateLimiter(int qps) {
//        this.qps = qps;
//        this.token = new AtomicInteger(qps);
//    }
//
//    public boolean acquire() {
//        int i = token.decrementAndGet();
//        if (i >= 0) {
//            return true;
//        } else {
//            synchronized (lock) {
//                try {
//                    lock.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                int i1 = token.get();
//                if (i1 > 0) {
//
//                }
//            }
//        }
//    }
//
//
//    /**
//     * 补充令牌
//     */
//    public void supplement(final ExecutorService executorService) {
//        this.token.set(qps);
//        executorService.execute(() -> {
//            synchronized (lock) {
//                lock.notifyAll();
//            }
//        });
//    }
//
//
//}
