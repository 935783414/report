package com.fms.avplan.deptExamReport.dao.impl;

import com.fms.avplan.deptExamReport.dao.IDeptExamReportDAO;
import com.fms.avplan.deptExamReport.model.PurchaseDeptReportModel1;
import com.fms.avplan.deptExamReport.report.DeptHead;
import com.fms.avplan.deptExamReport.report.DeptReport;
import com.fms.platform.common.BaseDAO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.report.ExcelHelp;
import org.hy.common.report.ReportHelp;
import org.hy.common.report.bean.RTemplate;
import org.hy.common.report.bean.RWorkbook;
import org.hy.common.report.error.RTemplateException;
import org.hy.common.xml.XJSON;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.Xjava;
import org.hy.common.xml.plugins.XSQLGroupResult;
import org.springframework.web.context.ContextLoader;

import java.io.File;
import java.util.*;


@SuppressWarnings("unused")
@Xjava(id = "deptReportDAO")
public class DeptExamReportDAOImpl extends BaseDAO<DeptReport> implements IDeptExamReportDAO {

    private static Logger logger = Logger.getLogger(DeptExamReportDAOImpl.class);
//    private Map<String, List<DeptReport>> map = new LinkedHashMap<>();


    private String[] shiyeDepts = {"ReportGlobal", "ReportButterfly", "ReportAdvanced", "ReportBoll", "ReportControlled", "ReportCv3000"};
    private String[] technicsOrDesign = {"ReportDesign", "ReportTechnics"};
    private String[] technicsOrDesignValue = {"产品研发", "工艺研发"};
    private String[] castingDepts = {"ReportSandCasting", "ReportPrecisionCasting"};

