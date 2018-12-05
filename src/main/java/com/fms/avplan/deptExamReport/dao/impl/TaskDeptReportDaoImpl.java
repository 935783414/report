/**
 * 
 */
package com.fms.avplan.deptExamReport.dao.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.Xjava;
import org.springframework.web.context.ContextLoader;

import com.fms.avplan.deptExamReport.dao.TaskIDeptReportDAO;
import com.fms.avplan.deptExamReport.report.DeptHead;
import com.fms.avplan.deptExamReport.report.TaskDept;
import com.fms.platform.common.BaseDAO;

/**
 * @author David
 * @createtime 2018年7月2日
 */

@Xjava(id="taskDeptReportDAO")
public class TaskDeptReportDaoImpl extends BaseDAO<TaskDept> implements TaskIDeptReportDAO {

	
	  private Map<String, List<TaskDept>> map = new LinkedHashMap<String, List<TaskDept>>();
	  private static String  SHEETNAME = "项目任务完成表";

	
	
	    @Override
	    public List<TaskDept> queryList(TaskDept params) {
	        return super.queryRecords("XSQL_taskreport_deptexamList",params);
	    }

	
	    @Override
	    public List<TaskDept> queryUnitDepts() {
	        return super.queryRecords("XSQL_taskreport_queryDepts",new TaskDept());
	    }

		
		@Override
		public List<TaskDept> queryReportDateList() {

			 return super.queryRecords("XSQL_taskreport_reportDateList",new TaskDept());
		}

	
		@Override
		public int updateDeptReport(TaskDept params) {
			 return super.update("XSQL_taskreport_updateList",params);
		}


