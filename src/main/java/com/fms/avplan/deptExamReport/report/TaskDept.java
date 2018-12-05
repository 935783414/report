/**
 * 
 */
package com.fms.avplan.deptExamReport.report;

import org.hy.common.Date;

import com.fms.platform.common.BaseModel;

/**
 * @author David
 * @createtime 2018年7月4日
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class TaskDept  extends BaseModel{
	
    private String innerid;
   
    private String countOrderNo;
    private String suborderno;
	private String orderno;
	private String  name;
	private String ordernoname;
	private String ownername;
	private String planstarttime;
	private String planfinishtime;
	private String duration;
	private String actualstarttime;
	private String actualfinishtime;
	private String actualduration;
	private String overtime;
	private String acttimeout;
	
	private String detpCode; 
	private String deptNameCons;

	    
    private int total;
	private int orderNum;

 
	    
	
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

	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
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

	public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }
	public String getCountOrderNo() {
		return countOrderNo;
	}

	public void setCountOrderNo(String countOrderNo) {
		this.countOrderNo = countOrderNo;
	}

	public String getSuborderno() {
		return suborderno;
	}

	public void setSuborderno(String suborderno) {
		this.suborderno = suborderno;
	}

	public String getInnerid() {
		return innerid;
	}

	public void setInnerid(String innerid) {
		this.innerid = innerid;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getActualstarttime() {
		return actualstarttime;
	}

	public void setActualstarttime(String actualstarttime) {
		this.actualstarttime = actualstarttime;
	}

	public String getActualfinishtime() {
		return actualfinishtime;
	}

	public void setActualfinishtime(String actualfinishtime) {
		this.actualfinishtime = actualfinishtime;
	}

	public String getActualduration() {
		return actualduration;
	}

	public void setActualduration(String actualduration) {
		this.actualduration = actualduration;
	}

	public String getOvertime() {
		return overtime;
	}

	public String getActtimeout() {
		return acttimeout;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public void setActtimeout(String acttimeout) {
		this.acttimeout = acttimeout;
	}

	public String getOrdernoname() {
	    return ordernoname;
	}

	public void setOrdernoname(String ordernoname) {
		this.ordernoname = ordernoname;
	}

	@Override
	public String getRowKey() {
		
		return getInnerid();
	}

}
