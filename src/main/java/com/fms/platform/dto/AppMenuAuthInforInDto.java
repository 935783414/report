package com.fms.platform.dto;

import org.hy.common.xml.annotation.Doc;

import com.fms.platform.common.BaseModel;





/**
 * 手机App应用端菜单鉴权接口输入参数定义
 * 
 * 输入参数的定义实体
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0
 */
public class AppMenuAuthInforInDto extends BaseModel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5130124508014467267L;
   
	/** 员工工号*/
    @Doc(index="01" ,info="员工工号"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
    private String jobNumber; // 员工工号

    /** 员工登录账号*/
    @Doc(index="02" ,info="员工登录账号"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
    private String userId;
    @Override
    public String getRowKey()
    {
        return null;
    }

    public String getJobNumber()
    {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber)
    {
        this.jobNumber = jobNumber;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    
    
}
