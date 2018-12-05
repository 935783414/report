package com.fms.avplan.deptWarnReport.dao.impl;

import com.fms.avplan.deptExamReport.model.PurchaseDeptReportModel1;
import com.fms.avplan.deptExamReport.report.DeptHead;
import com.fms.avplan.deptExamReport.report.DeptReport;
import com.fms.avplan.deptWarnReport.dao.IDeptWarnReportDAO;
import com.fms.platform.common.BaseDAO;
import com.fms.util.DateUtil;
import com.fms.util.WarnUtil;
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
import java.math.BigDecimal;
import java.util.*;


@SuppressWarnings("unused")
@Xjava(id = "deptWarnReportDAO")
public class DeptWarnReportDAOImpl extends BaseDAO<DeptReport> implements IDeptWarnReportDAO {

    private static Logger logger = Logger.getLogger(DeptWarnReportDAOImpl.class);
    private Map<String, List<DeptReport>> map = new LinkedHashMap<>();


    private String[] shiyeDepts = {"ReportGlobal", "ReportButterfly", "ReportAdvanced", "ReportBoll", "ReportControlled", "ReportCv3000"};
    private String[] technicsOrDesign = {"ReportDesign", "ReportTechnics"};
    private String[] technicsOrDesignValue = {"产品研发", "工艺研发"};
    private String[] castingDepts = {"ReportSandCasting", "ReportPrecisionCasting"};

    @Override
    public Map<String, Object> queryByInnerid(String innerid) {
        HashMap<Object, Object> params = new HashMap<>();
        params.put("innerid", innerid);
        //noinspection unchecked
        List<Map<String, Object>> result = (List<Map<String, Object>>) super.getXSQL("XSQL_DEPT_WARN_REPORT_selectById").query(params);
        return CollectionUtils.isEmpty(result) ? null : result.get(0);
    }

    @Override
    public List<DeptReport> queryList(DeptReport params) {
        return super.queryRecords("XSQL_report_deptexamList_warn", params);
    }

    @Override
    public int updateDeptReport(DeptReport params) {
        return super.update("XSQL_report_updateList_warn", params);
    }


    @Override
    public List<DeptReport> queryUnitDepts() {
        return super.queryRecords("XSQL_report_queryDepts_warn", new DeptReport());
    }

    @Override
    public void deleteData(DeptReport report) {
        super.update("XSQL_report_delete_warn", report);
        super.update("XSQL_report_delete_2_warn", report);
    }

    @Override
    public List<DeptReport> queryReportDateList() {
        return super.queryRecords("XSQL_report_reportDateList_warn", new DeptReport());
    }

    @Override
    public List<Map<String, String>> queryAllDelayingReasonList(DeptReport report) {
        //noinspection unchecked
        return (List<Map<String, String>>) super.getXSQL("XSQL_alldelaying_reason_warn").query(report);
    }

    @Override
    public List<Map<String, Object>> queryDesignDatas(DeptReport select) {
        //noinspection unchecked
        return (List<Map<String, Object>>) super.getXSQL("XSQL_desing_technics_reason_detail").query(select);
    }

    @Override
    public Map<String, Object> queryDetailReasons(String innerid) {
        Map<String, Object> params = new HashMap<>();
        params.put("innerid", innerid);
        //noinspection unchecked
        List<Map<String, Object>> result = (List<Map<String, Object>>) super.getXSQL("XSQL_delaying_reason_selectclob_warn").query(params);
        if (result != null && result.size() > 0) return result.get(0);
        return null;
    }

    public void reSaveDetailReport(String date) {
//        List<DeptReport> reportList =
        DeptReport select = new DeptReport();
        select.setReportdate(date);
//        select.setUnitname("调节阀事业部");
        List<Map<String, String>> examList = (List<Map<String, String>>) XJava.getXSQL("XSQL_QUERY_DEPT_WARN_REPORT_BY_ROWNUM").query(select);
        processBeforeReason(examList);
    }

