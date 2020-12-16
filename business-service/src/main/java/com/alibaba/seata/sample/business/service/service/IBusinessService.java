package com.alibaba.seata.sample.business.service.service;

/**
 * @author slievrly
 */
public interface IBusinessService {

    void reduceStockAndCreateOrder(int count, boolean mockException);

    String checkData();
}
