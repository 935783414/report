package com.fms.templateSys.dto;

import java.util.List;

import com.fms.platform.common.BaseOutDto;
import com.fms.templateSys.model.Template;





/**
 * ${template}输出参数定义
 * 
 * 返回时要求的一些非数据库层的信息在这里面定义，封装。
 *
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0
 */
public class TemplateOutDto extends BaseOutDto
{
    /**
	 * 封装列表对象，json关键字：datas
	 */
	private List<Template> datas;
	
	/**
	 * 封装详情信息(单个数据)对象，json关键字：data
	 */
	private Template data;
	
	
	public List<Template> getDatas() {
		return datas;
	}

	public void setDatas(List<Template> datas) {
		this.datas = datas;
	}

	public Template getData() {
		return data;
	}

	public void setData(Template data) {
		this.data = data;
	}


	
	


}
