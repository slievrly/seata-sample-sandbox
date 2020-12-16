package com.alibaba.seata.sample.storage.service.service.impl;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Storage;
import com.alibaba.seata.sample.storage.service.mapper.StorageMapper;
import com.alibaba.seata.sample.storage.service.service.IStorageService;

import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author slievrly
 */
@Service
public class StorageServiceImpl implements IStorageService {

    @Autowired
    private StorageMapper storageMapper;

    @Override
    public int reduceStock(String commodityCode, int count) {
        System.out.println("storage-service 当前正在执行的事务xid:" + RootContext.getXID());
        return storageMapper.updateStockByCode(commodityCode, count);
    }

    @Override
    public int initStock(String commodityCode, int count) {
        return storageMapper.insert(new Storage().setCommodityCode(commodityCode).setCount(count));
    }

    @Override
    public List<Storage> selectStockByCode(String commodityCode) {
        return storageMapper.selectStockByCode(commodityCode);
    }
}
