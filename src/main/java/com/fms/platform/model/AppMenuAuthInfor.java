package com.fms.platform.model;

import com.fms.platform.common.BaseModel;
import org.hy.common.xml.annotation.Doc;





/**
 * 手机App应用端菜单鉴权接口
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0
 */
public class AppMenuAuthInfor extends BaseModel
{
    private static final long serialVersionUID = 8859982104548101972L;

    
    /** 主键编号(系统采用消息流水号) */
    private String               sid;
    
    
    public String getSid() {
		return sid;
	}


    /** 菜单编号*/
	
	
	/** 图标名称*/
    @Doc(index="02" ,info="图标名称"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
    private String iconName; // 图标名称
	
	/** 员工登录账号*/
	@Doc(index="08" ,info="员工登录账号"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
	private String userId;

	public void setSid(String sid) {
		this.sid = sid;
	}



    
    public int getDrawableId()
    {
        return drawableId;
    }
    public void setDrawableId(int drawableId)
    {
        this.drawableId = drawableId;
    }
    
    
    public String getUserId()
    {
        return userId;
    }
    public void setUserId(String userId)
    {
        this.userId = userId;
    }
    public AppMenuAuthInfor()
    {
    }
    
    
    
    public String getIconName()
    {
        return iconName;
    }
    public void setIconName(String iconName)
    {
        this.iconName = iconName;
    }
    @Override
    public String getRowKey()
    { 
        return this.getSid();
    }


    
    
    
    
    

}