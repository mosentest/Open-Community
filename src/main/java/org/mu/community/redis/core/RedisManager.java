package org.mu.community.redis.core;

import org.mu.community.common.constants.PropertiesManager;
import org.mu.community.redis.util.PooledJedis;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by Muu on 2014/9/26.
 */
public class RedisManager {

    private static final String ip = PropertiesManager.getProperty("redis.ip", "127.0.0.1");
    private static final int port = PropertiesManager.getIntProperty("redis.port", 6379);
    private static final int maxActive = PropertiesManager.getIntProperty("redis.pool.maxActive", 300);
    private static final int maxIdle = PropertiesManager.getIntProperty("redis.pool.maxIdle", 100);
    private static final int maxWait = PropertiesManager.getIntProperty("redis.pool.maxWait", 5000);
    private static final boolean testOnBorrow = PropertiesManager.getBooleanProperty("redis.pool.testOnBorrow", true);
    private static final boolean testOnReturn = PropertiesManager.getBooleanProperty("redis.pool.testOnReturn", true);

    private static final JedisPoolConfig poolConfig;

    private static final JedisPool pool;

    static {
        poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(testOnBorrow);
        poolConfig.setTestOnReturn(testOnReturn);
        pool = new JedisPool(poolConfig, ip, port, 5000);
    }

    public void destroy() {
        pool.destroy();
    }

    public Jedis execute() {
        return pool.getResource();
    }

    public void returnResource(Jedis jedis) {
        pool.returnResource(jedis);
    }

}
