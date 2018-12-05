package com.fms.report.model;

import com.fms.platform.common.BaseModel;
import org.hy.common.xml.annotation.Doc;





/**
 * 卡类别记录信息
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public class Role extends BaseModel
{
    private static final long serialVersionUID = 8859982104548101972L;

    
    /** 主键编号(系统采用消息流水号) */
    private String               sid;
    
    
    public String getSid() {
		return sid;
	}



	/** 卡类别id*/
	@Doc(index="01" ,info="卡类别id"  ,value={"report.Role.Retrieve.A001"})
	private String base_roleID; // 卡类别id
	
	/** 卡类别名称*/
	@Doc(index="02" ,info="卡类别名称"  ,value={"report.Role.Retrieve.A001"})
	private String Base_RoleName; // 卡类别名称



	public String getBase_roleID() {
		return base_roleID;
	}

	public void setBase_roleID(String base_roleID) {
		this.base_roleID = base_roleID;
	}

	public String getBase_RoleName() {
		return Base_RoleName;
	}

	public void setBase_RoleName(String base_RoleName) {
		Base_RoleName = base_RoleName;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}



    
    public Role()
    {
    }
    
    
    
    @Override
    public String getRowKey()
    { 
        return this.getSid();
    }


    
    
    
    
    

}
