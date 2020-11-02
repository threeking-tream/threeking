package com.threeking.service.content.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: A.H
 * @Date: 2020/10/21 14:47
 */

@Configuration
@MapperScan("com.threeking.service.user.mapper")
public class MybatisPlusConfig {
}
