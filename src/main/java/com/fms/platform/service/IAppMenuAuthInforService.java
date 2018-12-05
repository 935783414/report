package com.fms.platform.service;

import org.hy.common.Return;

import com.fms.platform.dto.AppMenuAuthInforInDto;
import com.fms.platform.dto.AppMenuAuthInforOutDto;





/**
 * 手机App应用端菜单鉴权接口服务层接口
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0
 */
public interface IAppMenuAuthInforService
{
    
    /**
     * 手机App应用端菜单鉴权接口业务列表
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数
     */
    public Return<AppMenuAuthInforOutDto> queryAppMenuAuthInforList(AppMenuAuthInforInDto appMenuAuthInforInDto);
    
    /**
     * 手机App应用端菜单鉴权接口信息列表,带分页查询
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数
     */
    public Return<AppMenuAuthInforOutDto> queryAppMenuAuthInforListByPage(AppMenuAuthInforInDto appMenuAuthInforInDto);
    
    
    /**
     * 手机App应用端菜单鉴权接口查询返回单个对象
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数
     */
    public Return<AppMenuAuthInforOutDto> queryAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto);
    
    
    /**
     * 手机App应用端菜单鉴权接口单表插入
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数
     */
	public Return<AppMenuAuthInforOutDto> saveAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto);
	
	
	
    /**
     *  手机App应用端菜单鉴权接口单表更新
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数 
     */
	public Return<AppMenuAuthInforOutDto> updateAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto);
	
	
    /**
     *  手机App应用端菜单鉴权接口单表删除
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数   
     */
	public Return<AppMenuAuthInforOutDto> deleteAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto);
}
