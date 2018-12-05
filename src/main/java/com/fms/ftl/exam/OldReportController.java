package com.fms.ftl.exam;

import com.fms.avplan.deptExamReport.report.DeptReport;
import com.fms.avplan.deptExamReport.service.IDeptExamReportService;
import com.fms.platform.common.BaseAppMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.*;

/**
 * Created by Administrator on 2017-10-25.
 * 兼容老接口
 */

@Deprecated
@Controller
@RequestMapping("/report")
public class OldReportController extends BaseAppMessage {
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

}