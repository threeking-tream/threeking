package com.threeking.gateway.swagger;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import springfox.documentation.RequestHandler;
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
                .apis(new SwaggerFilter())
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

    static class SwaggerFilter implements Predicate<RequestHandler> {
        @Override
        public boolean apply(@Nullable RequestHandler requestHandler) {
            assert requestHandler != null;
            return requestHandler.findAnnotation(ApiOperation.class).isPresent();
        }
    }
}
