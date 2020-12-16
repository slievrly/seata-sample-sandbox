package com.alibaba.seata.sample.business.service.feign;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Order;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author slievrly
 */
@FeignClient(name = "order-service",url = "http://127.0.0.1:8082")
public interface IOrderService {

    @RequestMapping(value = "/createOrder")
    String createOrder(@RequestBody Order orders);

    @RequestMapping(value = "/selectOrderByCode")
    List<Order> selectOrderByCode(@RequestParam(name = "commodityCode") String commodityCode);

}
