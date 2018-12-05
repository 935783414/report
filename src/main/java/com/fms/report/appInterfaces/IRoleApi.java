package com.fms.report.appInterfaces;

import org.hy.common.xml.plugins.AppMessage;

import com.fms.report.dto.RoleInDto;





/**
 * 卡类别记录的接口
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
public interface IRoleApi
{
    
    /**
     * 卡类别记录业务列表查询
     * 
     * 接口编号：report.Role.Retrieve.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryRoleList(AppMessage<RoleInDto> i_AppMsg);
    
    
    
    /**
     * 卡类别记录业务列表分页查询
     * 
     * 接口编号：report.Role.Retrieve.A002
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    public AppMessage<Object> queryRoleListByPage(AppMessage<RoleInDto> i_AppMsg);
    
    
    
    
    /**
     * 卡类别记录查询返回单个对象
     * 
     * 接口编号：report.Role.Retrieve.A003
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryRole(AppMessage<RoleInDto> i_AppMsg);
    
    
    
    /**
     * 卡类别记录单表插入接口
     *
     * 接口编号：report.Role.Insert.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     */
	public AppMessage<Object> saveRole(AppMessage<RoleInDto> i_AppMsg);
	
	
	
    /**
     *  卡类别记录单表更新接口
     *
     * 接口编号：report.Role.Update.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg 
     */
	public AppMessage<Object> updateRole(AppMessage<RoleInDto> i_AppMsg);
	
	
    /**
     *  卡类别记录单表删除接口
     *
     * 接口编号：report.Role.Delete.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg   
     */
	public AppMessage<Object> deleteRole(AppMessage<RoleInDto> i_AppMsg);
    
}