		@Override
		public int saveDeptReport() {
			 int recordCount = 0;
		        try {

		            List<DeptHead> deptHeadList = new ArrayList<DeptHead>();

		            // 得到产品研发的统计数据
		            TaskDept report = new TaskDept();
                   
		            writeCollectionList("XSQL_avplan_taskdelayList_design", "ReportDesign", report, deptHeadList,
		                    "产品研发");

		         // 得到工艺部的超时统计数据
		            report = new TaskDept();
		            writeCollectionList("XSQL_avplan_taskdelayList_technics", "ReportTechnics", report, deptHeadList,
		                    "工艺研发");

		         // 得到工艺部的超时统计数据
		            report = new TaskDept();
		            writeCollectionList("XSQL_avplan_taskdelayList_project", "ProjectPlan", report, deptHeadList,
		                    "项目计划");
		            System.out.println("项目计划");
		         // 得到工艺部的超时统计数据
		            report = new TaskDept();
		            writeCollectionList("XSQL_avplan_taskdelayList_plan", "PlanController", report, deptHeadList,
		                    "计划调度部");
		         // 得到工艺部的超时统计数据
		            report = new TaskDept();
		            writeCollectionList("XSQL_avplan_taskdelayList_produc", "Produceplan", report, deptHeadList,
		                    "生产计划");
		            
		            
		            
		            // 循环各部门将数据插入report报表服务器中
		            System.out.println("循环各部门将数据插入report报表服务器中");
		            for (Map.Entry<String, List<TaskDept>> item : map.entrySet()) {
		                if (!Help.isNull(item.getValue())) {
		                    recordCount = recordCount +  item.getValue().size();
                   
		                    XJava.getXSQL("XSQL_avplan_taskDEPT_EXAM_REPORT_insert").executeUpdatesPrepared(item.getValue());
		                }
		            }

                 System.out.println("插入成功");
		        } catch (RTemplateException e) {
		            e.printStackTrace();
		            return -1;
		        }
		        return recordCount;
		}

		
    	@Override
		public String exportDeptReport(TaskDept report) {
			  String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/downloadFile");
	            String filePath = path+"/"+"导出报表"+(new Date()).getFullMilli_ID()+".xlsx";
	            clearPath(path,filePath);
	            System.out.println("导出路径:"+filePath);
//	            String path = "D:\\错误提示\\ReportNormal.xlsx";
	            String ymd = ""; // 年月日
	            try {
	                RWorkbook rWorkbook = null;
	                List<DeptHead> deptHeadList = new ArrayList<DeptHead>();
	                        Calendar now = Calendar.getInstance();

	                // 没有传入时间，用当前日期
	                if(Help.isNull(report.getPlanstarttime())) {

	                    ymd =  String.format("%02d", now.get(Calendar.YEAR)) + "年"
	                            + String.format("%02d", (now.get(Calendar.MONTH) + 1))  + "月"
	                            + String.format("%02d", now.get(Calendar.DAY_OF_MONTH)) + "日";

	                } else {
	                    ymd = report.getPlanstarttime();
	            }

	            String sheetname = ymd + SHEETNAME;

	        //因为产品研发、工艺研发、项目计划、项目计划调度、生产计划列一样(展示字段一样)，所以可以通用一个查询sql 
	            // 得到研发部的超时统计数据
	            report.setOwnername("产品研发");

	            writeCollectionList("XSQL_avplan_reportlist_taskDEPT_EXAM_REPORT", "ReportDesign", report, deptHeadList,
	                    "产品研发");
   
	            
	            // 得到工艺研发的超时统计数据
	            report.setOwnername("工艺研发");
	            writeCollectionList("XSQL_avplan_reportlist_taskDEPT_EXAM_REPORT", "ReportTechnics", report, deptHeadList,
	                    "工艺研发");
	           

	            // 得到项目计划的超时统计数据
	            report.setOwnername("项目计划");
	            writeCollectionList("XSQL_avplan_reportlist_taskDEPT_EXAM_REPORT", "ProjectPlan", report, deptHeadList,
	                    "项目计划");
	            
	         // 得到项目计划的超时统计数据
	            report.setOwnername("计划调度部");
	            writeCollectionList("XSQL_avplan_reportlist_taskDEPT_EXAM_REPORT", "PlanController", report, deptHeadList,
	                    "计划调度部");
	         // 得到工艺部的超时统计数据
	            report.setOwnername("生产计划");
	            writeCollectionList("XSQL_avplan_reportlist_taskDEPT_EXAM_REPORT", "Produceplan", report, deptHeadList,
	                    "生产计划");

	            // 最后写合计
	            RTemplate v_RTemplate = (RTemplate) XJava.getObject("ReportHead");
	            rWorkbook = ReportHelp.toExcel(rWorkbook, sheetname, deptHeadList, v_RTemplate, true);


	            // 循环各部门写入报表
	            for (Map.Entry<String, List<TaskDept>> item : map.entrySet()) {
	                if (!Help.isNull(item.getValue())) {
	                    rWorkbook = ReportHelp.toExcel(rWorkbook, sheetname, item.getValue(),
	                            (RTemplate) XJava.getObject(item.getKey()), true);
	                }
	            }
	            ExcelHelp.save(rWorkbook, filePath);
	        } catch (RTemplateException e) {
	            e.printStackTrace();
	        }
	   	        return filePath;
		}

		/* (non-Javadoc)
		 * @see com.fms.avplan.deptExamReport.dao.TaskIDeptReportDAO#deleteData(com.fms.avplan.deptExamReport.report.TaskDept)
		 */
		@Override
		public void deleteData(TaskDept report) {
			 super.update("XSQL_taskreport_delete",report);
			
		}
		 private void clearPath(String path, String filePath) {
		        File directory = new File(path);
		        if(!directory.exists()){
		            directory.mkdirs();
		        }
		        File thisFile = new File(filePath);
		        if(thisFile.exists()){
		            thisFile.delete();
		        }
		        File[] files = directory.listFiles();
		        for(File file : files){
		            long time = file.lastModified();
		            //删除10天前的文件
		            if((System.currentTimeMillis()-time)>10*24*60*60*1000){
		                file.delete();
		            }
		        }
		    }


