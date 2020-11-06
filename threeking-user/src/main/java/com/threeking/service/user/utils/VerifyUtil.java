package com.threeking.service.user.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.threeking.service.user.common.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import static java.util.concurrent.TimeUnit.MINUTES;

/**
 * 验证码相关工具
 * @Author: A.H
 * @Date: 2020/11/6 14:57
 */
@Component
public class VerifyUtil {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    /**
     * 发送验证码
     */
    public boolean sendVerifyCode(String phoneNo){

       String code = RandomUtil.randomString(4);

        redisTemplate.opsForValue().set(Constants.RED_USER_VERIFY + phoneNo,code,Constants.SEND_VERIFY_INTERVAL , MINUTES);
        //TODO:: 调用短信发送接口
        return true;
    }


    /**
     * 校验验证码
     * 使用最简单的手机号验证
     */
    public boolean checkVerifyCode(String phoneNo){

        Object validateCodeCache = redisTemplate.opsForValue().get(Constants.RED_USER_VERIFY + phoneNo);
        if (ObjectUtil.isEmpty(validateCodeCache)) {
            return false;
        }
        assert validateCodeCache != null;
        if(phoneNo.equals(validateCodeCache.toString())){
            //验证成功即可删除
            redisTemplate.delete(Constants.RED_USER_VERIFY + phoneNo);
            return true;
        }
        return false;
    }


}
