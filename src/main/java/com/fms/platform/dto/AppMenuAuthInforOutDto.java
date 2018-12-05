package com.fms.platform.dto;

import java.util.List;

import org.hy.common.xml.annotation.Doc;

import com.fms.platform.common.BaseOutDto;
import com.fms.platform.model.AppMenuAuthInfor;





/**
 * 手机App应用端菜单鉴权接口输出参数定义
 * 
 * 返回时要求的一些非数据库层的信息在这里面定义，封装。
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0
 */
public class AppMenuAuthInforOutDto extends BaseOutDto
{
    /**
	 * 封装列表对象，json关键字：datas
	 */
	private List<AppMenuAuthInfor> datas;
	
	/**
	 * 封装详情信息(单个数据)对象，json关键字：data
	 */
	@Doc(index="01" ,info="单个数据"  ,value={"platform.AppMenuAuthInfor.Retrieve.A001"})
	private AppMenuAuthInfor data;
	
	
	public List<AppMenuAuthInfor> getDatas() {
		return datas;
	}

	public void setDatas(List<AppMenuAuthInfor> datas) {
		this.datas = datas;
	}

	public AppMenuAuthInfor getData() {
		return data;
	}

	public void setData(AppMenuAuthInfor data) {
		this.data = data;
	}


	
	


}