		    /**
		     * 通用各部门写报表工具
		     * 将报表数据写入集合，准备插入到数据库中形成原始数据
		     * @param xsqlId
		     * @param v_RTemplate_str
		     * @param report
		     * @param deptHeadList
		     * @param departname
		     * @return
		     * @throws RTemplateException
		     */
		   private void writeCollectionList(String xsqlId, String v_RTemplate_str, TaskDept report, List<DeptHead> deptHeadList, String departname)
		            throws RTemplateException {

		        DeptHead deptHead = new DeptHead(); // 合计头部对象
		        deptHead.setDepartname(departname);
		        List<TaskDept> datas = (List<TaskDept>) XJava.getXSQL(xsqlId).query(report);

		        // 将旧的原因和申诉带过来
		        if(!Help.isNull(datas)) {
		            processBeforeReason(v_RTemplate_str, datas);
		        }

		        // 有数据情况
		        if(!Help.isNull(datas)) {

		            // 得到去重合同号记录
		            List<TaskDept> tempFindSames = new ArrayList(datas);
		            Help.toDistinct(tempFindSames,"orderno");
		            datas.get(0).setCountOrderNo("" + tempFindSames.size());
		            deptHead.setTaskInfo(datas.size() + "项/" + tempFindSames.size() + "份");
		        } else {
		            deptHead.setTaskInfo("无");
		        }

		        deptHeadList.add(deptHead);   
		        map.put(v_RTemplate_str, datas);

		    }


		    /**
		     * 获取之前的数据
		     * @param v_RTemplate_str
		     * @param datas
		     */
		   private void processBeforeReason(String v_RTemplate_str,List<TaskDept> datas) {
		        String unitname = "";

		        for(TaskDept taskdept :datas) {
		        	TaskDept report = new TaskDept();
		            if("ReportDesign".equals(v_RTemplate_str)) {
		                report.setSuborderno(taskdept.getSuborderno());
		                report.setName(taskdept.getName());
		                unitname = "产品研发";
		            }
		            else if("ReportTechnics".equals(v_RTemplate_str) ) {
		                report.setSuborderno(taskdept.getSuborderno());
		                report.setName(taskdept.getName());
		                unitname = "工艺研发";
		            }  else if("ProjectPlan".equals(v_RTemplate_str) ) {
		                report.setSuborderno(taskdept.getSuborderno());
		                report.setName(taskdept.getName());
		                unitname = "项目计划";
		            } else if("PlanController".equals(v_RTemplate_str) ) {
		                report.setSuborderno(taskdept.getSuborderno());
		                report.setName(taskdept.getName());
		                unitname = "计划调度部";
		            } else if("Produceplan".equals(v_RTemplate_str) ) {
		                report.setSuborderno(taskdept.getSuborderno());
		                report.setName(taskdept.getName());
		                unitname = "生产计划";
		            } 
		            
		        	report.setSuborderno(taskdept.getSuborderno());
	                report.setOwnername(taskdept.getOwnername());
	                unitname =taskdept.getOwnername();
		            report.setOwnername(unitname);
		            List<Map<String,String>> obj= getResult("XSQL_QUERY_taskDEPT_EXAM_REPORT_BY_ROWNUM", report);

		            // 获得以前的数据
		           /* if(!Help.isNull(obj) && obj.size()>0) {
		                Map<String,String> tempMap = obj.get(0);
		                // 铸造的只对系统bug的原因进行提取
		                if(
		                        !"ReportPrecisionCasting".equals(v_RTemplate_str) ||
		                        ("ReportPrecisionCasting".equals(v_RTemplate_str) && String.valueOf(tempMap.get("REASON")).contains("系统BUG") )
		                   ) {
		                	taskdept.setReason(String.valueOf(tempMap.get("REASON")));
		                    taskdept.setIsExam(String.valueOf(tempMap.get("ISEXAM")));
		                }
		                taskdept.setAppeal(String.valueOf(tempMap.get("APPEAL")));

		            } else {
		                // 对于采购新增项原因的处理
		                if("ReportPurchasing".equals(v_RTemplate_str)) {
		                    processPurchasingNewReason(report,taskdept);
		                }
		            }*/
		        }
		    }

