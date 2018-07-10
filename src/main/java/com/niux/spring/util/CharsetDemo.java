package com.niux.spring.util;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class CharsetDemo {

    public static void main(String[] args) throws UnsupportedEncodingException {

        int[] a = new int[]{1,2,3};
        List<String> strings = Arrays.asList("ass", "asas");

        strings.add("dd");

        byte[] bytes = "测试".getBytes();
        byte[] bytes1 = "测试".getBytes("utf-8");
        byte[] bytes2 = "测试".getBytes("gbk");
        byte[] bytes3 = "测试".getBytes("utf-8");

        System.out.println(bytes.equals(bytes1));
    }
}
