package com.threeking.service.content.feign;

import com.threeking.service.content.entity.AccountVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: A.H
 * @Date: 2020/10/17 14:05
 */
@FeignClient(value = "threeking-user")
public interface UserFeign {

    @GetMapping("/hello")
    String hello();

    @PostMapping("/register/accountRegister")
    Object accoutRegister(AccountVo vo);
}
