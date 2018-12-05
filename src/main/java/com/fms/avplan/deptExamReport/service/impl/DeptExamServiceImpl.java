package com.fms.avplan.deptExamReport.service.impl;

import com.fms.avplan.common.DeptComparator;
import com.fms.avplan.deptExamReport.dao.IDeptExamReportDAO;
import com.fms.avplan.deptExamReport.model.ProcessState;
import com.fms.avplan.deptExamReport.report.DeptReport;
import com.fms.avplan.deptExamReport.service.IDeptExamReportService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hy.common.Help;
import org.hy.common.xml.XJSON;
import org.hy.common.xml.annotation.Xjava;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@SuppressWarnings({"unused", "Duplicates"})
@Xjava(id = "deptExamReportService")
public class DeptExamServiceImpl implements IDeptExamReportService {

    @Xjava
    private IDeptExamReportDAO deptReportDAO;

    @Override
    public List<DeptReport> queryList(DeptReport params) {
        return deptReportDAO.queryList(params);
    }

    @Override
    public void updateDeptReport(DeptReport report) {
        deptReportDAO.updateDeptReport(report);
    }


    @Override
    public List<DeptReport> queryUnitDepts() {
        List<DeptReport> reportList = deptReportDAO.queryUnitDepts();
        Collections.sort(reportList, new DeptComparator());
        return reportList;
    }

    @Override
        public int saveDatas() {
        return deptReportDAO.saveDeptReport();
    }

    @Override
    public String exportDatas(DeptReport report,String dept) {
        return deptReportDAO.exportDeptReport(report,dept);
    }

    @Override
    public void deleteData(DeptReport report) {
        deptReportDAO.deleteData(report);
    }

    @Override
    public List<DeptReport> queryReportDateList() {
        return deptReportDAO.queryReportDateList();
    }

    @Override
    public Map<String, Map<String, Map<String, Object>>> queryReportReasonById(String innerid) {
        try {
            Map<String, Object> data = deptReportDAO.queryByInnerid(innerid);
            if (data == null) {
                throw new RuntimeException("错误的INNERID:" + innerid);
            }
//
            Map<String, Map<String, Map<String, Object>>> baseMap = new LinkedHashMap<>();
            DeptReport select = new DeptReport();
            String unitname = (String) data.get("UNITNAME");
            String orderno = (String) data.get("ORDERNO");
            String putype = (String) data.get("PUTYPE");
            select.setOrderno(orderno);
            select.setUnitname(unitname);
            //获取整体的数据 开始
            Map<String, Map<String, Object>> totalMap = getTotalMap(select);
            baseMap.put("总体情况:"+orderno, totalMap);
            //获取整体的数据 结束
            Map<String, Object> detailData = deptReportDAO.queryDetailReasons(innerid);
            if (detailData == null || detailData.size() == 0) {
                System.out.println("没有详情数据..."+innerid);
                return baseMap;
            }

            setDesignAndTechMap(detailData, baseMap, unitname);
//            baseMap.put("工艺设计", designMap);
            //获得采购的数据
            Map<String, Map<String, Object>> purchaseMap = getPurchaseMap(detailData, unitname);
            if(StringUtils.isNotBlank(putype)){
                putype = "<small style='color:#fff'>"+StringUtils.trimToEmpty(putype)+"</small>";
                baseMap.put("采购部  "+putype, purchaseMap);
            }else
                baseMap.put("采购部  ", purchaseMap);

            //获得事业部的数据 (铸造 机加 成套 附件连接）
            setCastingAndMachingMap(detailData, baseMap, unitname);

            return baseMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void  reSaveDetailReport(String date){
        this.deptReportDAO.reSaveDetailReport(date);
    }

    @SuppressWarnings("unchecked")
    private void setCastingAndMachingMap(Map<String, Object> data, Map<String, Map<String, Map<String, Object>>> baseMap, String unitname) {
        Map<String, Map<String, Object>> castingMap = new TreeMap<>();
        Map<String, Map<String, Object>> cateringMap = new TreeMap<>();
        Map<String, Map<String, Object>> machingMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(StringUtils.isBlank(o1)){
                    return -1;
                }
                if(StringUtils.isBlank(o2)){
                    return 1;
                }
                String o1Component = StringUtils.substring(o1,0,4);
                String o2Component = StringUtils.substring(o2,0,4);
                String o1item =StringUtils.substring(o1,4);
                String o2item =StringUtils.substring(o2,4);
                if(StringUtils.equals(o1Component,"阀体组件") && StringUtils.equals(o2Component,"执行机构") ){
                    return -1;
                }
                if(StringUtils.equals(o1Component,"执行机构") && StringUtils.equals(o2Component,"阀体组件") ){
                    return 1;
                }

                return o1item.compareTo(o2item);
            }
        });

