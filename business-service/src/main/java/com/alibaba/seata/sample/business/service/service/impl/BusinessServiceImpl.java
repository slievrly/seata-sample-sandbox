package com.alibaba.seata.sample.business.service.service.impl;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.alibaba.seata.sample.business.service.feign.IOrderService;
import com.alibaba.seata.sample.business.service.feign.IStorageService;
import com.alibaba.seata.sample.business.service.service.IBusinessService;
import com.alibaba.seata.sample.common.entity.Order;
import com.alibaba.seata.sample.common.entity.Storage;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.alibaba.seata.sample.common.Constants.SUCCESS_RESPONSE;

/**
 * @author slievrly
 */
@Service
public class BusinessServiceImpl implements IBusinessService {

    private static final int INIT_COUNT = 100;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStorageService storageService;

    private String opCommodityCode;

    @PostConstruct
    public void init() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            try {
                ThreadLocalRandom random = ThreadLocalRandom.current();
                String commodityCode = "C" + random.nextInt(999999);
                storageService.initStock(commodityCode, INIT_COUNT);
                opCommodityCode = commodityCode;
                break;
            } catch (Exception ignore) {
                ignore.printStackTrace();
                TimeUnit.MILLISECONDS.sleep(2);
            }
        }
    }

    @Override
    @GlobalTransactional(timeoutMills = 300000, name = "spring-cloud-alibaba-seata")
    public void reduceStockAndCreateOrder(int count, boolean mockException) {
        String xid = RootContext.getXID();
        System.out.println("business-service 当前正在执行的事务xid:" + xid);
        String result = storageService.reduceStock(opCommodityCode, count);

        if (!SUCCESS_RESPONSE.equals(result)) {
            throw new RuntimeException("库存扣减失败! xid:" + xid);
        }

        Order order = new Order().setCommodityCode(opCommodityCode).setCount(count).setMoney(count * 100).setUserId(
            "system");

        result = orderService.createOrder(order);

        if (!SUCCESS_RESPONSE.equals(result)) {
            throw new RuntimeException("创建订单失败! xid:" + xid);
        }
        if (mockException) {
            throw new RuntimeException("模拟用户业务异常! xid:" + xid);
        }

    }

    @Override
    public String checkData() {
        StringBuilder sb = new StringBuilder();
        List<Order> orders = orderService.selectOrderByCode(opCommodityCode);
        List<Storage> storages = storageService.selectStockByCode(opCommodityCode);
        int orderTotal = 0;
        int storageTotal = 0;
        sb.append("<strong>当前您操作的CommodityCode是: <font color=\"#FF0000\">" + opCommodityCode + "</font></strong>");
        sb.append("<hr>");
        sb.append("<strong>数据表:</strong>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<strong>order_tbl:</strong>");
        sb.append("<table border=\"2\">");
        sb.append("<tr>");
        sb.append("<td>Id</td><td>user_id</td><td>commodity_code</td><td>count</td><td>money</td>");
        sb.append("</tr>");
        for (Order order : orders) {
            sb.append("<tr>");
            sb.append("<td>" + order.getId() + "</td><td>" + order.getUserId() + "</td><td>" + order.getCommodityCode()
                + "</td><td>" + order.getCount() + "</td><td>" + order.getMoney() + "</td>");
            sb.append("</tr>");
            orderTotal += order.getCount();
        }
        sb.append("</table>");

        sb.append("<br/>");

        sb.append("<strong>storage_tbl:</strong>");
        sb.append("<table border=\"2\">");
        sb.append("<tr>");
        sb.append("<td>Id</td><td>commodity_code</td><td>count</td>");
        sb.append("</tr>");
        for (Storage storage : storages) {
            sb.append("<tr>");
            sb.append(
                "<td>" + storage.getId() + "</td><td>" + storage.getCommodityCode() + "</td><td>" + storage.getCount()
                    + "</td>");
            sb.append("</tr>");
            storageTotal += storage.getCount();
        }
        sb.append("</table>");
        sb.append("<hr>");
        sb.append("<strong>数据一致性校验:</strong>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<strong>调用前库存:</strong>");
        sb.append("<br/>");
        sb.append("初始化库存(storage_tbl): <strong><font color=\"#FF0000\">" + INIT_COUNT + "</font></strong>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append("<strong>调用后库存:</strong>");
        sb.append("<br/>");
        sb.append("已卖出库存数量(order_tbl): <strong><font color=\"#FF0000\">" + orderTotal + "</font></strong>");
        sb.append("<br/>");
        sb.append("剩余库存(storage_tbl): <strong><font color=\"#FF0000\">" + storageTotal + "</font></strong>");
        sb.append("<br/>");
        sb.append("<br/>");
        sb.append(
            "<strong>结论:</strong> 库存量调用前后 <strong><font color=\"#FF0000\">" + (INIT_COUNT == orderTotal + storageTotal
                ? "一致" : "不一致") + "</font></strong>");
        sb.append("<hr>");
        return sb.toString();
    }
}
