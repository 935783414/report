package com.fms.mes.dao.impl;

import com.fms.avplan.deptExamReport.dao.impl.DeptExamReportDAOImpl;
import com.fms.mes.dao.IPartsCheckedAbnormalDAO;
import com.fms.mes.model.PartsCheckedAbnormal;
import com.fms.platform.common.BaseDAO;
import org.apache.log4j.Logger;
import org.hy.common.xml.XJava;
import org.hy.common.xml.annotation.Xjava;

import java.util.List;
import java.util.Map;

/**
 * @version [版本号, ]
 * @Author: zy
 * @Description:
 * @Date: Created in 2018/7/23 17:24.
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Xjava(id="partsCheckedAbnormalDAO")
public class PartsCheckedAbnormalDAOImpl extends BaseDAO<PartsCheckedAbnormal> implements IPartsCheckedAbnormalDAO {
    private static Logger logger = Logger.getLogger(DeptExamReportDAOImpl.class);

    /**
     * 查询主表异常数据
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public List<PartsCheckedAbnormal> queryByList(PartsCheckedAbnormal model) throws Exception {
        return super.queryRecords("XSQL_mes_checked_abnormal_query",model);
    }

    /**
     * 查询分批表异常数据
     * @param model
     * @return
     * @throws Exception
     */
    @Override
    public List<PartsCheckedAbnormal> queryByBatchList(PartsCheckedAbnormal model) {
        return super.queryRecords("XSQL_mes_checked_abnormal_batch_query",model);
    }

    @Override
    public List<Map<String, String>> queryDepartmentInfo(Map<String,String> map) throws Exception {
        return (List<Map<String, String>>) XJava.getXSQL("XSQL_mes_departmentInfo_query").query(map);
    }
}
