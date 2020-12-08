package com.threeking.spi.kq.service.impl;

import com.threeking.spi.service.common.PayService;

/**
 * @Author: A.H
 * @Date: 2020/12/8 15:08
 */
public class KqPayImpl implements PayService {
    @Override
    public void pay() {
        System.out.println("快钱支付bbb");
    }
}
