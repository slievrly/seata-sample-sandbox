package com.alibaba.seata.sample.storage.service.controller;

import java.util.List;

import com.alibaba.seata.sample.common.Constants;
import com.alibaba.seata.sample.common.entity.Storage;
import com.alibaba.seata.sample.storage.service.service.IStorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author slievrly
 */
@RestController
public class StorageController {
    @Autowired
    IStorageService storageService;

    @RequestMapping("/reduceStock")
    public String reduceStock(@RequestParam(name = "commodityCode") String commodityCode,
                              @RequestParam(name = "count") Integer count) {
        return storageService.reduceStock(commodityCode, count) == 1 ? Constants.SUCCESS_RESPONSE
            : Constants.FAIL_RESPONSE;
    }

    @RequestMapping("/initStock")
    public int initStock(@RequestParam(name = "commodityCode") String commodityCode,
                         @RequestParam(name = "count") Integer count) {
        return storageService.initStock(commodityCode, count);
    }

    @RequestMapping("/selectStockByCode")
    public List<Storage> selectStockByCode(@RequestParam(name = "commodityCode") String commodityCode) {
        return storageService.selectStockByCode(commodityCode);
    }
}
