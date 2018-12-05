package com.fms.report.service.impl;

import com.fms.report.model.Excacess;
import org.hy.common.Return;
import org.hy.common.xml.annotation.Xjava;

import com.fms.platform.common.BaseService;
import com.fms.platform.common.util.PageUtil;
import com.fms.report.dao.IExcacessDAO;
import com.fms.report.dto.ExcacessInDto;
import com.fms.report.dto.ExcacessOutDto;
import com.fms.report.service.IExcacessService;

import java.util.List;


/**
 * 异常出入记录服务层接口
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0  
 */
@Xjava(id="reportExcacessService")
public class ExcacessServiceImpl extends BaseService implements IExcacessService
{
    
    @Xjava
    private IExcacessDAO                           reportExcacessDao;
    
    
    




	/**
     * 异常出入记录业务列表
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<ExcacessOutDto> queryExcacessList(ExcacessInDto excacessInDto)
    {
        // 对外接口返回消息主体的封装
    	ExcacessOutDto excacessOutDto = new ExcacessOutDto(); // 实例化对外dto
    	excacessOutDto.setDatas(this.reportExcacessDao.queryExcacessList(excacessInDto)); // 检索
    	//excacessOutDto.setData(excacessOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<ExcacessOutDto> v_ExecRet = new Return<ExcacessOutDto>(true);
        return v_ExecRet.paramObj(excacessOutDto);
        
    }



    /**
     * 用户信息列表,带分页查询
     *
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     *
     * @param
     * @return            Return.paramStr  异常时返回错误编号
     */
    public Return<ExcacessOutDto> queryExcacessListByPage(ExcacessInDto excacessInDto)
    {
        // 对外接口返回消息主体的封装
    	ExcacessOutDto excacessOutDto = new ExcacessOutDto(); // 实例化对外dto
    	PageUtil.PackInDto(excacessInDto.getCurrentPage(), excacessInDto.getLimit(), excacessInDto);
    	Integer recordCount = this.reportExcacessDao.queryExcacessListCount(excacessInDto);
    	// 分页信息放入输出参数中
    	PageUtil.PackOutDto(excacessOutDto, excacessInDto,recordCount);
    	
    	excacessOutDto.setDatas(this.reportExcacessDao.queryExcacessListByPage(excacessInDto)); // 列表结果集返回
    	
        Return<ExcacessOutDto> v_ExecRet = new Return<ExcacessOutDto>(true);
        return v_ExecRet.paramObj(excacessOutDto);
        
    }


	/**
     * 异常出入记录业务单个对象查询
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<ExcacessOutDto> queryExcacess(ExcacessInDto excacessInDto)
    {
        // 对外接口返回消息主体的封装
    	ExcacessOutDto excacessOutDto = new ExcacessOutDto(); // 实例化对外dto
    	excacessOutDto.setData(this.reportExcacessDao.queryExcacess(excacessInDto)); // 检索
    	//excacessOutDto.setData(excacessOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<ExcacessOutDto> v_ExecRet = new Return<ExcacessOutDto>(true);
        return v_ExecRet.paramObj(excacessOutDto);
        
    }
    
    
    /**
     * 异常出入记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数
     */
	public Return<ExcacessOutDto> saveExcacess(ExcacessInDto excacessInDto) {
		// 对外接口返回消息主体的封装
    	ExcacessOutDto excacessOutDto = new ExcacessOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.reportExcacessDao.saveExcacess(excacessInDto);
    	//excacessOutDto.setData(excacessOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<ExcacessOutDto> v_ExecRet = new Return<ExcacessOutDto>(true);
        // 行数等于1的时候，插入成功
    	v_ExecRet.set(lineCount == 1);
        return v_ExecRet.paramObj(excacessOutDto);
	};
	
	
	
    /**
     *  异常出入记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数 
     */
	public Return<ExcacessOutDto> updateExcacess(ExcacessInDto excacessInDto) {
		// 对外接口返回消息主体的封装
    	ExcacessOutDto excacessOutDto = new ExcacessOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.reportExcacessDao.updateExcacess(excacessInDto);
    	//excacessOutDto.setData(excacessOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<ExcacessOutDto> v_ExecRet = new Return<ExcacessOutDto>(true);
        // 行数>0的时候，更新成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(excacessOutDto);
	};
	
	
    /**
     *  异常出入记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       excacessInDto   输入参数
     * @return      ExcacessOutDto  输出参数   
     */
	public Return<ExcacessOutDto> deleteExcacess(ExcacessInDto excacessInDto) {
		// 对外接口返回消息主体的封装
    	ExcacessOutDto excacessOutDto = new ExcacessOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.reportExcacessDao.deleteExcacess(excacessInDto);
    	//excacessOutDto.setData(excacessOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<ExcacessOutDto> v_ExecRet = new Return<ExcacessOutDto>(true);
        // 行数>0的时候，删除成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(excacessOutDto);
	}



	;
	
	
	
	public IExcacessDAO getReportExcacessDao() {
		return reportExcacessDao;
	}



	public void setReportExcacessDao(IExcacessDAO reportExcacessDao) {
		this.reportExcacessDao = reportExcacessDao;
	}


	/*
	 * 异常出入查询
	 * */

	@Override
	public List<Excacess> accesssList() {
		return reportExcacessDao.accesssList() ;
	}

}
