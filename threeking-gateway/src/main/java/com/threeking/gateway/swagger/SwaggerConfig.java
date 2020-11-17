package com.threeking.gateway.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @Author: A.H
 * @Date: 2020/11/17 11:51
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public  Docket createApi(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .build()
                .securitySchemes(security());

    }
    private List<SecurityScheme> security() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        return Collections.singletonList(apiKey);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("mbye api")
                .description("nothing here")
                .version("1.0")
                .build();
    }
}
