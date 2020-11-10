package com.threeking.service.user;

import com.threeking.service.user.entity.UserInfo;
import com.threeking.service.user.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: A.H
 * @Date: 2020/11/9 18:14
 */
@SpringBootTest
public class MothodTest {

    @Autowired
    UserInfoServiceImpl userInfoService;

    @Test
    public void getTonken()
    {
        String t = userInfoService.getLoginToken(new UserInfo().setId(1L));

        System.out.println(t);

    }

    @Test
    public void test1(){
        userInfoService.test1();
        userInfoService.test3();
    }
}
