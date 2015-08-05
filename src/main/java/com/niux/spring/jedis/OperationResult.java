package com.niux.spring.jedis;

import redis.clients.jedis.ShardedJedis;

/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/10/15
 * Time: 15:48
 */
public class OperationResult<T> {

    private ShardedJedis shardedJedis;
    private T data;

    public OperationResult(ShardedJedis shardedJedis, T data) {
        this.shardedJedis = shardedJedis;
        this.data = data;
    }

    public ShardedJedis getShardedJedis() {
        return shardedJedis;
    }

    public T getData() {
        return data;
    }
}
