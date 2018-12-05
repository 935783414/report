package com.fms.platform.common.util;

import com.fms.platform.common.BaseModel;
import com.fms.platform.common.BaseOutDto;

public class PageUtil {
    
    
    
    /**
     * 获取总页数
     * @param  v_recordCount 是通过查询数量的到，所以结果放在outDto
     * @param  v_limit 分页
     * @return o_pageCount 
     */
	public static Integer getPageCount(Integer v_recordCount, Integer v_limit) {
		// 总条数/每页显示的条数=总页数
		Integer o_pageCount = 1;
		int size = v_recordCount / v_limit;
		// 最后一页的条数
		int mod = v_recordCount % v_limit;
		if (mod != 0)
			size++;
		if (v_recordCount == 0) {
			o_pageCount = 1;
		} else {
			o_pageCount = size;
		}
		return o_pageCount;
	}
	
	

	/**
     * 获取起始行数索引
     * 总页数
     * 
     * @param v_currentPage 当前页
     * @param v_limit       分页
     */
	public static Integer getFromIndex(Integer v_currentPage, Integer v_limit) {
		// System.out.println("from index:"+(currentPage-1) * pageSize);
		return (v_currentPage - 1) * v_limit;
	}
    
	/**
     * 输入bean中放入起始行数索引
     * 
     * @param v_currentPage 当前页
     * @param v_limit       分页
     * @param inDto         输入参数
     */
	public static void PackInDto(Integer v_currentPage, Integer v_limit, BaseModel inDto) {
		inDto.setStart(getFromIndex(v_currentPage, v_limit));
	}
	
	/**
	 * 输入输出bean转换
	 * 封装outDto分页信息
	 * 
	 * @param inDto
	 * @param outDto
	 * @param v_recordCount 是通过查询数量的到，所以结果放在outDto
	 * @return
	 */
	public static void PackOutDto (BaseOutDto outDto, BaseModel inDto, Integer v_recordCount ) {
		// 通过PageUtil 计算相关信息 recordCount是通过查询数量的到，所以结果放在outDto
		outDto.setLimit(inDto.getLimit());
		outDto.setStart(inDto.getStart());
		outDto.setCurrentPage(inDto.getCurrentPage());
		outDto.setPageCount(getPageCount(v_recordCount, outDto.getLimit()));
		outDto.setRecordCount(v_recordCount);
		
	}
}
