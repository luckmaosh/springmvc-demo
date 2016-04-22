package com.niux.springmvcdemo.utils;

/**
 * Created by shaohui.mao
 * on 16/4/21.下午5:21
 */
public class TestJob {


    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Job();
        }

        System.out.println(Job.getCount());

    }

    static class Job {


        private static long count = 0;

        public Job() {

            count++;
        }

        public static long getCount() {
            return count;
        }

        public static void setCount(long count) {
            Job.count = count;
        }
    }
}
