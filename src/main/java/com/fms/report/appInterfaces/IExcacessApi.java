package com.fms.report.appInterfaces;

import org.hy.common.xml.plugins.AppMessage;

import com.fms.report.dto.ExcacessInDto;





/**
 * 异常出入记录的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: 通用
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0  
 */
public interface IExcacessApi
{
    
    /**
     * 异常出入记录业务列表查询
     * 
     * 接口编号：report.Excacess.Retrieve.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryExcacessList(AppMessage<ExcacessInDto> i_AppMsg);
    
    
    
    /**
     * 异常出入记录业务列表分页查询
     * 
     * 接口编号：report.Excacess.Retrieve.A002
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    public AppMessage<Object> queryExcacessListByPage(AppMessage<ExcacessInDto> i_AppMsg);
    
    
    
    
    /**
     * 异常出入记录查询返回单个对象
     * 
     * 接口编号：report.Excacess.Retrieve.A003
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryExcacess(AppMessage<ExcacessInDto> i_AppMsg);
    
    
    
    /**
     * 异常出入记录单表插入接口
     *
     * 接口编号：report.Excacess.Insert.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     */
	public AppMessage<Object> saveExcacess(AppMessage<ExcacessInDto> i_AppMsg);
	
	
	
    /**
     *  异常出入记录单表更新接口
     *
     * 接口编号：report.Excacess.Update.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg 
     */
	public AppMessage<Object> updateExcacess(AppMessage<ExcacessInDto> i_AppMsg);
	
	
    /**
     *  异常出入记录单表删除接口
     *
     * 接口编号：report.Excacess.Delete.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg   
     */
	public AppMessage<Object> deleteExcacess(AppMessage<ExcacessInDto> i_AppMsg);
    
}