    @Override
    public String queryMachingTotalReason(String suborderno) {
        DeptReport select = new DeptReport();
        select.setSuborderno(suborderno);
        List<Map<String, String>> totals = (List<Map<String, String>>) XJava.getXSQL("XSQL_maching_reason_total").query(select);
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
            //准备临时数据
//            prepareTempData();

            final String key_warn = "warn";
            // 得到研发部的超时统计数据
            DeptReport report = new DeptReport();
            report.setWarnDays(7);//预测七天

            logger.info("研发部数据抽取开始---------------------------------------");
            writeCollectionList("XSQL_avplan_delayList_design", "ReportDesign", report, deptHeadList,
                    "研发部", false);
            logger.info("研发部数据抽取完成");

            //  得到工艺部的超时统计数据
            logger.info("工艺部数据抽取开始---------------------------------------");
            writeCollectionList("XSQL_avplan_delayList_technics", "ReportTechnics", report, deptHeadList,
                    "工艺部", false);
            logger.info("工艺部数据抽取完成");

            //得到铸造部的超时统计数据
            logger.info("砂铸部数据抽取开始---------------------------------------");
            report.setGytype("砂铸");
            report.setUnitname("砂铸部");
            writeCollectionList("XSQL_avplan_delayList_casting", "ReportSandCasting", report, deptHeadList,
                    "砂铸部", false);
            logger.info("砂铸部数据抽取完成");

            //得到铸造部的超时统计数据
            logger.info("精铸部数据抽取开始---------------------------------------");
            report.setGytype("精铸");
            report.setUnitname("精铸部");
            writeCollectionList("XSQL_avplan_delayList_casting", "ReportPrecisionCasting", report,
                    deptHeadList, "精铸部", false);
            logger.info("精铸部数据抽取完成");

            // 得到采购部的超时统计数据
            logger.info("采购部数据抽取开始---------------------------------------");
            writeCollectionList("XSQL_avplan_delayList_purchasing", "ReportPurchasing", report,
                    deptHeadList, "采购部", false);
            logger.info("采购部数据抽取完成");

            // 得到调节阀事业部的超时统计数据
            logger.info("调节阀事业部数据抽取开始---------------------------------------");
            report.setDeptNameCons("调节阀装配入库");
            report.setUnitname("调节阀事业部");
            report.setDetpCode("0720");
            writeCollectionList("XSQL_avplan_delayList_packaging", "ReportGlobal", report, deptHeadList,
                    "调节阀事业部", false);
            logger.info("调节阀事业部数据抽取完成");

            // 得到调节阀事业部的超时统计数据
            logger.info("球阀事业部数据抽取开始---------------------------------------");
            report.setDeptNameCons("球阀装配入库");
            report.setUnitname("球阀事业部");
            report.setDetpCode("0730");
            writeCollectionList("XSQL_avplan_delayList_packaging", "ReportBoll", report, deptHeadList,
                    "球阀事业部", false);
            logger.info("球阀事业部数据抽取完成");

            // 得到调节阀事业部的超时统计数据
            logger.info("蝶阀事业部数据抽取开始---------------------------------------");
            report.setDeptNameCons("蝶阀装配入库");
            report.setUnitname("蝶阀事业部");
            report.setDetpCode("0760");
            writeCollectionList("XSQL_avplan_delayList_packaging", "ReportButterfly", report, deptHeadList,
                    "蝶阀事业部", false);
            logger.info("蝶阀事业部数据抽取完成");

            // 得到调节阀事业部的超时统计数据
            logger.info("高端阀事业部数据抽取开始---------------------------------------");
            report.setDeptNameCons("高端阀装配入库");
            report.setUnitname("高端阀事业部");
            report.setDetpCode("0740");
            writeCollectionList("XSQL_avplan_delayList_packaging", "ReportAdvanced", report, deptHeadList,
                    "高端阀事业部", false);
            logger.info("高端阀事业部数据抽取完成");

            // 得到控制器件事业部的超时统计数据
            logger.info("控制器件事业部数据抽取开始---------------------------------------");
            report.setDeptNameCons("控制器件装配入库");
            report.setUnitname("控制器件事业部");
            report.setDetpCode("0900");
            writeCollectionList("XSQL_avplan_delayList_packaging", "ReportControlled", report,
                    deptHeadList, "控制器件事业部", false);
            logger.info("控制器件事业部数据抽取完成");

//             得到调节阀事业部的超时统计数据
            logger.info("CV3000事业部数据抽取开始---------------------------------------");
            report.setDeptNameCons("CV3000事业部装配入库");
            report.setUnitname("CV3000事业部");
            report.setDetpCode("1304");
            writeCollectionList("XSQL_avplan_delayList_packaging", "ReportCv3000", report, deptHeadList,
                    "CV3000事业部", false);
            logger.info("CV3000事业部数据抽取完成");


            // 循环各部门将数据插入report报表服务器中
            for (Map.Entry<String, List<DeptReport>> item : map.entrySet()) {
                if (!Help.isNull(item.getValue())) {
                    List<DeptReport> dateList = item.getValue();
//                    for (DeptReport re:dateList){
//                        if(StringUtils.trimToEmpty(re.getReason()).length()>1000){
//                            System.out.println("hetonghao:"+re.getOrderno()+re.getPutype()+"   ->"+re.getReason().length());
//                        }
//                    }
                    recordCount = recordCount + item.getValue().size();
                    XJava.getXSQL("XSQL_avplan_DEPT_WARN_REPORT_insert").executeUpdatesPrepared(item.getValue());
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
    public String exportDeptReport(DeptReport report) {
        //此接口调用时，map对象中会存储之前的数据， 所以在这里清空map，在执行后续操作
        map.clear();
        String path = ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/downloadFile");
        String filePath = path + "/" + "导出报表" + (new Date()).getFullMilli_ID() + ".xlsx";
        clearPath(path, filePath);
        System.out.println("导出路径:" + filePath);
//            String path = "D:\\错误提示\\ReportNormal.xlsx";
        String ymd; // 年月日
        try {
            Calendar now = Calendar.getInstance();
            // 没有传入时间，用当前日期
            if (Help.isNull(report.getReportdate())) {

                ymd = String.format("%02d", now.get(Calendar.YEAR)) + "年"
                        + String.format("%02d", (now.get(Calendar.MONTH) + 1)) + "月"
                        + String.format("%02d", now.get(Calendar.DAY_OF_MONTH)) + "日";

            } else {
                ymd = report.getReportdate();
            }

            String SHEETNAME = "项目计划预警";
            String sheetname = ymd + SHEETNAME;

            String[] deptNames = {"研发部", "工艺部", "采购部", "砂铸部", "精铸部", "调节阀事业部", "球阀事业部", "蝶阀事业部", "高端阀事业部", "控制器件事业部", "CV3000事业部"};
            String[] rtemplateStrArr = {"ReportDesign", "ReportTechnics", "ReportPurchasing", "ReportSandCasting", "ReportPrecisionCasting", "ReportGlobal", "ReportBoll", "ReportButterfly", "ReportAdvanced", "ReportControlled", "ReportCv3000"};
            String unitname = report.getUnitname();
            switch (unitname) {
                case "研发部":
                    deptNames = new String[]{"研发部"};
                    rtemplateStrArr = new String[]{"ReportDesign"};
                    break;
                case "工艺部":
                    deptNames = new String[]{"工艺部"};
                    rtemplateStrArr = new String[]{"ReportTechnics"};
                    break;
                case "砂铸部":
                    deptNames = new String[]{"砂铸部"};
                    rtemplateStrArr = new String[]{"ReportSandCasting"};
                    break;
                case "精铸部":
                    deptNames = new String[]{"精铸部"};
                    rtemplateStrArr = new String[]{"ReportPrecisionCasting"};
                    break;
                case "采购部":
                    deptNames = new String[]{"采购部"};
                    rtemplateStrArr = new String[]{"ReportPurchasing"};
                    break;
                case "调节阀事业部":
                    deptNames = new String[]{"调节阀事业部"};
                    rtemplateStrArr = new String[]{"ReportGlobal"};
                    break;
                case "球阀事业部":
                    deptNames = new String[]{"球阀事业部"};
                    rtemplateStrArr = new String[]{"ReportBoll"};
                    break;
                case "蝶阀事业部":
                    deptNames = new String[]{"蝶阀事业部"};
                    rtemplateStrArr = new String[]{"ReportButterfly"};
                    break;
                case "高端阀事业部":
                    deptNames = new String[]{"高端阀事业部"};
                    rtemplateStrArr = new String[]{"ReportAdvanced"};
                    break;
                case "控制器件事业部":
                    deptNames = new String[]{"控制器件事业部"};
                    rtemplateStrArr = new String[]{"ReportControlled"};
                    break;
                case "CV3000事业部":
                    deptNames = new String[]{"CV3000事业部"};
                    rtemplateStrArr = new String[]{"ReportCv3000"};
                    break;
            }

            List<DeptHead> deptHeadList = new ArrayList<>();
            for (int i = 0; i < deptNames.length; i++) {
                report.setUnitname(deptNames[i]);
                writeCollectionList("XSQL_avplan_reportlist_DEPT_WARN_REPORT", rtemplateStrArr[i], report, deptHeadList,
                        deptNames[i], true);
            }

            // 最后写合计
            RTemplate v_RTemplate = (RTemplate) XJava.getObject("ReportHead_warn");
            RWorkbook rWorkbook = ReportHelp.toExcel(null, sheetname, deptHeadList, v_RTemplate, true);

            // 循环各部门写入报表
            for (Map.Entry<String, List<DeptReport>> item : map.entrySet()) {
                if (!Help.isNull(item.getValue())) {
                    rWorkbook = ReportHelp.toExcel(rWorkbook, sheetname, item.getValue(),
                            (RTemplate) XJava.getObject(item.getKey() + "_warn"), true);
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
        this.getXSQL("XSQL_excute_st_trans_account_temp").execute();
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
                                     DeptReport report, List<DeptHead> deptHeadList, String departname, boolean isExport) {
        @SuppressWarnings("unchecked")
        List<DeptReport> datas = (List<DeptReport>) XJava.getXSQL(xsqlId).query(report);

        System.out.println(departname + "有数据:" + datas.size());

        // 将旧的原因和申诉带过来,导出不进行此数据操作
        if (!Help.isNull(datas) && !isExport) {
            processBeforeReason(v_RTemplate_str, datas);
        }

        if (isExport){
            for (DeptReport deptReport : datas) {//如果是导入操作 则替换掉 原因 字段中的 html标签
                String reason = deptReport.getReason();
                if (StringUtils.isBlank(reason)) {
                    continue;
                }
                reason = reason.replaceAll("</?[^>]+>", "");
                deptReport.setReason(reason);
            }

            // 添加表格头部信息
            DeptHead deptHead = new DeptHead(); // 合计头部对象
            deptHead.setDepartname(departname);
            setDeptHeadTaskInfo(deptHead, datas);
            deptHeadList.add(deptHead);
        }
        map.put(v_RTemplate_str, datas);

    }

    /**
     * 计算预警级别
     *
     * @param deptReport
     * @return 高  中  完成
     */
    private void evaluateWarnLevel(DeptReport deptReport) {

        //获取任务完成度
        BigDecimal finishPercent = getFinishPercent(deptReport);
        if (finishPercent == null) {
            logger.info("获取" + deptReport.getUnitname() + "订单号为" + deptReport.getOrderno() + "数据任务完成度出错");
            // 出错情况下，将预警等级设为  "中"
            deptReport.setWarn_level("中");
            deptReport.setFinish_percent(50);
            return;
        }

        //任务计划完成时间
        String planfinishtime = deptReport.getPlanfinishtime();
        if (planfinishtime == null) {
            logger.info("获取" + deptReport.getUnitname() + "订单号为" + deptReport.getOrderno() + "数据任务完成时间出错");
            // 出错情况下，将预警等级设为  "中"
            deptReport.setWarn_level("中");
            deptReport.setFinish_percent(50);
            return;
        }


        //当前时间
        Calendar finish = DateUtil.getChinaDateFormat(planfinishtime);
        Calendar current = Calendar.getInstance();
        current.add(Calendar.DAY_OF_MONTH, 3);// 当前时间加3天  用来与计划完成时间比较

        // 预警程度 保留两位小数
        double finishPercentDou = finishPercent.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        //  double finishPercentDou = finishPercent.doubleValue();
        deptReport.setFinish_percent(finishPercentDou);
        if (finishPercentDou == 100) {// 完成：未完成任务量 = 0
            deptReport.setWarn_level("完成");
        } else if (finishPercentDou <= 70 && (current.compareTo(finish) >= 0)) {// 高：完成任务量 <= 70% ，且距离计划完成日期 <= 3 天
            deptReport.setWarn_level("高");
        } else if (finishPercentDou > 70 && (current.compareTo(finish) <= 0)) {// 中：完成任务量 > 70%，且距离计划完成日期 >= 3 天
            deptReport.setWarn_level("中");
        } else {// 低：其他情况
            deptReport.setWarn_level("低");
        }
    }

    /**
     * 获取任务完成度
     *
     * @param deptReport
     * @return
     */
    private BigDecimal getFinishPercent(DeptReport deptReport) {
        String unitname = deptReport.getUnitname();
        switch (unitname) {
            case "研发部":
            case "工艺部":
                List<Map<String, Object>> listResult = (List<Map<String, Object>>) XJava.getXSQL("SELECT_FINISH_PERCENT_Design").query(deptReport);
                if (!org.apache.commons.collections.CollectionUtils.isEmpty(listResult)) {
                    Map<String, Object> mapResult = listResult.get(0);
                    return (BigDecimal) mapResult.get("FINISHPERCENTAGE");
                }
                break;
            case "采购部":// 任务完成率 =  入库（%）
                String inpercent = deptReport.getInpercent();// 入库（%）
                double finishPercent;
                try {
                    finishPercent = Double.parseDouble(inpercent);
                } catch (Exception e) {
                    finishPercent = 0;
                }
                return BigDecimal.valueOf(finishPercent);
            case "砂铸部":
            case "精铸部":// 任务完成率 =  入库数 / 计划数
                String a = deptReport.getFinishqty();//入库数
                String b = deptReport.getPlanqty();//计划数
                BigDecimal division = division(a, b);
                //单位为 %  需要乘100
                return division.multiply(new BigDecimal(100));
            default:// 其他事业部门    任务完成率 =  入库数量 / 需求数量
                a = deptReport.getIn_inv_count();// 入库数量
                b = deptReport.getRequired_qty();// 需求数量
                division = division(a, b);
                //单位为 %  需要乘100
                return division.multiply(new BigDecimal(100));
        }
        return null;
    }

    /**
     * 做除法
     *
     * @param a
     * @param b
     * @return
     */
    private BigDecimal division(String a, String b) {
        int aNum;
        int bNum;
        int finishPercent;
        try {
            aNum = Integer.parseInt(a);
            bNum = Integer.parseInt(b);
            finishPercent = aNum / bNum;
        } catch (Exception e) {
            finishPercent = 0;
        }
        return BigDecimal.valueOf(finishPercent);
    }

    /**
     * 处理之前的数据
     *
     * @param v_RTemplate_str 模版字串
     * @param datas           数据
     */
    private void processBeforeReason(String v_RTemplate_str, List<DeptReport> datas) {
        int i = 0;

        for (DeptReport deptReport : datas) {//得到的今天的预警数据
            // 计算预警级别
            evaluateWarnLevel(deptReport);

            DeptReport report = new DeptReport();
            String unitName = parseTemplateStr(v_RTemplate_str, deptReport, report);
            report.setUnitname(unitName);

            logger.info(unitName + "(" + (i++) + "):" + report.getSuborderno());
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
            List<Map<String, String>> examList = (List<Map<String, String>>) XJava.getXSQL("XSQL_QUERY_DEPT_WARN_REPORT_BY_ROWNUM").query(report);
            // 获得以前的数据
            if (!Help.isNull(examList) && examList.size() > 0) {
                Map<String, String> examMap = examList.get(0);
                //只要不是精铸砂铸，都读取原因 ||  是要是系统BUG，也读取原因。这样考虑到计划下迟原因是动态原因。需要每天验证
                String[] specials = {"ReportPrecisionCasting", "ReportSandCasting"};
                if (!(ArrayUtils.contains(specials, v_RTemplate_str)) || String.valueOf(examMap.get("REASON")).contains("系统BUG")) {
                    deptReport.setReason(StringUtils.trimToNull(examMap.get("REASON")));
                    deptReport.setIsExam(StringUtils.trimToNull(examMap.get("ISEXAM")));
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
        List<Map<String, String>> list = (List<Map<String, String>>) XJava.getXSQL("XSQL_desing_technics_reason_detail").query(params);
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
            XJava.getXSQL("XSQL_delaying_reason_insert_warn").executeUpdate(extMap);
            XJava.getXSQL("XSQL_delaying_reason_updateclob_warn").executeUpdateCLob(extMap, new String(allStr.getBytes("UTF-8"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getKeyByUnitname(String unitname) {
        if (StringUtils.equals(unitname, "研发部")) {
            return "ReportDesign_warn";
        } else if (StringUtils.equals(unitname, "工艺部")) {
            return "ReportTechnics_warn";
        } else if (StringUtils.equals(unitname, "砂铸部")) {
            return "ReportSandCasting_warn";
        } else if (StringUtils.equals(unitname, "精铸部")) {
            return "ReportPrecisionCasting_warn";
        } else if (StringUtils.equals(unitname, "采购部")) {
            return "ReportPurchasing_warn";
        } else if (StringUtils.equals(unitname, "调节阀事业部")) {
            return "ReportGlobal_warn";
        } else if (StringUtils.equals(unitname, "蝶阀事业部")) {
            return "ReportButterfly_warn";
        } else if (StringUtils.equals(unitname, "球阀事业部")) {
            return "ReportBoll_warn";
        } else if (StringUtils.equals(unitname, "高端阀事业部")) {
            return "ReportAdvanced_warn";
        } else if (StringUtils.equals(unitname, "控制器件事业部")) {
            return "ReportControlled_warn";
        } else if (StringUtils.equals(unitname, "CV3000事业部")) {
            return "ReportCv3000_warn";
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
        List<Map<String, Object>> castingList = getResult(report, "GXSQL_casting_reason_detail", "reasonDetailList");
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
        List<Map<String, String>> reasonList = (List<Map<String, String>>) XJava.getXSQL("XSQL_purchasingwg_reason").query(report);
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
        String xsql = "XSQL_purchasing_stock_info";
        if ("毛坯外协件".equals(putype)) {
            xsql = "XSQL_purchasing_stock_info_rough";
        }
        //noinspection unchecked
        List<PurchaseDeptReportModel1> reasonList = (List<PurchaseDeptReportModel1>) XJava.getXSQL(xsql).query(report);

        //noinspection unchecked
        List<DeptReport> dateList = (List<DeptReport>) XJava.getXSQL("XSQL_purchasing_date").query(report);
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
            XJava.getXSQL("XSQL_delaying_reason_insert_warn").executeUpdate(extMap);
            XJava.getXSQL("XSQL_delaying_reason_updateclob_warn").executeUpdateCLob(extMap, new String(allStr.getBytes("UTF-8"), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuilder reason = new StringBuilder("涉及物资" + reasonList.size() + "项;\n");

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
            String warnInterval = WarnUtil.getWarnInterval(datas);
            deptHead.setWarnInterval(warnInterval);
        } else {
            deptHead.setTaskInfo("无");
        }
    }

}
