/*
 * Copyright (C) 2015 Baidu, Inc. All Rights Reserved.
 */
package demo;

import redis.clients.jedis.Jedis;

public class JedisDemo {
    public static void main(String[] args) {
        Jedis jj = new Jedis("localhost");
        jj.set("key1", "I am value 1");
        String ss = jj.get("key1");
        System.out.println(ss);
        jj.lpush("crm", "1");
        jj.lpush("crm", "2");
        String crm = jj.lpop("crm");
        System.out.println(crm);
    }
}