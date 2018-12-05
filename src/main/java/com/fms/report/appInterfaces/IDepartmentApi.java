package com.fms.report.appInterfaces;

import org.hy.common.xml.plugins.AppMessage;

import com.fms.report.dto.DepartmentInDto;





/**
 * 考勤部门记录的接口
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
public interface IDepartmentApi
{
    
    /**
     * 考勤部门记录业务列表查询
     * 
     * 接口编号：report.Department.Retrieve.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryDepartmentList(AppMessage<DepartmentInDto> i_AppMsg);
    
    
    
    /**
     * 考勤部门记录业务列表分页查询
     * 
     * 接口编号：report.Department.Retrieve.A002
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    public AppMessage<Object> queryDepartmentListByPage(AppMessage<DepartmentInDto> i_AppMsg);
    
    
    
    
    /**
     * 考勤部门记录查询返回单个对象
     * 
     * 接口编号：report.Department.Retrieve.A003
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryDepartment(AppMessage<DepartmentInDto> i_AppMsg);
    
    
    
    /**
     * 考勤部门记录单表插入接口
     *
     * 接口编号：report.Department.Insert.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     */
	public AppMessage<Object> saveDepartment(AppMessage<DepartmentInDto> i_AppMsg);
	
	
	
    /**
     *  考勤部门记录单表更新接口
     *
     * 接口编号：report.Department.Update.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg 
     */
	public AppMessage<Object> updateDepartment(AppMessage<DepartmentInDto> i_AppMsg);
	
	
    /**
     *  考勤部门记录单表删除接口
     *
     * 接口编号：report.Department.Delete.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg   
     */
	public AppMessage<Object> deleteDepartment(AppMessage<DepartmentInDto> i_AppMsg);
    
}
