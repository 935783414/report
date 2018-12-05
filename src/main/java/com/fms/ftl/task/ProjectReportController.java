/**
 * 
 */
package com.fms.ftl.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fms.avplan.deptExamReport.report.ProjectDept;
import com.fms.avplan.deptExamReport.service.ProjectReportService;
import com.fms.platform.common.BaseAppMessage;

/**
 * @author David
 * @createtime 2018年7月9日
 */

@Controller
@RequestMapping("/report/task")
public class ProjectReportController extends BaseAppMessage{
	
	private ProjectReportService projectservice=(ProjectReportService) this.getObject("projectReportService");
	
	    @RequestMapping(value="/project")
        public String deptexam(String reportdate,String reportdate1,ModelMap model) throws ParseException {
           if(StringUtils.isBlank(reportdate) || StringUtils.isBlank(reportdate1)){
        	//获取之前当前时间的前一个月的时间点  设为开始时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
           Calendar c = Calendar.getInstance();  
           c.add(Calendar.MONTH, -1);    //得到前一个月    
           String start = format.format(c.getTime());
           model.addAttribute("time",start);   
           model.addAttribute("time1",DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd"));
         }else{
            model.addAttribute("time",DateFormatUtils.format(DateUtils.parseDate(reportdate,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
            model.addAttribute("time1",DateFormatUtils.format(DateUtils.parseDate(reportdate1,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
          }
        
          return "reports/task/porjectdept";
      }
	
	    @RequestMapping(value = "/pro/proList", produces = "application/json;charset=UTF-8")
	    @ResponseBody
	    public List<ProjectDept> dataList(String reportdate, String reportdate1) {
	    	ProjectDept report = new ProjectDept();


			 report.setXmactualstarttime(reportdate);
			report.setXmactualfinishtime(reportdate1);

	        List<ProjectDept> result = projectservice.queryList(report); 
	        int total = result.size();
	        Set<String> orderSet = new HashSet<>();
	        for(ProjectDept data:result){
	            orderSet.add(data.getXmorderno());
	        }
	        int orderNum = orderSet.size();
	        if(result!=null && result.size()>0){
	            result.get(0).setTotal(total);
	            result.get(0).setOrderNum(orderNum);
	        }
	        return result;
	    }

}
