package com.dissertation.homestayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients("com.dissertation.*")
@ComponentScan(basePackages = {"com.dissertation.*"})
@EntityScan(basePackages = {"com.dissertation.*"})
@EnableJpaRepositories(basePackages = {"com.dissertation.*"})
@EnableDiscoveryClient
public class HomestayServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomestayServiceApplication.class, args);
    }

}
