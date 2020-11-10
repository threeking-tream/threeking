package com.threeking.service.user.common;

/**
 * @Author: A.H
 * @Date: 2020/11/6 15:39
 */
public interface Constants {

    // 验证码发送间隔 300秒
    int SEND_VERIFY_INTERVAL = 300;


    //region Redis前缀相关

    // 验证码前缀
    String RED_USER_VERIFY = "red_user_verify_";

    // 用户登录Token保存时间 60 天
    int TIME_CACHE_USER = 60;

    // 用户登录信息保存实体前缀
    String RED_USER_LOGIN_SESSION = "SESSION_";


    //endregion
}
