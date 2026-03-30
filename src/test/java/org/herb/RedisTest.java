package org.herb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
public class RedisTest {

    // 替换成这个，专门存字符串，无乱码
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisConnection() {
        // 1. 写入数据
        stringRedisTemplate.opsForValue().set("test_key", "Hello Redis!");

        // 2. 读取数据
        String value = stringRedisTemplate.opsForValue().get("test_key");

        // 3. 打印结果
        System.out.println("✅ Redis 读取结果：" + value);
    }
}