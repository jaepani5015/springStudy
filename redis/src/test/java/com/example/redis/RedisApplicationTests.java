package com.example.redis;

import com.example.redis.test.Info2;
import com.example.redis.test.Info3;
import com.example.redis.test.InfoRepository2;
import com.example.redis.test.InfoRepository3;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SpringBootTest
class RedisApplicationTests {

    @Autowired
    InfoRepository infoRepository;

    @Autowired
    InfoRepository2 infoRepository2;

    @Autowired
    InfoRepository3 infoRepository3;

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    void 해시데이터_3개() {
        Info info = new Info("user", "jaehwankim", "27", new Info.Datas("hello", "world"), LocalDate.now());
        Info info2 = new Info("user2", "jaehwankim", "27", new Info.Datas("hello", "world"), LocalDate.now());
        Info info3 = new Info("user3", "jaehwankim", "27", new Info.Datas("hello", "world"), LocalDate.now());
        infoRepository.save(info);
        infoRepository.save(info2);
        infoRepository.save(info3);
    }

    @Test
    void 해시데이터_조회() {
        Set<String> keys = redisTemplate.keys("info*");
        keys.forEach(key -> System.out.println("key = " + key));
    }

    @Test
    void 해시데이터_삭제() {
        Info2 info = new Info2("user", "jaehwankim", "27", new Info2.Datas2("hello", "world"), LocalDate.now());
        Info3 info2 = new Info3("user", "jaehwankim", "27", new Info3.Datas3("hello", "world"), LocalDate.now());
        infoRepository2.save(info);
        infoRepository3.save(info2);

        Set<String> keys = redisTemplate.keys("info*");
        keys.forEach(key -> {
            System.out.println("hash data size :: " + redisTemplate.countExistingKeys(keys));
            redisTemplate.expire(key, Duration.ofMillis(1000));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
