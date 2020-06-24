package com.example.myprovider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
public class MyProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyProviderApplication.class, args);
    }

}
