package threeking.spi.test;


import com.threeking.spi.service.common.PayService;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @Author: A.H
 * @Date: 2020/12/8 14:18
 */
public class MainTest {
    public static void main(String[] args) {
        ServiceLoader<PayService> load = ServiceLoader.load(PayService.class);
        load.forEach(PayService::pay);
    }
}
