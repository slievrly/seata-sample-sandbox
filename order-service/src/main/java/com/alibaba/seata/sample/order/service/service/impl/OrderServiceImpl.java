package com.alibaba.seata.sample.order.service.service.impl;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Order;
import com.alibaba.seata.sample.order.service.mapper.OrderMapper;
import com.alibaba.seata.sample.order.service.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author slievrly
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void createOrder(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public List<Order> selectOrderByCode(String commodityCode) {
        return orderMapper.selectOrderByCode(commodityCode);
    }
}
