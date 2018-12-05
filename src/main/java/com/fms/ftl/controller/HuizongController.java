package com.fms.ftl.controller;

import com.fms.platform.common.BaseAppMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


@Controller
@RequestMapping("/report/huizong")
public class HuizongController extends BaseAppMessage {

    /*
     * 考勤汇总表
     * */
    @RequestMapping(value="/statistics")
    public String deptexam(String year, String month, ModelMap model) throws ParseException {
        System.out.println("进入考情汇总去");
        if(StringUtils.isBlank(year) || StringUtils.isBlank(month)){
            //获取之前当前时间的前一个月的时间点  设为开始时间
            Calendar date = Calendar.getInstance();
            String year1 = String.valueOf(date.get(Calendar.YEAR));
            String month1 = String.valueOf(date.get(Calendar.MONTH));
            model.addAttribute("year",year1);
            model.addAttribute("month",month1);
        }else{
            model.addAttribute("year",year);
            model.addAttribute("month",month);
           }
        return "/reports/statistics";
    }

    @RequestMapping(value = "/statsList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Object> accesssList(String year, String month, String dept, String basePerName,String baseRoleName){

        return  null;
    }

}
