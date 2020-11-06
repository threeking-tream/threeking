package com.threeking.service.user.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: A.H
 * @Date: 2020/10/28 11:41
 */
@Data
@AllArgsConstructor
public class UserDto {

    @NotNull(message = "用户ID不能为空")
    @ApiModelProperty(value = "用户ID",allowEmptyValue = false,hidden = true)
    private String userId;

    @ApiModelProperty(value = "用户名称",hidden = true)
    private String userName;
}
