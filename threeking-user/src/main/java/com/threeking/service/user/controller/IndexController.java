package com.threeking.service.user.controller;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.threeking.service.user.entity.dto.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: A.H
 * @Date: 2020/10/16 15:25
 */
@RefreshScope
@RestController
public class IndexController {

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    JdbcTemplate jdbcTemplate;

//    @Autowired
//    UserInfoMapper userInfoMapper;

    @Value("${common}")
    private String common;

    @Value("${timeout}")
    private String timeout;

    @GetMapping("/")
    public String index(){
        return "你和憨憨,地址都拼不全";
    }

    @RequestMapping(value = "/hello", method ={RequestMethod.GET,RequestMethod.POST})
    public String hello(@RequestParam("userId") String userId){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        System.out.println(request.getQueryString());

        System.out.println("adsadasd = :"+ userId.toString());
        return "this is user server";
    }
    @GetMapping("/cp")
    public String cp(UserVo userVo){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        System.out.println(request.getQueryString());
        System.out.println(userVo.toString());
        return "this nacos setting common : " + common;
    }

    @PostMapping("/ccp")
    public String ccp(UserVo userVo)
    {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();

        System.out.println(request.getQueryString());

        return userVo.toString();
    }

    @GetMapping("timeout")
    public String nacostimeout(){
        //String as =configurableApplicationContext.getEnvironment().getProperty("common");
        return "this nacos ext setting  timeout : " + timeout;
    }

    @GetMapping("/jdbc")
    public Object jdbcStart(){
        return jdbcTemplate.queryForList("select * from user_info");
    }

//    @Autowired
//    UserInfoMapper userInfoMapper;
//
    @GetMapping("/getList")
    public Object getList(){
        //return userInfoMapper.selectList(null);
        return null;
    }


}
