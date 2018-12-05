package com.fms.report.dto;

import java.util.List;

import com.fms.platform.common.BaseOutDto;
import com.fms.report.model.Department;





/**
 * 考勤部门记录输出参数定义
 * 
 * 返回时要求的一些非数据库层的信息在这里面定义，封装。
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public class DepartmentOutDto extends BaseOutDto
{
    /**
	 * 封装列表对象，json关键字：datas
	 */
	private List<Department> datas;
	
	/**
	 * 封装详情信息(单个数据)对象，json关键字：data
	 */
	private Department data;
	
	
	public List<Department> getDatas() {
		return datas;
	}

	public void setDatas(List<Department> datas) {
		this.datas = datas;
	}

	public Department getData() {
		return data;
	}

	public void setData(Department data) {
		this.data = data;
	}


	
	


}
