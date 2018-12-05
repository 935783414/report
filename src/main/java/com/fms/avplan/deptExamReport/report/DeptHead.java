package com.fms.avplan.deptExamReport.report;

/**
 * Created by Administrator on 2018/6/19.
 */
public class DeptHead {
    private String departname; // 部门名称
    private String taskInfo;   // 任务情况

    private String warnInterval;//报表预警时间区间

    public String getWarnInterval() {
        return warnInterval;
    }

    public void setWarnInterval(String warnInterval) {
        this.warnInterval = warnInterval;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }
}
