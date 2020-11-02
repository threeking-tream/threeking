package com.threeking.service.user.service.impl;

import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.mapper.UserInfoMapper;
import com.threeking.service.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户主表 服务实现类
 * </p>
 *
 * @author ah
 * @since 2020-10-21
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
