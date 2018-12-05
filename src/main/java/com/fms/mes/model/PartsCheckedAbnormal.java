package com.fms.mes.model;

import com.fms.platform.common.BaseModel;

/**
 * @version [版本号, ]
 * @Author: zy
 * @Description:
 * @Date: Created in 2018/7/23 17:12.
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PartsCheckedAbnormal extends BaseModel {

    private int id;								//主键
    private String departCode;					//部门编号
    private String planCode;					//路线单号
    private String itemCode;					//零件代号
    private int processNo;						//工序号
    private String processName;					//工序名称
    private String endFlag;						//完工标识
    private String saveTime;					//保存时间
    private String checkPerson;					//检验员
    private String batchCheckFlag;				//分批检测标识
    private int planQTY;						//计划数量
    private int receiveQTY;						//领用数量
    private int samplingQty;					//抽检数
    private int batchCheckQty;					//主表中分批检测数量
    private int qualifiedQty;					//合格数量
    private int unqualifiedQty;					//不合格数量
    private int samplingQtySum;					//分批表抽检数量和
    private int batchCheckQtySum;				//分批表分批检测数量和
    private int qualifiedQtySum;				//分批表合格数量和
    private int unqualifiedQtySum;				//分批表不合格数量和
    private String qtyDifferent;				//检测数量!=合格数+不合格数
    private String qtyDifferentInfo;			//数量不一致情况信息
    private String receiveDifferentChecked;		//领用数量分批数量不一致
    private String receiveDifferentCheckedInfo;	//领用数量分批数量不一致信息
    private String receiveDifferentPlan;		//领用数量计划数量不一致
    private String receiveDifferentPlanInfo;	//领用数量计划数量不一致信息
    private String completeInfo;	            //完成情况
    private String startDate;                   //起始日期
    private String endDate;                     //终止日期

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getProcessNo() {
        return processNo;
    }

    public void setProcessNo(int processNo) {
        this.processNo = processNo;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getEndFlag() {
        return endFlag;
    }

    public void setEndFlag(String endFlag) {
        this.endFlag = endFlag;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public String getCheckPerson() {
        return checkPerson;
    }

    public void setCheckPerson(String checkPerson) {
        this.checkPerson = checkPerson;
    }

    public String getBatchCheckFlag() {
        return batchCheckFlag;
    }

    public void setBatchCheckFlag(String batchCheckFlag) {
        this.batchCheckFlag = batchCheckFlag;
    }

    public int getPlanQTY() {
        return planQTY;
    }

    public void setPlanQTY(int planQTY) {
        this.planQTY = planQTY;
    }

    public int getReceiveQTY() {
        return receiveQTY;
    }

    public void setReceiveQTY(int receiveQTY) {
        this.receiveQTY = receiveQTY;
    }

    public int getSamplingQty() {
        return samplingQty;
    }

    public void setSamplingQty(int samplingQty) {
        this.samplingQty = samplingQty;
    }

    public int getBatchCheckQty() {
        return batchCheckQty;
    }

    public void setBatchCheckQty(int batchCheckQty) {
        this.batchCheckQty = batchCheckQty;
    }

    public int getQualifiedQty() {
        return qualifiedQty;
    }

    public void setQualifiedQty(int qualifiedQty) {
        this.qualifiedQty = qualifiedQty;
    }

    public int getUnqualifiedQty() {
        return unqualifiedQty;
    }

    public void setUnqualifiedQty(int unqualifiedQty) {
        this.unqualifiedQty = unqualifiedQty;
    }

    public int getSamplingQtySum() {
        return samplingQtySum;
    }

    public void setSamplingQtySum(int samplingQtySum) {
        this.samplingQtySum = samplingQtySum;
    }

    public int getBatchCheckQtySum() {
        return batchCheckQtySum;
    }

    public void setBatchCheckQtySum(int batchCheckQtySum) {
        this.batchCheckQtySum = batchCheckQtySum;
    }

    public int getQualifiedQtySum() {
        return qualifiedQtySum;
    }

    public void setQualifiedQtySum(int qualifiedQtySum) {
        this.qualifiedQtySum = qualifiedQtySum;
    }

    public int getUnqualifiedQtySum() {
        return unqualifiedQtySum;
    }

    public void setUnqualifiedQtySum(int unqualifiedQtySum) {
        this.unqualifiedQtySum = unqualifiedQtySum;
    }

    public String getQtyDifferent() {
        return qtyDifferent;
    }

    public void setQtyDifferent(String qtyDifferent) {
        this.qtyDifferent = qtyDifferent;
    }

    public String getQtyDifferentInfo() {
        return qtyDifferentInfo;
    }

    public void setQtyDifferentInfo(String qtyDifferentInfo) {
        this.qtyDifferentInfo = qtyDifferentInfo;
    }

    public String getReceiveDifferentChecked() {
        return receiveDifferentChecked;
    }

    public void setReceiveDifferentChecked(String receiveDifferentChecked) {
        this.receiveDifferentChecked = receiveDifferentChecked;
    }

    public String getReceiveDifferentCheckedInfo() {
        return receiveDifferentCheckedInfo;
    }

    public void setReceiveDifferentCheckedInfo(String receiveDifferentCheckedInfo) {
        this.receiveDifferentCheckedInfo = receiveDifferentCheckedInfo;
    }

    public String getReceiveDifferentPlan() {
        return receiveDifferentPlan;
    }

    public void setReceiveDifferentPlan(String receiveDifferentPlan) {
        this.receiveDifferentPlan = receiveDifferentPlan;
    }

    public String getReceiveDifferentPlanInfo() {
        return receiveDifferentPlanInfo;
    }

    public void setReceiveDifferentPlanInfo(String receiveDifferentPlanInfo) {
        this.receiveDifferentPlanInfo = receiveDifferentPlanInfo;
    }

    public String getCompleteInfo() {
        return completeInfo;
    }

    public void setCompleteInfo(String completeInfo) {
        this.completeInfo = completeInfo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String getRowKey() {
        return null;
    }
}
