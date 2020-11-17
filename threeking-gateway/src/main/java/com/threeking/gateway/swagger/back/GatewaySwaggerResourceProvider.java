///**
// * 以gateway方式聚合各个服务的swagger接口文档（还可以加入healthy，去逐个匹配healthy，判断是否存活，活的话加入SwaggerResource列表，否则不加入）
// *
// * @author lbj
// * @date 2020/09/22 18:25
// */
//@Primary
//@Component
//@ConditionalOnProperty(prefix = "myyshop.swagger.provider", name = "type", havingValue = "gateway")
//public class GatewaySwaggerResourceProvider implements SwaggerResourcesProvider {
//    /**
//     * swagger2默认的url后缀
//     */
//    private static final String SWAGGER2URL = "/v2/api-docs";
//
//    private static final String OAS_30_URL = "/v3/api-docs";
//
//    /**
//     * 网关路由
//     */
//    @Autowired
//    private RouteLocator routeLocator;
//
//    @Autowired
//    private GatewayProperties gatewayProperties;
//
//    /**
//     * 网关应用名称
//     */
//    @Value("${spring.application.name}")
//    private String self;
//
//    @Override
//    public List<SwaggerResource> get() {
//        List<RouteDefinition> ds = gatewayProperties.getRoutes();
//        List<SwaggerResource> resources = new ArrayList<>();
//        List<String> routeHosts = new ArrayList<>();
//        // 获取所有可用的host：serviceId
//        routeLocator.getRoutes()
//                .filter(route -> route.getUri().getHost() != null)
//                .filter(route -> Objects.equals(route.getUri().getScheme(), "lb"))
////                .filter(route -> !self.equals(route.getUri().getHost()))
//                .subscribe(route -> routeHosts.add(route.getUri().getHost()));
//
//        // 记录已经添加过的server，存在同一个应用注册了多个服务在nacos上
//        Set<String> dealed = new HashSet<>();
//        routeHosts.forEach(instance -> {
//            // 拼接url
//            String url = "/" + instance.toLowerCase() + SWAGGER2URL;
//            if (!dealed.contains(url)) {
//                dealed.add(url);
//                SwaggerResource swaggerResource = new SwaggerResource();
//                swaggerResource.setUrl(url);
//                swaggerResource.setName(instance);
//                //swaggerResource.setSwaggerVersion("3.0.3");
//                resources.add(swaggerResource);
//            }
//        });
//        return resources;
//    }
//}