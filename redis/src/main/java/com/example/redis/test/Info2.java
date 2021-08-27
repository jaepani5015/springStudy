package com.example.redis.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash("info2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info2 {
    @Id
    private String id;
    private String name;
    private String age;
    private Datas2 datas;
    private LocalDate date;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Datas2 {
        private String test;
        private String test2;
    }
}
