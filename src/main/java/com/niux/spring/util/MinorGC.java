package com.niux.spring.util;

/**
 * eden 对象通过分配担保机制提前转移到老年代去
 * vm 参数  -verbose:gc -Xms20M -Xmx20M -Xmn10M
 * -XX:SurvivorRatio=8   -XX:+PrintGCDetails
 */
public class MinorGC {
    private static final int _1MB = 1024 * 1024;

    public static void testMinorGC() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB]; //出现一次minor GC
    }

    public static void main(String[] args) {
        while (true) {
            testMinorGC();
        }
    }
}  