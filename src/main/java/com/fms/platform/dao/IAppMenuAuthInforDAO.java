package com.fms.platform.dao; 

import java.util.List;

import com.fms.platform.common.BaseModel;
import com.fms.platform.model.AppMenuAuthInfor;





/**
 * 手机App应用端菜单鉴权接口业务操作DAO
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0
 */
public interface IAppMenuAuthInforDAO 
{
    
    /**
     * 根据传入的条件进行查询
     * 获得数据列表
     * （非主键对象等条件）
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<AppMenuAuthInfor>     查询列表
     */
	public List<AppMenuAuthInfor> queryAppMenuAuthInforList(BaseModel i_Params);
	
    /**
     * 根据传入的条件进行查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Integer  
     */
	public Integer queryAppMenuAuthInforListCount(BaseModel i_Params);
    
    /**
     * 查询单个对象
     * 获得数据列表
     * （非主键对象等条件）
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      AppMenuAuthInfor 单个对象返回
     */
	public AppMenuAuthInfor queryAppMenuAuthInfor(BaseModel i_Params);
	
    /**
     * 根据传入的条件进行分页查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<AppMenuAuthInfor>  
     */
	public List<AppMenuAuthInfor> queryAppMenuAuthInforListByPage(BaseModel i_Params);
	
	
	
    /**
     * 手机App应用端菜单鉴权接口单表插入
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数  
     */
	public Integer saveAppMenuAuthInfor(BaseModel i_Params);
	
	
	
    /**
     *  手机App应用端菜单鉴权接口单表更新
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	public Integer updateAppMenuAuthInfor(BaseModel i_Params);
	
	
    /**
     *  手机App应用端菜单鉴权接口单表删除
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	public Integer deleteAppMenuAuthInfor(BaseModel i_Params);
}
