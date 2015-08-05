package com.niux.spring.jedis;

/**
 * Created with IntelliJ IDEA.
 * User: mazhen01
 * Date: 2014/10/15
 * Time: 15:21
 */
public interface JedisOperation {
    public <T> OperationResult<T> doJedis();
}
