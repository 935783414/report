package com.fms.templateSys.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.hy.common.Date;
import org.hy.common.StringHelp;
import org.hy.common.file.FileHelp;
import org.hy.common.xml.annotation.Doc;


/**
 * <br>
 * <b>功能：</b>详细的功能描述<br>
 * <b>作者：</b>易思博西安团队<br>
 * <b>日期：</b> 2011-7-22 <br>
 * <b>更新者：</b><br>
 * <b>日期：</b> <br>
 * <b>更新内容：</b><br>
 */
public class CommonPageParser {
	
	private static VelocityEngine ve;// = VelocityEngineUtil.getVelocityEngine();
	private final static String CONTENT_ENCODING ="UTF-8";
	private static final Log log = LogFactory.getLog(CommonPageParser.class);
	private static boolean isReplace = true;  //是否可以替换文件 true =可以替换，false =不可以替换
	private static String method;
	private static String argv;
	
	public static String ROOT_PATH = "src/main/java/com/fms/templateSys/";
	
	static{
		try{
			//获取文件模板根路径
			String  templateBasePath = "D:\\Workspaces\\orgMgr\\template\\";//"Constant.WORK_TEMPLATE_PATH;
			Properties properties = new Properties();
			properties.setProperty(Velocity.RESOURCE_LOADER,"file");
			properties.setProperty("file.resource.loader.description","Velocity File Resource Loader");
			properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,  templateBasePath);
			properties.setProperty(Velocity.FILE_RESOURCE_LOADER_CACHE, "true");
	        properties.setProperty("file.resource.loader.modificationCheckInterval", "30");
	        properties.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM_CLASS,  "org.apache.velocity.runtime.log.Log4JLogChute");
	        properties.setProperty("runtime.log.logsystem.log4j.logger", "org.apache.velocity");
	        properties.setProperty("directive.set.null.allowed", "true");
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.init(properties);
			ve = velocityEngine;
		}catch(Exception e){
			log.error(e);
		}
	}
	
	
	public static void setVariable(String v_template, String v_author, String v_time, String v_system, String v_beanDesc){
		VelocityContext context = new VelocityContext();
		context.put("template", v_template);    // 模块名
		context.put("author", v_author);        // 作者
		context.put("time", v_time);            // 时间
		context.put("system", v_system);        // 来自哪各系统
		context.put("beanDesc", v_beanDesc);    // 模块描述
	}
	
	public static void WriterPage(VelocityContext context,String templateName,String fileDirPath,String targetFile){
		try{
			File file = new File(fileDirPath+targetFile);
			if(!file.exists()){
				new File(file.getParent()).mkdirs();
			}else{
				if(isReplace){
					log.info("替换文件"+file.getAbsolutePath());
				}else{
				}
			}
			
			Template template = ve.getTemplate(templateName, CONTENT_ENCODING);
			FileOutputStream fos = new FileOutputStream(file);  
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos,CONTENT_ENCODING));
			template.merge(context, writer);
			writer.flush();
		    writer.close();
	    	fos.close();  
