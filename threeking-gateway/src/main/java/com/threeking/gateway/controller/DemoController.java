package com.threeking.gateway.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemoController {

    @Autowired
    WebApplicationContext applicationContext;

    /**
     * 将所有接口都查询出来
     * @return
     */
    @ApiOperation(value = "v1/getAllUrl",notes = "查询所有接口")
    @RequestMapping(value = "v1/getAllUrl", method = RequestMethod.POST)
    public Object getAllUrl() {
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();

//      List<String> urlList = new ArrayList<>();
//      for (RequestMappingInfo info : map.keySet()) {
//          // 获取url的Set集合，一个方法可能对应多个url
//          Set<String> patterns = info.getPatternsCondition().getPatterns();
//
//          for (String url : patterns) {
//              urlList.add(url);
//          }
//      }

        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            Map<String, String> map1 = new HashMap<String, String>();
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                map1.put("url", url);
            }

            map1.put("className", method.getMethod().getDeclaringClass().getName()); // 类名
            map1.put("method", method.getMethod().getName()); // 方法名
            RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                map1.put("type", requestMethod.toString());
            }

            final MethodParameter[] methodParameters = method.getMethodParameters();
            for (MethodParameter methodParameter : methodParameters) {
                map1.put("param", methodParameter.getNestedParameterType().getSimpleName());
            }


            list.add(map1);
        }
        return list;
    }
}
