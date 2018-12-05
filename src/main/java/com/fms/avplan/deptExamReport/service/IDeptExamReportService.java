package com.fms.avplan.deptExamReport.service;


import com.fms.avplan.deptExamReport.report.DeptReport;

import java.util.List;
import java.util.Map;

/**
 * 部门考核报表接口
 */
public interface IDeptExamReportService {
    List<DeptReport> queryList(DeptReport params);

    void updateDeptReport(DeptReport report);

    List<DeptReport> queryUnitDepts();

    int saveDatas();

    String exportDatas(DeptReport report,String dept);

    void deleteData(DeptReport report);

    List<DeptReport> queryReportDateList();

    Map<String,Map<String,Map<String,Object>>> queryReportReasonById(String innerid);

    void reSaveDetailReport(String date);

    Map<String, Map<String, Object>> getTotalMap(DeptReport select);
}
