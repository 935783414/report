package com.fms.report.dao; 

import java.util.List;

import com.fms.platform.common.BaseModel;
import com.fms.report.model.Excacess;





/**
 * 异常出入记录业务操作DAO
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public interface IExcacessDAO 
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
     * @return      List<Excacess>     查询列表
     */
	public List<Excacess> queryExcacessList(BaseModel i_Params);
	
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
	public Integer queryExcacessListCount(BaseModel i_Params);
    
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
     * @return      Excacess 单个对象返回
     */
	public Excacess queryExcacess(BaseModel i_Params);
	
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
     * @return      List<Excacess>  
     */
	public List<Excacess> queryExcacessListByPage(BaseModel i_Params);
	
	
	
    /**
     * 异常出入记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数  
     */
	public Integer saveExcacess(BaseModel i_Params);
	
	
	
    /**
     *  异常出入记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	public Integer updateExcacess(BaseModel i_Params);
	
	
    /**
     *  异常出入记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       i_Params 插入目标集合
     * @return      Integer 影响行数   
     */
	public Integer deleteExcacess(BaseModel i_Params);

	/*
	 * 异常出入查询
	 * */
	public List<Excacess> accesssList();
}
