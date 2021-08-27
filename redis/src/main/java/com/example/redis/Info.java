package com.example.redis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash("info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info {
    @Id
    private String id;

    private String name;
    private String age;
    private Datas datas;
    private LocalDate date;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Datas {
        private String test;
        private String test2;
    }
}
