//package com.threeking.gateway.swagger;
//
//import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
//import com.google.common.collect.Lists;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.annotation.Order;
//import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.*;
//
//import java.util.List;
//

// 这个config不需要了
///**
// *
// * @Author: A.H
// * @Date: 2020/10/23 18:28
// */
////@Configuration
////@EnableSwagger2
////@EnableSwaggerBootstrapUI
////@Import(BeanValidatorPluginsConfiguration.class)
//public class Swagger2Config {
//
////    @Bean(value = "userApi")
////    @Order(value = 1)
////    public Docket groupRestApi() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .apiInfo(apiInfo())
////                .select()
////                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
////                .paths(PathSelectors.any())
////                .build()
////                .securityContexts(Lists.newArrayList(securityContext()))
////                .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
////    }
////
////    private ApiInfo apiInfo() {
////        return new ApiInfoBuilder().title("XXX服务平台接口文档").description("XXX多租户微服务平台")
////                .contact(new Contact("Minco", "", "")).version("1.0-FAST").build();
////    }
////
////    private ApiKey apiKey() {
////        return new ApiKey("TOKEN", "token", "header");
////    }
////
////    private SecurityContext securityContext() {
////        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.regex("/.*")).build();
////    }
////
////    List<SecurityReference> defaultAuth() {
////        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
////        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
////        authorizationScopes[0] = authorizationScope;
////        return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
////    }
//}