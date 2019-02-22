package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.connection.RedisServer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@SpringBootApplication
@EnableSwagger2
@EnableCaching
public class DemoApplication {


    private RedisServer redisServer;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
