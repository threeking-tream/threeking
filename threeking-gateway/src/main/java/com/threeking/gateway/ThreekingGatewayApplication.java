package com.threeking.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class ThreekingGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreekingGatewayApplication.class, args);
    }

}
