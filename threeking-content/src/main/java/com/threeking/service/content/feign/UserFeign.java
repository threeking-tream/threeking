package com.threeking.service.content.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: A.H
 * @Date: 2020/10/17 14:05
 */
@FeignClient(value = "threeking-user")
public interface UserFeign {

    @GetMapping("/hello")
    String hello();
}
