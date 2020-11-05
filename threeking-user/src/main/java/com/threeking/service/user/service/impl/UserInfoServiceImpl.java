package com.threeking.service.user.service.impl;

import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.entity.dto.AccountVo;
import com.threeking.service.user.mapper.UserInfoMapper;
import com.threeking.service.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threeking.service.user.utils.IdGenerator;
import com.threeking.service.user.utils.PwdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * 用户主表 服务实现类
 * </p>
 *
 * @author ah
 * @since 2020-11-03
 */
@Slf4j
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    PwdGenerator pwdGenerator;

    /**
     * 账号密码注册方式
     * @param vo
     * @return
     */
    @Override
    public APIResponse accountRegister(AccountVo vo) {
        String userCode = idGenerator.simpleUUID();
        try {
            if(checkAccount(vo.getAccount()) > 0){
                return APIResponse.errorResp("账号已经存在");
            }

            PwdGenerator.PwdAndSalt hexPassword = pwdGenerator.getHexPassword(vo.getPassword());
            UserInfo userInfo = new UserInfo()
                    .setUserCode(userCode)
                    .setAccount(vo.getAccount())
                    .setPhone(vo.getAccount())
                    .setNikeName(vo.getAccount())
                    .setPassword(hexPassword.getPassword())
                    .setPwdSalt(hexPassword.getSalt())
                    .setSex(0)
                    .setCreateUser("system")
                    .setUpdateUser("system");

            return baseMapper.insert(userInfo)>0?
                    APIResponse.successResp("注册成功"):
                    APIResponse.errorResp("注册失败");
        } catch (Exception e) {
            log.error(e.getMessage());
            return APIResponse.errorResp("注册发生异常");
        }
    }


    /**
     * 校验账号是否存在
     * @param account
     * @return
     */
    @Override
    public Integer checkAccount(String account){
        if(StringUtils.isEmpty(account)) {return 0;}

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("account",account)
                .or()
                .eq("phone",account);


        Integer integer = baseMapper.selectCount(wrapper);
        return integer;
    }

}
