package com.threeking.service.user.common;

import lombok.Getter;


@Getter
public enum HttpCode {


    //请求成功
    SUCCESS("0", "请求成功"),

    // 功能型消息以1000起
    /** 验证码无效  */
    VERIFY_ERROR("1001", "验证码输入有误"),

    PASSWORD_ERROR("1002","密码错误"),

    // 用户相关以 3000 起
    USER_NO_EXITS("3001","用户不存在");



    // 财务类型的以 8000起

    private final String code;

    /** 描述 **/
    private final String desc;

    HttpCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}