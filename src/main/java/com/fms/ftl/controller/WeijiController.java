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
@RequestMapping("/report/weiji")
public class WeijiController extends BaseAppMessage {
    /*
     * 违纪分析表
     * */
    @RequestMapping(value="/discipline")
    public String discipline(String reportdate, String reportdate1, ModelMap model) throws ParseException {
        System.out.println("进入违纪分析");
        if(StringUtils.isBlank(reportdate) || StringUtils.isBlank(reportdate1)){
            //获取之前当前时间的前一个月的时间点  设为开始时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, -1);    //得到前一个月
            String start = format.format(c.getTime());
            model.addAttribute("time",start);
            model.addAttribute("time1", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd"));
        }else{
            model.addAttribute("time",DateFormatUtils.format(DateUtils.parseDate(reportdate,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
            model.addAttribute("time1",DateFormatUtils.format(DateUtils.parseDate(reportdate1,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
        }

        return "/reports/discipline";
    }


    @RequestMapping(value = "/ciplineList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Object> ciplineList(String year, String month, String dept, String basePerName, String baseRoleName){

        return  null;
    }
}
