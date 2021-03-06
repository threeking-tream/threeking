package com.threeking.gateway.filter;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;


/**
 * 实现全局鉴权
 * @Author: A.H
 * @Date: 2020/10/27 16:53
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    static final String API_URI = "/v2/api-docs";
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String uri = request.getURI().getPath();
        if (StringUtils.contains(uri,API_URI)){
            return chain.filter(exchange);
        }
        String hostAddress = "";
        List<String> iplist = exchange.getRequest().getHeaders().get("X-Forwarded-For");
        if(iplist!= null && iplist.size() > 0) {
            hostAddress=iplist.get(0);
        }
        exchange.getRequest().mutate().header("realIp", hostAddress).build();

        //放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
