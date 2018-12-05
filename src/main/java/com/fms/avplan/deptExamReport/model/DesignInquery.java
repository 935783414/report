package com.fms.avplan.deptExamReport.model;

import com.fms.platform.common.BaseModel;
import org.hy.common.xml.annotation.Doc;


/**
 * AVPLAN工时定额未完成和材料定额未完成查询接口
 *
 * @author 穆军强
 * @version v1.0
 * 创建时间 2017-06-05
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DesignInquery extends BaseModel {
    private static final long serialVersionUID = 8859982104548101972L;

    private String dayplatform;    //台
    private String dayshares; //份

    private String weekplatform;    //台
    private String weekshares; //份

    private String monthplatform;    //台
    private String monthshares; //份
    /**
     * 主键编号(系统采用消息流水号)
     */
    private String sid;
    //本月第一天和本月最后一天
    private String mfirst;
    private String mlast;

    //本周第一天和本周最后一天
    private String wfirst;
    private String wlast;

    public String getSid() {
        return sid;
    }

    private int numbers;

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }

    private String status;
    /**
     * 部件类型
     */
    @Doc(index = "01", info = "部件类型", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String proCategory; // 部件类型

    /**
     * 计划月次
     */
    @Doc(index = "02", info = "计划月", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String weekNo; // 计划月

    /**
     * 特品号
     */
    @Doc(index = "03", info = "特品号", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String specialProCode; // 特品号

    /**
     * 合同号
     */
    @Doc(index = "04", info = "合同号", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String planNo; // 合同号

    /**
     * 产品类型
     */
    @Doc(index = "05", info = "产品类型", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String proType; // 产品类型

    /**
     * 台数
     */
    @Doc(index = "06", info = "台数", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String proCnt; // 台数

    /**
     * 交货日期
     */
    @Doc(index = "07", info = "交货日期", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String submitDate; // 交货日期

    /**
     * 商品计划排产日期
     */
    @Doc(index = "08", info = "商品计划排产日期", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String proPlanManufacture; // 商品计划排产日期

    /**
     * 特品计划提交时间
     */
    @Doc(index = "09", info = "特品计划提交时间", value = {"aavplan.DesignInquery.Retrieve.A001"})
    private String specialProPart; // 特品计划提交时间

    /**
     * 特品计划下达时间
     */
    @Doc(index = "10", info = "特品计划下达时间", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String specialProReq; // 特品计划下达时间

    /**
     * 销售特品但递交技术时间
     */
    @Doc(index = "11", info = "销售特品单递交技术时间", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String sailSpecialProSubmitTechnoDate; // 销售特品但递交技术时间

    /**
     * 总数量
     */
    @Doc(index = "08", info = "50为字符", value = {"avplan.Materialquota.Retrieve.A001"})
    private String displaynumber; // 总数量
    /**
     * 销售特品但递交技术时间
     */
    @Doc(index = "12", info = "特品完成状态", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String sailSpecialState; // 完成状态

    /**
     * 销售特品但递交技术时间
     */
    @Doc(index = "13", info = "特品完成时间", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String sailSpecialDate; // 特品完成时间
    /**
     * 计划月次
     */
    @Doc(index = "14", info = "计划月次", value = {"avplan.DesignInquery.Retrieve.A001"})
    private String planMonth; // 计划月次

    public String getMfirst() {
        return mfirst;
    }

    public void setMfirst(String mfirst) {
        this.mfirst = mfirst;
    }

    public String getMlast() {
        return mlast;
    }

    public void setMlast(String mlast) {
        this.mlast = mlast;
    }

    public String getWfirst() {
        return wfirst;
    }

    public void setWfirst(String wfirst) {
        this.wfirst = wfirst;
    }

    public String getWlast() {
        return wlast;
    }

    public void setWlast(String wlast) {
        this.wlast = wlast;
    }

    private String orderno;
    private String specailproductcode;
    private String suborderno;
    private String productclass;
    private String producttype;
    private String departname;
    private String planstarttime;
    private String planfinishtime;
    private String flag;

    public String getDayplatform() {
        return dayplatform;
    }

    public void setDayplatform(String dayplatform) {
        this.dayplatform = dayplatform;
    }

    public String getDayshares() {
        return dayshares;
    }

    public void setDayshares(String dayshares) {
        this.dayshares = dayshares;
    }

    public String getWeekplatform() {
        return weekplatform;
    }

    public void setWeekplatform(String weekplatform) {
        this.weekplatform = weekplatform;
    }

    public String getWeekshares() {
        return weekshares;
    }

    public void setWeekshares(String weekshares) {
        this.weekshares = weekshares;
    }

    public String getMonthplatform() {
        return monthplatform;
    }

    public void setMonthplatform(String monthplatform) {
        this.monthplatform = monthplatform;
    }

    public String getMonthshares() {
        return monthshares;
    }

    public void setMonthshares(String monthshares) {
        this.monthshares = monthshares;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getSpecailproductcode() {
        return specailproductcode;
    }

    public void setSpecailproductcode(String specailproductcode) {
        this.specailproductcode = specailproductcode;
    }

    public String getSuborderno() {
        return suborderno;
    }

    public void setSuborderno(String suborderno) {
        this.suborderno = suborderno;
    }

    public String getProductclass() {
        return productclass;
    }

    public void setProductclass(String productclass) {
        this.productclass = productclass;
    }

    public String getProducttype() {
        return producttype;
    }

    public void setProducttype(String producttype) {
        this.producttype = producttype;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPlanMonth() {
        return planMonth;
    }

    public void setPlanMonth(String planMonth) {
        this.planMonth = planMonth;
    }

    public String getProCategory() {
        return proCategory;
    }

    public void setProCategory(String proCategory) {
        this.proCategory = proCategory;
    }

    public String getProType() {
        return proType;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public String getProCnt() {
        return proCnt;
    }

    public void setProCnt(String proCnt) {
        this.proCnt = proCnt;
    }

    public String getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(String submitDate) {
        this.submitDate = submitDate;
    }

    public String getProPlanManufacture() {
        return proPlanManufacture;
    }

    public void setProPlanManufacture(String proPlanManufacture) {
        this.proPlanManufacture = proPlanManufacture;
    }

    public String getSailSpecialProSubmitTechnoDate() {
        return sailSpecialProSubmitTechnoDate;
    }

    public void setSailSpecialProSubmitTechnoDate(String sailSpecialProSubmitTechnoDate) {
        this.sailSpecialProSubmitTechnoDate = sailSpecialProSubmitTechnoDate;
    }

    public String getPlanNo() {
        return this.planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }


    public void setSid(String sid) {
        this.sid = sid;
    }


    public DesignInquery() {
    }

    public String getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(String weekNo) {
        this.weekNo = weekNo;
    }

    public String getSpecialProCode() {
        return specialProCode;
    }

    public void setSpecialProCode(String specialProCode) {
        this.specialProCode = specialProCode;
    }

    public String getSpecialProPart() {
        return specialProPart;
    }

    public void setSpecialProPart(String specialProPart) {
        this.specialProPart = specialProPart;
    }

    public String getSpecialProReq() {
        return specialProReq;
    }

    public void setSpecialProReq(String specialProReq) {
        this.specialProReq = specialProReq;
    }

    public String getSailSpecialState() {
        return sailSpecialState;
    }

    public void setSailSpecialState(String sailSpecialState) {
        this.sailSpecialState = sailSpecialState;
    }

    public String getSailSpecialDate() {
        return sailSpecialDate;
    }

    public void setSailSpecialDate(String sailSpecialDate) {
        this.sailSpecialDate = sailSpecialDate;
    }

    public String getDisplaynumber() {
        return displaynumber;
    }

    public void setDisplaynumber(String displaynumber) {
        this.displaynumber = displaynumber;
    }

    public static long getSerialVersionUID() {

        return serialVersionUID;
    }

    @Override
    public String getRowKey() {
        return this.getSid();
    }


}
