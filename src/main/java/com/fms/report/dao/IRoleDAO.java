package com.fms.report.dao; 

import java.util.List;

import com.fms.platform.common.BaseModel;
import com.fms.report.model.Role;





/**
 * 卡类别记录业务操作DAO
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public interface IRoleDAO 
{
    
    /**
     * 根据传入的条件进行查询
     * 获得数据列表
     * （非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Role>     查询列表
     */
	public List<Role> queryRoleList(BaseModel i_Params);
	
    /**
     * 根据传入的条件进行查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Integer  
     */
	public Integer queryRoleListCount(BaseModel i_Params);
    
    /**
     * 查询单个对象
     * 获得数据列表
     * （非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      Role 单个对象返回
     */
	public Role queryRole(BaseModel i_Params);
	
    /**
     * 根据传入的条件进行分页查询
     * 获得数据列表数量
     * （非主键对象等条件）
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 检索条件
     * @return      List<Role>  
     */
	public List<Role> queryRoleListByPage(BaseModel i_Params);
	
	
	
    /**
     * 卡类别记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数  
     */
	public Integer saveRole(BaseModel i_Params);
	
	
	
    /**
     *  卡类别记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	public Integer updateRole(BaseModel i_Params);
	
	
    /**
     *  卡类别记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	public Integer deleteRole(BaseModel i_Params);

	/*
	 * 查询卡类别
	 * */
	public List<Role> queryRoleList();
}
