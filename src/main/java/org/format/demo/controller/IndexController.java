package org.format.demo.controller;

import jedis.JedisExecutor;
import jedis.JedisOperation;
import jedis.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.ShardedJedis;

import java.util.Set;


@Controller
@RequestMapping("/index")
public class IndexController {

    public static Logger logger = LoggerFactory.getLogger(IndexController.class);


    @Autowired
    private JedisExecutor jedisExecutor;

    @RequestMapping
    public ModelAndView index(@RequestHeader HttpHeaders headers) {
        logger.info("from request:" + headers.getFirst("LOGID"));
        Set<String> keys = headers.keySet();
        if (!CollectionUtils.isEmpty(keys)) {
            for (String key : keys) {
                System.out.println("from request " + key + "=" + headers.getFirst(key));
            }
        }


        try {
            Object kkk = jedisExecutor.execute(new JedisOperation() {
                @Override
                public <T> OperationResult<T> doJedis() {
                    ShardedJedis shardedJedis = jedisExecutor.getShardedJedis();
                    return new OperationResult(shardedJedis, shardedJedis.exists("kkk"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModelAndView view = new ModelAndView("index");
        view.addObject("welcome", "hello");
        return view;
    }

}