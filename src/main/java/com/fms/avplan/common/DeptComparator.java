package com.fms.avplan.common;

import com.fms.avplan.deptExamReport.report.DeptReport;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Comparator;

public class DeptComparator implements Comparator<DeptReport> {

    public static final String[] deptNames = {"研发部", "工艺部", "采购部", "砂铸部", "精铸部", "调节阀事业部", "球阀事业部", "蝶阀事业部", "高端阀事业部", "控制器件部", "CV3000事业部"};

    @Override
    public int compare(DeptReport o1, DeptReport o2) {
        if (o1 == null && o2 == null) return 0;
        if (o1 == null) return -1;
        if (o2 == null) return 1;
        if (o1.getUnitname() == null && o2.getUnitname() == null) return 0;
        if (o1.getUnitname() == null) return -1;
        if (o2.getUnitname() == null) return 1;
        int i1 = ArrayUtils.indexOf(deptNames, o1.getUnitname());
        int i2 = ArrayUtils.indexOf(deptNames, o2.getUnitname());
        if (i1 == -1 && i2 == -1) return o1.getUnitname().compareTo(o2.getUnitname());
        if (i1 == -1) return 1;
        return i1 - i2;
    }
}
