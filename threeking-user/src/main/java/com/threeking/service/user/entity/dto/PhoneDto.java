package com.threeking.service.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @Author: A.H
 * @Date: 2020/11/2 17:30
 */
@Getter
@Setter
@ApiModel(value = "PhoneDto",description = "手机号注册或登录入参")
public class PhoneDto {

    @Pattern(regexp = "1[\\d]{10}",message = "请输正确手机号")
    @ApiModelProperty(value = "用户ID")
    private String phone;

    @NotEmpty(message = "验证码不能为空")
    @ApiModelProperty(value = "验证码")
    private String verify;
}
