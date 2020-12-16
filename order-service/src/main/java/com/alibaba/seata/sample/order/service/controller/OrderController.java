package com.alibaba.seata.sample.order.service.controller;

import java.util.List;

import com.alibaba.seata.sample.common.Constants;
import com.alibaba.seata.sample.common.entity.Order;
import com.alibaba.seata.sample.order.service.service.IOrderService;

import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author slievrly
 */
@RestController
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @RequestMapping(value = "/createOrder", method = RequestMethod.POST, produces = "application/json")
    public String create(@RequestBody Order order) {
        System.out.println("order-service 当前正在执行的事务xid:" + RootContext.getXID());
        try {
            orderService.createOrder(order);
        } catch (Exception exx) {
            exx.printStackTrace();
            return Constants.FAIL_RESPONSE;
        }
        return Constants.SUCCESS_RESPONSE;
    }

    @RequestMapping(value = "/selectOrderByCode", method = RequestMethod.GET, produces = "application/json")
    public List<Order> selectOrderByCode(String commodityCode) {
        return orderService.selectOrderByCode(commodityCode);
    }
}
