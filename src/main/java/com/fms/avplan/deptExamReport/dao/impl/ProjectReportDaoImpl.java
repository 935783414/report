/**
 * 
 */
package com.fms.avplan.deptExamReport.dao.impl;



import java.util.List;

import org.hy.common.xml.annotation.Xjava;

import com.fms.avplan.deptExamReport.dao.ProjectReportDAO;
import com.fms.avplan.deptExamReport.report.ProjectDept;
import com.fms.platform.common.BaseDAO;

/**
 * @author David
 * @createtime 2018年7月9日
 */
@Xjava(id="projectReportDAO")
public class ProjectReportDaoImpl extends BaseDAO<ProjectDept>implements ProjectReportDAO{


	@Override
	public List<ProjectDept> queryList(ProjectDept params) {
		return super.queryRecords("XSQL_report_projectList",params);
	}

}