        Map<String, Map<String, Object>> packagingMap = new TreeMap<>();
        Map<String, Map<String, Object>> fjljMap = new TreeMap<>();
        try {
            if (StringUtils.contains(unitname, "事业部")) {
                String reason = (String) data.get("REASON");

                Map<String, Object> datas = (Map<String, Object>) new XJSON().parser(reason);
                if (datas != null && datas.size() != 0) {
                    List<Map<String, Object>> castingList = (List<Map<String, Object>>) datas.get("casting");
                    List<Map<String, Object>> machingList = (List<Map<String, Object>>) datas.get("maching");
                    List<Map<String, Object>> cateringList = (List<Map<String, Object>>) datas.get("catering");
                    List<Map<String, Object>> packagingList = (List<Map<String, Object>>) datas.get("packaging");
                    List<Map<String, Object>> fjljList = (List<Map<String, Object>>) datas.get("fjlj");
                    getCastingMap(castingMap, castingList);
                    getMachingMap(machingMap, machingList);
                    getCateringMap(cateringMap, cateringList);
                    getPackagingMap(packagingMap, packagingList);
                    getFJLJMap(fjljMap, fjljList);
                }

            } else if (StringUtils.equals("精铸部", unitname) || StringUtils.equals("沙铸部", unitname)) {
                String reason = (String) data.get("REASON");
                Map<String, Object> datas = (Map<String, Object>) new XJSON().parser(reason);
                if (datas != null && datas.size() != 0) {
                    List<Map<String, Object>> castingList = (List<Map<String, Object>>) datas.get("casting");
                    getCastingMap(castingMap, castingList);
                }
            } else {
                System.out.println("从其他数据源中获取事业部的数据·");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("错误的JSON格式:" + e);
        }
        if (castingMap.size() > 0)
            baseMap.put("铸造 "+StringUtils.trimToEmpty((String) data.get("SUBORDERNO")), castingMap);
        if (machingMap.size() > 0){
            Map<String, Map<String, Object>> linkMap = new LinkedHashMap<>();
            linkMap.putAll(machingMap);
            baseMap.put("零件精加工 "+StringUtils.trimToEmpty((String) data.get("SUBORDERNO")), linkMap);
        }
        if (cateringMap.size() > 0){
            Map<String, Map<String, Object>> linkMap = new LinkedHashMap<>();
            linkMap.putAll(cateringMap);
            baseMap.put("产品配餐", cateringMap);
        }
        if (packagingMap.size() > 0){
            Map<String, Map<String, Object>> linkMap = new LinkedHashMap<>();
            linkMap.putAll(packagingMap);
            baseMap.put("成套入库", linkMap);
        }
        if (fjljMap.size() > 0){
            Map<String, Map<String, Object>> linkMap = new LinkedHashMap<>();
            linkMap.putAll(fjljMap);
            baseMap.put("附件连接", linkMap);
        }

    }
//    cateringMap, cateringList
    private void getCateringMap(Map<String,Map<String,Object>> cateringMap, List<Map<String,Object>> cateringList) {
        if (cateringList == null || cateringList.size() == 0) return;
        for (Map<String, Object> result : cateringList) {
             String productno = (String) result.get("PRODUCTNO");
            if(StringUtils.isBlank(productno)){
                continue;
            }
            String key = productno ;
            Map<String, Object> res = new LinkedHashMap<>();
            String stage = (String)result.get("STAGE");
            String type ;
            switch (stage){
                case "待分配":
                    type = "default";
                    break;
                case "已配送":type = "success";break;
                case "配餐中":type = "primary";break;
                case "待配餐":type = "info";break;
                default:
                    type = ProcessState.SUCCESS;
            }
            List<ProcessState> _partStatus = new ArrayList<>();

            ProcessState outTimeState = new ProcessState(type,stage);
            _partStatus.add(outTimeState);

            res.put(ProcessState.KEY, _partStatus);
            res.put("配餐日期", result.get("FINISHTIME"));
            cateringMap.put(key, res);
        }
    }

