package com.threeking.service.user.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.common.utils.JacksonUtils;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.alibaba.nacos.common.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.threeking.service.user.common.APIResponse;
import com.threeking.service.user.common.Constants;
import com.threeking.service.user.common.DataStatusEnum;
import com.threeking.service.user.common.HttpCode;
import com.threeking.service.user.config.WechatConfig;
import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.entity.dto.AccountDto;
import com.threeking.service.user.entity.dto.PhoneDto;
import com.threeking.service.user.entity.vo.LoginVo;
import com.threeking.service.user.mapper.UserInfoMapper;
import com.threeking.service.user.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.threeking.service.user.service.IUserAsyncMethod;
import com.threeking.service.user.utils.IdGenerator;
import com.threeking.service.user.utils.LoginUtil;
import com.threeking.service.user.utils.PwdGenerator;
import com.threeking.service.user.utils.VerifyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CharsetEditor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.beans.beancontext.BeanContextProxy;
import java.nio.charset.CharsetEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


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

    @Qualifier("UserAsync")
    @Autowired
    IUserAsyncMethod iUserAsyncMethod;

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
        if(verifyUtil.checkVerifyCode(dto.getPhone(),dto.getVerify())){
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
    public String sendSmsVerify(String phoneNo){

        return verifyUtil.sendVerifyCode(phoneNo);
    }

    @Override
    public void test() throws InterruptedException {
        //String validateCode = String.valueOf(RandomUtil.randomString(6));

        String verify = verifyUtil.sendVerifyCode("13266666666");
        System.out.println(verify);
//        iUserAsyncMethod.updateLastLoginTime(1L);
//        stringRedisTemplate.opsForValue().set("1332333333",
//                validateCode,
//                200,
//                TimeUnit.SECONDS);
//
//        redisTemplate.opsForValue().set("132666666663",verify,60,TimeUnit.SECONDS);
//
//        String token = getLoginToken(new UserInfo().setId(1L));
//        System.out.println(token);
//
//
//        System.out.println(wechatConfig.getWcappid());
//        System.out.println(wechatConfig.getWcappSecret());
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

    @Override
    public APIResponse loginWithAccount(AccountDto dto) throws InterruptedException {

        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("account", dto.getAccount())
                .eq("data_status", DataStatusEnum.NORMAL.getCode());

        UserInfo user = baseMapper.selectOne(wrapper);
        if(ObjectUtil.isEmpty(user)){
            return APIResponse.httpCodeResp(HttpCode.USER_NO_EXITS);
        }
        //if()
        //最普通的密码校验，前端传入的未MD5加密之后的直接比较
        if(dto.getPassword().equals(user.getPassword())){
            return APIResponse.httpCodeResp(HttpCode.PASSWORD_ERROR);
        }
        // 升级版的，前端发送前请求数据库，获取盐值加密，后传入后台，但是不推荐，因为前端传入的密码依然是固定的，也可能被拦截
        // 最终版，前端获取盐值，和随机码，用原始密码+盐值+随机码加密，传入后台进行判断，判定是，用数据库加密密码+盐值，再加密与前端入参对比



        //异步调用更新登录时间
        iUserAsyncMethod.updateLastLoginTime(user.getId());


        return APIResponse.successResp(packLoginVo(user));

    }

    @Override
    public APIResponse<LoginVo> loginWithPhone(PhoneDto dto) throws InterruptedException {

        //先验证验证码
        if(!verifyUtil.checkVerifyCode(dto.getPhone(),dto.getVerify())){
            return APIResponse.httpCodeResp(HttpCode.VERIFY_ERROR);
        }

        // 查询用户信息
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("phone", dto.getPhone())
                .eq("data_status", DataStatusEnum.NORMAL.getCode());
        UserInfo user = baseMapper.selectOne(wrapper);
        if(ObjectUtil.isEmpty(user)){
            return APIResponse.httpCodeResp(HttpCode.USER_NO_EXITS);
        }

        //异步调用更新登录时间
        iUserAsyncMethod.updateLastLoginTime(user.getId());


        return APIResponse.successResp(packLoginVo(user));
    }

    private LoginVo packLoginVo(UserInfo user){
        // 封装返回登录token及前端使用非敏感信息
        String token = getLoginToken(user);
        LoginVo loginVo = new LoginVo();
        BeanUtils.copyProperties(user,loginVo);
        loginVo.setToken(token);
        return loginVo;
    }


    public String getLoginToken(UserInfo user)
    {
        String strToken = MD5Utils.md5Hex(String.valueOf(user.getId()),"UTF-8").concat(RandomUtil.randomString(6));

        long cacheTime = LoginUtil.getLoginCacheTime();
        String loginSessionKey = Constants.RED_USER_LOGIN_SESSION + user.getId();

        //将Key缓存起来
        stringRedisTemplate.opsForValue().set(strToken, user.getId().toString(), cacheTime,TimeUnit.DAYS);
        //将用户登录信息缓存起来
        stringRedisTemplate.opsForValue().set(loginSessionKey, JacksonUtils.toJson(user), cacheTime,TimeUnit.DAYS);

        return strToken;
    }



    public void test1(){
        Map<String, String> result = Maps.newHashMap();
        // 生成4位验证码
        String verificationCode = String.valueOf(RandomUtil.randomString(4));
        result.put("verificationCode", verificationCode);
        result.put("passwordSalt", "1232312312321321312");
        System.out.println(verificationCode.toString());
        this.redisTemplate.opsForValue().set("aaaaaaaaa", verificationCode, 300, TimeUnit.SECONDS);
    }

    public void test3(){
        Object o = this.redisTemplate.opsForValue().get("aaaaaaaaa");
        System.out.println(o.toString());
    }
}
