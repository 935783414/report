package com.fms.templateSys.appInterfaces;

import org.hy.common.xml.plugins.AppMessage;

import com.fms.templateSys.dto.TemplateInDto;





/**
 * ${template}的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: 通用
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0  
 */
public interface ITemplateApi
{
    
    /**
     * ${template}业务列表查询
     * 
     * 接口编号：${system}.Template.Retrieve.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryTemplateList(AppMessage<TemplateInDto> i_AppMsg);
    
    
    
    /**
     * ${template}业务列表分页查询
     * 
     * 接口编号：${system}.Template.Retrieve.A002
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    public AppMessage<Object> queryTemplateListByPage(AppMessage<TemplateInDto> i_AppMsg);
    
    
    
    
    /**
     * ${template}查询返回单个对象
     * 
     * 接口编号：${system}.Template.Retrieve.A003
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryTemplate(AppMessage<TemplateInDto> i_AppMsg);
    
    
    
    /**
     * ${template}单表插入接口
     *
     * 接口编号：${system}.Template.Insert.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg
     */
	public AppMessage<Object> saveTemplate(AppMessage<TemplateInDto> i_AppMsg);
	
	
	
    /**
     *  ${template}单表更新接口
     *
     * 接口编号：${system}.Template.Update.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg 
     */
	public AppMessage<Object> updateTemplate(AppMessage<TemplateInDto> i_AppMsg);
	
	
    /**
     *  ${template}单表删除接口
     *
     * 接口编号：${system}.Template.Delete.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg   
     */
	public AppMessage<Object> deleteTemplate(AppMessage<TemplateInDto> i_AppMsg);
    
}
