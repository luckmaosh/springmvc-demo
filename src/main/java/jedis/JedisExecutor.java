package jedis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * jedis执行器.
 * User: mazhen01
 * Date: 2014/10/15
 * Time: 14:11
 */
public class JedisExecutor {

    private final static Logger logger = LoggerFactory.getLogger(JedisExecutor.class);

    private ShardedJedisPool shardedJedisPool;

    public <T> T execute(JedisOperation jedisOperation) throws Exception {
        OperationResult<T> result = null;
        try {
            result = jedisOperation.doJedis();
            return result.getData();
        } catch (Exception ex) {
            logger.error("redis:do redis error.", ex);
            throw ex;
        } finally {
            if (result != null && result.getShardedJedis() != null) {
                shardedJedisPool.returnResource(result.getShardedJedis());
            }
        }
    }

    public ShardedJedis getShardedJedis() {
        if (shardedJedisPool != null) {
            return shardedJedisPool.getResource();
        }
        return null;
    }

    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

//        public static void main(String[] args) {
//        final JedisExecutor jedisExecutor = new JedisExecutor();
//            try {
//                Long k = jedisExecutor.execute(new JedisOperation() {
//                    @Override
//                    public OperationResult doJedis() {
//                        return new OperationResult<Long>(jedisExecutor.getShardedJedis(), jedisExecutor.getShardedJedis().lpush("", ""));
//                    }
//                });
//            } catch (Exception ex) {
//
//            }
//    }
}
