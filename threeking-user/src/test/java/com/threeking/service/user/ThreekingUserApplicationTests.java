package com.threeking.service.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.mapper.UserInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ThreekingUserApplicationTests {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Test
    void contextLoads() {
        List<UserInfo> userInfos = userInfoMapper.selectList(null);
        userInfos.forEach(System.out::println);
    }

    @Test
    void pageSelect(){
        Page<UserInfo> page = new Page<>(1,5);
        Page<UserInfo> userlist = userInfoMapper.selectPage(page, null);

        userlist.getRecords().forEach(System.out::println);
    }

}
