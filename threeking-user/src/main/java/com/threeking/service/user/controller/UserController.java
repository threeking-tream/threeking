package com.threeking.service.user.controller;

import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;

/**
 * @Author: A.H
 * @Date: 2020/11/2 15:28
 */
@Slf4j
@Api(value = "UserController",tags = "用户通用接口")
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @Autowired
    IUserInfoService iUserInfoService;

    @ApiOperation(value = "/accountRegister",notes = "账号密码注册方式",response = AccountDto.class)
    @PostMapping("/accountRegister")
    public APIResponse accountRegister(@RequestBody AccountDto accountDto) throws Exception {
        return iUserInfoService.accountRegister(accountDto);
    }
}