//	    	System.out.println("页面生成成功"+file.getAbsolutePath());
		}catch (Exception e) {
			log.error(e);
		}
	} 
	
	/**
	 * 
	 * @param varBean 替换的变量bean
	 * @param sourceFile 已经存在的模板文件
	 * @param targetFile 需要建立的新文件
	 * @return
	 * @throws Exception 
	 */
	public static boolean replaceFile (VariableBean varBean, File sourceFile ,File targetFile) throws Exception {

		if (!targetFile.exists()) {
			targetFile.createNewFile();
			System.out.println("被复制的文件已创建");
		} else if (targetFile.exists()) {
			System.out.println("被复制的文件已存在");
		}
		
		FileHelp fileHelp = new FileHelp();
		// 加个true 表示要文件里的回车
		String fileContent = fileHelp.getContent(sourceFile, "UTF-8", true);
		// 替换的变量值
		String[] sourceStr = new String[]{"templateSys","${template}", "${author}", "${createDate}", 
				             "${system}", "et$system", "$system", "${beanDesc}", "template", "Template", "//${field}",
				             "${selectClm}", "${tableName}", "${condition}", "${insertCondition}", 
				             "${updateClm}", "${insertClm}", "${dmlCondition}", "${sql}", "${oracle_comment}"
				             ,"${sqlserver_comment}"};
		String [] str = new String[2];
		if(varBean.getCommentSql().indexOf("@") > 0) {
			str = varBean.getCommentSql().split("@");
		}
		// 替换的内容
		String[] targetStr = new String[]{varBean.getSystemCode(), varBean.getTemplateName(), varBean.getAuthor(), 
				             varBean.getCreateTime(), varBean.getSystemCode(), "et" + toUpperCaseFirstOne(varBean.getSystemCode()), toLowerCaseFirstOne(varBean.getSystemCode()), varBean.getBeanDesc(), 
				             toLowerCaseFirstOne(varBean.getTemplateClass()), varBean.getTemplateClass(), varBean.getBeanField(),
				             varBean.getSelectClm(), varBean.getTableName(), varBean.getCondition(), varBean.getInsertCondition(),
				             varBean.getUpdateClm(), varBean.getInsertClm(), varBean.getDmlCondition(), varBean.getCreateSql(),
				             str[0] == null?"":str[0],str[1] == null?"":str[1]};
		fileContent = StringHelp.replaceAll(fileContent, sourceStr, targetStr);
		 
		// 写出新的文件
		BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
		bw.write(fileContent);
		
//		BufferedReader br = new BufferedReader(new FileReader(sourceFile));
//		BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
//		String str = null;
//		while ((str = br.readLine()) != null) {
//			String[] sourceStr = new String[]{"${template}", "${author}", "${createDate}", "${system}", "${beanDesc}", "template", "Template"};
//			String[] targetStr = new String[]{varBean.getTemplateName(), varBean.getAuthor(), varBean.getCreateTime(), varBean.getSystemCode(), varBean.getBeanDesc(), toLowerCaseFirstOne(varBean.getTemplateClass()), varBean.getTemplateClass()};
//			System.out.println("---------------------");
//			System.out.println("替换前"+str);
//			Help.print(targetStr);
//			// StringHelp类可以替换特殊符号的类以及可以多个关键字替换
//			str = StringHelp.replaceAll(str, sourceStr, targetStr);
//			System.out.println("替换后"+str);
//			System.out.println("---------------------");
//			
//			bw.write(str+"\r\n");
//		}
//		br.close();
		bw.flush();
		bw.close();
		return true;
	}
	
	/**
	 * 获得所在工程本地路径
	 */
	public static String getProjectPath() {
		File f = new File(".");
		String absolutePath = f.getAbsolutePath();
		String proPath = absolutePath.substring(0,absolutePath.length()-1);
		return proPath;
	}
	
	/**
	 * 递归查询指定模板目录下所有文件夹及其中文件
	 * 将每个模板替换成目标类
	 * 
	 * @param dir
	 * @param varBean 变量封装bean
	 * @throws Exception
	 */
	final static void generateAllFiles(File dir, VariableBean varBean, Boolean isSqlScript) throws Exception {
		
		File sourceFile = null;// 已经存在的模板文件
		File targetFile = null;// 需要建立的新文件
		String sourceFilePath = "";
		String targetFilePath = "";
		File[] fs = dir.listFiles(); // 获得当前文件夹所有文件及子文件夹
		for (int i = 0; i < fs.length; i++) {
			sourceFilePath = fs[i].getAbsolutePath(); // 模板文件路径
			System.out.println(fs[i].getAbsolutePath());
			if(!isSqlScript) {
				// 判断非“main”、"xls"文件夹，main是运行文件夹  xls模板文件夹
				if (fs[i].isDirectory() && sourceFilePath.indexOf("templateSys\\main") == -1  && sourceFilePath.indexOf("xls") == -1
						 && sourceFilePath.indexOf("sql") == -1) {
						// 递归调用
						generateAllFiles(fs[i], varBean, isSqlScript);
					// 是文件的情况
				} else if(fs[i].isFile()){
					// 替换模板路径中的template为具体业务包名
					targetFilePath = StringHelp.replaceAll(sourceFilePath,new String[] {"templateSys","template","Template"},
							         new String[] {varBean.getSystemCode(), toLowerCaseFirstOne(varBean.getTemplateClass()), varBean.getTemplateClass()});
					// 生成要生成类的目录
					targetFile = new File(targetFilePath);
					mkDir(targetFile.getParentFile());
					sourceFile = fs[i]; // 模板文件
					
					// 进行模板替换，文件生成操作
					replaceFile(varBean, sourceFile, targetFile);
				} 
		  } else {
				// 判断非“main”、"xls"文件夹，main是运行文件夹  xls模板文件夹
				if (fs[i].isDirectory()) {
						// 递归调用
						generateAllFiles(fs[i], varBean, isSqlScript);
					// 是文件的情况
				} else if(fs[i].isFile()){
					// 替换模板路径中的template为具体业务包名
					targetFilePath = StringHelp.replaceAll(sourceFilePath,new String[] {"templateSys","template","Template"},
							         new String[] {varBean.getSystemCode(), toLowerCaseFirstOne(varBean.getTemplateClass()), varBean.getTemplateClass()});
					// 生成要生成类的目录
					targetFile = new File(targetFilePath);
					mkDir(targetFile.getParentFile());
					sourceFile = fs[i]; // 模板文件
					
					// 进行模板替换，文件生成操作
					replaceFile(varBean, sourceFile, targetFile);
			}
		}
		}
	}
	
	
	/**
	 * 递归创建文件目录
	 * @param file
	 */
	public static void mkDir(File file){
		  if(file.getParentFile().exists()){
		   file.mkdir();
		  }else{
		   mkDir(file.getParentFile());
		   file.mkdir();
		  }
		 }
	
	
	/**
	 * 首字母转小写
	 * 
	 * @param s
	 * @return
	 */
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
	/**
	 * 首字母转大写
	 * 
	 * @param s
	 * @return
	 */
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    /**
     * 生成文件
     * 
     * @param filePath    需要的接口文件
     * @param sheetName   sheet页
     * @param isSqlScript 是否生成脚本
     */
    public static void createFile(String filePath, String sheetName, Boolean isSqlScript) {
    	// plm系统接口定义.xls
    	File dir_xls = new File(getProjectPath() + ROOT_PATH + "xls/" + filePath);
    	File dir_sql = new File(getProjectPath() + ROOT_PATH + "sql/");
    	File dir_temp = new File(getProjectPath() + ROOT_PATH);
		List<List<Object>> sheetList = null;
		try {
			sheetList = POIUtil.readXlsBySheet(dir_xls, sheetName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(sheetList != null && sheetList.size() != 0) {
			
			// 得到模板替换变量值
			List<Object> variableList = sheetList.get(2);
			String[] variableStr = new String[6]; // 初始化6
			for (int i = 1; i < variableList.size(); i++) {
				// 给数组赋值
				variableStr[i-1] = (String)variableList.get(i);
//				System.out.println(variableStr[i-1]);
			}
			// 生成时间
			variableStr[5] = Date.getNowTime().getYMD();
			// 变量bean定义
			VariableBean varBean = new VariableBean(variableStr);
			
			List<Object> filedList = new ArrayList<Object>();
			List<ColumnData> dataList = new ArrayList<ColumnData>(); // 封装bean的list
			ColumnData clmData = null;
			// 遍历数据表
			for(int i = 5;i < sheetList.size(); i++) {
				filedList = sheetList.get(i);
				if(filedList != null && filedList.size() != 0) {
					String v_str = (String)filedList.get(1);
					if(v_str == null || "".equals(v_str)) {
						break;
					}
					clmData = new ColumnData();
					// 封装field
					clmData.setIndexNo((String)filedList.get(0));        // 序号
					clmData.setHumpName(getHumpName(v_str));             // 驼峰命名
					clmData.setColumnName(v_str);                        // 字段
					clmData.setDataType((String)filedList.get(2));
					clmData.setColumnComment((String)filedList.get(3));  // 注释
					clmData.setFieldRule((String)filedList.get(4));      // 字段要求
					clmData.setApiCode((String)filedList.get(5));        // 所属接口编号
					clmData.setFiledType((String)filedList.get(6));      // 数据库字段类型
					dataList.add(clmData);
					//System.out.println(getHumpName(v_str));
				}
			}
			variableList = sheetList.get(3); // 得到表定义
			String tableName = (String)variableList.get(1);
			varBean.setTableName(tableName);
			
//			varBean.setTemplateClass("Person");
//			varBean.setTemplateName("人类");
//			varBean.setAuthor("邹德福");
//			varBean.setSystemCode("mes");
//			varBean.setCreateTime(Date.getNowTime().getYMD());
//			varBean.setBeanDesc("这是个描述人行为的业务模块，在这里有很多的封装方法");
			try {
				// varBean.setSelectClm(selectClm);
				// varBean.setCondition(condition);
				String filed = getBeanFeilds(dataList, varBean, isSqlScript);
				varBean.setBeanField(filed);
				// 如果只生成脚本，只读取脚本文件
				if(isSqlScript) {
					dir_temp = dir_sql;
				}
				CommonPageParser.generateAllFiles(dir_temp, varBean, isSqlScript);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }
    
    
 
    
    /**
     * 生成实体Bean 的属性和set,get方法
     * @param dataList
     * @param varBean
     * @param isSqlScript 是否生成
     * @return
     * @throws SQLException
     */
    public static String getBeanFeilds(List<ColumnData> dataList, VariableBean varBean, Boolean isSqlScript) {
    	StringBuilder str = new StringBuilder();
    	StringBuilder getset = new StringBuilder();
    	
    	StringBuilder selectClm = new StringBuilder(); // 检索select部分
    	StringBuilder searchCondition = new StringBuilder(); // 检索条件部分
    	StringBuilder dmlCondition = new StringBuilder(); // 删除和更新条件部分
    	StringBuilder insertCondition = new StringBuilder(); // 插入语句条件部分
    	StringBuilder updateClm = new StringBuilder(); // 跟新语句update部分
    	StringBuilder insertClm = new StringBuilder(); // 更新语句insertClm部分
    	StringBuilder createSql = new StringBuilder(); // sql脚本create部分
    	StringBuilder commentSql_oracle = new StringBuilder(); // oracle sql脚本注释部分
    	StringBuilder commnetSql_sqlserver = new StringBuilder(); // sqlserver sql脚本注释部分
    	
    	
    	int i = 0;
        for(ColumnData d : dataList){
        	String name = d.getHumpName();//得到驼峰法命名的变量
			String type =  d.getDataType();
			String comment =  d.getColumnComment();
			String fieldRule = d.getFieldRule();
			String apiCode = d.getApiCode();
			String indexNo = d.getIndexNo();
			String columnName = d.getColumnName();
			String fileType = d.getFiledType();
			
			if(!isSqlScript) {
	//			type=this.getType(type);
				String maxChar=name.substring(0, 1).toUpperCase();
				// 生成注释
				str.append("/** ").append(comment).append("*/").append("\r\t");
				//  生成类似@Doc(index="01" ,info="用户编号。2^32位长度"  ,value={"User.Retrieve.A001"})
				str.append("@Doc(index=\"").append(indexNo).append("\" ,info=\"").append(fieldRule).append("\"  ,value={\"").append(apiCode).append("\"})");
				str.append("\r\t").append("private ").append(type+" ").append(name).append("; // ").append(comment).append("\r\t\r\t");
				String method=maxChar+name.substring(1, name.length());
				getset.append("\r\t").append("public ").append(type+" ").append("get"+method+"() {\r\t");
				getset.append("    return this.").append(name).append(";\r\t}");
				getset.append("\r\t").append("public void ").append("set"+method+"("+type+" "+name+") {\r\t");
				getset.append("    this."+name+"=").append(name).append(";\r\t}");
				
				// 美观用的空格
				String tabspace = "\t\t\t\t\t\t\t  ";
				if(i == 0) {
					// select 字段
					selectClm.append(tabspace).append(columnName).append(" ").append(name).append("\r");
					// insert value字段
					insertCondition.append(tabspace).append("':").append(name).append("'").append("\r");
					// update 字段
					updateClm.append(tabspace + "    ").append(columnName).append(" = ").append("':").append(name).append("'").append("\r");
					// insert 字段
					insertClm.append(tabspace).append(columnName).append("\r");
					// 删除和更新的检索条件
					dmlCondition.append(tabspace).append("   AND  ").append(columnName).append(" = ':").append(name).append("'").append("\r");
				} else {
					// , company_address companyAddress
					selectClm.append(tabspace).append(",").append(columnName).append(" ").append(name).append("\r");
					// ,':companyAddress'
					insertCondition.append(tabspace).append(",':").append(name).append("'").append("\r");
					// <[ and  company_address = ':companyAddress' ]>
					updateClm.append(tabspace).append("<[ ,").append(columnName).append(" = ").append("':").append(name).append("' ]>").append("\r");
					// ,companyAddress
					insertClm.append(tabspace).append(",").append(columnName).append("\r");
					// <[ and  company_address = ':companyAddress' ]>
					dmlCondition.append(tabspace).append("<[ AND  ").append(columnName).append(" = ':").append(name).append("' ]>").append("\r");
				}
				// 检索条件<[ and  name = ':name' ]>
				searchCondition.append(tabspace).append("<[ AND  ").append(columnName).append(" = ':").append(name).append("' ]>").append("\r");
			} else {
	        	// [name] [varchar](50),
				// 美观用的空格
				String tabspace = "\t\t";
				createSql.append(tabspace).append(columnName).append(" ").append(fileType).append(",\r");
				// oracle COMMENT ON COLUMN LDRK_GIRD_PERSON_CHOICE.ID IS '主键ID';
				commentSql_oracle.append("COMMENT ON COLUMN ").append(varBean.getTableName()).append(".").append(columnName).append(" IS ").append("'").append(comment).append("';\r");
				// EXEC   sp_addextendedproperty 'MS_Description', '性别', 'user', dbo, 'table', person, 'column', sex
				commnetSql_sqlserver.append("EXEC   sp_addextendedproperty 'MS_Description', '")
				.append(comment).append("', 'user', dbo, 'table', ").append(varBean.getTableName())
				.append(", 'column', ").append(columnName).append(";\r");
			}
			i ++;
        } 
        varBean.setSelectClm(selectClm.toString());
        varBean.setCondition(searchCondition.toString());
        varBean.setInsertCondition(insertCondition.toString());
        varBean.setUpdateClm(updateClm.toString());
        varBean.setDmlCondition(dmlCondition.toString());
        varBean.setInsertClm(insertClm.toString());
        
        String createSqlStr = createSql.toString();
        if(createSqlStr.length()>0)
        	createSqlStr = createSqlStr.substring(0,createSqlStr.lastIndexOf(","));
        varBean.setCreateSql(createSqlStr);
        varBean.setCommentSql(commentSql_oracle.toString() + "@" + commnetSql_sqlserver);
        argv = str.toString();
        method = getset.toString();
		return argv + method;
    }
    
    /**
     * 得到驼峰法的变量命名
     * @param name
     * @return
     */
    public static String getHumpName(String name) {
    	//拆分字段，并且组装成驼峰法命名规则
		String[] spName = name.toLowerCase().split("_");
		if(spName.length == 0) {
			return name;
		}
		name = "";
		for(String na : spName){
			name += na.substring(0, 1).toUpperCase() + na.substring(1).toLowerCase();
		}
		name = name.substring(0, 1).toLowerCase() + name.substring(1);
		return name;
	}
	
	public static void main(String[] args) {
		
		createFile("plm系统接口定义.xls", "Person", true);
		
//		// TODO Auto-generated method stub
//		System.out.print(getProjectPath());
//		// 变量bean定义
//		VariableBean varBean = new VariableBean();
//		varBean.setTemplateClass("Person");
//		varBean.setTemplateName("人类");
//		varBean.setAuthor("邹德福");
//		varBean.setSystemCode("mes");
//		varBean.setCreateTime(Date.getNowTime().getYMD());
//		varBean.setBeanDesc("这是个描述人行为的业务模块，在这里有很多的封装方法");
//		try {

		
		
//			generateAllFiles(dir, varBean);
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}

