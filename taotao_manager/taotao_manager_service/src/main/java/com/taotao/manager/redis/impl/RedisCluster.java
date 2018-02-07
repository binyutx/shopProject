package com.taotao.manager.redis.impl; /**
 * 〈一句话功能简述〉<br>
 * 〈redis单机版〉
 *
 * @author kepad
 * @create 2018/2/6
 * @since 1.0.0
 */

import com.taotao.manager.redis.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * @创建人 kepade
 * @创建时间 2018/2/6
 * @描述
 */

public class RedisCluster implements RedisUtils {
    @Autowired
    private JedisCluster jedisCluster;
    @Override
    public void set(String key, String value) {
        this.jedisCluster.set(key,value);
    }

    @Override
    public String get(String key) {
        String result = this.jedisCluster.get(key);
        return result;
    }

    @Override
    public void del(String key) {
        this.jedisCluster.del(key);
    }

    @Override
    public void expire(String key, Integer seconds) {
        this.jedisCluster.expire(key,seconds);
    }

    @Override
    public void set(String key, String value, Integer seconds) {
      this.jedisCluster.set(key,value);
      this.jedisCluster.expire(key,seconds);
    }

    @Override
    public Long incr(String key) {
        Long count = this.jedisCluster.incr(key);
        return count;
    }
}
