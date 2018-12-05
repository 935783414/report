package com.fms.report.service;

import com.fms.report.model.Excacess;
import org.hy.common.Return;

import com.fms.report.dto.ExcacessInDto;
import com.fms.report.dto.ExcacessOutDto;

import java.util.List;


/**
 * 异常出入记录服务层接口
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public interface IExcacessService
{
    
    /**
     * 异常出入记录业务列表
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数
     */
    public Return<ExcacessOutDto> queryExcacessList(ExcacessInDto excacessInDto);
    
    /**
     * 异常出入记录信息列表,带分页查询
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数
     */
    public Return<ExcacessOutDto> queryExcacessListByPage(ExcacessInDto excacessInDto);
    
    
    /**
     * 异常出入记录查询返回单个对象
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数
     */
    public Return<ExcacessOutDto> queryExcacess(ExcacessInDto excacessInDto);
    
    
    /**
     * 异常出入记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数
     */
	public Return<ExcacessOutDto> saveExcacess(ExcacessInDto excacessInDto);
	
	
	
    /**
     *  异常出入记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数 
     */
	public Return<ExcacessOutDto> updateExcacess(ExcacessInDto excacessInDto);
	
	
    /**
     *  异常出入记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数   
     */
	public Return<ExcacessOutDto> deleteExcacess(ExcacessInDto excacessInDto);

	/*
	* 异常出入查询
	* */
	public List<Excacess> accesssList();
}
