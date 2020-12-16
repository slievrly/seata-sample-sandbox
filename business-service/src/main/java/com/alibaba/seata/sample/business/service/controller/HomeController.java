package com.alibaba.seata.sample.business.service.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.seata.sample.business.service.service.IBusinessService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author slievrly
 */
@RestController
public class HomeController {

    @Autowired
    private IBusinessService businessService;

    @GetMapping(value = "/seata/feign", produces = "application/json")
    public String feign(int count, boolean mockException) {
        StringBuilder result = new StringBuilder();
        if (count <= 0) {
            return "expect: count>0 ";
        }
        try {
            businessService.reduceStockAndCreateOrder(count, mockException);
        } catch (Exception exx) {
            result.append("失败:").append(exx.getMessage()).append("事务发生回滚!");
        }
        if (result.length() == 0) {
            result.append("调用扣减库存生成订单服务成功!");
        }
        return result.toString();
    }

    @GetMapping(value = "/seata/check", produces = "application/json")
    public void check(HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=gb2312");
        response.getWriter().write(businessService.checkData());
    }
}
