package threeking.spi.test;


import com.alibaba.fastjson.JSON;
import com.threeking.spi.service.common.PayService;


import java.util.HashMap;
import java.util.ServiceLoader;


/**
 * @Author: A.H
 * @Date: 2020/12/8 14:18
 */
public class MainTest {
    public static void main(String[] args) {
//        ServiceLoader<PayService> load = ServiceLoader.load(PayService.class);
//        load.forEach(PayService::pay);
        test();
    }


    static void test(){


        Order order = new Order();

        order.setId(1);

        order.setName("order1");

//        ListorderDetailList = new ArrayList();
//
//        OrderDetail orderDetail = new OrderDetail();
//
//        orderDetail.setOrderid(1);
//
//        orderDetail.setOrderPrice("1USD");
//
//        orderDetail.setOrderSku("Sku1");
//
//        orderDetailList.add(orderDetail);
//
//        OrderDetail orderDetail2 = new OrderDetail();
//
//        orderDetail2.setOrderid(1);
//
//        orderDetail2.setOrderPrice("2USD");
//
//        orderDetail2.setOrderSku("Sku2");
//
//        orderDetailList.add(orderDetail2);
        String detail = "妖鸟";
        try {
            HashMap addMap = new HashMap();

            HashMap addValMap = new HashMap();

            addMap.put("detail", Class.forName("java.lang.String"));

            addValMap.put("detail", detail);

            Object obj2= new ClassUtil().dynamicClass(order,addMap,addValMap);

            System.out.println(JSON.toJSONString(obj2));

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

}
