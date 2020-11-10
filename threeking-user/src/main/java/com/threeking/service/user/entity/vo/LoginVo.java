package com.threeking.service.user.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: A.H
 * @Date: 2020/11/9 18:34
 */
@Data
@NoArgsConstructor
public class LoginVo {

    @ApiModelProperty(value = "用户编码")
    private String userCode;

    @ApiModelProperty(value = "用户账号")
    private String account;

    @ApiModelProperty(value = "昵称")
    private String nikeName;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "0:女 1: 男 ")
    private Integer sex;

    @ApiModelProperty(value = "登录Token")
    private String token;
}
