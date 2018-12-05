package com.fms.avplan.deptExamReport.model;

public class PurchaseDeptReportModel1 {
    private String item_code;
    private String item_name;
    private String part_plan_no;
    private Double order_qty;
    private Double plan_qty1;
    private Double plan_qty;
    private Double available_stock;
    private String cgrk_date;
    private String flag;
    private String lastindate;
    private Double lastinqty;
    private Double currentstocks;
    private String planstarttime;
    private String planfinishtime;

    public String getPlanstarttime() {
        return planstarttime;
    }

    public void setPlanstarttime(String planstarttime) {
        this.planstarttime = planstarttime;
    }

    public String getPlanfinishtime() {
        return planfinishtime;
    }

    public void setPlanfinishtime(String planfinishtime) {
        this.planfinishtime = planfinishtime;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPart_plan_no() {
        return part_plan_no;
    }

    public void setPart_plan_no(String part_plan_no) {
        this.part_plan_no = part_plan_no;
    }

    public Double getOrder_qty() {
        return order_qty;
    }

    public void setOrder_qty(Double order_qty) {
        this.order_qty = order_qty;
    }

    public Double getPlan_qty1() {
        return plan_qty1;
    }

    public void setPlan_qty1(Double plan_qty1) {
        this.plan_qty1 = plan_qty1;
    }

    public Double getPlan_qty() {
        return plan_qty;
    }

    public void setPlan_qty(Double plan_qty) {
        this.plan_qty = plan_qty;
    }

    public Double getAvailable_stock() {
        return available_stock;
    }

    public void setAvailable_stock(Double available_stock) {
        this.available_stock = available_stock;
    }

    public String getCgrk_date() {
        return cgrk_date;
    }

    public void setCgrk_date(String cgrk_date) {
        this.cgrk_date = cgrk_date;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getLastindate() {
        return lastindate;
    }

    public void setLastindate(String lastindate) {
        this.lastindate = lastindate;
    }

    public Double getLastinqty() {
        return lastinqty;
    }

    public void setLastinqty(Double lastinqty) {
        this.lastinqty = lastinqty;
    }

    public Double getCurrentstocks() {
        return currentstocks;
    }

    public void setCurrentstocks(Double currentstocks) {
        this.currentstocks = currentstocks;
    }
}
