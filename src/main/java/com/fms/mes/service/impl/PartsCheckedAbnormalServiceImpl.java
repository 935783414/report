package com.fms.mes.service.impl;

import com.fms.mes.dao.IPartsCheckedAbnormalDAO;
import com.fms.mes.model.PartsCheckedAbnormal;
import com.fms.mes.service.IPartsCheckedAbnormalService;
import org.hy.common.xml.annotation.Xjava;

import java.util.List;
import java.util.Map;

/**
 * @version [版本号, ]
 * @Author: zy
 * @Description:
 * @Date: Created in 2018/7/23 17:23.
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Xjava(id="PartsCheckedAbnormalService")
public class PartsCheckedAbnormalServiceImpl implements IPartsCheckedAbnormalService {

    @Xjava
    private IPartsCheckedAbnormalDAO partsCheckedAbnormalDAO;

    /**
     * 查询列表数据
     * @param model
     * @return
     */
    @Override
    public List<PartsCheckedAbnormal> queryByList(PartsCheckedAbnormal model) throws Exception {
        //查询主表异常数据
        List<PartsCheckedAbnormal> list = partsCheckedAbnormalDAO.queryByList(model);
        //查询分批表异常数据
        List<PartsCheckedAbnormal> batchList = partsCheckedAbnormalDAO.queryByBatchList(model);
        //合并结果集
        list.addAll(batchList);

        return list;
    }

    @Override
    public List<Map<String, String>> queryDepartmentInfo(Map<String,String> map) throws Exception {
        return partsCheckedAbnormalDAO.queryDepartmentInfo(map);
    }
}
