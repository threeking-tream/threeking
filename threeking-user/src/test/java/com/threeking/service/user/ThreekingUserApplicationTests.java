package com.threeking.service.user;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.nacos.common.utils.ByteUtils;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.mapper.UserInfoMapper;
import com.threeking.service.user.service.IUserInfoService;
import com.threeking.service.user.utils.IdGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.regex.Pattern;

@SpringBootTest
class ThreekingUserApplicationTests {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    IdGenerator idGenerator;

    @Autowired
    IUserInfoService iUserInfoService;
    @Test
    void contextLoads() {
        //List<UserInfo> userInfos = userInfoMapper.selectList(null);
        List<UserInfo> userInfos = iUserInfoService.list(null);
        userInfos.forEach(System.out::println);
    }

    @Test
    void pageSelect(){
        Page<UserInfo> page = new Page<>(1,5);
        Page<UserInfo> userlist = userInfoMapper.selectPage(page, null);

        userlist.getRecords().forEach(System.out::println);
    }

    @Test
    void inserUser(){

        UserInfo userInfo =   new UserInfo()
                .setAccount("aa12")
                .setUserCode("aa12")
                .setNikeName("aaa12")
                .setPhone("adda12")
                .setPassword("adasd12")
                .setPwdSalt("sad12")
                .setCreateUser("sad12")
                .setUpdateUser("das12");
        iUserInfoService.save(userInfo);

    }

    @Test
    public void testSnowflakeId(){
        System.out.println("Snowflake:" + idGenerator.snowflakeId());
        System.out.println("SimpleUUID" +idGenerator.simpleUUID());
        System.out.println("mongodb:"+ idGenerator.objectId() );
    }

    @Test
    public void testRandomString(){
        System.out.println(RandomUtil.randomString(32));
    }

    @Test
    public void checkRandomMd5(){
        String pwd = "123";
        String salt = String.valueOf(RandomUtil.randomString(32));
        try {
           String dcode =   MD5Utils.md5Hex(ByteUtils.toBytes(pwd + salt));

           if(dcode.equals(MD5Utils.md5Hex(ByteUtils.toBytes(pwd + salt))))
            {
                System.out.println("k可以匹配");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("取值出错");
        }

    }
    @Test
    public void checkSelectOne(){
        iUserInfoService.checkAccount("aaaaa");
    }


    void checkPhone(String phoneNo){
        String reg = "^1[\\d]{10}$";

        Pattern pattern = Pattern.compile(reg);

        if(Pattern.matches(reg, phoneNo)){
            System.out.println(phoneNo+ "正确");
        }
        else {
            System.out.println(phoneNo+ "错误");
        }
    }

    @Test
    void regixTest(){

        String phone = "13211112525";

        checkPhone("13211112525");
        checkPhone("23211112525");
        checkPhone("11112525");

    }
}
