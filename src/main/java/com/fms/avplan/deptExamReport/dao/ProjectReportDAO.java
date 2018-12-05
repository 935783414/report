/**
 * 
 */
package com.fms.avplan.deptExamReport.dao;

import java.util.List;

import com.fms.avplan.deptExamReport.report.ProjectDept;

/**
 * @author David
 * @createtime 2018年7月9日
 */
public interface ProjectReportDAO {
	
	List<ProjectDept> queryList(ProjectDept params);
}
