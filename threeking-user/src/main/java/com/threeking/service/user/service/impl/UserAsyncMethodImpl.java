package com.threeking.service.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.mapper.UserInfoMapper;
import com.threeking.service.user.service.IUserAsyncMethod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: A.H
 * @Date: 2020/11/9 16:55
 */
@EnableAsync
@Service("UserAsync")
@Slf4j
public class UserAsyncMethodImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserAsyncMethod {

    @Async
    @Override
    public APIResponse updateLastLoginTime(Long id) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        System.out.println("执行.....耗时"+(end -start)+"ms");

        baseMapper.updateById(new UserInfo().setId(id).setUpdateTime(new Date()));

        return null;
    }
}
