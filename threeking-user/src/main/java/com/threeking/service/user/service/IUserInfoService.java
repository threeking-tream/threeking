package com.threeking.service.user.service;

import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.threeking.service.user.entity.dto.AccountVo;

/**
 * <p>
 * 用户主表 服务类
 * </p>
 *
 * @author ah
 * @since 2020-11-03
 */
public interface IUserInfoService extends IService<UserInfo> {

    APIResponse accountRegister(AccountVo vo);
    Integer checkAccount(String account);
}
