package com.fms.report.service;

import com.fms.report.model.Role;
import org.hy.common.Return;

import com.fms.report.dto.RoleInDto;
import com.fms.report.dto.RoleOutDto;

import java.util.List;


/**
 * 卡类别记录服务层接口
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public interface IRoleService
{
    
    /**
     * 卡类别记录业务列表
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数
     */
    public Return<RoleOutDto> queryRoleList(RoleInDto roleInDto);
    
    /**
     * 卡类别记录信息列表,带分页查询
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数
     */
    public Return<RoleOutDto> queryRoleListByPage(RoleInDto roleInDto);
    
    
    /**
     * 卡类别记录查询返回单个对象
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数
     */
    public Return<RoleOutDto> queryRole(RoleInDto roleInDto);
    
    
    /**
     * 卡类别记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数
     */
	public Return<RoleOutDto> saveRole(RoleInDto roleInDto);
	
	
	
    /**
     *  卡类别记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数 
     */
	public Return<RoleOutDto> updateRole(RoleInDto roleInDto);
	
	
    /**
     *  卡类别记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数   
     */
	public Return<RoleOutDto> deleteRole(RoleInDto roleInDto);

	/*
	 * 查询卡类别
	 * */
	public List<Role> queryRoleList();
}
