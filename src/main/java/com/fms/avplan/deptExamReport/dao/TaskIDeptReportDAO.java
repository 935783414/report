/**
 * 
 */
package com.fms.avplan.deptExamReport.dao;

import java.util.List;
import com.fms.avplan.deptExamReport.report.TaskDept;

/**
 * @author David
 * @createtime 2018年7月2日
 */
public interface TaskIDeptReportDAO {
	
	    List<TaskDept> queryList(TaskDept params);

	    int updateDeptReport(TaskDept params);

	    int saveDeptReport();

	    String exportDeptReport(TaskDept params);

	    List<TaskDept> queryUnitDepts();

	    void deleteData(TaskDept report);

	    List<TaskDept> queryReportDateList();

}
