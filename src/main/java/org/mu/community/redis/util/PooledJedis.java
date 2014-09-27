package org.mu.community.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.Pool;

import java.io.Closeable;
import java.net.URI;

/**
 * Created by Muu on 2014/9/27.
 */
public class PooledJedis extends Jedis implements Closeable {

    private final Pool pool;

    private boolean broken;

    public PooledJedis(String host, Pool<Jedis> pool) {
        super(host);
        this.pool = pool;
    }

    public PooledJedis(String host, int port, Pool<Jedis> pool) {
        super(host, port);
        this.pool = pool;
    }

    public PooledJedis(String host, int port, int timeout, Pool<Jedis> pool) {
        super(host, port, timeout);
        this.pool = pool;
    }

    public PooledJedis(JedisShardInfo shardInfo, Pool<Jedis> pool) {
        super(shardInfo);
        this.pool = pool;
    }

    public PooledJedis(URI uri, Pool<Jedis> pool) {
        super(uri);
        this.pool = pool;
    }
    @Override
    public void close() {
        if (broken) {
            pool.returnBrokenResource(this);
            return;
        }
        pool.returnResource(this);
    }

    public void broke() {
        broken = true;
    }
}
