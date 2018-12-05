package com.fms.avplan.deptExamReport.report;

import com.fms.platform.common.BaseModel;

/**
 * Created by David on 2018-08-08.
 */
public class OrderReport extends BaseModel {


    private String innerid;
    private String  rowtip;
    private String  ordercount;
    private String  shopcount;
    private String  maxdates;
    private String  mindates;
    private String  avgdates;
    private String  maxactdates;
    private String  minactdates;
    private String avgactdates;
    private String starttime2;  //sql语句中的限制 年份

    private int total;
    private int orderNum;

    public String getInnerid() {
        return innerid;
    }

    public void setInnerid(String innerid) {
        this.innerid = innerid;
    }

    public String getRowtip() {
        return rowtip;
    }

    public void setRowtip(String rowtip) {
        this.rowtip = rowtip;
    }

    public String getOrdercount() {
        return ordercount;
    }

    public void setOrdercount(String ordercount) {
        this.ordercount = ordercount;
    }

    public String getShopcount() {
        return shopcount;
    }

    public void setShopcount(String shopcount) {
        this.shopcount = shopcount;
    }

    public String getMaxdates() {
        return maxdates;
    }

    public void setMaxdates(String maxdates) {
        this.maxdates = maxdates;
    }

    public String getMindates() {
        return mindates;
    }

    public void setMindates(String mindates) {
        this.mindates = mindates;
    }

    public String getAvgdates() {
        return avgdates;
    }

    public void setAvgdates(String avgdates) {
        this.avgdates = avgdates;
    }

    public String getMaxactdates() {
        return maxactdates;
    }

    public void setMaxactdates(String maxactdates) {
        this.maxactdates = maxactdates;
    }

    public String getMinactdates() {
        return minactdates;
    }

    public void setMinactdates(String minactdates) {
        this.minactdates = minactdates;
    }

    public String getAvgactdates() {
        return avgactdates;
    }

    public void setAvgactdates(String avgactdates) {
        this.avgactdates = avgactdates;
    }

    public String getStarttime2() {
        return starttime2;
    }

    public void setStarttime2(String starttime2) {
        this.starttime2 = starttime2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    @Override
    public String getRowKey() {
        return getInnerid();
    }
}