    private void getFJLJMap(Map<String,Map<String,Object>> fjljMap, List<Map<String,Object>> fjljList) {
        if (fjljList == null || fjljList.size() == 0) return;
        for (Map<String, Object> result : fjljList) {

            String item_name= (String) result.get("CPBH");
            String  item_code  = (String) result.get("PRODUCT_CLASS");
            if(StringUtils.isBlank(item_name)){
                continue;
            }
            String key = item_name + "（" + item_code + "）";
            Map<String, Object> res = new LinkedHashMap<>();
            res.put("需求数量", result.get("QUITY"));
            res.put("入库数量", result.get("IN_INV_COUNT"));
            res.put("入库日期", result.get("IN_INV_DATE"));
            fjljMap.put(key, res);
        }
    }


    private void getPackagingMap(Map<String,Map<String,Object>> packagingMap, List<Map<String,Object>> packaging) throws ParseException {
        if (packaging == null || packaging.size() == 0) return;
        for (Map<String, Object> result : packaging) {

            String item_name= (String) result.get("CPBH");
            String  item_code  = (String) result.get("PRODUCT_CLASS");
            if(StringUtils.isBlank(item_name)){
                continue;
            }
            String ispackage = (String) result.get("ISPACKAGE");
            String key = "产品编号："+item_name + "（" + item_code + "）";
            Map<String, Object> res = new LinkedHashMap<>();

            String planstarttime = (String)result.get("PLANSTARTTIME");
            String planfinishtime = (String)result.get("PLANFINISHTIME");
            String instoragetime = (String)result.get("INSTORAGETIME");
            String packagetime = formatDate((String)result.get("PACKAGETIME"));
//            Date plan_start_time = DateUtils.parseDate(planstarttime,datePatterns);



            List<ProcessState> _partStatus = new ArrayList<>();
            res.put(ProcessState.KEY, _partStatus);
            ProcessState outTimeState = new ProcessState(ProcessState.SUCCESS,"正&nbsp;&nbsp;常");
            ProcessState finishState = new ProcessState(ProcessState.DANGER,"未&nbsp;完&nbsp;成");
            if(StringUtils.isNotBlank(instoragetime)){
                finishState = new ProcessState(ProcessState.SUCCESS,"已&nbsp;完&nbsp;成");
            }

            _partStatus.add(outTimeState);
            _partStatus.add(finishState);

            if(StringUtils.isNotBlank(planfinishtime)){
                Date plan_finish_time = DateUtils.parseDate(planfinishtime,datePatterns);
                instoragetime = progressOutTime(instoragetime, plan_finish_time, outTimeState);
            }

            res.put("位号", result.get("POSITION_NO"));
            res.put("部门", result.get("DEPART_CODE"));
            res.put("需求数量", result.get("QUITY"));
            res.put("计划开始时间", planstarttime);
            res.put("计划完成时间", planfinishtime);

            res.put("是否成套", ispackage);
            res.put("成套日期", packagetime);
            res.put("入库日期", instoragetime);
            packagingMap.put(key, res);
        }
    }

    public String formatDate(String date) throws ParseException {
        return DateFormatUtils.format(DateUtils.parseDate(date,datePatterns),"yyyy-MM-dd");
    }



