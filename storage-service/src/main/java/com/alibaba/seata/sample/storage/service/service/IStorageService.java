package com.alibaba.seata.sample.storage.service.service;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Storage;

/**
 * @author slievrly
 */
public interface IStorageService {

    int reduceStock(String commodityCode, int count);

    int initStock(String commodityCode, int count);

    List<Storage> selectStockByCode(String commodityCode);

}
