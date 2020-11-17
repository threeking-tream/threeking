package com.threeking.gateway.swagger;

import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.support.NameUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;

///**
// * 以gateway方式聚合各个服务的swagger接口文档（还可以加入healthy，去逐个匹配healthy，判断是否存活，活的话加入SwaggerResource列表，否则不加入）
// *
// */
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    private final RouteLocator routeLocator;

    private final GatewayProperties gatewayProperties;

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routes = new ArrayList<>();

        // 取出gateway的route
        routeLocator.getRoutes()
                .filter(route -> route.getUri().getHost() != null)
                .filter(route -> Objects.equals(route.getUri().getScheme(), "lb"))
                .subscribe(route -> routes.add(route.getUri().getHost()));
        // 结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
        // 打开下面注释可以自动扫描接入gateway的服务，为了演示，只扫描system
        gatewayProperties.getRoutes().stream().filter(routeDefinition ->  routes.contains(routeDefinition.getId()))
                .forEach(routeDefinition ->
                        routeDefinition.getPredicates().stream()
                                .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
                                .forEach(predicateDefinition -> resources
                                        .add(swaggerResource(routeDefinition.getId(), predicateDefinition.getArgs()
                                                .get(NameUtils.GENERATED_NAME_PREFIX + "0").replace("/**", API_URI)))));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}