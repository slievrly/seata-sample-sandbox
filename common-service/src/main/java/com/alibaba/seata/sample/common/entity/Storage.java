package com.alibaba.seata.sample.common.entity;

/**
 * @author slievrly
 */
public class Storage {
    private Integer id;

    private String commodityCode;

    private Integer count;

    public Integer getId() {
        return id;
    }

    public Storage setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public Storage setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Storage setCount(Integer count) {
        this.count = count;
        return this;
    }
}
