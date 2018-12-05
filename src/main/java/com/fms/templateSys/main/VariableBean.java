package com.fms.templateSys.main;

public class VariableBean {

	 private String templateClass; // 类名
	 private String templateName;  // 模块名
	 private String beanDesc;      // 模块描述
	 private String author;        // 作者
	 private String createTime;    // 生成时间
	 private String systemCode;    // 系统编码
	 private String beanField;     // bean 定义及方法
	 private String tableName;     // 表名
	 private String selectClm;     // select语句字段
	 private String dmlCondition;     // select语句字段
	 private String condition;     // 检索动态条件
	 private String insertCondition; // 插入语句条件
	 private String updateClm;       // 更新语句字段
	 private String insertClm;       // 插入语句字段
	 private String createSql; // sql脚本create部分
	 private String commentSql; // sql脚本注释部分
	 
	 
	 
	 public VariableBean() {
	 }
	 public VariableBean(String ... varialbe) {
		 
		this.templateClass = varialbe[0];
		this.templateName = varialbe[1];
		this.beanDesc = varialbe[2];
		this.author = varialbe[3];
		this.systemCode = varialbe[4];
		// 生成时间永远作为最后一个
		this.createTime = varialbe[5];
		
	}

	public String getTemplateName() {
		return templateName;
	}




	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}




	public String getAuthor() {
		return author;
	}




	public void setAuthor(String author) {
		this.author = author;
	}


	public String getCreateTime() {
		return createTime;
	}

	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	public String getSystemCode() {
		return systemCode;
	}




	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}




	public String getBeanDesc() {
		return beanDesc;
	}




	public void setBeanDesc(String beanDesc) {
		this.beanDesc = beanDesc;
	}






	public String getTemplateClass() {
		return templateClass;
	}





	public void setTemplateClass(String templateClass) {
		this.templateClass = templateClass;
	}




	public String getBeanField() {
		return beanField;
	}
	
	public void setBeanField(String beanField) {
		this.beanField = beanField;
	}
	
	
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getSelectClm() {
		return selectClm;
	}
	
	public void setSelectClm(String selectClm) {
		this.selectClm = selectClm;
	}
	
	public String getCondition() {
		return condition;
	}
	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getInsertCondition() {
		return insertCondition;
	}
	
	public void setInsertCondition(String insertCondition) {
		this.insertCondition = insertCondition;
	}
	
	
	public String getUpdateClm() {
		return updateClm;
	}
	
	public void setUpdateClm(String updateClm) {
		this.updateClm = updateClm;
	}
	
	
	public String getInsertClm() {
		return insertClm;
	}
	public void setInsertClm(String insertClm) {
		this.insertClm = insertClm;
	}
	
	
	public String getDmlCondition() {
		return dmlCondition;
	}
	public void setDmlCondition(String dmlCondition) {
		this.dmlCondition = dmlCondition;
	}
	
	
	public String getCreateSql() {
		return createSql;
	}
	public void setCreateSql(String createSql) {
		this.createSql = createSql;
	}
	public String getCommentSql() {
		return commentSql;
	}
	public void setCommentSql(String commentSql) {
		this.commentSql = commentSql;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
