package com.threeking.service.user.utils;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.common.utils.ByteUtils;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.alibaba.nacos.common.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.ibatis.javassist.bytecode.ByteArray;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

/**
 * 密码生成器
 * @Author: A.H
 * @Date: 2020/11/4 11:51
 */
@Component
public class PwdGenerator {

    public PwdAndSalt getHexPassword(String pwd) throws NoSuchAlgorithmException {

        //如果没有输入密码，则默认随机给8位密码
        if (StringUtils.isEmpty(pwd)){
            pwd = RandomUtil.randomString(8);
        }
        //获取随机32位盐值
        String salt = String.valueOf(RandomUtil.randomString(32));
        //加盐值生成密码 用来保护输入相同密码的人，密码在库里可以展示不同数据
       return new PwdAndSalt()
               .setPassword(MD5Utils.md5Hex(ByteUtils.toBytes(pwd + salt)))
               .setSalt(salt);
    }

    @Data
    @Accessors(chain = true)
    public static class PwdAndSalt{
        private String password;
        private String salt;
    }
}
