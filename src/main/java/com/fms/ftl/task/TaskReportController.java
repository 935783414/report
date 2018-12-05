/**
 * 
 */
package com.fms.ftl.task;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fms.avplan.deptExamReport.report.TaskDept;
import com.fms.avplan.deptExamReport.service.TaskReportService;
import com.fms.platform.common.BaseAppMessage;

/**
 * @author David
 * @createtime 2018年7月2日
 */

@Controller
@RequestMapping("/report/task")
public class TaskReportController extends BaseAppMessage {

	private TaskReportService taskdeptReportService = (TaskReportService) this.getObject("taskDeptReportService");

	@RequestMapping(value = "/taskdept")
    public String deptexam(String reportdate,String reportdate1,ModelMap model) throws ParseException {
		 
        List<TaskDept> result = taskdeptReportService.queryUnitDepts();
        model.addAttribute("depts", result);
        if(StringUtils.isBlank(reportdate) || StringUtils.isBlank(reportdate1)){
        	//获取之前当前时间的前一个月的时间点  设为开始时间
          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
           Calendar c = Calendar.getInstance();  
           c.add(Calendar.MONTH, -1);    //得到前一个月    
           String start = format.format(c.getTime());
           model.addAttribute("time",start);
         //  model.addAttribute("time",DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd"));      
           model.addAttribute("time1",DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd"));
        }else{
            //new Date(reportdate,"yyyy年MM月dd日")
            model.addAttribute("time",DateFormatUtils.format(DateUtils.parseDate(reportdate1,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
            model.addAttribute("time1",DateFormatUtils.format(DateUtils.parseDate(reportdate1,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
        }
        return "/reports/task/taskdept";
    }
    
    @RequestMapping(value = "/taskdept/examList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TaskDept> examList() {
        List<TaskDept> result = taskdeptReportService.queryReportDateList();
        return result;
    }

    @RequestMapping(value = "/taskdept/manager")
    public String deptexamManager(ModelMap model) {
        return "/reports/task/taskdeptexamManager";
    }

    @RequestMapping(value = "/taskdept/dataList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<TaskDept> dataList(String unitname, String reportdate, String reportdate1, String orderno) {
    	TaskDept report = new TaskDept();
        if (StringUtils.isNotBlank(unitname)) {
            report.setOwnername(unitname);
        } else {
        	//report.setOwnername("项目计划"); 
        	report.setOwnername("产品研发");
        }
      // String reportdateStr =reportdate;
      // String reportdateStr1 =reportdate1;
//       if (StringUtils.isNotBlank(reportdate)) {
//            reportdateStr = DateFormatUtils.format(new Date(reportdate), "yyyy年MM月dd日");
//        }else{
//            reportdateStr = DateFormatUtils.format(System.currentTimeMillis(), "yyyy年MM月dd日");
//        }
  
        report.setPlanstarttime(reportdate);
        report.setPlanfinishtime(reportdate1);
        if (StringUtils.isNotBlank(orderno)) {
            report.setOrderno(orderno);
        }
        List<TaskDept> result = taskdeptReportService.queryList(report); 
        int total = result.size();
        Set<String> orderSet = new HashSet<>();
        for(TaskDept data:result){
            orderSet.add(data.getOrderno());
        }
        int orderNum = orderSet.size();
        if(result!=null && result.size()>0){
            result.get(0).setTotal(total);
            result.get(0).setOrderNum(orderNum);
        }
        return result;
    }

   /* @RequestMapping(value = "/taskdept/editData")
    @ResponseBody
    public Map<String, Object> editData(String innerid, String reason, String isExam,String appeal) {
//        List<DeptReport> result = deptExamReportService.queryList(new DeptReport());
        TaskDept report = new TaskDept();
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
        taskdeptReportService.updateDeptReport(report);
        System.out.println("is Success?");
        return new HashMap<>();
    }
*/

   @RequestMapping(value = "/taskdept/saveDatas", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> saveDatas() {
        Map<String,Object> result= new HashMap<>();
        int data = taskdeptReportService.saveDatas();
        result.put("success",data>0);
        return result;
    }

    @RequestMapping(value = "/taskdept/deleteData", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> deleteData(String reportdate) {
        TaskDept report = new TaskDept();
        report.setPlanstarttime(reportdate);
        taskdeptReportService.deleteData(report);
        Map<String,Object> result= new HashMap<>();
        result.put("success",true);
        return result;
    }


    @RequestMapping(value = "/taskdept/exportDatas", produces = "application/json;charset=UTF-8" )
    @ResponseBody
    public Map<String,Object> exportDatas(String reportdate){
        Map<String,Object> result= new HashMap<>();
        try{
        	TaskDept report = new TaskDept();
            report.setPlanstarttime(DateFormatUtils.format(new Date(reportdate), "yyyy年MM月dd日"));
            String reportPath = taskdeptReportService.exportDeptReport(report);
            String path = new String(Base64.encodeBase64(reportPath.getBytes("UTF-8")),"UTF-8");
            result.put("success",true);
            result.put("path",path);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",false);
        }
        return result;
    }

    @RequestMapping(value = "/taskdept/download", produces = "application/json;charset=UTF-8" )
    public ResponseEntity<byte[]> download(String path) {
        System.out.println("start download...");
        HttpHeaders headers =null;
        try {
            String truePath =new String(Base64.decodeBase64(path.getBytes("UTF-8")),"UTF-8");
            File result = new File(truePath);
            String fileName = new String(result.getName().getBytes("UTF-8"),"ISO8859-1");
            headers =new HttpHeaders();
            headers.setContentDispositionFormData("attachment",fileName);
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            System.out.println("download Finished...");
            return new ResponseEntity<>(FileUtils.readFileToByteArray(result), headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
