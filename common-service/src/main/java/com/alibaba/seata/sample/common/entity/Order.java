package com.alibaba.seata.sample.common.entity;

/**
 * @author slievrly
 */
public class Order {
    private Integer id;

    private String userId;

    private String commodityCode;

    private Integer count;

    private Integer money;

    public Integer getId() {
        return id;
    }

    public Order setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Order setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getCommodityCode() {
        return commodityCode;
    }

    public Order setCommodityCode(String commodityCode) {
        this.commodityCode = commodityCode;
        return this;
    }

    public Integer getCount() {
        return count;
    }

    public Order setCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getMoney() {
        return money;
    }

    public Order setMoney(Integer money) {
        this.money = money;
        return this;
    }
}
