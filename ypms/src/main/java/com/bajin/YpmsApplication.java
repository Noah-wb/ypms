package com.bajin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bajin.dao")
public class YpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YpmsApplication.class, args);
    }

}
