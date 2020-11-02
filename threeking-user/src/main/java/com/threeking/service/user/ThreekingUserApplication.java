package com.threeking.service.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.oas.annotations.EnableOpenApi;

//@EnableOpenApi
@EnableDiscoveryClient
@SpringBootApplication
public class ThreekingUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreekingUserApplication.class, args);
    }

}
