//package com.threeking.gateway.filter;
//
//import com.threeking.gateway.swagger.Swagger3Provider;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.core.Ordered;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.stereotype.Component;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
///**
// * @Author: A.H
// * @Date: 2020/10/27 18:00
// */
//@Component
//public class SwaggerHeaderFilter implements GlobalFilter, Ordered {
//    private static final String HEADER_NAME = "X-Forwarded-Prefix";
//
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        ServerHttpRequest request = exchange.getRequest();
//        String path = request.getURI().getPath();
//        if(!StringUtils.endsWithIgnoreCase(path, Swagger3Provider.API_URI)){
//            return chain.filter(exchange);
//        }
//        String basepath = path.substring(0, path.lastIndexOf(Swagger3Provider.API_URI));
//        ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basepath).build();
//        ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
//
//        return chain.filter(newExchange);
//
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
