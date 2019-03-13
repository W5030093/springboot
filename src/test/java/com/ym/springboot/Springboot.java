package com.ym.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void contextLoads() {

        List<String> list = new ArrayList<>();
        //设置手机号
        list.add("limit:1785...");
        DefaultRedisScript<Long> stringDefaultRedisScript = new DefaultRedisScript<>();
        //设置lua脚本的内容 设置一个短信验证码限次  每分钟最多10次
        stringDefaultRedisScript.setScriptText("local num=redis.call('incr',KEYS[1])\n" +
                "if tonumber(num)==1 then\n" +
                "   redis.call('expire',KEYS[1],ARGV[1])\n" +
                "   return 1\n" +
                "elseif tonumber(num)>tonumber(ARGV[2]) then\n" +
                "   return 0\n" +
                "else\n" +
                "   return 1\n" +
                "end\n");
        List<String> strings = new ArrayList<>();
        //返回值long类型
        stringDefaultRedisScript.setResultType(Long.class);
        for(int i=0;i<=10;i++){
            Long resuklt = (Long) redisTemplate.execute(stringDefaultRedisScript,list,60,10);
            System.out.println(resuklt);
        }

    }

    public static void main(String[] args) {


        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.216.128",6379);
        jedisPool.getResource().get("1");
    }
}
