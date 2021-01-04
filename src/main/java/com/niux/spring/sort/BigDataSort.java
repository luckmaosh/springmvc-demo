package com.niux.spring.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class BigDataSort {
    public static final int CAPACITY = 1000; //数据的容量
    private static final Logger logger = LoggerFactory.getLogger(BigDataSort.class);

    //定义一个byte数组缓存所有的数据
    private byte[] dataBytes = new byte[1 << 29];

    public static void main(String[] args) {
        BigDataSort ms = new BigDataSort();

        byte[] bytes = null;

        Random random = new Random();
        for (int i = 0; i < CAPACITY; i++) {
            int num = random.nextInt();
            System.out.println("读取了第" + (i + 1) + "\t个数" + num);
            bytes = ms.splitBigData(num);
        }
        System.out.println("");
        ms.output(bytes);
    }


    /*
     * 读取数据，并将对应数据对应到bit中，并返回byte数组中
     *
     * */

    private byte[] splitBigData(int num) {
        long bitIndex = num + (1L << 31); //获取num数据对应bit数组索引
        int index = (int) (bitIndex / 8); //bit数组在byte数组中的索引
        int innerIndex = (int) (bitIndex % 8); //bitIndex在byte[]数组索引index中的具体位置

        System.out.println("byte[" + index + "]中的索引：" + innerIndex);
        dataBytes[index] = (byte) (dataBytes[index] | (1 << innerIndex));
        return dataBytes;

    }


    /*
     * 输出数组中的数据
     *
     * */

    private void output(byte[] bytes) {
        int count = 0;
        for (int i = 0; i < bytes.length; i++) {
            for (int j = 0; j < 8; j++) {
                if (!(((bytes[i]) & (1 << j)) == 0)) {
                    count++;
                    int number = (int) ((((long) i * 8 + j) - (1l << 31)));
                    logger.info("取出的第{}个数:{}" , count , number);
                }
            }
        }
    }
}

