/**
 * 
 */
package com.fms.avplan.deptExamReport.service.impl;

import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.avplan.deptExamReport.dao.ProjectReportDAO;
import com.fms.avplan.deptExamReport.dao.TaskIDeptReportDAO;
import com.fms.avplan.deptExamReport.report.ProjectDept;
import com.fms.avplan.deptExamReport.report.TaskDept;
import com.fms.avplan.deptExamReport.service.ProjectReportService;
import com.fms.avplan.deptExamReport.service.TaskReportService;

/**
 * @author David
 * @createtime 2018年7月6日
 */
@Xjava(id="projectReportService")
public class ProjectReportServiceImpl implements ProjectReportService {

	 @Xjava
	    private ProjectReportDAO projectreportdao;


	@Override
	public List<ProjectDept> queryList(ProjectDept params) {
		return projectreportdao.queryList(params);
	 
	}
	


	
	
	

}
