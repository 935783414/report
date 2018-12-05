/**
 * 
 */
package com.fms.avplan.deptExamReport.service.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;


import com.fms.avplan.deptExamReport.dao.TaskIDeptReportDAO;
import com.fms.avplan.deptExamReport.report.TaskDept;
import com.fms.avplan.deptExamReport.service.TaskReportService;

/**
 * @author David
 * @createtime 2018年7月6日
 */
@Xjava(id="taskDeptReportService")
public class TaskReportServiceImpl implements TaskReportService {

	 @Xjava
	    private TaskIDeptReportDAO taskideptReportDAO;
	
	@Override
	public List<TaskDept> queryList(TaskDept params) {
		return taskideptReportDAO.queryList(params);
	}

	
	@Override
	public int updateDeptReport(TaskDept params) {
		return taskideptReportDAO.updateDeptReport(params);
	}

	
	@Override
	public int saveDatas() {
		// TODO Auto-generated method stub
		return taskideptReportDAO.saveDeptReport();
	}

	
	@Override
	public String exportDeptReport(TaskDept params) {
		// TODO Auto-generated method stub
		return taskideptReportDAO.exportDeptReport(params);
	}

	
	@Override
	public List<TaskDept> queryUnitDepts() {
		// TODO Auto-generated method stub
		return taskideptReportDAO.queryUnitDepts();
	}

	@Override
	public void deleteData(TaskDept report) {
		// TODO Auto-generated method stub
		taskideptReportDAO.deleteData(report);
	}

	/* (non-Javadoc)
	 * @see com.fms.avplan.deptExamReport.service.TaskReportService#queryReportDateList()
	 */
	@Override
	public List<TaskDept> queryReportDateList() {
		// TODO Auto-generated method stub
		return taskideptReportDAO.queryReportDateList();
	}

}
