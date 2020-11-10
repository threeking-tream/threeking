package com.threeking.service.user.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.entity.dto.PhoneDto;
import com.threeking.service.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired
    IUserInfoService iUserInfoService;

    @PostMapping("/login")
    @ApiOperationSupport()
    public APIResponse login(@RequestBody PhoneDto phoneDto) throws Exception {

        return APIResponse.successResp(iUserInfoService.loginWithPhone(phoneDto));
    }

    @PostMapping("/accountLogin")
    @ApiOperationSupport()
    public APIResponse accountLogin(@RequestBody AccountDto accountDto) throws Exception {

        return APIResponse.successResp(iUserInfoService.loginWithAccount(accountDto));
    }
}
