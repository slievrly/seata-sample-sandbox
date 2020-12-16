package com.alibaba.seata.sample.storage.service.mapper;

import java.util.List;

import com.alibaba.seata.sample.common.entity.Storage;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author slievrly
 */
@Mapper
public interface StorageMapper {

    Storage selectById(@Param("id") Integer id);

    List<Storage> selectStockByCode(@Param("commodityCode") String commodityCode);

    int updateStockByCode(@Param("commodityCode") String commodityCode, @Param("count") int count);

    int insert(Storage storage);
}