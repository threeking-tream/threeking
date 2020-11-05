package com.threeking.service.user.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * @Author: A.H
 * @Date: 2020/11/2 17:30
 */
@Getter
@Setter
@ToString
@ApiModel(value = "AccountVo",description = "账号注册入参")
public class AccountVo {

    @NotEmpty(message = "账号不能为空")
    @ApiModelProperty(value = "用户ID")
    private String account;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "用户ID")
    private String password;
}
