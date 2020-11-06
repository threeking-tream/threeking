package com.threeking.gateway.common;

import lombok.Getter;

/**
 * 消息
 * @Author: A.H
 * @Date: 2020/11/6 14:46
 */
@Getter
public enum HttpCode {

    //请求成功
    SUCCESS(0, "请求成功");

    private final int code;

    /** 描述 **/
    private final String desc;

    HttpCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
