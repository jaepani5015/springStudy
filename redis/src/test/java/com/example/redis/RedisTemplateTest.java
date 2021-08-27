package com.example.redis;

import com.example.redis.test.TestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Indexed;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void test() {
        System.out.println("redisTemplate = " + redisTemplate);
    }

    @Test
    void redisTemplate_테스트() throws JsonProcessingException {
        TestData testData = new TestData("welcome to spring world", "27", "male");
        Map<String, String> map = new HashMap<>();
        map.put("who", objectMapper.writeValueAsString(testData));
        redisTemplate.opsForHash().putAll("user", map);

        System.out.println("redisTemplate putAll = " + redisTemplate.opsForHash().get("user", "who").toString());
    }

    @Test
    void 객체_변환() throws JsonProcessingException {
        String o = redisTemplate.opsForHash().get("user", "who").toString();
        TestData testData = objectMapper.readValue(o, TestData.class);
        String s = testData.toString();
        System.out.println("s = " + s);
    }

    @Test
    void redis_데이터_수정() throws JsonProcessingException {
        TestData testData = new TestData("hello world", "25", "male");
        Map<String, String> map = new HashMap<>();
        map.put("who", objectMapper.writeValueAsString(testData));
        redisTemplate.opsForHash().putAll("user", map);

        System.out.println("redisTemplate change data = " + redisTemplate.opsForHash().get("user", "who"));
    }
}
