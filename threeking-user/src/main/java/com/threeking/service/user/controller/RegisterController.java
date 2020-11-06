package com.threeking.service.user.controller;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.spring.util.ObjectUtils;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.entity.dto.PhoneDto;
import com.threeking.service.user.service.IUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @ApiOperation(value = "/accountRegister",notes = "账号密码注册方式",response = AccountDto.class)
    @PostMapping("/accountRegister")
    public APIResponse accountRegister(@RequestBody AccountDto accountDto){
        return iUserInfoService.accountRegister(accountDto);
    }

    @ApiOperation(value = "/phoneRegister", notes = "手机验证码注册", response = PhoneDto.class)
    @PostMapping("/phoneRegister")
    public APIResponse phoneRegister(@RequestBody @Valid PhoneDto phoneDto){
        return APIResponse.successResp("注册成功");
    }


    @PostMapping("/testPhoneRegister")
    public String testPhoneRegister(@RequestBody @Valid PhoneDto dto){

//        if (result.hasErrors())
//        {
//            List<ObjectError> ls=result.getAllErrors();
//            ls.forEach(e-> System.out.println(e.getDefaultMessage()));
//            //默认返回第一个错误，大家可以根据需求定制
//            return ls.get(0).getDefaultMessage();
//        }
        System.out.println(dto.toString());
        return "phoneRegister...";
    }

}
