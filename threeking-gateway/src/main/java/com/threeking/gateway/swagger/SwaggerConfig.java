package com.threeking.gateway.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @Author: A.H
 * @Date: 2020/11/17 11:51
 */
//@EnableOpenApi
//@Configuration
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

    /**
     * API 页面上半部分展示信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("threeking gateway api")
                .description("nothing here")
                .version("1.0")
                .contact(new Contact("Applaction",null,null))
                .build();
    }
}
