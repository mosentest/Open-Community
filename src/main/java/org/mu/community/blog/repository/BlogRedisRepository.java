package org.mu.community.blog.repository;

import org.mu.community.blog.entity.Blog;
import org.mu.community.redis.core.RedisManager;
import org.mu.community.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Muu on 2014/9/26.
 */
@Repository("blogRedisRepository")
public class BlogRedisRepository {

    private RedisManager redisManager;

    public void addBlog(Blog blog) {
        Jedis jedis = redisManager.execute();
        String blogString = String.valueOf(blog.getId());
        jedis.lpush("recent-blog", blogString);
        jedis.ltrim("recent-blog", 0, 19);
        jedis.zincrby("featured-blog", 1, blogString);//featured
        redisManager.returnResource(jedis);
    }

    public List<Long> getFeatured(long offset, long limit) {
        Jedis jedis = redisManager.execute();
        Set<String> result = jedis.zrange("featured-blog", offset, limit);
        redisManager.returnResource(jedis);
        return result.stream().map(v -> Long.valueOf(v)).collect(Collectors.toList());
    }

    public long countFeatured() {
        Jedis jedis = redisManager.execute();
        long count = jedis.zcard("featured-blog");
        redisManager.returnResource(jedis);
        return count;
    }

    @Autowired
    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

}
