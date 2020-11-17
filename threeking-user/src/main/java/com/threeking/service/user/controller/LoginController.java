package com.threeking.service.user.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.entity.dto.PhoneDto;
import com.threeking.service.user.entity.vo.LoginVo;
import com.threeking.service.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    @ApiOperation(value = "/login",notes = "手机验证码登录")
    public APIResponse<LoginVo>  login(@RequestBody @Valid PhoneDto phoneDto) throws Exception {

        return iUserInfoService.loginWithPhone(phoneDto);
    }

    @PostMapping("/accountLogin")
    @ApiOperationSupport()
    @ApiOperation(value = "/accountLogin",notes = "账号密码登录")
    public APIResponse<LoginVo>  accountLogin(@RequestBody @Valid AccountDto accountDto) throws Exception {

        return iUserInfoService.loginWithAccount(accountDto);
    }

    @PostMapping("/sendVerify")
    @ApiOperation(value = "/sendVerify",notes = "获取验证码")
    public APIResponse<String> sendVerify(@RequestParam(value = "phoneNo",required = false) String phoneNo){
        return APIResponse.successResp(iUserInfoService.sendSmsVerify(phoneNo));
    }
}
