package jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Random;

/**
 * Created by shaohui.mao
 * on 15/11/18.下午2:14
 */
public class RedLock {
    private static final Logger logger = LoggerFactory.getLogger(RedLock.class);
    private static final String KEY = "foo.lock";

    private static JedisPool jedispool = null;


    public static void main(String[] args) {


        jedispool = new JedisPool("127.0.0.1");
        for (int i = 0; i < 100; i++) {

            LockTask lockTask = new LockTask(jedispool);
            lockTask.start();
        }

    }


    static class LockTask extends Thread {
        private JedisPool jedisPool;

        public LockTask(JedisPool jedisPool) {
            this.jedisPool = jedisPool;
        }

        public void run() {

            Jedis jedis = jedisPool.getResource();
            Long setnx = jedis.setnx(KEY, System.currentTimeMillis() + 10 + 1 + "");
            if (setnx != null && setnx > 0) {
                logger.info("thread {} get the lock", Thread.currentThread().getName());
            } else {
                boolean success = false;
                while (!success) {
                    logger.info("try to sleep");
                    try {
                        Thread.sleep(new Random().nextInt(10));
                    } catch (InterruptedException e) {
                        logger.error(e.getMessage(), e);
                    }

                    //检测死锁  , 可能被人获得了,可能失效了
                    String t1_str = jedis.get(KEY);
                    if (t1_str == null) {
                        Long setnx1 = jedis.setnx(KEY, System.currentTimeMillis() + 10 + 1 + "");
                        if (setnx1 > 0) {
                            success = true;
                        }
                    } else {
                        long t1 = Long.parseLong(t1_str);
                        long t2 = System.currentTimeMillis();
                        if (t1 < t2) {  //已经过时
                            String t3 = jedis.getSet(KEY, t2 + 10 + 1 + "");
                            logger.info("t3=" + t3);
                            if (t3 != null && t3.equals(t1_str)) {
                                logger.info("finally acquire th lock");
                                success = true;

                            }
                        }
                    }

                }
            }


            //do something
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //释放锁
            Long del = jedis.del(KEY);
            if (del > 0) {
                logger.info("dele lock success");
            } else {
                logger.info("release lock fail");
            }

            jedispool.returnResource(jedis);


        }
    }

}