    @Override
    public Map<String, Object> queryByInnerid(String innerid) {
        HashMap<Object, Object> params = new HashMap<>();
        params.put("innerid", innerid);
        //noinspection unchecked
        List<Map<String, Object>> result = (List<Map<String, Object>>) super.getXSQL("XSQL_DEPT_EXAM_REPORT_selectById").query(params);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public List<DeptReport> queryList(DeptReport params) {
        return super.queryRecords("XSQL_report_deptexamList", params);
    }

    @Override
    public int updateDeptReport(DeptReport params) {
        return super.update("XSQL_report_updateList", params);
    }


    @Override
    public List<DeptReport> queryUnitDepts() {
        return super.queryRecords("XSQL_report_queryDepts", new DeptReport());
    }

    @Override
    public void deleteData(DeptReport report) {
        super.update("XSQL_report_delete", report);
        super.update("XSQL_report_delete_2", report);
    }

    @Override
    public List<DeptReport> queryReportDateList() {
        return super.queryRecords("XSQL_report_reportDateList", new DeptReport());
    }

    @Override
    public List<Map<String, String>> queryAllDelayingReasonList(DeptReport report) {
        //noinspection unchecked
        return (List<Map<String, String>>) super.getXSQL("XSQL_alldelaying_reason").query(report);
    }

    @Override
    public List<Map<String, Object>> queryDesignDatas(DeptReport select) {
        //noinspection unchecked
        return (List<Map<String, Object>>) super.getXSQL("XSQL_avplan_exam_desing_technics_reason_detail").query(select);
    }

    @Override
    public Map<String, Object> queryDetailReasons(String innerid) {
        Map<String, Object> params = new HashMap<>();
        params.put("innerid", innerid);
        //noinspection unchecked
        List<Map<String, Object>> result = (List<Map<String, Object>>) super.getXSQL("XSQL_delaying_reason_selectclob").query(params);
        if (result != null && result.size() > 0) return result.get(0);
        return null;
    }

    public void reSaveDetailReport(String date) {
//        List<DeptReport> reportList =
        DeptReport select = new DeptReport();
        select.setReportdate(date);
//        select.setUnitname("调节阀事业部");
        //noinspection unchecked
        List<Map<String, String>> examList = (List<Map<String, String>>) XJava.getXSQL("XSQL_QUERY_DEPT_EXAM_REPORT_BY_ROWNUM").query(select);
        processBeforeReason(examList);
    }

    @Override
    public String queryMachingTotalReason(String suborderno) {
        DeptReport select = new DeptReport();
        select.setSuborderno(suborderno);
        //noinspection unchecked
        List<Map<String, String>> totals = (List<Map<String, String>>) XJava.getXSQL("XSQL_cloud_exam_maching_reason_total").query(select);
        if (totals != null && totals.size() > 0) {
            Map<String, String> totalMap = totals.get(0);
            return totalMap.get("TOTAL_REASON");
        }
        return null;
    }

    @Override
    public int saveDeptReport() {

        int recordCount = 0;
        try {

            List<DeptHead> deptHeadList = new ArrayList<>();
            DeptReport report;

            Map<String, List<DeptReport>> map = new LinkedHashMap<>();

            //准备临时数据
            prepareTempData();
            //得到研发部的超时统计数据
            report = new DeptReport();
            logger.info("研发部数据抽取开始---------------------------------------");
            writeCollectionList("XSQL_avplan_exam_delayList_design", "ReportDesign", report, deptHeadList,
                    "研发部", false,map);
            logger.info("研发部数据抽取完成");
            // 得到工艺部的超时统计数据
            report = new DeptReport();
            logger.info("工艺部数据抽取开始---------------------------------------");
            writeCollectionList("XSQL_avplan_exam_delayList_technics", "ReportTechnics", report, deptHeadList,
                    "工艺部", false,map);

            // 得到铸造部的超时统计数据
            logger.info("砂铸部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setGytype("砂铸");
            report.setUnitname("砂铸部");
            writeCollectionList("XSQL_avplan_exam_delayList_casting", "ReportSandCasting", report, deptHeadList,
                    "砂铸部", false,map);
            logger.info("砂铸部数据抽取完成");

            // 得到铸造部的超时统计数据
            logger.info("精铸部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setGytype("精铸");
            report.setUnitname("精铸部");
            writeCollectionList("XSQL_avplan_exam_delayList_casting", "ReportPrecisionCasting", report,
                    deptHeadList, "精铸部", false,map);
            logger.info("精铸部数据抽取完成");

            // 得到采购部的超时统计数据
            logger.info("采购部数据抽取开始---------------------------------------");
            report = new DeptReport();
            writeCollectionList("XSQL_avplan_exam_delayList_purchasing", "ReportPurchasing", report,
                    deptHeadList, "采购部", false,map);
            logger.info("采购部数据抽取完成");

            //得到调节阀事业部的超时统计数据
            logger.info("调节阀事业部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setDeptNameCons("调节阀装配入库");
            report.setUnitname("调节阀事业部");
            report.setDetpCode("0720");
            writeCollectionList("XSQL_avplan_exam_delayList_packaging", "ReportGlobal", report, deptHeadList,
                    "调节阀事业部", false,map);
            logger.info("调节阀事业部数据抽取完成");

            // 得到球阀事业部的超时统计数据
            logger.info("球阀事业部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setDeptNameCons("球阀装配入库");
            report.setUnitname("球阀事业部");
            report.setDetpCode("0730");
            writeCollectionList("XSQL_avplan_exam_delayList_packaging", "ReportBoll", report, deptHeadList,
                    "球阀事业部", false,map);
            logger.info("球阀事业部数据抽取完成");

            // 得到蝶阀事业部的超时统计数据
            logger.info("蝶阀事业部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setDeptNameCons("蝶阀装配入库");
            report.setUnitname("蝶阀事业部");
            report.setDetpCode("0760");
            writeCollectionList("XSQL_avplan_exam_delayList_packaging", "ReportButterfly", report, deptHeadList,
                    "蝶阀事业部", false,map);
            logger.info("蝶阀事业部数据抽取完成");

            // 得到高端阀事业部的超时统计数据
            logger.info("高端阀事业部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setDeptNameCons("高端阀装配入库");
            report.setUnitname("高端阀事业部");
            report.setDetpCode("0740");
            writeCollectionList("XSQL_avplan_exam_delayList_packaging", "ReportAdvanced", report, deptHeadList,
                    "高端阀事业部", false,map);
            logger.info("高端阀事业部数据抽取完成");

            // 得到控制器件事业部的超时统计数据
            logger.info("控制器件事业部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setDeptNameCons("控制器件装配入库");
            report.setUnitname("控制器件事业部");
            report.setDetpCode("0900");
            writeCollectionList("XSQL_avplan_exam_delayList_packaging", "ReportControlled", report,
                    deptHeadList, "控制器件事业部", false,map);
            logger.info("控制器件事业部数据抽取完成");

            // 得到CV3000事业部的超时统计数据
            logger.info("CV3000事业部数据抽取开始---------------------------------------");
            report = new DeptReport();
            report.setDeptNameCons("CV3000事业部装配入库");
            report.setUnitname("CV3000事业部");
            report.setDetpCode("1304");
            writeCollectionList("XSQL_avplan_exam_delayList_packaging", "ReportCv3000", report, deptHeadList,
                    "CV3000事业部", false,map);
            logger.info("CV3000事业部数据抽取完成");


            // 循环各部门将数据插入report报表服务器中
            for (Map.Entry<String, List<DeptReport>> item : map.entrySet()) {
                if (!Help.isNull(item.getValue())) {
                    recordCount = recordCount + item.getValue().size();
                    System.out.println(item.getKey()+"条数:" + item.getValue().size());
                    for(DeptReport re:item.getValue()){
                        if(StringUtils.trimToEmpty(re.getReason()).length()>1333){
                            System.out.println("长度大于1333的："+re.getUnitname()+":"+re.getOrderno()+"("+re.getSuborderno()+")");
                        }
                    }
                    XJava.getXSQL("XSQL_avplan_DEPT_EXAM_REPORT_insert").executeUpdatesPrepared(item.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        System.out.println("导出完成....");
        return recordCount;
    }

    /**
     * 导出报表
     *
     * @param report 根据时间出报表 传入时间格式：YYYY年MM月dd日
     * @return 导出报表的path
     */
    @Override
    public String exportDeptReport(DeptReport report,String dept) {
        Map<String, List<DeptReport>> map = new LinkedHashMap<>();
        String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/downloadFile");
        String filePath = path + "/" + "导出报表" + (new Date()).getFullMilli_ID() + ".xlsx";
        clearPath(path, filePath);
        System.out.println("导出路径:" + filePath);
//            String path = "D:\\错误提示\\ReportNormal.xlsx";
        String ymd; // 年月日
        try {
            RWorkbook rWorkbook = null;
            List<DeptHead> deptHeadList = new ArrayList<>();
            Calendar now = Calendar.getInstance();

            // 没有传入时间，用当前日期
            if (Help.isNull(report.getReportdate())) {

                ymd = String.format("%02d", now.get(Calendar.YEAR)) + "年"
                        + String.format("%02d", (now.get(Calendar.MONTH) + 1)) + "月"
                        + String.format("%02d", now.get(Calendar.DAY_OF_MONTH)) + "日";

            } else {
                ymd = report.getReportdate();
            }

            String SHEETNAME = "项目计划日考核表";
            String sheetname = ymd + SHEETNAME;


            if(StringUtils.equals(dept,"研发部") || StringUtils.isBlank(dept)){
                // 得到研发部的超时统计数据
                report.setUnitname("研发部");
                writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportDesign", report, deptHeadList,
                        "研发部", true,map);
            }

            if(StringUtils.equals(dept,"工艺部") || StringUtils.isBlank(dept)){
            // 得到工艺部的超时统计数据
            report.setUnitname("工艺部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportTechnics", report, deptHeadList,
                    "工艺部", true,map);
            }
            if(StringUtils.equals(dept,"砂铸部") || StringUtils.isBlank(dept)){
            // 得到铸造部的超时统计数据
            report.setUnitname("砂铸部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportSandCasting", report, deptHeadList,
                    "砂铸部", true,map);
            }
            if(StringUtils.equals(dept,"精铸部") || StringUtils.isBlank(dept)){
            // 得到铸造部的超时统计数据
            report.setUnitname("精铸部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportPrecisionCasting", report,
                    deptHeadList, "精铸部", true,map);
            }

            if(StringUtils.equals(dept,"采购部") || StringUtils.isBlank(dept)){
            // 得到采购部的超时统计数据
            report.setUnitname("采购部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportPurchasing", report,
                    deptHeadList, "采购部", true,map);
            }

            if(StringUtils.equals(dept,"调节阀事业部") || StringUtils.isBlank(dept)){
            // 得到调节阀事业部的超时统计数据
            report.setUnitname("调节阀事业部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportGlobal", report, deptHeadList,
                    "调节阀事业部", true,map);
            }


            if(StringUtils.equals(dept,"球阀事业部") || StringUtils.isBlank(dept)){
            // 得到调节阀事业部的超时统计数据
            report.setUnitname("球阀事业部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportBoll", report, deptHeadList,
                    "球阀事业部", true,map);
            }

            if(StringUtils.equals(dept,"蝶阀事业部") || StringUtils.isBlank(dept)){
            // 得到调节阀事业部的超时统计数据
            report.setUnitname("蝶阀事业部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportButterfly", report, deptHeadList,
                    "蝶阀事业部", true,map);
            }

            if(StringUtils.equals(dept,"高端阀事业部") || StringUtils.isBlank(dept)){
            // 得到调节阀事业部的超时统计数据
            report.setUnitname("高端阀事业部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportAdvanced", report, deptHeadList,
                    "高端阀事业部", true,map);
            }

            if(StringUtils.equals(dept,"控制器件事业部") || StringUtils.isBlank(dept)){
            // 得到调节阀事业部的超时统计数据
            report.setUnitname("控制器件事业部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportControlled", report,
                    deptHeadList, "控制器件事业部", true,map);
            }

            if(StringUtils.equals(dept,"CV3000事业部") || StringUtils.isBlank(dept)){
            // 得到调节阀事业部的超时统计数据
            report.setUnitname("CV3000事业部");
            writeCollectionList("XSQL_avplan_reportlist_DEPT_EXAM_REPORT", "ReportCv3000", report, deptHeadList,
                    "CV3000事业部", true,map);
            }

            if(StringUtils.isBlank(dept)){
                // 最后写合计
                RTemplate v_RTemplate = (RTemplate) XJava.getObject("ReportHead");
                rWorkbook = ReportHelp.toExcel(null, sheetname, deptHeadList, v_RTemplate, true);
            }

            // 循环各部门写入报表
            for (Map.Entry<String, List<DeptReport>> item : map.entrySet()) {
                if (!Help.isNull(item.getValue())) {
                    rWorkbook = ReportHelp.toExcel(rWorkbook, sheetname, item.getValue(), (RTemplate) XJava.getObject(item.getKey()), true);
                }
            }
            ExcelHelp.save(rWorkbook, filePath);
        } catch (RTemplateException e) {
            e.printStackTrace();
        }
        return filePath;
    }

    private void prepareTempData() {
        logger.info("开始进行临时数据的初始化");
        this.getXSQL("XSQL_cloud_exam_excute_st_trans_account_temp").execute();
        logger.info("完成进行临时数据的初始化");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void clearPath(String path, String filePath) {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File thisFile = new File(filePath);
        if (thisFile.exists()) {
            thisFile.delete();
        }
        File[] files = directory.listFiles();
        if (files == null) return;
        for (File file : files) {
            long time = file.lastModified();
            //删除10天前的文件
            if ((System.currentTimeMillis() - time) > 10 * 24 * 60 * 60 * 1000) {
                file.delete();
            }
        }
    }

    /**
     * 通用各部门写报表工具
     * 将报表数据写入集合，准备插入到数据库中形成原始数据
     * 向原因详细表中插入数据
     *
     * @param xsqlId          xsqlId
     * @param v_RTemplate_str v_RTemplate_str
     * @param report          数据
     * @param deptHeadList    头部列表
     * @param departname      部门名称
     * @param isExport        是否为导出
     */
    private void writeCollectionList(String xsqlId, String v_RTemplate_str,
                                     DeptReport report, List<DeptHead> deptHeadList, String departname, boolean isExport,Map<String, List<DeptReport>> map) {

        DeptHead deptHead = new DeptHead(); // 合计头部对象
        deptHead.setDepartname(departname);
        @SuppressWarnings("unchecked")
        List<DeptReport> datas = (List<DeptReport>) XJava.getXSQL(xsqlId).query(report);

        // 将旧的原因和申诉带过来,导出不进行此数据操作
        if (!Help.isNull(datas) && !isExport) {
            processBeforeReason(v_RTemplate_str, datas);
        } else
            logger.info(departname + "数据为空");
        // 有数据情况
        setDeptHeadTaskInfo(deptHead, datas);
        deptHeadList.add(deptHead);
        map.put(v_RTemplate_str, datas);

    }


    /**
     * 处理之前的数据
     *
     * @param v_RTemplate_str 模版字串
     * @param datas           数据
     */
    private void processBeforeReason(String v_RTemplate_str, List<DeptReport> datas) {
        int i = 0;

        for (DeptReport deptReport : datas) {//得到的今天的超时数据
            DeptReport report = new DeptReport();
            String unitName = parseTemplateStr(v_RTemplate_str, deptReport, report);
            report.setUnitname(unitName);
            logger.info(unitName + "(" + (i++) + "):" + report.getOrderno());

            //noinspection unchecked
            List<Map<String, String>> examList = (List<Map<String, String>>) XJava.getXSQL("XSQL_QUERY_DEPT_EXAM_REPORT_BY_ROWNUM").query(report);
            // 获得以前的数据
            if (!Help.isNull(examList) && examList.size() > 0) {
                Map<String, String> examMap = examList.get(0);
                //只要不是精铸砂铸，都读取原因 ||  是要是系统BUG，也读取原因。这样考虑到计划下迟原因是动态原因。需要每天验证
                String[] specials = {"ReportPrecisionCasting", "ReportSandCasting"};
                if (!(ArrayUtils.contains(specials, v_RTemplate_str)) || String.valueOf(examMap.get("REASON")).contains("系统BUG")) {
                    deptReport.setReason(StringUtils.trimToNull(examMap.get("REASON")));
                    deptReport.setIsExam(StringUtils.trimToNull(examMap.get("ISEXAM")));
                }
                if (StringUtils.equals(deptReport.getIsExam(), "null")) deptReport.setIsExam(null);

                deptReport.setAppeal(StringUtils.trimToEmpty(examMap.get("APPEAL")));
            }
            processNewReasonsByDept(v_RTemplate_str, deptReport, report);
        }// end for
    }

    private void processNewReasonsByDept(String v_RTemplate_str, DeptReport deptReport, DeptReport report) {
        if ("ReportPurchasing".equals(v_RTemplate_str)) {//采购
            processPurchasingNewReason(report, deptReport);
        } else if (ArrayUtils.contains(shiyeDepts, v_RTemplate_str)) {//事业部
            processPackageDeptNewReason(report, deptReport);
        } else if (ArrayUtils.contains(technicsOrDesign, v_RTemplate_str)) {//工艺设计
            int index = Arrays.binarySearch(technicsOrDesign, v_RTemplate_str);
            String ownername = technicsOrDesignValue[index];
            processDesignTechnicsNewReason(report, deptReport, ownername);
        } else if (ArrayUtils.contains(castingDepts, v_RTemplate_str)) {//铸造
            processCastingNewReason(report, deptReport);
        }
    }

    /**
     * 处理之前的数据
     *
     * @param datasMap 数据
     */
    private void processBeforeReason(List<Map<String, String>> datasMap) {
        int i = 0;
        for (Map<String, String> deptReportMap : datasMap) {//得到的今天的超时数据
            DeptReport deptReport = new DeptReport();
            deptReport.init(deptReportMap);
            DeptReport report = new DeptReport();
            String v_RTemplate_str = getKeyByUnitname(deptReport.getUnitname());
            String unitName = parseTemplateStr(v_RTemplate_str, deptReport, report);
            report.setUnitname(unitName);
            //查考核原因
            //noinspection unchecked
            List<Map<String, String>> examList = (List<Map<String, String>>) XJava.getXSQL("XSQL_QUERY_DEPT_EXAM_REPORT_BY_ROWNUM").query(report);
            // 获得以前的数据
            if (!Help.isNull(examList) && examList.size() > 0) {
                Map<String, String> examMap = examList.get(0);
                //只要不是精铸砂铸，都读取原因 ||  是要是系统BUG，也读取原因。这样考虑到计划下迟原因是动态原因。需要每天验证
                String[] specials = {"ReportPrecisionCasting", "ReportSandCasting"};
                if (!(ArrayUtils.contains(specials, v_RTemplate_str)) || String.valueOf(examMap.get("REASON")).contains("系统BUG")) {
                    deptReport.setReason(StringUtils.trimToNull(examMap.get("REASON")));
                    deptReport.setIsExam(StringUtils.trimToNull(examMap.get("ISEXAM")));
                }
                if (StringUtils.equals(deptReport.getIsExam(), "null")) {
                    deptReport.setIsExam(null);
                }
                System.out.println(deptReport.getUnitname() + (i++) + ":" + String.valueOf(examMap.get("APPEAL")));
                deptReport.setAppeal(StringUtils.trimToEmpty(examMap.get("APPEAL")));
            }
            processNewReasonsByDept(v_RTemplate_str, deptReport, report);
        }// end for
    }

    private void processDesignTechnicsNewReason(DeptReport report, DeptReport deptReport, String ownername) {
        //获取工艺/设计原因
        Map<String, String> params = new HashMap<>();
        params.put("suborderno", report.getSuborderno());
        params.put("ownername", ownername);
        //noinspection unchecked
        List<Map<String, String>> list = (List<Map<String, String>>) XJava.getXSQL("XSQL_avplan_exam_desing_technics_reason_detail").query(params);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("design", list);
        toSaveReasonsClob(deptReport, dataMap);
    }

    private void toSaveReasonsClob(DeptReport deptReport, Map<String, Object> dataMap) {
        try {
            XJSON json = new XJSON();
            String allStr = json.toJson(dataMap).toJSONString();
//             new XJSON(dataMap).toJSONString();
            Map<String, String> extMap = new HashMap<>();
            extMap.put("innerid", deptReport.getInnerid());
            extMap.put("orderno", deptReport.getOrderno());
            extMap.put("suborderno", deptReport.getSuborderno());
            extMap.put("reportdate", deptReport.getReportdate());
            extMap.put("unitname", deptReport.getUnitname());
            XJava.getXSQL("XSQL_delaying_reason_insert").executeUpdate(extMap);
            XJava.getXSQL("XSQL_delaying_reason_updateclob").executeUpdateCLob(extMap, new String(allStr.getBytes("UTF-8"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getKeyByUnitname(String unitname) {
        if (StringUtils.equals(unitname, "研发部")) {
            return "ReportDesign";
        } else if (StringUtils.equals(unitname, "工艺部")) {
            return "ReportTechnics";
        } else if (StringUtils.equals(unitname, "砂铸部")) {
            return "ReportSandCasting";
        } else if (StringUtils.equals(unitname, "精铸部")) {
            return "ReportPrecisionCasting";
        } else if (StringUtils.equals(unitname, "采购部")) {
            return "ReportPurchasing";
        } else if (StringUtils.equals(unitname, "调节阀事业部")) {
            return "ReportGlobal";
        } else if (StringUtils.equals(unitname, "蝶阀事业部")) {
            return "ReportButterfly";
        } else if (StringUtils.equals(unitname, "球阀事业部")) {
            return "ReportBoll";
        } else if (StringUtils.equals(unitname, "高端阀事业部")) {
            return "ReportAdvanced";
        } else if (StringUtils.equals(unitname, "控制器件事业部")) {
            return "ReportControlled";
        } else if (StringUtils.equals(unitname, "CV3000事业部")) {
            return "ReportCv3000";
        }
        return "";
    }

    private String parseTemplateStr(String v_RTemplate_str, DeptReport deptReport, DeptReport report) {
        String unitname = "";
        if ("ReportDesign".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            report.setProducttype(deptReport.getProducttype());
            unitname = "研发部";
        } else if ("ReportTechnics".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            report.setProducttype(deptReport.getProducttype());
            unitname = "工艺部";
        } else if ("ReportSandCasting".equals(v_RTemplate_str)) {
            report.setOrderno(deptReport.getOrderno());
            report.setPart_plan_no(deptReport.getPart_plan_no());
            unitname = "砂铸部";
        } else if ("ReportPrecisionCasting".equals(v_RTemplate_str)) {
            report.setOrderno(deptReport.getOrderno());
            report.setPart_plan_no(deptReport.getPart_plan_no());
            unitname = "精铸部";
        } else if ("ReportPurchasing".equals(v_RTemplate_str)) {
            report.setOrderno(deptReport.getOrderno());
            report.setPutype(deptReport.getPutype());
            unitname = "采购部";
        } else if ("ReportGlobal".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            unitname = "调节阀事业部";
        } else if ("ReportButterfly".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            unitname = "蝶阀事业部";
        } else if ("ReportBoll".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            unitname = "球阀事业部";
        } else if ("ReportAdvanced".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            unitname = "高端阀事业部";
        } else if ("ReportControlled".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            unitname = "控制器件事业部";
        } else if ("ReportCv3000".equals(v_RTemplate_str)) {
            report.setSuborderno(deptReport.getSuborderno());
            unitname = "CV3000事业部";
        }
        return unitname;
    }


    /**
     * 处理事业部部门新增项原因
     *
     * @param report     条件reprot
     * @param deptReport datas中report值
     */
    @SuppressWarnings("unchecked")
    private void processPackageDeptNewReason(DeptReport report, DeptReport deptReport) {
        if (report.getOrderno() == null && report.getSuborderno() != null) {
            report.setOrderno(StringUtils.substring(report.getSuborderno(), 0, 8));
        }
        //设置铸造原因
        deptReport.setSuborderno(report.getSuborderno());
//        List<DeptReport> reasonReport = this.queryRecords("XSQL_query_maching_package_reason", report);
        List<DeptReport> reasonList = new ArrayList<>();
        XSQLGroupResult dataResult = XJava.getXSQLGroup("GXSQL_cloud_exam_maching_reason").executes(report);
        if (dataResult.isSuccess()) {
            //noinspection unchecked
            reasonList = (List<DeptReport>) dataResult.getReturns().get("reasonDetailList");
            List<DeptReport> machingList = (List<DeptReport>) dataResult.getReturns().get("machingDetailList");
            if (!Help.isNull(machingList)) {
                DeptReport pt =machingList.get(0);
                deptReport.setPt_count(pt.getPt_count());
                deptReport.setLast_pt_date(pt.getLast_pt_date());
            }
        }

        if (!Help.isNull(reasonList)) {
            DeptReport re = reasonList.get(0);
            String reason = re.getReason();
            deptReport.setReason(reason);

        }
        DeptReport select = new DeptReport();
        select.setOrderno(report.getOrderno());
        select.setSuborderno(report.getSuborderno());
        //获取铸造原因
        List<Map<String, Object>> castingList = getResult(select, "GXSQL_cloud_exam_casting_reason_detail", "reasonDetailList");
        //获取机加原因
        List<Map<String, Object>> machingList = getResult(select, "GXSQL_cloud_exam_maching_reason_detail", "reasonDetailList");
        //获取配餐
        List<Map<String, String>> cateringList = (List<Map<String, String>>) XJava.getXSQL("XSQL_msg_exam_catering_detail").query(report);
        //获取成套
        List<Map<String, Object>> packagingList = getResult(select, "GXSQL_avplan_exam_package_detail", "reasonDetailList");
        //获取 附件连接
        List<Map<String, String>> fjljList = (List<Map<String, String>>) XJava.getXSQL("XSQL_cloud_exam_fjlj_detail").query(report);
        if (fjljList == null) {
            fjljList = new ArrayList<>();
        }
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("casting", castingList);
        dataMap.put("maching", machingList);
        dataMap.put("packaging", packagingList);
        dataMap.put("catering", cateringList);
        dataMap.put("fjlj", fjljList);
        toSaveReasonsClob(deptReport, dataMap);
    }

    private List<Map<String, Object>> getResult(DeptReport report, String groupSqlId, @SuppressWarnings("SameParameterValue") String returnId) {
        List<Map<String, Object>> dataList = new ArrayList<>();
        XSQLGroupResult dataResult = XJava.getXSQLGroup(groupSqlId).executes(report);
        if (dataResult.isSuccess()) {
            //noinspection unchecked
            dataList = (List<Map<String, Object>>) dataResult.getReturns().get(returnId);
        }
        if (dataList == null) {
            dataList = new ArrayList<>();
        }
        return dataList;
    }


    private void processCastingNewReason(DeptReport report, DeptReport deptReport) {
        if (report.getOrderno() == null && report.getSuborderno() != null) {
            report.setOrderno(StringUtils.substring(report.getSuborderno(), 0, 8));
        }
        //获取铸造原因
        List<Map<String, Object>> castingList = getResult(report, "GXSQL_cloud_exam_casting_reason_detail", "reasonDetailList");
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("casting", castingList);
        toSaveReasonsClob(deptReport, dataMap);
    }

    /**
     * 处理采购新增项原因
     *
     * @param report     条件reprot
     * @param deptReport datas中report值
     */
    private void processPurchasingNewReason(DeptReport report, DeptReport deptReport) {
        // 采购的原因
        String[] puTypes1 = {"标准件", "外购件", "毛坯外协件"};
        if (ArrayUtils.contains(puTypes1, report.getPutype())) {
            processPurchase2Reason(report, deptReport);//如果是标准件、外购件、毛坯外协件
        } else {
            processPurchase1Reason(report, deptReport);//外购执行机构、定向采购、外购附件
        }
    }

    private void processPurchase1Reason(DeptReport report, DeptReport deptReport) {
        if ("外购执行机构".equals(report.getPutype())) {
            report.setPutype("执行机构");
        } else if ("定向采购".equals(report.getPutype())) {
            report.setPutype("阀体组件");
        } else if ("外购附件".equals(report.getPutype())) {
            report.setPutype("附件");
        }
        //noinspection unchecked
        List<Map<String, String>> reasonList = (List<Map<String, String>>) XJava.getXSQL("XSQL_avplan_exam_purchasing_reason").query(report);
        try {
            List<String> reasons = new ArrayList<>();
            if(!Help.isNull(reasonList)){
                for(Map<String, String> reasonMap : reasonList){
                    String reason = reasonMap.get("REASON");
                    reasons.add(reason);
                }
            }
            String allStr = new XJSON().parser(reasons, "reasons").toJSONString();

            Map<String, String> extMap = new HashMap<>();
            extMap.put("innerid", deptReport.getInnerid());
            extMap.put("orderno", deptReport.getOrderno());
            extMap.put("suborderno", deptReport.getSuborderno());
            extMap.put("reportdate", deptReport.getReportdate());
            extMap.put("unitname", deptReport.getUnitname());
            XJava.getXSQL("XSQL_delaying_reason_insert_warn").executeUpdate(extMap);
            XJava.getXSQL("XSQL_delaying_reason_updateclob_warn").executeUpdateCLob(extMap, new String(allStr.getBytes("UTF-8"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        int i =0;
        for (Map<String, String> map : reasonList) {

            if(i<5){
                sb.append(StringUtils.trimToEmpty(map.get("REASON"))).append("<br/>");
            }else {
                sb.append("......<br/>");
                break;
            }
            i++;

        }
        deptReport.setReason(sb.toString());
    }

    private void processPurchase2Reason(DeptReport report, DeptReport deptReport) {
        int i = 0;
        String putype = deptReport.getPutype();
        String xsql = "XSQL_purchasing_exam_stock_info";
        if ("毛坯外协件".equals(putype)) {
            xsql = "XSQL_purchasing_exam_stock_info_rough";
        }
        //noinspection unchecked
        List<PurchaseDeptReportModel1> reasonList = (List<PurchaseDeptReportModel1>) XJava.getXSQL(xsql).query(report);

        //noinspection unchecked
        List<DeptReport> dateList = (List<DeptReport>) XJava.getXSQL("XSQL_avplan_exam_purchasing_date").query(report);
        if (!Help.isNull(dateList) && !Help.isNull(reasonList)) {
            DeptReport dateReport = dateList.get(0);
            String planstarttime = Help.isNull(dateReport.getPlanstarttime()) ? "" : dateReport.getPlanstarttime();
            String planfinishtime = Help.isNull(dateReport.getPlanfinishtime()) ? "" : dateReport.getPlanfinishtime();
            for (PurchaseDeptReportModel1 model : reasonList) {
                model.setPlanstarttime(planstarttime);
                model.setPlanfinishtime(planfinishtime);
            }
        }
        try {
            String allStr = new XJSON().parser(reasonList, "datas").toJSONString();

            Map<String, String> extMap = new HashMap<>();
            extMap.put("innerid", deptReport.getInnerid());
            extMap.put("orderno", deptReport.getOrderno());
            extMap.put("suborderno", deptReport.getSuborderno());
            extMap.put("reportdate", deptReport.getReportdate());
            extMap.put("unitname", deptReport.getUnitname());
            XJava.getXSQL("XSQL_delaying_reason_insert").executeUpdate(extMap);
            XJava.getXSQL("XSQL_delaying_reason_updateclob").executeUpdateCLob(extMap, new String(allStr.getBytes("UTF-8"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder reason = new StringBuilder("涉及物资" + reasonList.size() + "项;<br/>");

        for (PurchaseDeptReportModel1 model : reasonList) {
            if (i < 5 && "标准件".equals(deptReport.getPutype())) {
                reason.append(StringUtils.trimToEmpty(model.getItem_name()))
                        .append("(")
                        .append(StringUtils.trimToEmpty(model.getItem_code()))
                        .append(") ;");
            } else if (i < 5 && !"标准件".equals(deptReport.getPutype())) {
                reason.append(String.valueOf(StringUtils.trimToEmpty(model.getItem_name())))
                        .append("(")
                        .append(StringUtils.trimToEmpty(model.getItem_code()))
                        .append(") ")
                        .append(":最后入库时间：")
                        .append(String.valueOf(StringUtils.trimToEmpty(model.getLastindate())))
                        .append(",最后入库数量：")
                        .append(String.valueOf(StringUtils.trimToEmpty(model.getLastinqty() == null ? "0" : model.getLastinqty().toString())))
                        .append(",现时库存量：")
                        .append(String.valueOf(StringUtils.trimToEmpty(model.getCurrentstocks() == null ? "0" : model.getCurrentstocks().toString())))
                        .append(";");
            }
            if (i == 5) {
                reason.append("......<br/>");
                break;
            } else {
                reason.append("<br/>");
            }
            i++;
        }
        if ("标准件".equals(deptReport.getPutype())) {
            reason.append("以成品最终入库为最终考核时效。");
            deptReport.setIsExam("暂不考核");

        }
        deptReport.setReason(StringUtils.abbreviate(reason.toString(), 1000));
    }

    public static void setDeptHeadTaskInfo(DeptHead deptHead, List<DeptReport> datas) {
        if (!Help.isNull(datas)) {
            // 得到去重合同号记录
            //noinspection unchecked
            List<DeptReport> tempFindSames = new ArrayList(datas);
            Help.toDistinct(tempFindSames, "orderno");
            datas.get(0).setCountOrderNo("" + tempFindSames.size());
            deptHead.setTaskInfo(datas.size() + "项/" + tempFindSames.size() + "份");
        } else {
            deptHead.setTaskInfo("无");
        }
    }

}
