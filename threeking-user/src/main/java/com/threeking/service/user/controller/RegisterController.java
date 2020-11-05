package com.threeking.service.user.controller;

import com.alibaba.nacos.common.utils.StringUtils;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.dto.AccountVo;
import com.threeking.service.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户注册
 * @Author: A.H
 * @Date: 2020/11/2 15:28
 */
@Slf4j
@Api(value = "RegisterController",tags = "用户注册接口")
@RequestMapping(value = "/register")
@RestController
public class RegisterController {

    @Autowired
    IUserInfoService iUserInfoService;

    @ApiOperation(value = "/accountRegister",notes = "账号密码注册方式",response = AccountVo.class)
    @PostMapping("/accountRegister")
    public APIResponse accountRegister(@RequestBody AccountVo accountVo){
        return iUserInfoService.accountRegister(accountVo);
    }

}
