package com.alibaba.seata.sample.order.service.mapper;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Order;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author slievrly
 */
@Mapper
public interface OrderMapper {

    int insert(Order order);

    List<Order> selectOrderByCode(String commodityCode);

}