/**
 * 
 */
package com.fms.avplan.deptExamReport.dao.impl;



import com.fms.avplan.deptExamReport.dao.OrderReportDAO;
import com.fms.avplan.deptExamReport.report.OrderReport;
import com.fms.platform.common.BaseDAO;
import org.hy.common.xml.annotation.Xjava;

import java.util.List;

/**
 * @author David
 * @createtime 2018年8月7日
 */
@Xjava(id="orderReportDAO")
public class OrderReportDaoImpl extends BaseDAO<OrderReport>implements OrderReportDAO {

	@Override
	public List<OrderReport> queryList(OrderReport param) {
		return queryRecords("XSQL_report_orderProject",param);
	}
}