    //07  6 2018 12:53PM
    String[] datePatterns = {
            "yyyy-MM-dd","yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd","yyyy-MM-dd HH:mm:ss.S",
            "yyyy/MM/dd","yyyy/MM/dd HH:mm:ss",
            "MM d yyyy hh:mma","MM  d yyyy hh:mma",
            "MM  d yyyy h:mma","MM  d yyyy  h:mma",
            "MM d yyyy  h:mma","MM  d yyyy h:ma"};
    private void getMachingMap(Map<String, Map<String, Object>> machingMap, List<Map<String, Object>> maching) throws ParseException {
        if (maching == null || maching.size() == 0) return;
        for (Map<String, Object> result : maching) {
            String item_code = (String) result.get("ITEM_CODE");
            String item_name = (String) result.get("ITEM_NAME");
            String component_flag = (String) result.get("COMPONENT_FLAG");
            String part_plan_no = (String) result.get("PART_PLAN_NO");
            String component="";
            if(StringUtils.equals(component_flag,"FTZJ")){
                component = "阀体组件：";
            }else if(StringUtils.equals(component_flag,"ZXJG")){
                component = "执行机构：";
            }
            String key = component+item_name + "（" + item_code + "<i class='hidden'>"+part_plan_no+"</i>）";

            Map<String, Object> res = new LinkedHashMap<>();

            String planstarttime = (String)result.get("PLANSTARTTIME");
            String planfinishtime = (String)result.get("PLANFINISHTIME");
            String finishdate = (String)result.get("FINISH_DATE");
            Date plan_start_time = DateUtils.parseDate(planstarttime,datePatterns);
            Date plan_finish_time = DateUtils.parseDate(planfinishtime,datePatterns);
            BigDecimal finish_qty = new BigDecimal("0");
            if(NumberUtils.isCreatable((String)result.get("FINISH_QTY"))){
                finish_qty= NumberUtils.createBigDecimal((String)result.get("FINISH_QTY"));
            }

            BigDecimal order_qty = new BigDecimal("0");
            if(NumberUtils.isCreatable((String)result.get("ORDER_QTY"))){
                order_qty= NumberUtils.createBigDecimal((String)result.get("ORDER_QTY"));
            }
            List<ProcessState> _partStatus = new ArrayList<>();
            res.put(ProcessState.KEY, _partStatus);
            ProcessState outTimeState = new ProcessState(ProcessState.SUCCESS,"正&nbsp;&nbsp;常");
            ProcessState finishState = new ProcessState(ProcessState.DANGER,"未&nbsp;完&nbsp;成");
            _partStatus.add(outTimeState);
            _partStatus.add(finishState);
            //如果计划完成时间大于当前时间，返回success
            finishdate = progressOutTime(finishdate, plan_finish_time, outTimeState);
            if(finish_qty.compareTo(order_qty) >= 0){
//                _partStatus.set(1,"success");
                finishState.set(ProcessState.SUCCESS,"已&nbsp;完&nbsp;成");
            }

            res.put("部件标识", result.get("COMPONENT_FLAG"));
            res.put("路线单号", part_plan_no);
            res.put("工艺分类", result.get("GY_TYPE"));
            res.put("材质", result.get("ITEM_CL"));
            res.put("计划开始时间", planstarttime);
            res.put("计划完成时间", planfinishtime);
            res.put("机加完成时间", finishdate);
            res.put("机加完成数量", result.get("FINISH_QTY"));
            res.put("计划数量", result.get("ORDER_QTY"));
            res.put("领料时间", result.get("RECEIVE_MATERIAL_DATE"));
            res.put("领料数量", result.get("RECEIVE_MATERIAL_QTY"));
            machingMap.put(key, res);
        }
    }

