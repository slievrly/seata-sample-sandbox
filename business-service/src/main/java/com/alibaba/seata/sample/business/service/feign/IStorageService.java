package com.alibaba.seata.sample.business.service.feign;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Storage;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author slievrly
 */
@FeignClient(name = "storage-service",url = "http://127.0.0.1:8083")
public interface IStorageService {

    @RequestMapping("/reduceStock")
    String reduceStock(@RequestParam(name = "commodityCode") String commodityCode,
                       @RequestParam(name = "count") Integer count);

    @RequestMapping("/initStock")
    int initStock(@RequestParam(name = "commodityCode") String commodityCode,
                  @RequestParam(name = "count") Integer count);

    @RequestMapping(value = "/selectStockByCode")
    List<Storage> selectStockByCode(@RequestParam(name = "commodityCode") String commodityCode);
}
