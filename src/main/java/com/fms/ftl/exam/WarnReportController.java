package com.fms.ftl.exam;

import com.fms.avplan.deptExamReport.report.DeptReport;
import com.fms.avplan.deptWarnReport.service.IDeptWarnReportService;
import com.fms.platform.common.BaseAppMessage;
import com.fms.util.DateUtil;
import com.fms.util.WarnUtil;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hy.common.Date;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.*;

/**
 * Created by earlman on 2018-07-10.
 */
@Controller
@RequestMapping("/warn")
public class WarnReportController extends BaseAppMessage {


    private IDeptWarnReportService deptWarnReportService = (IDeptWarnReportService) this.getObject("deptWarnReportService");

    @RequestMapping(value = "/deptWarn")
    public String redirectExam(String reportdate, ModelMap model) throws ParseException {
        model.addAttribute("reportdate",reportdate);
        return "redirect:/warn/deptWarn/admin.z";
    }

    @RequestMapping(value = "/deptWarn/{manager}")
    public String deptWarn(@PathVariable("manager") String manager, String reportDate, ModelMap model) throws ParseException {
        if (StringUtils.equals(manager, "manager")) {
            return "/reports/warn/deptWarnManager";
        }
        List<DeptReport> dateList = deptWarnReportService.queryReportDateList();
        if (dateList != null && dateList.size() > 0) {
            DeptReport report = dateList.get(0);
            model.addAttribute("time", DateFormatUtils.format(DateUtils.parseDate(report.getReportdate(), "yyyy年MM月dd日", "yyyy年M月dd日"), "yyyy-MM-dd"));
        }
        List<DeptReport> result = deptWarnReportService.queryUnitDepts();

        model.addAttribute("depts", result);
        model.addAttribute("user", manager);
        //避免前端界面初始化时 报空
        model.addAttribute("maxData", "");
        return "/reports/warn/deptWarn";
    }

    @RequestMapping(value = "/warnList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<DeptReport> warnList() {
        List<DeptReport> result = deptWarnReportService.queryReportDateList();
        return result;
    }


    @RequestMapping(value = "/dataList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<DeptReport> dataList(String unitname, Integer warnDays, String reportdate, String orderno) {
        DeptReport report = new DeptReport();
        if (StringUtils.isNotBlank(unitname)) {
            report.setUnitname(unitname);
        } else {
            report.setUnitname("采购部");
        }
        String reportdateStr;
        if (StringUtils.isNotBlank(reportdate)) {
            reportdateStr = DateFormatUtils.format(new Date(reportdate), "yyyy年MM月dd日");
        } else {
            reportdateStr = DateFormatUtils.format(System.currentTimeMillis(), "yyyy年MM月dd日");
        }
        if (warnDays != null) {
            report.setWarnDays(warnDays);
        } else {
            report.setWarnDays(0);
        }

        report.setReportdate(reportdateStr);
        if (StringUtils.isNotBlank(orderno)) {
            report.setOrderno(orderno);
        }
        List<DeptReport> result = deptWarnReportService.queryList(report);

        int total = result.size();
        Set<String> orderSet = new HashSet<>();
        for (DeptReport data : result) {
            orderSet.add(data.getOrderno());
        }
        int orderNum = orderSet.size();
        if (result != null && result.size() > 0) {
            DeptReport deptReport = result.get(0);
            deptReport.setTotal(total);
            deptReport.setOrderNum(orderNum);

            String warnInterval = WarnUtil.getWarnInterval(result);
            Long lastReportDate = WarnUtil.getLastReportDate(result);
            deptReport.setWarnInterval(warnInterval);
            deptReport.setMaxReportData(DateUtil.formatDate(lastReportDate));
        }
        return result;
    }

    @RequestMapping(value = "/editData")
    @ResponseBody
    public Map<String, Object> editData(String innerid, String reason, String isExam, String appeal) {
        DeptReport report = new DeptReport();
        report.setInnerid(innerid);
        if (StringUtils.isNotBlank(reason)) {
            report.setReason(reason);
        }
        if (StringUtils.isNotBlank(isExam)) {
            report.setIsExam(isExam);
        }
        if (StringUtils.isNotBlank(appeal)) {
            report.setAppeal(appeal);
        }
        deptWarnReportService.updateDeptReport(report);
        return new HashMap<>();
    }


    @RequestMapping(value = "/createWarnReport", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> createWarnReport() {
        Map<String, Object> result = new HashMap<>();
        int data = deptWarnReportService.saveDatas();
        result.put("success", data > 0);
        return result;
    }

    @RequestMapping(value = "/deleteData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> deleteData(String reportdate) {
        DeptReport report = new DeptReport();
        report.setReportdate(reportdate);
        deptWarnReportService.deleteData(report);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }


    @RequestMapping(value = "/exportDatas", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> exportDatas(String reportdate, String unitname) {
        Map<String, Object> result = new HashMap<>();
        try {
            DeptReport report = new DeptReport();
            report.setUnitname(unitname);
            report.setReportdate(DateFormatUtils.format(new Date(reportdate), "yyyy年MM月dd日"));
            String reportPath = deptWarnReportService.exportDatas(report);
            String path = new String(Base64.encodeBase64(reportPath.getBytes("UTF-8")), "UTF-8");
            result.put("success", true);
            result.put("path", path);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/detail")
    public String detail(String innerid, ModelMap model) {

        Map<String, Map<String, Map<String, Object>>> result = null;
        try {
            result = deptWarnReportService.queryReportReasonById(innerid);
        } catch (Exception e) {
            e.printStackTrace();
            result = new TreeMap<>();
        }
        model.addAttribute("result", result);
        return "/reports/warn/deptWarnDetail";
    }
}
