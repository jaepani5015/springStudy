package com.example.redis.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;

@RedisHash("info3")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Info3 {
    @Id
    private String id;
    private String name;
    private String age;
    private Datas3 datas;
    private LocalDate date;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Datas3 {
        private String test;
        private String test2;
    }
}
