package com.fms.report.service;

import com.fms.report.dto.DepartmentInDto;
import com.fms.report.model.Department;
import org.hy.common.Return;

import com.fms.report.dto.DepartmentOutDto;

import java.util.List;


/**
 * 考勤部门记录服务层接口
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
public interface IDepartmentService
{
	Return<DepartmentOutDto> queryDepartmentList(DepartmentInDto inDto);
    /**
     * 考勤部门记录业务列表
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       departmentInDto   输入参数
     * @return      DepartmentOutDto  输出参数
     */

    /**
     * 考勤部门记录信息列表,带分页查询
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       departmentInDto   输入参数
     * @return      DepartmentOutDto  输出参数
     */
    public Return<DepartmentOutDto> queryDepartmentListByPage(Department department);


    /**
     * 考勤部门记录查询返回单个对象
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       departmentInDto   输入参数
     * @return      DepartmentOutDto  输出参数
     */
    public Return<DepartmentOutDto> queryDepartment(Department department);


    /**
     * 考勤部门记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       departmentInDto   输入参数
     * @return      DepartmentOutDto  输出参数
     */
	public Return<DepartmentOutDto> saveDepartment(Department department);



    /**
     *  考勤部门记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       departmentInDto   输入参数
     * @return      DepartmentOutDto  输出参数
     */
	public Return<DepartmentOutDto> updateDepartment(Department department);


    /**
     *  考勤部门记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       departmentInDto   输入参数
     * @return      DepartmentOutDto  输出参数
     */
	public Return<DepartmentOutDto> deleteDepartment(Department department);

	/*部门选择*/
	public  List<Department> queryDepartmentList2();


}
