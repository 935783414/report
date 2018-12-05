package com.fms.ftl.controller;

import com.fms.platform.common.BaseAppMessage;
import com.fms.report.model.Excacess;
import com.fms.report.service.IExcacessService;
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
@RequestMapping("/report/yichang")
public class YichangController extends BaseAppMessage {
    /*
     * 异常出入分析表
     * */
    private IExcacessService excacessService =(IExcacessService) this.getObject("reportExcacessService");


    @RequestMapping(value="/exception")
    public String exception(String reportdate, ModelMap model) throws ParseException {
        if(StringUtils.isBlank(reportdate)){
            //获取之前当前时间的前一个月的时间点  设为开始时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 0);    //得到当前月
            String start = format.format(c.getTime());
            model.addAttribute("time", DateFormatUtils.format(System.currentTimeMillis(), "yyyy-MM-dd"));
        }else{
            model.addAttribute("time",DateFormatUtils.format(DateUtils.parseDate(reportdate,"yyyy年MM月dd日","yyyy年M月dd日"), "yyyy-MM-dd"));
        }
        return "/reports/exception";
    }

    @RequestMapping(value = "/accesssList",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Excacess> accesssList(String reportdate, String unitname,String basepername,String baserolename){
        List<Excacess> excacessList = excacessService.accesssList();
        return  excacessList;
    }

}
