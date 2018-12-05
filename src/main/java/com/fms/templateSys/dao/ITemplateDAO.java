package com.fms.templateSys.dao; 

import java.util.List;

import com.fms.platform.common.BaseModel;
import com.fms.templateSys.model.Template;





/**
 * ${template}业务操作DAO
 *
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0
 */
public interface ITemplateDAO 
{
    
    /**
     * 根据传入的条件进行查询
     * 获得数据列表
     * （非主键对象等条件）
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Template>     查询列表
     */
	public List<Template> queryTemplateList(BaseModel i_Params);
	
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
	public Integer queryTemplateListCount(BaseModel i_Params);
    
    /**
     * 查询单个对象
     * 获得数据列表
     * （非主键对象等条件）
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Template 单个对象返回
     */
	public Template queryTemplate(BaseModel i_Params);
	
    /**
     * 根据传入的条件进行分页查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Template>  
     */
	public List<Template> queryTemplateListByPage(BaseModel i_Params);
	
	
	
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
	public Integer saveTemplate(BaseModel i_Params);
	
	
	
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
	public Integer updateTemplate(BaseModel i_Params);
	
	
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
	public Integer deleteTemplate(BaseModel i_Params);
}
