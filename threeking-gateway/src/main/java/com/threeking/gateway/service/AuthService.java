package com.threeking.gateway.service;

import ch.qos.logback.classic.spi.EventArgUtil;
import javassist.expr.NewArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: A.H
 * @Date: 2020/10/29 15:48
 */
@Slf4j
@Service
public class AuthService {



    /**
     * 根据前端标识获取用户信息，可以是从redis，db等中获取
     * @param code
     * @return
     */
    public String getQueryUserInfo(String code){

        //TODO: 根据code从redis或者数据库中获取用户信息

        //测试
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId("123");
        userInfo.setUserName("奥特曼打小怪兽");

        log.info(userInfo.toString());
        return userInfo.toQuery();

    }


    @Data
    @NoArgsConstructor
    public static class UserInfo{

        private String userId;

        private String userName;

        /**
         * 重写一下toString方案，使其返回&参数拼接的字符串，方便使用
         * @return
         */
        public String toQuery(){

            String query = "userId" +
                    '=' +
                    this.getUserId() +
                    '&' +
                    "userName" +
                    '=' +
                    this.getUserName();
            // 注意一定要使用URLEncoder.encode转码，否则传值会有编码问题
            return URLEncoder.encode(query, StandardCharsets.UTF_8);
        }
    }

}
