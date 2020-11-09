package com.threeking.service.user.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: A.H
 * @Date: 2020/11/6 18:48
 */
@Data
@NoArgsConstructor
@Component
@ConfigurationProperties(prefix = "wechat")
public class WechatConfig {

    private String wcappid;

    private String wcappSecret;
}
