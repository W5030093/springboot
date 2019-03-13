/**
 * FileName: RedisUtils
 * Author:   王永超
 * Date:     2019/3/13 10:01
 * Description:
 * History:
 */
package com.ym.springboot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2019/3/13
 * @since 1.0.0
 */
public class RedisUtils {

    @Autowired
    private static RedisKeyValueTemplate redisKeyValueTemplate;

    @Autowired
    private static RedisTemplate<String,Object> redisTemplate;

    public static void main(String[] args) {

    }
}
