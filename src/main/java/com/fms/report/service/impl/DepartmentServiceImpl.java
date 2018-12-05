package com.fms.report.service.impl;

import com.fms.report.model.Department;
import org.hy.common.Return;
import org.hy.common.xml.annotation.Xjava;

import com.fms.platform.common.BaseService;
import com.fms.platform.common.util.PageUtil;
import com.fms.report.dao.IDepartmentDAO;
import com.fms.report.dto.DepartmentInDto;
import com.fms.report.dto.DepartmentOutDto;
import com.fms.report.service.IDepartmentService;

import java.util.List;


/**
 * 考勤部门记录服务层接口
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0
 */
@Xjava(id="reportDepartmentService")
public class DepartmentServiceImpl extends BaseService implements IDepartmentService
{

    @Xjava
    private IDepartmentDAO                           reportDepartmentDao;







	/**
     * 考勤部门记录业务列表
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<DepartmentOutDto> queryDepartmentList(DepartmentInDto departmentInDto)
    {
        // 对外接口返回消息主体的封装
    	DepartmentOutDto departmentOutDto = new DepartmentOutDto(); // 实例化对外dto
    	departmentOutDto.setDatas(this.reportDepartmentDao.queryDepartmentList(departmentInDto)); // 检索
    	//departmentOutDto.setData(departmentOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<DepartmentOutDto> v_ExecRet = new Return<DepartmentOutDto>(true);
        return v_ExecRet.paramObj(departmentOutDto);

    }



    /**
     * 用户信息列表,带分页查询
     *
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     * @return            Return.paramStr  异常时返回错误编号
     */
    public Return<DepartmentOutDto> queryDepartmentListByPage(DepartmentInDto departmentInDto)
    {
        // 对外接口返回消息主体的封装
    	DepartmentOutDto departmentOutDto = new DepartmentOutDto(); // 实例化对外dto
    	PageUtil.PackInDto(departmentInDto.getCurrentPage(), departmentInDto.getLimit(), departmentInDto);
    	Integer recordCount = this.reportDepartmentDao.queryDepartmentListCount(departmentInDto);
    	// 分页信息放入输出参数中
    	PageUtil.PackOutDto(departmentOutDto, departmentInDto,recordCount);

    	departmentOutDto.setDatas(this.reportDepartmentDao.queryDepartmentListByPage(departmentInDto)); // 列表结果集返回

        Return<DepartmentOutDto> v_ExecRet = new Return<DepartmentOutDto>(true);
        return v_ExecRet.paramObj(departmentOutDto);

    }


	/**
     * 考勤部门记录业务单个对象查询
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<DepartmentOutDto> queryDepartment(DepartmentInDto departmentInDto)
    {
        // 对外接口返回消息主体的封装
    	DepartmentOutDto departmentOutDto = new DepartmentOutDto(); // 实例化对外dto
    	departmentOutDto.setData(this.reportDepartmentDao.queryDepartment(departmentInDto)); // 检索
    	//departmentOutDto.setData(departmentOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<DepartmentOutDto> v_ExecRet = new Return<DepartmentOutDto>(true);
        return v_ExecRet.paramObj(departmentOutDto);

    }


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
	public Return<DepartmentOutDto> saveDepartment(DepartmentInDto departmentInDto) {
		// 对外接口返回消息主体的封装
    	DepartmentOutDto departmentOutDto = new DepartmentOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.reportDepartmentDao.saveDepartment(departmentInDto);
    	//departmentOutDto.setData(departmentOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<DepartmentOutDto> v_ExecRet = new Return<DepartmentOutDto>(true);
        // 行数等于1的时候，插入成功
    	v_ExecRet.set(lineCount == 1);
        return v_ExecRet.paramObj(departmentOutDto);
	};



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
	public Return<DepartmentOutDto> updateDepartment(DepartmentInDto departmentInDto) {
		// 对外接口返回消息主体的封装
    	DepartmentOutDto departmentOutDto = new DepartmentOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.reportDepartmentDao.updateDepartment(departmentInDto);
    	//departmentOutDto.setData(departmentOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<DepartmentOutDto> v_ExecRet = new Return<DepartmentOutDto>(true);
        // 行数>0的时候，更新成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(departmentOutDto);
	};


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
	public Return<DepartmentOutDto> deleteDepartment(DepartmentInDto departmentInDto) {
		// 对外接口返回消息主体的封装
    	DepartmentOutDto departmentOutDto = new DepartmentOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.reportDepartmentDao.deleteDepartment(departmentInDto);
    	//departmentOutDto.setData(departmentOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<DepartmentOutDto> v_ExecRet = new Return<DepartmentOutDto>(true);
        // 行数>0的时候，删除成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(departmentOutDto);
	};



	public IDepartmentDAO getReportDepartmentDao() {
		return reportDepartmentDao;
	}



	public void setReportDepartmentDao(IDepartmentDAO reportDepartmentDao) {
		this.reportDepartmentDao = reportDepartmentDao;
	}


	@Override
	public Return<DepartmentOutDto> queryDepartmentListByPage(Department department) {
		return null;
	}

	@Override
	public Return<DepartmentOutDto> queryDepartment(Department department) {
		return null;
	}

	@Override
	public Return<DepartmentOutDto> saveDepartment(Department department) {
		return null;
	}

	@Override
	public Return<DepartmentOutDto> updateDepartment(Department department) {
		return null;
	}

	@Override
	public Return<DepartmentOutDto> deleteDepartment(Department department) {
		return null;
	}

	/*部门选择*/
	@Override
	public  List<Department> queryDepartmentList2(){
		return  reportDepartmentDao.queryDepartmentList2();
	}

}
