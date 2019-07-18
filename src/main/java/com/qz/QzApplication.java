package com.qz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.qz.mapper")
@SpringBootApplication
public class QzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QzApplication.class, args);
    }

}
