package com.threeking.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.UserInfo;
import org.springframework.stereotype.Component;

/**
 * @Author: A.H
 * @Date: 2020/11/9 16:54
 */
@Component
public interface IUserAsyncMethod extends IService<UserInfo> {

    APIResponse updateLastLoginTime(Long id) throws InterruptedException;
}
