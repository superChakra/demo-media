package com.superchakra.train;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.superchakra.train")
@MapperScan("com.superchakra.train.mapper")
public class FileMain8002 {
    public static void main(String[] args) {
        SpringApplication.run(FileMain8002.class, args);
    }
}