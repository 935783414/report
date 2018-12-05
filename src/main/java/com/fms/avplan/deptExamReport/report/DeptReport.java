package com.fms.avplan.deptExamReport.report;

import com.fms.platform.common.BaseModel;
import org.apache.commons.lang3.ArrayUtils;
import org.hy.common.Date;
import org.hy.common.MethodReflect;
import org.hy.common.xml.SerializableClass;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by Administrator on 2018/6/6.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class DeptReport extends BaseModel {

    private String innerid;
;
    private String maxReportData;//预警报表生成最大时间，用于返回前台展示
    private String warnInterval;//报表预警时间区间，用于返回前台展示

    public String getWarnInterval() {
        return warnInterval;
    }

    public void setWarnInterval(String warnInterval) {
        this.warnInterval = warnInterval;
    }

    public String getMaxReportData() {
        return maxReportData;
    }

    public void setMaxReportData(String maxReportData) {
        this.maxReportData = maxReportData;
    }

    private Integer warnDays;//查询条件 新增字段

    private String warn_level;//预警等级
    private double finish_percent;// 任务完成度

    public double getFinish_percent() {
        return finish_percent;
    }

    public void setFinish_percent(double finish_percent) {
        this.finish_percent = finish_percent;
    }

    public String getWarn_level() {
        return warn_level;
    }

    public void setWarn_level(String warn_level) {
        this.warn_level = warn_level;
    }

    public Integer getWarnDays() {
        return warnDays;
    }

    public void setWarnDays(Integer warnDays) {
        this.warnDays = warnDays;
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
    private String status;
    private String stime;       // 铸件下达第一次时间
    private String ftime;       // 铸件下达第二次时间
    private String countOrderNo;

    private String reason;      // 原因说明
    private String isExam;      // 是否考核
    // 采购增加字段
    private String putype;      // 采购类型
    private String planpercent;
    private String orderpercent;
    private String receivepercent;
    private String checkpercent;
    private String inpercent;
    // 事业部增加啊字段
    private String plan_no;
    private String zxjg;
    private String fj;
    private String required_qty;
    private String in_inv_count;
    // 铸造增加啊字段
    private String gytype;       // 工艺分类
    private String part_plan_no; // 路线单号
    private String planno;       // 计划月次
    private String itemcode;     // 图号编码
    private String itemname;     // 图号名称
    private String cl;           // 材质
    private String single;       // 单重
    private String zstime;       // 铸件下达第一次时间
    private String zftime;       // 铸件下达第二次时间
    private String znum;         // 铸件下达数量
    private String planqty;      // 计划数
    private String zjtcs;        // 投产数
    private String zjwgs;        // 完工数
    private String finishqty;    // 入库数
    private String pt_count;    // 配套数量
    private String last_pt_date;    // 配套时间


    private String detpCode;
    private String deptNameCons;

    private Date createtime;
    private String reportdate;
    private String unitname;
    private String appeal;

    private int total;
    private int orderNum;


    public String getPt_count() {
        return pt_count;
    }

    public void setPt_count(String pt_count) {
        this.pt_count = pt_count;
    }

    public String getLast_pt_date() {
        return last_pt_date;
    }

    public void setLast_pt_date(String last_pt_date) {
        this.last_pt_date = last_pt_date;
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

    public String getAppeal() {
        return appeal;
    }

    public void setAppeal(String appeal) {
        this.appeal = appeal;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getReportdate() {
        return reportdate;
    }

    public void setReportdate(String reportdate) {
        this.reportdate = reportdate;
    }

    public String getUnitname() {
        return unitname;
    }

    public void setUnitname(String unitname) {
        this.unitname = unitname;
    }

    public String getInnerid() {
        return innerid;
    }

    public void setInnerid(String innerid) {
        this.innerid = innerid;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getIsExam() {
        return isExam;
    }

    public void setIsExam(String isExam) {
        this.isExam = isExam;
    }

    public String getCountOrderNo() {
        return countOrderNo;
    }

    public void setCountOrderNo(String countOrderNo) {
        this.countOrderNo = countOrderNo;
    }

    public String getDetpCode() {
        return detpCode;
    }

    public void setDetpCode(String detpCode) {
        this.detpCode = detpCode;
    }

    public String getDeptNameCons() {
        return deptNameCons;
    }

    public void setDeptNameCons(String deptNameCons) {
        this.deptNameCons = deptNameCons;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public String getFtime() {
        return ftime;
    }

    public void setFtime(String ftime) {
        this.ftime = ftime;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPutype() {
        return putype;
    }

    public void setPutype(String putype) {
        this.putype = putype;
    }

    public String getPlanpercent() {
        return planpercent;
    }

    public void setPlanpercent(String planpercent) {
        this.planpercent = planpercent;
    }

    public String getOrderpercent() {
        return orderpercent;
    }

    public void setOrderpercent(String orderpercent) {
        this.orderpercent = orderpercent;
    }

    public String getReceivepercent() {
        return receivepercent;
    }

    public void setReceivepercent(String receivepercent) {
        this.receivepercent = receivepercent;
    }

    public String getCheckpercent() {
        return checkpercent;
    }

    public void setCheckpercent(String checkpercent) {
        this.checkpercent = checkpercent;
    }

    public String getInpercent() {
        return inpercent;
    }

    public void setInpercent(String inpercent) {
        this.inpercent = inpercent;
    }

    public String getPlan_no() {
        return plan_no;
    }

    public void setPlan_no(String plan_no) {
        this.plan_no = plan_no;
    }

    public String getZxjg() {
        return zxjg;
    }

    public void setZxjg(String zxjg) {
        this.zxjg = zxjg;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getRequired_qty() {
        return required_qty;
    }

    public void setRequired_qty(String required_qty) {
        this.required_qty = required_qty;
    }

    public String getIn_inv_count() {
        return in_inv_count;
    }

    public void setIn_inv_count(String in_inv_count) {
        this.in_inv_count = in_inv_count;
    }

    public String getGytype() {
        return gytype;
    }

    public void setGytype(String gytype) {
        this.gytype = gytype;
    }

    public String getPart_plan_no() {
        return part_plan_no;
    }

    public void setPart_plan_no(String part_plan_no) {
        this.part_plan_no = part_plan_no;
    }

    public String getPlanno() {
        return planno;
    }

    public void setPlanno(String planno) {
        this.planno = planno;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getCl() {
        return cl;
    }

    public void setCl(String cl) {
        this.cl = cl;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getZstime() {
        return zstime;
    }

    public void setZstime(String zstime) {
        this.zstime = zstime;
    }

    public String getZftime() {
        return zftime;
    }

    public void setZftime(String zftime) {
        this.zftime = zftime;
    }

    public String getZnum() {
        return znum;
    }

    public void setZnum(String znum) {
        this.znum = znum;
    }

    public String getPlanqty() {
        return planqty;
    }

    public void setPlanqty(String planqty) {
        this.planqty = planqty;
    }

    public String getZjtcs() {
        return zjtcs;
    }

    public void setZjtcs(String zjtcs) {
        this.zjtcs = zjtcs;
    }

    public String getZjwgs() {
        return zjwgs;
    }

    public void setZjwgs(String zjwgs) {
        this.zjwgs = zjwgs;
    }

    public String getFinishqty() {
        return finishqty;
    }

    public void setFinishqty(String finishqty) {
        this.finishqty = finishqty;
    }

    @Override
    public String getRowKey() {
        return getInnerid();
    }


    public Map<String, Object> toTreeMap(boolean i_IsRecursive) {
        Map<String, Object> v_Ret = new TreeMap<>();
        fieldToMap(i_IsRecursive, v_Ret);
        return v_Ret;
    }



    public Map<String, Object> toTreeMap(boolean i_IsRecursive,Comparator<String> comparator) {
        if(comparator==null)return  toTreeMap( i_IsRecursive);
        Map<String, Object> v_Ret = new TreeMap<>(comparator);
        fieldToMap(i_IsRecursive, v_Ret);
        return v_Ret;
    }


    public Map<String, Object> toTreeMap(Comparator<String> comparator) {
        if(comparator==null)return  toTreeMap( true);
        Map<String, Object> v_Ret = new TreeMap<>(comparator);
        fieldToMap(true, v_Ret);
        return v_Ret;
    }

    public static final String[] fields={"orderno","producttype","reason"};
    public static final String[] fieldShows={"合同号","商品类型","原因"};
    public Map<String, Object> toTreeMapFormatterKeys(Comparator<String> comparator) {
        if(comparator==null)return  toTreeMap( true);
        Map<String, Object> v_Ret = new TreeMap<>(comparator);
        for(int i = 0; i < this.gatPropertySize(); ++i) {
            Object v_Value = this.gatPropertyValue(i);
            if (v_Value != null) {
                if (MethodReflect.isExtendImplement(v_Value, SerializableClass.class)) {
                    int index = ArrayUtils.indexOf(fields,this.gatPropertyShortName(i));
                    if(index>-1){
                        v_Ret.put(fieldShows[index], ((SerializableClass)v_Value).toMap(true));
                    }
                } else {
                    int index = ArrayUtils.indexOf(fields,this.gatPropertyShortName(i));
                    if(index>-1){
                        v_Ret.put(fieldShows[index], v_Value);
                    }
                }
            }
        }
        return v_Ret;
    }


    @SuppressWarnings("ConstantConditions")
    private void fieldToMap(boolean i_IsRecursive, Map<String, Object> v_Ret) {
        for(int i = 0; i < this.gatPropertySize(); ++i) {
            Object v_Value = this.gatPropertyValue(i);
            if (v_Value != null) {
                if (i_IsRecursive && MethodReflect.isExtendImplement(v_Value, SerializableClass.class)) {
                    v_Ret.put(this.gatPropertyShortName(i), ((SerializableClass)v_Value).toMap(i_IsRecursive));
                } else {
                    v_Ret.put(this.gatPropertyShortName(i), v_Value);
                }
            }// end ifnull
        }// end for
    }// end method
}
