package com.fms.report.dao.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.report.dao.IDepartmentDAO;
import com.fms.report.model.Department;
import com.fms.platform.common.BaseDAO;
import com.fms.platform.common.BaseModel;





/**
 * 考勤部门记录业务操作DAO
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
@Xjava(id="reportDepartmentDao")
public class DepartmentDAOImpl extends BaseDAO<Department> implements IDepartmentDAO
{

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Department>     查询列表
     */
	@Override
	public List<Department> queryDepartmentList(BaseModel i_Params) {
		System.out.println("444444444444444");
		List<Department> departmentlist = super.queryRecords("XSQL_report_Department_QUERYLIST", i_Params);
		return departmentlist;
	}
	


    /**
     * 根据传入的条件进行查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Integer  
     */
	@Override
	public Integer queryDepartmentListCount(BaseModel i_Params) {
		
		// 返回查询数量 自定义查询数量的sql标识
		return super.QueryRecordsCount("XSQL_report_Department_QueryList_Count", i_Params);
	}

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Department>     查询列表
     */
	@Override
	public Department queryDepartment(BaseModel i_Params) {
		List<Department> departmentlist = super.queryRecords("XSQL_report_Department_QUERYLIST", i_Params);
		return departmentlist == null || departmentlist.size() == 0 ? null:(Department)departmentlist.get(0);
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
	public List<Department> queryDepartmentListByPage(BaseModel i_Params) {

		// 返回查询数量
		return super.queryByPage("XSQL_report_Department_QueryByPage", i_Params);
	}

	@Override
	public Integer saveDepartment(BaseModel i_Params) {
		return null;
	}

	@Override
	public Integer updateDepartment(BaseModel i_Params) {
		return null;
	}

	@Override
	public Integer deleteDepartment(BaseModel i_Params) {
		return null;
	}

	/*
	* 部门查询
	* */
	@Override
	public List<Department> queryDepartmentList2() {
		List<Department> departmentlist = super.queryRecords("XSQL_report_Department_QUERYLIST");
		return departmentlist;
	}


}
