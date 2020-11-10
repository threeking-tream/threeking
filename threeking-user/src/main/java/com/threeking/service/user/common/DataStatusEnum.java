package com.threeking.service.user.common;

import lombok.Getter;


@Getter
public enum DataStatusEnum {


    //请求成功
    FAIL((byte)0, "无效"),

    // 功能型消息以1000起
    /** 验证码无效  */
    NORMAL((byte)1, "有效");


    // 财务类型的以 8000起

    private final Byte code;

    /** 描述 **/
    private final String desc;

    DataStatusEnum(Byte code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}