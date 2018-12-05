/**
 * 
 */
package com.fms.avplan.deptExamReport.dao;

import com.fms.avplan.deptExamReport.report.OrderReport;

import java.util.List;

/**
 * @author David
 * @createtime 2018年8月7日
 */
public interface OrderReportDAO {

	List<OrderReport> queryList(OrderReport param);

}
