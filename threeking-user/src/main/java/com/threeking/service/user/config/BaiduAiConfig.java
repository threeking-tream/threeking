package com.threeking.service.user.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: A.H
 * @Date: 2020/11/6 18:48
 */
@Data
//@Component
//@ConfigurationProperties(prefix = "baiduai")
public class BaiduAiConfig {

    private String appid;

    private String appkey;

    private String secretKey;

}
