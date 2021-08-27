package com.example.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

//@Component
@RequiredArgsConstructor
public class Test implements ApplicationRunner {

    private final InfoRepository infoRepository;

    private final RedisTemplate<String, String> template;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        /*
        Info info = new Info("user", "jaehwankim", "27", new Info.Datas("hello", "world"), LocalDate.now());
        Info info2 = new Info("user2", "jaehwankim", "27", new Info.Datas("hello", "world"), LocalDate.now());
        Info info3 = new Info("user3", "jaehwankim", "27", new Info.Datas("hello", "world"), LocalDate.now());
        infoRepository.save(info);
        infoRepository.save(info2);
        infoRepository.save(info3);
        */

        Info info3 = new Info("user3", "hello", "25", new Info.Datas("spring", "boot"), LocalDate.now());
        infoRepository.save(info3);

        Set<String> info1 = template.keys("info*");
//        info1.forEach(e -> template.expire(e, Duration.ofMillis(3000)));
    }
}