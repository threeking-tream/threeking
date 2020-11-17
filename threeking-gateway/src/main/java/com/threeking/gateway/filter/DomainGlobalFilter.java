package com.threeking.gateway.filter;

import com.threeking.gateway.common.ManageExcludeConfig;
import com.threeking.gateway.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;


import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @Author: A.H
 * @Date: 2020/10/28 17:04
 */
@Slf4j
@Component
public class DomainGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    ManageExcludeConfig manageExcludeConfig;

    @Autowired
    AuthService authService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        HttpMethod method = request.getMethod();

        String url = request.getURI().getPath();
        if(manageExcludeConfig.isExclude(url)){
            log.info("非过滤地址，直接跳过.....");
            return chain.filter(exchange);
        }

        URI uri = exchange.getRequest().getURI();
        StringBuilder query = new StringBuilder();
        String originalQuery = uri.getRawQuery();
        if (StringUtils.hasText(originalQuery)) {
            query.append(originalQuery);
            if (originalQuery.charAt(originalQuery.length() - 1) != '&') {
                query.append('&');
            }
        }
        // 模拟从前端获取到的用户标识，可以是token，session，或者其他约定的参数
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if(StringUtils.isEmpty(token)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String strInfo = authService.getQueryUserInfo(token);
        if(StringUtils.isEmpty(strInfo)){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        // 从身份类里面获取用户信息
        query.append(strInfo);

        try {
            URI newUri = UriComponentsBuilder.fromUri(uri).replaceQuery(query.toString()).encode(StandardCharsets.UTF_8).build(true).toUri();
            ServerHttpRequest newRequest = exchange.getRequest().mutate().uri(newUri).build();
            return chain.filter(exchange.mutate().request(newRequest).build());
        } catch (RuntimeException var9) {
            var9.printStackTrace();
            throw new IllegalStateException("Invalid URI query: \"" + query.toString() + "\"");
        }

    }


    @Override
    public int getOrder() {
        return 1;
    }
}
