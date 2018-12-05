package com.fms.avplan.deptWarnReport.dao;


import com.fms.avplan.deptExamReport.report.DeptReport;

import java.util.List;
import java.util.Map;

public interface IDeptWarnReportDAO {

    Map<String, Object> queryByInnerid(String innerid);

    List<DeptReport> queryList(DeptReport params);

    int updateDeptReport(DeptReport params);

    int saveDeptReport();

    String exportDeptReport(DeptReport params);

    List<DeptReport> queryUnitDepts();

    void deleteData(DeptReport report);

    List<DeptReport> queryReportDateList();

    List<Map<String, String>> queryAllDelayingReasonList(DeptReport report);

    List<Map<String, Object>> queryDesignDatas(DeptReport select);

    Map<String, Object> queryDetailReasons(String innerid);

    void reSaveDetailReport(String date);

    String queryMachingTotalReason(String suborderno);
}
