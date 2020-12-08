package com.threeking.spi.service.impl;

import com.threeking.spi.service.common.PayService;

/**
 * @Author: A.H
 * @Date: 2020/12/8 10:18
 */
public class AliPayImpl implements PayService {
    @Override
    public void pay() {
        System.out.println("支付宝支付aa");
    }
}
