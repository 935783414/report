/**
 * 
 */
package com.fms.ftl.task;

import com.fms.avplan.deptExamReport.report.OrderReport;
import com.fms.avplan.deptExamReport.service.OrderReportService;
import org.springframework.ui.ModelMap;
import com.fms.platform.common.BaseAppMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author David
 * @createtime 2018年8月7日
 */

@Controller
@RequestMapping("/report/task")
public class OrderRateController extends BaseAppMessage{
	
	private OrderReportService orderservice=(OrderReportService) this.getObject("orderReportService");

    @RequestMapping(value="/orderno")
    public String deptexam(ModelMap model) throws ParseException {
         Date date=new Date();
        //获取限制年份 starttime1，用于sql语句中的限制条件
        SimpleDateFormat format1=new SimpleDateFormat("yyyy年MM月dd日");
        //获取限制年份 starttime2，用于sql语句中的限制条件
        SimpleDateFormat format2=new SimpleDateFormat("yyyy");
        Calendar c = Calendar.getInstance();
        String starttime1= format1.format(c.getTime());
        String starttime2 = format2.format(c.getTime());
        model.addAttribute("starttime1",starttime1);
        model.addAttribute("starttime2",starttime2);
        return "reports/task/orderdept";
    }


    @RequestMapping(value = "/orderList", produces = "application/json;charset=UTF-8")
	    @ResponseBody
	    public List<OrderReport> dataList() {
            OrderReport order=new OrderReport();
			//获取限制年份 starttime2，用于sql语句中的限制条件
			SimpleDateFormat format2 = new SimpleDateFormat("yyyy");
			Calendar c = Calendar.getInstance();
			String starttime2 = format2.format(c.getTime());
            order.setStarttime2(starttime2);
			List<OrderReport> result = orderservice.queryList(order);

			int total = result.size();
	        Set<String> orderSet = new HashSet<>();
            for(OrderReport data:result){
                orderSet.add(data.getRowtip());
            }
	        int orderNum = orderSet.size();
	        if(result!=null && result.size()>0){
	            result.get(0).setTotal(total);
	            result.get(0).setOrderNum(orderNum);
	        }
	        return result;
	    }

}
