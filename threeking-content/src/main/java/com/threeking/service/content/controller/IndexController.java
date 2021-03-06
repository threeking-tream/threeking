package com.threeking.service.content.controller;

import com.threeking.service.content.entity.AccountVo;
import com.threeking.service.content.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: A.H
 * @Date: 2020/10/16 17:19
 */
@RestController
public class IndexController {

    @Autowired
    UserFeign userFeign;

    @GetMapping("/")
    public String hello(){
        String u = userFeign.hello();
        return "hello! this is content server, and user=" +u ;
    }


    @GetMapping("/register")
    public String register(){
        AccountVo accountVo = new AccountVo();
        accountVo.setAccount("sad");
        accountVo.setPassword("asdas");

        Object o = userFeign.accoutRegister(accountVo);

        System.out.println(o.toString());
        return "ok";
    }
}
