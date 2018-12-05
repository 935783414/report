package com.fms.mes.dao;

import com.fms.mes.model.PartsCheckedAbnormal;

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
public interface IPartsCheckedAbnormalDAO {
    /**
     * 查询主表异常数据
     * @param model
     * @return
     * @throws Exception
     */
    List<PartsCheckedAbnormal> queryByList(PartsCheckedAbnormal model) throws Exception;

    /**
     * 查询分批表异常数据
     * @param model
     * @return
     * @throws Exception
     */
    List<PartsCheckedAbnormal> queryByBatchList(PartsCheckedAbnormal model) throws Exception;

    List<Map<String,String>> queryDepartmentInfo(Map<String,String> map) throws Exception;
}