    private void getCastingMap(Map<String, Map<String, Object>> castingMap, List<Map<String, Object>> casting) throws ParseException {
        if (casting == null || casting.size() == 0) return;
        for (Map<String, Object> result : casting) {
            String item_code = (String) result.get("ITEM_CODE");
            String item_name = (String) result.get("ITEM_NAME");
            String key = item_name + "（" + item_code + "）";
            Map<String, Object> res = new LinkedHashMap<>();

            String planstarttime = (String)result.get("PLANSTARTTIME");
            String planfinishtime = (String)result.get("PLANFINISHTIME");
            String finishdate = (String)result.get("ZJ_KF_RKRQ2");
            if(StringUtils.equals(finishdate,"2000-01-01")){
                finishdate = null;
            }
            List<ProcessState> stateList = new ArrayList<>();
            res.put(ProcessState.KEY, stateList );
            String progressName = (String) result.get("PU_PROGRESS_NAME");
            ProcessState outTimeState = new ProcessState(ProcessState.SUCCESS,"正&nbsp;&nbsp;常");
            ProcessState finishState = new ProcessState(ProcessState.DANGER,"未&nbsp;完&nbsp;成");
            stateList.add(outTimeState);
            stateList.add(finishState);

            if(planstarttime !=null){
                Date plan_start_time = DateUtils.parseDate(planstarttime,datePatterns);
            }
            if(planstarttime !=null){
                Date plan_finish_time = DateUtils.parseDate(planfinishtime,datePatterns);
                finishdate = progressOutTime(finishdate, plan_finish_time, outTimeState);
            }

            //如果计划完成时间大于当前时间，返回success

            BigDecimal order_qty = new BigDecimal(0);
            BigDecimal zj_kf_rks = new BigDecimal(0);
            if(NumberUtils.isCreatable((String)result.get("ORDER_QTY"))){
                order_qty= NumberUtils.createBigDecimal((String)result.get("ORDER_QTY"));
            }
            if(NumberUtils.isCreatable((String)result.get("ZJ_KF_RKS"))){
                zj_kf_rks= NumberUtils.createBigDecimal((String)result.get("ZJ_KF_RKS"));
            }

            if(order_qty.compareTo(zj_kf_rks) <= 0){
                finishState.set(ProcessState.SUCCESS,"已&nbsp;完&nbsp;成");
            }

            if(StringUtils.equals(progressName,"铸件库存满足")){
                outTimeState.set(ProcessState.SUCCESS,"正&nbsp;&nbsp;常");
                finishState.set(ProcessState.SUCCESS,"铸件库存满足");
            }
            res.put("路线单号", result.get("PART_PLAN_NO"));
            res.put("工艺分类", result.get("GY_TYPE"));
            res.put("铸件需求数量", order_qty);
            res.put("铸件投产数量", result.get("ZJ_TCS"));
            res.put("计划开始时间", planstarttime);
            res.put("计划完成时间", planfinishtime);
            res.put("铸件下达数量", result.get("ZJ_SX"));
            res.put("铸件完工数量", result.get("ZJ_WGS"));
            res.put("铸件入库数量", zj_kf_rks);
            res.put("铸件入库时间", finishdate);
            castingMap.put(key, res);
        }
    }

    private String progressOutTime(String finishdate, Date plan_finish_time, ProcessState outTimeState) throws ParseException {
        if(DateUtils.truncatedCompareTo(plan_finish_time,new Date(),Calendar.DAY_OF_MONTH) < 0){
            if(StringUtils.isBlank(finishdate)){
                outTimeState.set(ProcessState.DANGER,"超&nbsp;&nbsp;时");
            }else {
                Date finish_date = DateUtils.parseDate(finishdate,Locale.ENGLISH,datePatterns);
                if(DateUtils.truncatedCompareTo(plan_finish_time,finish_date,Calendar.DAY_OF_MONTH) < 0){
                    outTimeState.set(ProcessState.DANGER,"超&nbsp;&nbsp;时");
                }
                finishdate = DateFormatUtils.format(finish_date,"yyyy-MM-dd",Locale.ENGLISH);
            }
        }// end if
        return finishdate;
    }

    private Map<String, Map<String, Object>> getPurchaseMap(Map<String, Object> data, String unitname) throws Exception {
        Map<String, Map<String, Object>> purchaseMap = new TreeMap<>(new DeptsComparator());
        if (StringUtils.equals(unitname, "采购部")) {
            System.out.println("当前查询的采购部的数据·");
            toBasePurchaseReasons(data, purchaseMap);
            toSpecPurchaseReasons(data,purchaseMap);
        }
        return purchaseMap;
    }

    private void toSpecPurchaseReasons(Map<String,Object> data, Map<String,Map<String,Object>> purchaseMap) throws Exception {
        String reasonJSON = (String) data.get("REASON");
        Map<String, Object> dataObject = (Map<String, Object>) new XJSON().parser(reasonJSON);
        List<String> datas = (List<String>) dataObject.get("reasons");
        if(!Help.isNull(datas)){
            String key = "采购详情";
            Map<String, Object> res = new LinkedHashMap<>();
            int i =1;
            for (String reason :datas){
                res.put(i+"",reason);
                i++;
            }
            purchaseMap.put(key, res);
        }
    }

