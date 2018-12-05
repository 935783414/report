package com.fms.mes.service;

import com.fms.mes.model.PartsCheckedAbnormal;

import java.util.List;
import java.util.Map;

/**
 * @version [版本号, ]
 * @Author: zy
 * @Description:
 * @Date: Created in 2018/7/23 17:22.
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface IPartsCheckedAbnormalService {
    /**
     * 查询列表数据
     * @param model
     * @return
     */
    List<PartsCheckedAbnormal> queryByList(PartsCheckedAbnormal model) throws Exception;

    List<Map<String,String>> queryDepartmentInfo(Map<String,String> map) throws Exception;
}
