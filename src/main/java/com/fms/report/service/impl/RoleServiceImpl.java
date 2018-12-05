package com.fms.report.service.impl;

import com.fms.report.model.Role;
import org.hy.common.Return;
import org.hy.common.xml.annotation.Xjava;

import com.fms.platform.common.BaseService;
import com.fms.platform.common.util.PageUtil;
import com.fms.report.dao.IRoleDAO;
import com.fms.report.dto.RoleInDto;
import com.fms.report.dto.RoleOutDto;
import com.fms.report.service.IRoleService;

import java.util.List;


/**
 * 卡类别记录服务层接口
 *
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0  
 */
@Xjava(id="reportRoleService")
public class RoleServiceImpl extends BaseService implements IRoleService
{
    
    @Xjava
    private IRoleDAO                           reportRoleDao;
    
    
    




	/**
     * 卡类别记录业务列表
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<RoleOutDto> queryRoleList(RoleInDto roleInDto)
    {
        // 对外接口返回消息主体的封装
    	RoleOutDto roleOutDto = new RoleOutDto(); // 实例化对外dto
    	roleOutDto.setDatas(this.reportRoleDao.queryRoleList(roleInDto)); // 检索
    	//roleOutDto.setData(roleOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<RoleOutDto> v_ExecRet = new Return<RoleOutDto>(true);
        return v_ExecRet.paramObj(roleOutDto);
        
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
    public Return<RoleOutDto> queryRoleListByPage(RoleInDto roleInDto)
    {
        // 对外接口返回消息主体的封装
    	RoleOutDto roleOutDto = new RoleOutDto(); // 实例化对外dto
    	PageUtil.PackInDto(roleInDto.getCurrentPage(), roleInDto.getLimit(), roleInDto);
    	Integer recordCount = this.reportRoleDao.queryRoleListCount(roleInDto);
    	// 分页信息放入输出参数中
    	PageUtil.PackOutDto(roleOutDto, roleInDto,recordCount);
    	
    	roleOutDto.setDatas(this.reportRoleDao.queryRoleListByPage(roleInDto)); // 列表结果集返回
    	
        Return<RoleOutDto> v_ExecRet = new Return<RoleOutDto>(true);
        return v_ExecRet.paramObj(roleOutDto);
        
    }


	/**
     * 卡类别记录业务单个对象查询
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<RoleOutDto> queryRole(RoleInDto roleInDto)
    {
        // 对外接口返回消息主体的封装
    	RoleOutDto roleOutDto = new RoleOutDto(); // 实例化对外dto
    	roleOutDto.setData(this.reportRoleDao.queryRole(roleInDto)); // 检索
    	//roleOutDto.setData(roleOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<RoleOutDto> v_ExecRet = new Return<RoleOutDto>(true);
        return v_ExecRet.paramObj(roleOutDto);
        
    }
    
    
    /**
     * 卡类别记录单表插入
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数
     */
	public Return<RoleOutDto> saveRole(RoleInDto roleInDto) {
		// 对外接口返回消息主体的封装
    	RoleOutDto roleOutDto = new RoleOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.reportRoleDao.saveRole(roleInDto);
    	//roleOutDto.setData(roleOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<RoleOutDto> v_ExecRet = new Return<RoleOutDto>(true);
        // 行数等于1的时候，插入成功
    	v_ExecRet.set(lineCount == 1);
        return v_ExecRet.paramObj(roleOutDto);
	};
	
	
	
    /**
     *  卡类别记录单表更新
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数 
     */
	public Return<RoleOutDto> updateRole(RoleInDto roleInDto) {
		// 对外接口返回消息主体的封装
    	RoleOutDto roleOutDto = new RoleOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.reportRoleDao.updateRole(roleInDto);
    	//roleOutDto.setData(roleOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<RoleOutDto> v_ExecRet = new Return<RoleOutDto>(true);
        // 行数>0的时候，更新成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(roleOutDto);
	};
	
	
    /**
     *  卡类别记录单表删除
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param       roleInDto   输入参数
     * @return      RoleOutDto  输出参数   
     */
	public Return<RoleOutDto> deleteRole(RoleInDto roleInDto) {
		// 对外接口返回消息主体的封装
    	RoleOutDto roleOutDto = new RoleOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.reportRoleDao.deleteRole(roleInDto);
    	//roleOutDto.setData(roleOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<RoleOutDto> v_ExecRet = new Return<RoleOutDto>(true);
        // 行数>0的时候，删除成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(roleOutDto);
	};
	
	
	
	public IRoleDAO getReportRoleDao() {
		return reportRoleDao;
	}



	public void setReportRoleDao(IRoleDAO reportRoleDao) {
		this.reportRoleDao = reportRoleDao;
	}


	/*
	 * 查询卡类别
	 * */
	public List<Role> queryRoleList(){
		List<Role> roles = reportRoleDao.queryRoleList();
		return  roles;

	}

}
