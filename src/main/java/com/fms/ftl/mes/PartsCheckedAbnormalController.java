package com.fms.ftl.mes;

import com.fms.mes.model.PartsCheckedAbnormal;
import com.fms.mes.service.IPartsCheckedAbnormalService;
import com.fms.platform.common.BaseAppMessage;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @version [版本号, ]
 * @Author: zy
 * @Description:
 * @Date: Created in 2018/7/23 14:16.
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Controller
@RequestMapping("/mes/partsChecked")
public class PartsCheckedAbnormalController extends BaseAppMessage {

    private IPartsCheckedAbnormalService partsCheckedAbnormalService = (IPartsCheckedAbnormalService) this.getObject("PartsCheckedAbnormalService");

    /**
     * @Author: zy
     * @Description: 页面跳转
     * @Date: Created in 14:20 2018/7/23.
     * @param:
     * @throws Exception
     */
    @RequestMapping("list")
    public String list(Model model) throws Exception {
        Map<String,String> map = new HashMap<String,String>();

        map.put("departType","1");
        List<Map<String,String>> departMapList = partsCheckedAbnormalService.queryDepartmentInfo(map);
        map.clear();

        for(Map<String, String> deMap : departMapList){
            map.put(deMap.get("DEPARTCODE"),deMap.get("DEPARTNAME"));
        }
        model.addAttribute("departMap",map);
        return "/reports/mes/partsCheckedAbnormal";
    }

    /**
     * @Author: zy
     * @Description: 查询列表数据
     * @Date: Created in 17:35 2018/7/23.
     * @param:
     * @throws Exception
     */
    @RequestMapping("dataList")
    @ResponseBody
    public List<PartsCheckedAbnormal> dataList(PartsCheckedAbnormal model){

        List<PartsCheckedAbnormal> dataList = new ArrayList<PartsCheckedAbnormal>();

        try {
            //获取今天日期
            String date = DateFormatUtils.format(new Date(),"yyyy/MM/dd");

            if(StringUtils.isEmpty(model.getPlanCode())){
                model.setPlanCode(null);
            }
            if(StringUtils.isEmpty(model.getDepartCode())){
                model.setDepartCode(null);
            }
            if(StringUtils.isEmpty(model.getEndDate())){
                model.setEndDate(null);
            }
            if(StringUtils.isEmpty(model.getStartDate())){
                model.setStartDate(date);
            }

            dataList = partsCheckedAbnormalService.queryByList(model);
        }catch (Exception e){
            e.printStackTrace();
        }

        return dataList;
    }
}
