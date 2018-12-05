package com.fms.avplan.deptWarnReport.service;


import com.fms.avplan.deptExamReport.report.DeptReport;

import java.util.List;
import java.util.Map;

/**
 * 部门预警报表接口
 */
public interface IDeptWarnReportService {
    List<DeptReport> queryList(DeptReport params);

    void updateDeptReport(DeptReport report);

    List<DeptReport> queryUnitDepts();

    int saveDatas();

    String exportDatas(DeptReport report);

    void deleteData(DeptReport report);

    List<DeptReport> queryReportDateList();

    Map<String,Map<String,Map<String,Object>>> queryReportReasonById(String innerid);

    void reSaveDetailReport(String date);
}
