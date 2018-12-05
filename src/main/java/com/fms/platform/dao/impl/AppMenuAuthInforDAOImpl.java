package com.fms.platform.dao.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.platform.dao.IAppMenuAuthInforDAO;
import com.fms.platform.model.AppMenuAuthInfor;
import com.fms.platform.common.BaseDAO;
import com.fms.platform.common.BaseModel;





/**
 * 手机App应用端菜单鉴权接口业务操作DAO
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0
 */
@Xjava(id="platformAppMenuAuthInforDao")
public class AppMenuAuthInforDAOImpl extends BaseDAO<AppMenuAuthInfor> implements IAppMenuAuthInforDAO
{

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<AppMenuAuthInfor>     查询列表
     */
	@Override
	public List<AppMenuAuthInfor> queryAppMenuAuthInforList(BaseModel i_Params) {
		List<AppMenuAuthInfor> appMenuAuthInforlist = super.queryRecords("XSQL_platform_AppMenuAuthInfor_QUERYLIST", i_Params);
		return appMenuAuthInforlist;
	}
	


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
	@Override
	public Integer queryAppMenuAuthInforListCount(BaseModel i_Params) {
		
		// 返回查询数量 自定义查询数量的sql标识
		return super.QueryRecordsCount("XSQL_platform_AppMenuAuthInfor_QueryList_Count", i_Params);
	}

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<AppMenuAuthInfor>     查询列表
     */
	@Override
	public AppMenuAuthInfor queryAppMenuAuthInfor(BaseModel i_Params) {
		List<AppMenuAuthInfor> appMenuAuthInforlist = super.queryRecords("XSQL_platform_AppMenuAuthInfor_QUERYLIST", i_Params);
		return appMenuAuthInforlist == null || appMenuAuthInforlist.size() == 0 ? null:(AppMenuAuthInfor)appMenuAuthInforlist.get(0);
	}
	
    /**
     * 根据传入的条件进行查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
     * @author      zdf
     * @createDate  2016-2-17
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Integer  
     */
	@Override
	public List<AppMenuAuthInfor> queryAppMenuAuthInforListByPage(BaseModel i_Params) {

		// 返回查询数量
		return super.queryByPage("XSQL_platform_AppMenuAuthInfor_QueryByPage", i_Params);
	}
	
	
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
	@Override
	public Integer saveAppMenuAuthInfor(BaseModel i_Params) {
		return super.save("XSQL_platform_AppMenuAuthInfor_INSERT", i_Params);
	};
	
	
	
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
	@Override
	public Integer updateAppMenuAuthInfor(BaseModel i_Params) {
		return super.update("XSQL_platform_AppMenuAuthInfor_UPDATE", i_Params);
	};
	
	
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
	@Override
	public Integer deleteAppMenuAuthInfor(BaseModel i_Params) {
		return super.delete("XSQL_platform_AppMenuAuthInfor_DELETE", i_Params);
	};
}
