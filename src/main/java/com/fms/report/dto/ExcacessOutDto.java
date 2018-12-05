package com.fms.report.dto;

import java.util.List;

import com.fms.platform.common.BaseOutDto;
import com.fms.report.model.Excacess;





/**
 * 异常出入记录输出参数定义
 * 
 * 返回时要求的一些非数据库层的信息在这里面定义，封装。
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public class ExcacessOutDto extends BaseOutDto
{
    /**
	 * 封装列表对象，json关键字：datas
	 */
	private List<Excacess> datas;
	
	/**
	 * 封装详情信息(单个数据)对象，json关键字：data
	 */
	private Excacess data;
	
	
	public List<Excacess> getDatas() {
		return datas;
	}

	public void setDatas(List<Excacess> datas) {
		this.datas = datas;
	}

	public Excacess getData() {
		return data;
	}

	public void setData(Excacess data) {
		this.data = data;
	}


	
	


}
