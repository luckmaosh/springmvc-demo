package com.niux.springmvcdemo.utils;

/**
 * Created by shaohui.mao
 * on 16/4/21.上午9:03
 */
public class Test {


    public int[] getNext(String b) {
        int len = b.length();
        int j = 0;

        int next[] = new int[len + 1];//next表示长度为i的字符串前缀和后缀的最长公共部分，从1开始
        next[0] = next[1] = 0;

        for (int i = 1; i < len; i++)//i表示字符串的下标，从0开始
        {//j在每次循环开始都表示next[i]的值，同时也表示需要比较的下一个位置
            while (j > 0 && b.charAt(i) != b.charAt(j)) j = next[j];
            if (b.charAt(i) == b.charAt(j)) j++;
            next[i + 1] = j;
        }

        return next;
    }



    static class TestThread extends Thread {
        int inner;

        public TestThread(int inner) {
            this.inner = inner;
        }

        @Override
        public synchronized void run() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            inner++;
        }
    }

    public static void main(String[] args) {

        Test test = new Test();
        int[] abcbcs = test.getNext("cccbccccc");

        System.out.println(abcbcs);

    }
}
