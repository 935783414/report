package com.fms.report.dao.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.report.dao.IRoleDAO;
import com.fms.report.model.Role;
import com.fms.platform.common.BaseDAO;
import com.fms.platform.common.BaseModel;





/**
 * 卡类别记录业务操作DAO
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
@Xjava(id="reportRoleDao")
public class RoleDAOImpl extends BaseDAO<Role> implements IRoleDAO {

	/**
	 * 根据传入的条件进行查询（非主键对象等条件）
	 *
	 * @param i_Params 检索条件
	 * @return List<Role>     查询列表
	 * @author 陈伟
	 * @createDate 2018-12-03
	 * @version v1.0
	 */
	@Override
	public List<Role> queryRoleList(BaseModel i_Params) {
		List<Role> rolelist = super.queryRecords("XSQL_report_Role_QUERYLIST", i_Params);
		return rolelist;
	}


	/**
	 * 根据传入的条件进行查询
	 * 获得数据列表数量
	 * （非主键对象等条件）
	 *
	 * @param i_Params 检索条件
	 * @return Integer
	 * @author 陈伟
	 * @createDate 2018-12-03
	 * @version v1.0
	 */
	@Override
	public Integer queryRoleListCount(BaseModel i_Params) {

		// 返回查询数量 自定义查询数量的sql标识
		return super.QueryRecordsCount("XSQL_report_Role_QueryList_Count", i_Params);
	}

	/**
	 * 根据传入的条件进行查询（非主键对象等条件）
	 *
	 * @param i_Params 检索条件
	 * @return List<Role>     查询列表
	 * @author 陈伟
	 * @createDate 2018-12-03
	 * @version v1.0
	 */
	@Override
	public Role queryRole(BaseModel i_Params) {
		List<Role> rolelist = super.queryRecords("XSQL_report_Role_QUERYLIST", i_Params);
		return rolelist == null || rolelist.size() == 0 ? null : (Role) rolelist.get(0);
	}

	/**
	 * 根据传入的条件进行查询
	 * 获得数据列表数量
	 * （非主键对象等条件）
	 *
	 * @param i_Params 检索条件
	 * @return Integer
	 * @author zdf
	 * @createDate 2016-2-17
	 * @version v1.0
	 */
	@Override
	public List<Role> queryRoleListByPage(BaseModel i_Params) {

		// 返回查询数量
		return super.queryByPage("XSQL_report_Role_QueryByPage", i_Params);
	}


	/**
	 * 卡类别记录单表插入
	 *
	 * @param i_Params 插入目标集合
	 * @return Integer 影响行数
	 * @author 陈伟
	 * @createDate 2018-12-03
	 * @version v1.0
	 */
	@Override
	public Integer saveRole(BaseModel i_Params) {
		return super.save("XSQL_report_Role_INSERT", i_Params);
	}

	;


	/**
	 * 卡类别记录单表更新
	 *
	 * @param i_Params 插入目标集合
	 * @return Integer 影响行数
	 * @author 陈伟
	 * @createDate 2018-12-03
	 * @version v1.0
	 */
	@Override
	public Integer updateRole(BaseModel i_Params) {
		return super.update("XSQL_report_Role_UPDATE", i_Params);
	}

	;


	/**
	 * 卡类别记录单表删除
	 *
	 * @param i_Params 插入目标集合
	 * @return Integer 影响行数
	 * @author 陈伟
	 * @createDate 2018-12-03
	 * @version v1.0
	 */
	@Override
	public Integer deleteRole(BaseModel i_Params) {
		return super.delete("XSQL_report_Role_DELETE", i_Params);
	}

	;

	/*
	 * 查询卡类别
	 * */
	@Override
	public List<Role> queryRoleList() {
		return super.queryRecords("XSQL_report_Role_QUERYLIST");
	}


}