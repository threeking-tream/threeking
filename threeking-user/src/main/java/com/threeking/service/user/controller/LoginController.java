package com.threeking.service.user.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.threeking.service.user.common.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: A.H
 * @Date: 2020/11/5 9:52
 */
@RequestMapping("/login")
@RestController
@Api(value = "LoginController", tags = "用户登录接口")
public class LoginController {


    @PostMapping("/login")
    @ApiOperationSupport()
    public APIResponse login() {
        return APIResponse.successResp("执行成功");
    }
}
