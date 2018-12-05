package com.fms.util;

import com.fms.avplan.deptExamReport.report.DeptReport;
import org.apache.commons.collections.CollectionUtils;
import org.hy.common.Date;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WarnUtil {

    /**
     * 获取报表最新上报时间
     *
     * @param result
     */
    public static Long getLastReportDate(List<DeptReport> result) {

        if (CollectionUtils.isEmpty(result)) {
            return null;
        }

        // 计算返回数据最新创建时间
        ArrayList<Long> dataList = new ArrayList<>();
        for (DeptReport data : result) {
            Date createtime = data.getCreatetime();
            if (createtime == null) {
                continue;
            }
            dataList.add(createtime.getTime());
        }
        DeptReport deptReport = result.get(0);
        if (dataList.size() > 0) {
            //计算最大报表生成时间
            return Collections.max(dataList);
        }
        return null;
    }

    /**
     * 获取预警区间
     *
     * @param result
     * @return
     */
    public static String getWarnInterval(List<DeptReport> result) {
        if (CollectionUtils.isEmpty(result)) {
            return "";
        }
        // 计算返回数据最大报表时间 最小报表时间
        ArrayList<String> finishDataList = new ArrayList<>();
        for (DeptReport data : result) {
            String planfinishtime = data.getPlanfinishtime();
            finishDataList.add(planfinishtime);
        }
        if (finishDataList.size() > 0) {
            //计算 报表时间区间
            String max = Collections.max(finishDataList);
            String min = Collections.min(finishDataList);
            return min + "至" + max;
        }

        return "";
    }

}
