package com.superchakra.train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.superchakra.train")
@MapperScan("com.superchakra.train.mapper")
public class UserMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(UserMain8001.class,args);
    }
}