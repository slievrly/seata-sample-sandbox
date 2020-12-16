package com.alibaba.seata.sample.order.service.service;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Order;

/**
 * @author slievrly
 */
public interface IOrderService {

    void createOrder(Order order);

    List<Order> selectOrderByCode(String commodityCode);
}
