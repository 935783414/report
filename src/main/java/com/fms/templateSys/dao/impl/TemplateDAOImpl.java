package com.fms.templateSys.dao.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.templateSys.dao.ITemplateDAO;
import com.fms.templateSys.model.Template;
import com.fms.platform.common.BaseDAO;
import com.fms.platform.common.BaseModel;





/**
 * ${template}业务操作DAO
 *
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0
 */
@Xjava(id="$systemTemplateDao")
public class TemplateDAOImpl extends BaseDAO<Template> implements ITemplateDAO
{

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Template>     查询列表
     */
	@Override
	public List<Template> queryTemplateList(BaseModel i_Params) {
		List<Template> templatelist = super.queryRecords("XSQL_${system}_Template_QUERYLIST", i_Params);
		return templatelist;
	}
	


    /**
     * 根据传入的条件进行查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Integer  
     */
	@Override
	public Integer queryTemplateListCount(BaseModel i_Params) {
		
		// 返回查询数量 自定义查询数量的sql标识
		return super.QueryRecordsCount("XSQL_${system}_Template_QueryList_Count", i_Params);
	}

    /**
     * 根据传入的条件进行查询（非主键对象等条件）
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Template>     查询列表
     */
	@Override
	public Template queryTemplate(BaseModel i_Params) {
		List<Template> templatelist = super.queryRecords("XSQL_${system}_Template_QUERYLIST", i_Params);
		return templatelist == null || templatelist.size() == 0 ? null:(Template)templatelist.get(0);
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
	public List<Template> queryTemplateListByPage(BaseModel i_Params) {

		// 返回查询数量
		return super.queryByPage("XSQL_${system}_Template_QueryByPage", i_Params);
	}
	
	
    /**
     * ${template}单表插入
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数  
     */
	@Override
	public Integer saveTemplate(BaseModel i_Params) {
		return super.save("XSQL_${system}_Template_INSERT", i_Params);
	};
	
	
	
    /**
     *  ${template}单表更新
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	@Override
	public Integer updateTemplate(BaseModel i_Params) {
		return super.update("XSQL_${system}_Template_UPDATE", i_Params);
	};
	
	
    /**
     *  ${template}单表删除
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	@Override
	public Integer deleteTemplate(BaseModel i_Params) {
		return super.delete("XSQL_${system}_Template_DELETE", i_Params);
	};
}