    private void toBasePurchaseReasons(Map<String, Object> data, Map<String, Map<String, Object>> purchaseMap) throws Exception {
        String reason = (String) data.get("REASON");
        //noinspection unchecked
        Map<String, Object> dataObject = (Map<String, Object>) new XJSON().parser(reason);
        //noinspection unchecked
        List<Map<String, Object>> datas = (List<Map<String, Object>>) dataObject.get("datas");
        if (datas != null && datas.size() != 0) {
            for (Map<String, Object> result : datas) {
                String key = (String) result.get("item_name");
                String key2 = (String) result.get("item_code");
                key += "（" + key2 + "）";
                Map<String, Object> res = new LinkedHashMap<>();
                String planstarttime  = StringUtils.trimToEmpty((String) result.get("planstarttime"));
                String planfinishtime = StringUtils.trimToEmpty((String) result.get("planfinishtime"));
                String rukudate       =StringUtils.trimToEmpty((String) result.get("cgrk_date"));

                List<ProcessState> stateList = new ArrayList<>();
                res.put(ProcessState.KEY, stateList );
                String progressName = (String) result.get("PU_PROGRESS_NAME");
                ProcessState outTimeState = new ProcessState(ProcessState.SUCCESS,"正&nbsp;&nbsp;常");
                ProcessState finishState = new ProcessState(ProcessState.DANGER,"未&nbsp;完&nbsp;成");
                stateList.add(outTimeState);
                stateList.add(finishState);
                if(StringUtils.isNotBlank(planfinishtime)) {
                    rukudate  = progressOutTime(rukudate, DateUtils.parseDate(planfinishtime,datePatterns), outTimeState);
                }
                BigDecimal order_qty = new BigDecimal(0);
                BigDecimal plan_qty = new BigDecimal(0);
                if(NumberUtils.isCreatable((String)result.get("order_qty"))){
                    order_qty= NumberUtils.createBigDecimal((String)result.get("order_qty"));
                }
                if(NumberUtils.isCreatable((String)result.get("plan_qty"))){
                    plan_qty= NumberUtils.createBigDecimal((String)result.get("plan_qty"));
                }

                if(order_qty.compareTo(plan_qty) <= 0){
                    finishState.set(ProcessState.SUCCESS,"已&nbsp;完&nbsp;成");
                }
                String lastindate = StringUtils.trimToEmpty((String) result.get("lastindate"));
                if(StringUtils.isNotBlank(lastindate)) {
                    lastindate = DateFormatUtils.format(DateUtils.parseDate(lastindate, datePatterns), "yyyy-MM-dd");
                }
                res.put("路线单号", result.get("part_plan_no"));
                res.put("需求数量", order_qty.toString());
                res.put("计划开始时间", planstarttime);
                res.put("计划完成时间", planfinishtime);
                res.put("完成数量", plan_qty.toString());
                res.put("最近入库时间", lastindate);
                res.put("最近入库数量", StringUtils.trimToEmpty((String) result.get("lastinqty")));
                res.put("当前库存量", StringUtils.trimToEmpty((String) result.get("currentstocks")));
                res.put("采购入库时间", rukudate);
                purchaseMap.put(key, res);
            }
        }
    }

