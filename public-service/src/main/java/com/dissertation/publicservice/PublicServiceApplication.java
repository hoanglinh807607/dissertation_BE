package com.dissertation.publicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.dissertation.publicservice")
public class PublicServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PublicServiceApplication.class, args);
    }

}
