package com.fms.templateSys.main;

/**
 * 表字段类
 * @author Administrator
 *
 */
public class ColumnData {

	/** 表的字段名 */
	private String columnName; //表的字段名
	
	/** 驼峰法变量名 */
	private String humpName ;  //驼峰法变量名
	
	/** 字段的数据类型 */
	private String dataType;   //字段的数据类型
	
	/** 字段的注释 */
	private String columnComment;//字段的注释
	
	/** 字段要求 */
	private String fieldRule;//字段要求
	
	/** 所属接口编号 */
	private String apiCode;//所属接口编号
	
	/** 序号 */
	private String indexNo;//序号
	
	/** 序号 */
	private String filedType;//序号
	
	
	
	public String getHumpName() {
		return humpName;
	}
	public void setHumpName(String humpName) {
		this.humpName = humpName;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getColumnComment() {
		return columnComment;
	}
	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}
	public String getFieldRule() {
		return fieldRule;
	}
	public void setFieldRule(String fieldRule) {
		this.fieldRule = fieldRule;
	}
	public String getApiCode() {
		return apiCode;
	}
	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}
	public String getIndexNo() {
		return indexNo;
	}
	public void setIndexNo(String indexNo) {
		this.indexNo = indexNo;
	}
	public String getFiledType() {
		return filedType;
	}
	public void setFiledType(String filedType) {
		this.filedType = filedType;
	}
	
	
}
