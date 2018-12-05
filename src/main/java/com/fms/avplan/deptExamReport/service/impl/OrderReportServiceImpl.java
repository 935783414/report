/**
 * 
 */
package com.fms.avplan.deptExamReport.service.impl;


import com.fms.avplan.deptExamReport.dao.OrderReportDAO;
import com.fms.avplan.deptExamReport.report.OrderReport;

import com.fms.avplan.deptExamReport.service.OrderReportService;

import org.hy.common.xml.annotation.Xjava;

import java.util.List;

/**
 * @author David
 * @createtime 2018年8月7日
 */
@Xjava(id="orderReportService")
public class OrderReportServiceImpl implements OrderReportService {

	 @Xjava
	    private OrderReportDAO orderReportDAO;


	@Override
	public List<OrderReport> queryList(OrderReport param) {
		return orderReportDAO.queryList(param);
	}
}
