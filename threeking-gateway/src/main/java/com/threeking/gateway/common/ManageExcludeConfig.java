package com.threeking.gateway.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 排查参数
 * @Author: A.H
 * @Date: 2020/10/28 17:22
 */
@Data
@AllArgsConstructor
@Component
@PropertySource(value = {"classpath:conf/manage.properties"})
@ConfigurationProperties(prefix = "manage")
public class ManageExcludeConfig {
    //排除列表
    private List<String> excludes;

    /**
     * 验证是否排除
     * @return
     */
    public boolean isExclude(String uri) {
        return urlFilter(excludes, uri);
    }

    /**
     * 验证是否过滤
     * @param patternStrs
     * @param uri
     * @return
     */
    private boolean urlFilter(List<String> patternStrs, String uri) {
        for (String str : patternStrs) {
            if (Pattern.compile(str).matcher(uri).find()) {
                return true;
            }
        }

        return false;

    }
}
