package com.fms.templateSys.service;

import org.hy.common.Return;

import com.fms.templateSys.dto.TemplateInDto;
import com.fms.templateSys.dto.TemplateOutDto;





/**
 * ${template}服务层接口
 *
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0
 */
public interface ITemplateService
{
    
    /**
     * ${template}业务列表
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数
     */
    public Return<TemplateOutDto> queryTemplateList(TemplateInDto templateInDto);
    
    /**
     * ${template}信息列表,带分页查询
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数
     */
    public Return<TemplateOutDto> queryTemplateListByPage(TemplateInDto templateInDto);
    
    
    /**
     * ${template}查询返回单个对象
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数
     */
    public Return<TemplateOutDto> queryTemplate(TemplateInDto templateInDto);
    
    
    /**
     * ${template}单表插入
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数
     */
	public Return<TemplateOutDto> saveTemplate(TemplateInDto templateInDto);
	
	
	
    /**
     *  ${template}单表更新
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数 
     */
	public Return<TemplateOutDto> updateTemplate(TemplateInDto templateInDto);
	
	
    /**
     *  ${template}单表删除
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       templateInDto   输入参数
     * @return      TemplateOutDto  输出参数   
     */
	public Return<TemplateOutDto> deleteTemplate(TemplateInDto templateInDto);
}