    private void setDesignAndTechMap(Map<String, Object> data, Map<String, Map<String, Map<String, Object>>> baseMap, String unitname){
        Map<String, Map<String, Object>> resultMap = new TreeMap<>();
        String reason = (String) data.get("REASON");
        String showName = "";
        try {
            Map<String, Object> datas = (Map<String, Object>) new XJSON().parser(reason);
            if (datas != null && datas.size() != 0) {
                List<Map<String, Object>> dataList = (List<Map<String, Object>>) datas.get("design");
                if(StringUtils.equals(unitname,"研发部")){
                    getDesignTechMap(resultMap, dataList);
                    showName = "产品设计";
                }
                if(StringUtils.equals(unitname,"工艺部")){
                    getDesignTechMap(resultMap, dataList);
                    showName = "工艺设计";
                }
            }
            if(resultMap.size()>0){
                Map<String, Map<String, Object>> linkMap = new LinkedHashMap<>();
                linkMap.putAll(resultMap);
                baseMap.put(showName, linkMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("错误的JSON格式:" + e);
        }
    }

    private void getDesignTechMap(Map<String,Map<String,Object>> resultMap, List<Map<String,Object>> dataList) throws Exception {
        {
            if (dataList == null || dataList.size() == 0) return ;
            for (Map<String, Object> result : dataList) {

                String item_name= (String) result.get("NAME");
                if(StringUtils.isBlank(item_name)){
                    continue;
                }
                String key = item_name ;
                Map<String, Object> res = new LinkedHashMap<>();
                res.put("计划开始时间", result.get("PLANSTARTTIME"));
                res.put("计划结束时间", result.get("PLANFINISHTIME"));
                String actualstarttime = result.get("ACTUALSTARTTIME").toString();
                if (!StringUtils.isBlank(actualstarttime)) {
                    res.put("实际开始时间", actualstarttime);
                }
                String actualfinishtime = result.get("ACTUALFINISHTIME").toString();
                if (!StringUtils.isBlank(actualfinishtime)) {
                    res.put("实际结束时间", actualfinishtime);
                }
                resultMap.put(key, res);
            }
        }
    }


    /**
     * 整体
     *
     * @param select 查询条件
     * @return 总体情况数据
     */
    public Map<String, Map<String, Object>> getTotalMap(DeptReport select) {

        Map<String, Map<String, Object>> totalLinkMap = new LinkedHashMap<>();
        Map<String, Map<String, Object>> totalMap = new TreeMap<>(new DeptsComparator());

        List<Map<String, String>> deptReportList = deptReportDAO.queryAllDelayingReasonList(select);

        for (Map<String, String> deptReportMap : deptReportList) {
            HashMap<String, Object> subMap = new HashMap<>();
            String reason = null;
            String unitname = deptReportMap.get("UNITNAME");
            Map<String, Object> stringObjectMap = totalMap.get(unitname);
            switch (unitname) {
                case "研发部": //特品单号（ 特品型号）
                    reason = deptReportMap.get("DESIGN");
                    break;
                case "工艺部"://特品单号（ 特品型号）
                    reason = deptReportMap.get("TECH");
                    break;
                case "采购部"://采购类型
                    reason = deptReportMap.get("PURCHASE");
                    break;
                case "砂铸部": //路线单号( 图号)
                case "精铸部"://路线单号( 图号)
                    reason = deptReportMap.get("CASTING");
                    break;
//                "调节阀事业部","球阀事业部","蝶阀事业部","高端阀事业部","控制器件部","CV3000事业部"
                case "调节阀事业部":
                case "球阀事业部":
                case "蝶阀事业部":
                case "高端阀事业部":
                case "控制器件部":
                case "CV3000事业部":
                    reason = deptReportMap.get("MACHING");
                    break;
            }
            if (StringUtils.isBlank(reason) ) {
                continue;
            }
            reason = StringUtils.trimToEmpty(reason);
//            String isExam = deptReportMap.get("ISEXAM");
//
            //原因
            if (null != stringObjectMap) {//多条原因
                Object oldReason = stringObjectMap.get("项目进度");
                subMap.put("项目进度", oldReason + "<p>" + StringUtils.replace(reason,"\\n","<br/>") + "</p>");
            } else {
                subMap.put("项目进度", "<p>" + StringUtils.replace(reason,"\\n","<br/>") + "</p>");
            }
            //合同号
//            subMap.put("合同号", select.getOrderno());
            totalMap.put(unitname, subMap);
        }
        totalLinkMap.putAll(totalMap);
        return totalMap;
    }


    private class DeptsComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return comparatorByKeys(o1, o2, DeptComparator.deptNames);
        }
    }

    private int comparatorByKeys(String o1, String o2, String[] keys) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return -1;
        int i1 = ArrayUtils.indexOf(keys, o1);
        int i2 = ArrayUtils.indexOf(keys, o2);
        if (i1 == -1 && i2 == -1) return o1.compareTo(o2);
        if (i1 == -1) return 1;
        int i = i1 - i2;
        if (i > 0) i = 1;
        if (i < 0) i = -1;
        return i;
    }



}
