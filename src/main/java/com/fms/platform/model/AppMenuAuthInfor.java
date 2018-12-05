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


    /** 菜单编号*/	@Doc(index="01" ,info="菜单编号"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private String menuCode; // 菜单编号		/** 图标ID*/	@Doc(index="02" ,info="图标ID"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private String drawableIdStr; // 图标ID
	
	
	/** 图标名称*/
    @Doc(index="02" ,info="图标名称"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
    private String iconName; // 图标名称
		/** 图标ID*/	@Doc(index="02" ,info="图标ID"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private int drawableId; // 图标ID		/** 菜单名称*/	@Doc(index="03" ,info="菜单名称"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private String menuName; // 菜单名称		/** 菜单activity类名全路径*/	@Doc(index="04" ,info="菜单activity类名全路径"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private String activityName; // 菜单activity类名全路径		/** 菜单显示顺序*/	@Doc(index="05" ,info="菜单显示顺序"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private Integer menuSort; // 菜单显示顺序		/** 菜单状态*/	@Doc(index="06" ,info="菜单状态"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private String menuStatus; // 菜单状态		/** 员工工号*/	@Doc(index="07" ,info="员工工号"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})	private String jobNumber; // 员工工号	
	/** 员工登录账号*/
	@Doc(index="08" ,info="员工登录账号"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
	private String userId;		public String getMenuCode() {	    return this.menuCode;	}	public void setMenuCode(String menuCode) {	    this.menuCode=menuCode;	}	public String getDrawableIdStr() {	    return this.drawableIdStr;	}	public void setDrawableIdStr(String drawableIdStr) {	    this.drawableIdStr=drawableIdStr;	}	public String getMenuName() {	    return this.menuName;	}	public void setMenuName(String menuName) {	    this.menuName=menuName;	}	public String getActivityName() {	    return this.activityName;	}	public void setActivityName(String activityName) {	    this.activityName=activityName;	}	public Integer getMenuSort() {	    return this.menuSort;	}	public void setMenuSort(Integer menuSort) {	    this.menuSort=menuSort;	}	public String getMenuStatus() {	    return this.menuStatus;	}	public void setMenuStatus(String menuStatus) {	    this.menuStatus=menuStatus;	}	public String getJobNumber() {	    return this.jobNumber;	}	public void setJobNumber(String jobNumber) {	    this.jobNumber=jobNumber;	}

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
