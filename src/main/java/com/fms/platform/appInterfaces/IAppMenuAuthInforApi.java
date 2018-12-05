package com.fms.platform.appInterfaces;

import org.hy.common.xml.plugins.AppMessage;

import com.fms.platform.dto.AppMenuAuthInforInDto;





/**
 * 手机App应用端菜单鉴权接口的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: 通用
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0  
 */
public interface IAppMenuAuthInforApi
{
    
    /**
     * 手机App应用端菜单鉴权接口业务列表查询
     * 
     * 接口编号：platform.AppMenuAuthInfor.Retrieve.A001
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       i_AppMsg 输入参数封装
     * @return
     */
    public AppMessage<Object> queryAppMenuAuthInforList(AppMessage<AppMenuAuthInforInDto> i_AppMsg);
}