		    /**
		     * 处理采购新增项原因
		     * @param report 条件reprot
		     * @param deptReport datas中report值
		     */
		 /*  private void processPurchasingNewReason(TaskDept report,TaskDept deptReport) {

		                // 采购的原因
		                if(("标准件".equals(deptReport.getPutype())) || "外购件".equals(deptReport.getPutype())
		                        || "毛坯外协件".equals(deptReport.getPutype()))
		                {
		                    int i= 0;
		                    String xsql = "XSQL_purchasing_exam_stock_info";

		                    if( "毛坯外协件".equals(deptReport.getPutype())) {
		                        xsql = "XSQL_purchasing_exam_stock_info_rough";
		                    }
		                    List<Map<String,String>> obj=  (List<Map<String,String>>)XJava.getXSQL(xsql).query(report);
		                    String reason = "涉及物资" + obj.size() + "项;\n";
		                    StringBuilder sb = new StringBuilder();
		                    StringBuilder sb1 = new StringBuilder();
		                    sb.append(reason);
		                    sb1.append(reason);
		                    for(Map<String,String> map:obj) {
		                        sb.append("路线单号:").append(String.valueOf(map.get("PART_PLAN_NO")).replace(" ","")).append(",图号：").append(String.valueOf(map.get("ITEM_CODE")))
		                                    .append("(").append(String.valueOf(map.get("ITEM_NAME")))
		                                    .append("),需求数量：").append(String.valueOf(map.get("ORDER_QTY"))).append(",完成数量：").append(String.valueOf(map.get("PLAN_QTY"))).append(",最后入库时间：")
		                                    .append(String.valueOf(map.get("LASTINDATE"))).append(",最后入库数量：")
		                                    .append(String.valueOf(map.get("LASTINQTY"))).append(",现时库存量：").append(String.valueOf(map.get("CURRENTSTOCKS"))).append(";\n");




		                        if(i<3 && "标准件".equals(deptReport.getPutype())) {
		                            sb1.append(String.valueOf(map.get("ITEM_NAME")));

		                        } else if(i<40  && !"标准件".equals(deptReport.getPutype())) {
		                            sb1.append(String.valueOf(map.get("ITEM_NAME")))
		                                    .append(":最后入库时间：").append(String.valueOf(map.get("LASTINDATE"))).append(",最后入库数量：")
		                                    .append(String.valueOf(map.get("LASTINQTY"))).append(",现时库存量：").append(String.valueOf(map.get("CURRENTSTOCKS"))).append(";\n");
		                        }
		                        i++;
		                    }
		                    if("标准件".equals(deptReport.getPutype())) {

		                        sb1.append("等,以成品最终入库为最终考核时效。");
		                        deptReport.setIsExam("暂不考核");

		                    }
		                    deptReport.setReason(sb1.toString());

//		                    // 将Clob数据放到
//		                    Map<String,String> paramMap = new HashMap<String,String>();
//		                    paramMap.put("INNERID", deptReport.getInnerid());
//		                    XJava.getXSQL("XSQL_avplan_exam_delayList_technics").executeUpdate(paramMap);
//		                    XJava.getXSQL("XSQL_avplan_exam_delayList_technics").executeUpdateBLob()

		                } else {
		                    if("外购执行机构".equals(report.getPutype())) {
		                        report.setPutype("执行机构");
		                    } else if("定向采购".equals(report.getPutype())) {
		                        report.setPutype("阀体组件");
		                    } else if("外购附件".equals(report.getPutype())) {
		                        report.setPutype("附件");
		                    }
		                    List<Map<String,String>> obj=  (List<Map<String,String>>)XJava.getXSQL("XSQL_avplan_exam_purchasing_reason").query(report);
		                    StringBuilder sb = new StringBuilder();
		                    for(Map<String,String> map:obj) {
		                        sb.append(String.valueOf(map.get("REASON"))).append("\n");

		                    }
		                    deptReport.setReason(sb.toString());
		                }
		            }

*/


		    /**
		     * 根据sqlid获得结果
		     * @param xsqlid
		     * @param report
		     * @return
		     */
		    private List<Map<String,String>>  getResult(String xsqlid, TaskDept report) {
		        List<Map<String,String>> obj=  (List<Map<String,String>>)XJava.getXSQL(xsqlid).query(report);
		        return  obj;
		    }
	
}
