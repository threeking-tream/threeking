package com.threeking.spi.wx.pay.service.impl;

import com.threeking.spi.service.common.PayService;

/**
 * @Author: A.H
 * @Date: 2020/12/8 15:33
 */
public class WxPayImpl implements PayService {
    @Override
    public void pay() {
        System.out.println("微信支付ccc");
    }
}
