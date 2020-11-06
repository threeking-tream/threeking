package com.threeking.service.user.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.common.HttpCode;
import com.threeking.service.user.config.WechatConfig;
import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.entity.dto.PhoneDto;
import com.threeking.service.user.mapper.UserInfoMapper;
import com.threeking.service.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threeking.service.user.utils.IdGenerator;
import com.threeking.service.user.utils.PwdGenerator;
import com.threeking.service.user.utils.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.spring.web.json.Json;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    RestTemplate restTemplate;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    PwdGenerator pwdGenerator;

    @Autowired
    VerifyUtil verifyUtil;

    @Autowired
    WechatConfig wechatConfig;

    /**
     * 账号密码注册方式
     * @param dto
     * @return
     */
    @Override
    public APIResponse<String> accountRegister(AccountDto dto) throws NoSuchAlgorithmException {

        if (checkAccount(dto.getAccount()) > 0) {
            return APIResponse.errorResp("账号已经存在");
        }
        String userCode = idGenerator.simpleUUID();
        PwdGenerator.PwdAndSalt hexPassword = pwdGenerator.getHexPassword(dto.getPassword());
        UserInfo userInfo = new UserInfo()
                .setUserCode(userCode)
                .setAccount(dto.getAccount())
                .setPhone(dto.getAccount())
                .setNikeName(dto.getAccount())
                .setPassword(hexPassword.getPassword())
                .setPwdSalt(hexPassword.getSalt())
                .setSex(0)
                .setCreateUser("system")
                .setUpdateUser("system");

        return baseMapper.insert(userInfo) > 0 ?
                APIResponse.successResp("注册成功") :
                APIResponse.errorResp("注册失败");

    }

    /**
     * 手机验证码注册
     * @param dto
     * @return
     * @throws NoSuchAlgorithmException
     */
    @Override
    public APIResponse phoneRegister(PhoneDto dto) throws NoSuchAlgorithmException {
        //先验证验证码
        if(verifyUtil.checkVerifyCode(dto.getPhone())){
            return APIResponse.httpCodeResp(HttpCode.VERIFY_ERROR);
        }
        // 校验手机号
        if (checkAccount(dto.getPhone()) > 0) {
            return APIResponse.errorResp("手机号已经存在");
        }
        String userCode = idGenerator.simpleUUID();
        PwdGenerator.PwdAndSalt hexPassword = pwdGenerator.getHexPassword(null);
        UserInfo userInfo = new UserInfo()
                .setUserCode(userCode)
                .setAccount(dto.getPhone())
                .setPhone(dto.getPhone())
                .setNikeName(dto.getPhone())
                .setPassword(hexPassword.getPassword())
                .setPwdSalt(hexPassword.getSalt())
                .setSex(0)
                .setCreateUser("system")
                .setUpdateUser("system");

        return baseMapper.insert(userInfo) > 0 ?
                APIResponse.successResp("注册成功") :
                APIResponse.errorResp("注册失败");
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

        return baseMapper.selectCount(wrapper);
    }

    @Override
    public void test(){
        System.out.println(wechatConfig.getWcappid());
        System.out.println(wechatConfig.getWcappSecret());
    }


    public APIResponse<Map> getAccessToken(String code) throws Exception {

        String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        String appId= "123123123";
        String appSecret ="sdsafsdfdfdsfdsf";

        String url = String.format(ACCESS_TOKEN_URL, appId, appSecret, code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, headers);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> parse = mapper.readValue(responseEntity.getBody(), HashMap.class);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            try {
                HashMap<Object, Object> wat = new HashMap<>();
                wat.put("openid",parse.get("openid"));
                wat.put("unionid",parse.get("unionid"));

                return APIResponse.successResp(wat);
            } catch (Exception e) {
                return APIResponse.errorResp(parse.get("errorMsg"));
            }
        }

        return APIResponse.errorResp("微信接口请求异常");
    }

}
