package com.threeking.service.user.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @Author: A.H
 * @Date: 2020/11/13 13:55
 */
@Component
public class ValidUtil {

    public boolean checkPhone(String phone){
        String reg = "^1[\\d]{10}$";

        return Pattern.matches(reg,phone);
    }
}
