package com.threeking.gateway.service;

import ch.qos.logback.classic.spi.EventArgUtil;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import javassist.expr.NewArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    final String RED_USER_LOGIN_SESSION = "red_user_login_session_";
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /**
     * 根据前端标识获取用户信息，可以是从redis，db等中获取
     * @param code
     * @return
     */
    public String getQueryUserInfo(String code){
        try {
            //根据code从redis或者数据库中获取用户信息
            String userId = stringRedisTemplate.opsForValue().get(code);
            assert userId != null;
            String loginSessionKey = RED_USER_LOGIN_SESSION + userId;
            String session = stringRedisTemplate.opsForValue().get(loginSessionKey);
            assert session != null;
            UserInfo user = JacksonUtils.toObj(session, UserInfo.class);

            log.info(user.toString());
            return user.toQuery();

        }catch (Exception e){
            log.error("获取用户信息时出错："+e.getMessage());
            return null;
        }
    }


    @Data
    @NoArgsConstructor
    public static class UserInfo{

        @JsonProperty(value = "id")
        private String userId;

        @JsonProperty(value = "nikeName")
        private String userName;

        /**
         * 重写一下toString方案，使其返回&参数拼接的字符串，方便使用
         * @return
         */
        public String toQuery() throws IllegalAccessException {

            StringBuilder query = new StringBuilder();
            // 注意一定要使用URLEncoder.encode转码，否则传值会有编码问题
            for(Field field : this.getClass().getDeclaredFields()){
                query.append(field.getName());
                query.append('=');
                query.append(URLEncoder.encode((String) field.get(this), StandardCharsets.UTF_8));
                query.append('&');
            }
            return query.deleteCharAt(query.length()-1).toString();
        }
    }

}
