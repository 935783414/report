package com.fms.report.dao.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.report.dao.IExcacessDAO;
import com.fms.report.model.Excacess;
import com.fms.platform.common.BaseDAO;
import com.fms.platform.common.BaseModel;





/**
 * 异常出入记录业务操作DAO
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
@Xjava(id="reportExcacessDao")
public class ExcacessDAOImpl extends BaseDAO<Excacess> implements IExcacessDAO
{

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Excacess>     查询列表
     */
	@Override
	public List<Excacess> queryExcacessList(BaseModel i_Params) {
		List<Excacess> excacesslist = super.queryRecords("XSQL_report_Excacess_QUERYLIST", i_Params);
		return excacesslist;
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
	public Integer queryExcacessListCount(BaseModel i_Params) {
		
		// 返回查询数量 自定义查询数量的sql标识
		return super.QueryRecordsCount("XSQL_report_Excacess_QueryList_Count", i_Params);
	}

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Excacess>     查询列表
     */
	@Override
	public Excacess queryExcacess(BaseModel i_Params) {
		List<Excacess> excacesslist = super.queryRecords("XSQL_report_Excacess_QUERYLIST", i_Params);
		return excacesslist == null || excacesslist.size() == 0 ? null:(Excacess)excacesslist.get(0);
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
	public List<Excacess> queryExcacessListByPage(BaseModel i_Params) {

		// 返回查询数量
		return super.queryByPage("XSQL_report_Excacess_QueryByPage", i_Params);
	}
	
	
    /**
     * 异常出入记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数  
     */
	@Override
	public Integer saveExcacess(BaseModel i_Params) {
		return super.save("XSQL_report_Excacess_INSERT", i_Params);
	};
	
	
	
    /**
     *  异常出入记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	@Override
	public Integer updateExcacess(BaseModel i_Params) {
		return super.update("XSQL_report_Excacess_UPDATE", i_Params);
	};
	
	
    /**
     *  异常出入记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	@Override
	public Integer deleteExcacess(BaseModel i_Params) {
		return super.delete("XSQL_report_Excacess_DELETE", i_Params);
	}

	@Override
	public List<Excacess> accesssList() {
		return  super.queryRecords("XSQL_report_Excacess_QUERYLIST");
	}

	;
}
