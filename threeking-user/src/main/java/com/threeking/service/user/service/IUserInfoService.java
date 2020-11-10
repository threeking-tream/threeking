package com.threeking.service.user.service;

import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.entity.dto.PhoneDto;

import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 用户主表 服务类
 * </p>
 *
 * @author ah
 * @since 2020-11-03
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 账号密码注册
     * @param dto
     * @return
     */
    APIResponse<String> accountRegister(AccountDto dto) throws NoSuchAlgorithmException;


    /**
     * 手机验证码注册
     * @param dto
     * @return
     */
    APIResponse<String> phoneRegister(PhoneDto dto) throws NoSuchAlgorithmException;


    Integer checkAccount(String account);

    void test() throws InterruptedException;


    APIResponse loginWithPhone(PhoneDto phoneDto) throws InterruptedException;

    APIResponse loginWithAccount(AccountDto dto) throws InterruptedException;

}
