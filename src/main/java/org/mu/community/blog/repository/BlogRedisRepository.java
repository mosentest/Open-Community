package org.mu.community.blog.repository;

import org.mu.community.redis.core.RedisManager;
import org.mu.community.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Muu on 2014/9/26.
 */
@Repository("blogRedisRepository")
public class BlogRedisRepository {

    private RedisManager redisManager;

    public List<Long> getRecentComment(long user) {
        String key = "rbc:" + user;
        Jedis jedis = redisManager.execute();
        List<String> result = jedis.lrange(key, 0, -1);
        redisManager.returnResource(jedis);
        return RedisUtil.convertList(result, v -> Long.parseLong(v));
    }

    public void addComment(long user, long comment) {
        String key = "rbc:" + user;
        Jedis jedis = redisManager.execute();
        jedis.lpush(key, String.valueOf(comment));
        jedis.ltrim(key, 0, 5);
        redisManager.returnResource(jedis);
    }

    @Autowired
    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

}
