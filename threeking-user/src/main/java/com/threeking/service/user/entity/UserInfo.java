package com.threeking.service.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户主表
 * </p>
 *
 * @author ah
 * @since 2020-10-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UserInfo对象", description="用户主表")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    @ApiModelProperty(value = "0:女 1: 男 ")
    private Integer sex;

    private Date createTime;

    private String cretaeUser;

    private Date updateTime;

    private String updateUser;

    @ApiModelProperty(value = "0：有效 1: 无效 ")
    private Integer deteled;


}
