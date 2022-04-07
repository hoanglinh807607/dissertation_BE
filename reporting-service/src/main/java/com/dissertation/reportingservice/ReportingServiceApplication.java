package com.dissertation.reportingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableFeignClients("com.dissertation.*")
@EntityScan(basePackages = {"com.dissertation.*"})
@EnableJpaRepositories(basePackages = {"com.dissertation.*"})
@EnableDiscoveryClient
public class ReportingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportingServiceApplication.class, args);
    }

}
