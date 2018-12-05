package com.fms.report.model;

import com.fms.platform.common.BaseModel;
import org.hy.common.xml.annotation.Doc;





/**
 * 考勤部门记录信息
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public class Department extends BaseModel
{
    private static final long serialVersionUID = 8859982104548101972L;

    
    /** 主键编号(系统采用消息流水号) */
    private String               sid;
    
    
    public String getSid() {
		return sid;
	}


    /** 部门id*/
	@Doc(index="01" ,info="部门id"  ,value={"report.Department.Retrieve.A001"})
	private String Base_GroupID; // 部门id

	/** 部门名称*/
	@Doc(index="02" ,info="部门名称"  ,value={"report.Department.Retrieve.A001"})
	private String Base_GroupName; // 部门名称
	
	/** 部门编号*/
	@Doc(index="03" ,info="部门编号"  ,value={"report.Department.Retrieve.A001"})
	private String Base_AuthID; // 部门编号


	public String getBase_GroupID() {
		return Base_GroupID;
	}

	public void setBase_GroupID(String base_GroupID) {
		Base_GroupID = base_GroupID;
	}

	public String getBase_GroupName() {
		return Base_GroupName;
	}

	public void setBase_GroupName(String base_GroupName) {
		Base_GroupName = base_GroupName;
	}

	public String getBase_AuthID() {
		return Base_AuthID;
	}

	public void setBase_AuthID(String base_AuthID) {
		Base_AuthID = base_AuthID;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}



    
    public Department()
    {
    }
    
    
    
    @Override
    public String getRowKey()
    { 
        return this.getSid();
    }


    
    
    
    
    

}
