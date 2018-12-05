package com.fms.ftl.exam;

import com.fms.avplan.deptExamReport.report.DeptReport;
import com.fms.avplan.deptExamReport.service.IDeptExamReportService;
import com.fms.platform.common.BaseAppMessage;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hy.common.Date;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2017-10-25.
 */

@Controller
@RequestMapping("/exam")
public class ExamReportController extends BaseAppMessage {
    private IDeptExamReportService deptExamReportService = (IDeptExamReportService) this.getObject("deptExamReportService");

    @RequestMapping(value = "/deptexam")
    public String redirectExam(String reportdate, ModelMap model) throws ParseException {
        model.addAttribute("reportdate",reportdate);
        return "redirect:/exam/deptexam/admin.z";
    }

    @RequestMapping(value = "/deptexam/{manager}")
    public String deptexam(String reportdate, @PathVariable("manager") String manager, ModelMap model) throws ParseException {
        if(StringUtils.equals(manager,"manager")){
            return "/reports/deptexam/deptexamManager";
        }
        List<DeptReport> dateList = deptExamReportService.queryReportDateList();
        if(dateList!=null && dateList.size()>0){
            DeptReport report = dateList.get(0);
            reportdate = report.getReportdate();
            model.addAttribute("time", DateFormatUtils.format(DateUtils.parseDate(reportdate, "yyyy年MM月dd日", "yyyy年M月dd日"), "yyyy-MM-dd"));
        }
        List<DeptReport> result = deptExamReportService.queryUnitDepts();
        model.addAttribute("depts", result);
        model.addAttribute("user", manager);
        return "/reports/deptexam/deptexam";
    }

    @RequestMapping(value = "/deptexam/examList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<DeptReport> examList() {
        List<DeptReport> result = deptExamReportService.queryReportDateList();
        return result;
    }



    @RequestMapping(value = "/deptexam/dataList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<DeptReport> dataList(String unitname, String reportdate, String orderno) {
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
        report.setReportdate(reportdateStr);
        if (StringUtils.isNotBlank(orderno)) {
            report.setOrderno(orderno);
        }
        List<DeptReport> result = deptExamReportService.queryList(report);
        int total = result.size();
        Set<String> orderSet = new HashSet<>();
        for (DeptReport data : result) {
            orderSet.add(data.getOrderno());
        }
        int orderNum = orderSet.size();
        if (result != null && result.size() > 0) {
            result.get(0).setTotal(total);
            result.get(0).setOrderNum(orderNum);
        }
        return result;
    }

    @RequestMapping(value = "/deptexam/editData")
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
        deptExamReportService.updateDeptReport(report);
        return new HashMap<>();
    }


    @RequestMapping(value = "/deptexam/saveDatas", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> saveDatas() {
        Map<String, Object> result = new HashMap<>();
        int data = deptExamReportService.saveDatas();
        result.put("success", data > 0);
        return result;
    }

    @RequestMapping(value = "/deptexam/deleteData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> deleteData(String reportdate) {
        DeptReport report = new DeptReport();
        report.setReportdate(reportdate);
        deptExamReportService.deleteData(report);
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        return result;
    }


    @RequestMapping(value = "/deptexam/exportDatas", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String, Object> exportDatas(String reportdate,String dept) {
        Map<String, Object> result = new HashMap<>();
        try {
            DeptReport report = new DeptReport();
            report.setReportdate(DateFormatUtils.format(new Date(reportdate), "yyyy年MM月dd日"));
            String reportPath = deptExamReportService.exportDatas(report,dept);
            String path = new String(Base64.encodeBase64(reportPath.getBytes("UTF-8")), "UTF-8");
            result.put("success", true);
            result.put("path", path);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("success", false);
        }
        return result;
    }

    @RequestMapping(value = "/deptexam/download", produces = "application/json;charset=UTF-8")
    public ResponseEntity<byte[]> download(String path) {
        System.out.println("start download...");
        HttpHeaders headers = null;
        try {
            String truePath = new String(Base64.decodeBase64(path.getBytes("UTF-8")), "UTF-8");
            File result = new File(truePath);
            String fileName = new String(result.getName().getBytes("UTF-8"), "ISO8859-1");
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment", fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            System.out.println("download Finished...");
            return new ResponseEntity<>(FileUtils.readFileToByteArray(result), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/deptexam/detail")
    public String detail(String innerid, ModelMap model) {

        Map<String, Map<String, Map<String, Object>>> result = null;
        try {
            result = deptExamReportService.queryReportReasonById(innerid);
        } catch (Exception e) {
            e.printStackTrace();
            result = new TreeMap<>();
        }
        model.addAttribute("result", result);
        return "/reports/deptexam/deptexamDetail";
    }

    @RequestMapping(value = "/deptexam/reDetail")
    @ResponseBody
    public boolean reDetail(String date){
//        date = "2018年07月08日";
        this.deptExamReportService.reSaveDetailReport(date);
        return true;
    }
}