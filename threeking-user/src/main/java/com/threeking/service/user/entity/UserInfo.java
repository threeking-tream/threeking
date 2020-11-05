package com.threeking.service.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户主表
 * </p>
 *
 * @author ah
 * @since 2020-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="UserInfo对象", description="用户主表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "盐值")
    private String pwdSalt;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    @ApiModelProperty(value = "0: 无效 1：有效 ")
    private Integer dataStatus;


}
