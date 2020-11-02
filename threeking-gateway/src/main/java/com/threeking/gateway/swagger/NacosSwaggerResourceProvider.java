//package com.threeking.gateway.swagger;
//
//import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Primary;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import springfox.documentation.swagger.web.SwaggerResource;
//import springfox.documentation.swagger.web.SwaggerResourcesProvider;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * 以nacos方式聚合各个服务的swagger接口文档, 直接获取nacos存活的服务
// * 但是无法获取Gateway路由规则
// */
//@Primary
//@Component
//@ConditionalOnProperty(prefix = "com.threeking.gateway", name = "type", havingValue = "nacos", matchIfMissing = true)
//public class NacosSwaggerResourceProvider implements SwaggerResourcesProvider {
//    /**
//     * swagger2默认的url后缀
//     */
//    private static final String SWAGGER2URL = "/v2/api-docs";
//
//    private static final String OAS_30_URL = "/v3/api-docs";
//
//    @Autowired
//    private NacosDiscoveryProperties nacosDiscoveryProperties;
//
//    /**
//     * 网关应用名称
//     */
//    @Value("${spring.application.name}")
//    private String self;
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<SwaggerResource> resources = new ArrayList<>();
//
//        String hasIpCount = "true";
//        String withInstances = "false";
//        String pageNo = "1";
//        String pageSize = "1000";
//        String httpUrlEx = "http://%s/nacos/v1/ns/catalog/services?hasIpCount=%s&withInstances=%s&pageNo=%s&pageSize=%s&serviceNameParam=&groupNameParam=%s&namespaceId=%s";
//        String httpUrl = String.format(httpUrlEx, nacosDiscoveryProperties.getServerAddr(), hasIpCount, withInstances, pageNo, pageSize, nacosDiscoveryProperties.getGroup(), nacosDiscoveryProperties.getNamespace());
//        ResponseEntity<Map> responseEntityMap = new RestTemplate().getForEntity(httpUrl, Map.class);
//        Map responseMap = responseEntityMap.getBody();
//        List<Map> serviceList = (List<Map>) responseMap.get("serviceList");
//        Integer count = (Integer) responseMap.get("count");
//        serviceList.stream().map(service -> String.valueOf(service.get("name"))).distinct()
//                .forEach(serviceName -> {
//                    String url = "/" + serviceName.toLowerCase() + SWAGGER2URL;
//                    SwaggerResource swaggerResource = new SwaggerResource();
//                    swaggerResource.setSwaggerVersion("2.0");
//                    if(Objects.equals(serviceName.toLowerCase(), self)){
//                        url = OAS_30_URL;
//                        swaggerResource.setSwaggerVersion("3.0.3");
//                    }
//                    swaggerResource.setUrl(url);
//                    swaggerResource.setName(serviceName);
//                    resources.add(swaggerResource);
//                });
//        return resources;
//    }
//}