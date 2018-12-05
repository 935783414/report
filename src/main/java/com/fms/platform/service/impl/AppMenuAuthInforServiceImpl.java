package com.fms.platform.service.impl;

import org.hy.common.Return;
import org.hy.common.xml.annotation.Xjava;

import com.fms.platform.common.BaseService;
import com.fms.platform.common.util.PageUtil;
import com.fms.platform.dao.IAppMenuAuthInforDAO;
import com.fms.platform.dto.AppMenuAuthInforInDto;
import com.fms.platform.dto.AppMenuAuthInforOutDto;
import com.fms.platform.service.IAppMenuAuthInforService;





/**
 * 手机App应用端菜单鉴权接口服务层接口
 *
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0  
 */
@Xjava(id="platformAppMenuAuthInforService")
public class AppMenuAuthInforServiceImpl extends BaseService implements IAppMenuAuthInforService
{
    
    @Xjava
    private IAppMenuAuthInforDAO                           platformAppMenuAuthInforDao;
    
    
    




	/**
     * 手机App应用端菜单鉴权接口业务列表
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       inDto
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<AppMenuAuthInforOutDto> queryAppMenuAuthInforList(AppMenuAuthInforInDto appMenuAuthInforInDto)
    {
        // 对外接口返回消息主体的封装
    	AppMenuAuthInforOutDto appMenuAuthInforOutDto = new AppMenuAuthInforOutDto(); // 实例化对外dto
    	appMenuAuthInforOutDto.setDatas(this.platformAppMenuAuthInforDao.queryAppMenuAuthInforList(appMenuAuthInforInDto)); // 检索
    	//appMenuAuthInforOutDto.setData(appMenuAuthInforOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<AppMenuAuthInforOutDto> v_ExecRet = new Return<AppMenuAuthInforOutDto>(true);
        return v_ExecRet.paramObj(appMenuAuthInforOutDto);
        
    }



    /**
     * 用户信息列表,带分页查询
     *
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return            Return.paramStr  异常时返回错误编号
     */
    public Return<AppMenuAuthInforOutDto> queryAppMenuAuthInforListByPage(AppMenuAuthInforInDto appMenuAuthInforInDto)
    {
        // 对外接口返回消息主体的封装
    	AppMenuAuthInforOutDto appMenuAuthInforOutDto = new AppMenuAuthInforOutDto(); // 实例化对外dto
    	PageUtil.PackInDto(appMenuAuthInforInDto.getCurrentPage(), appMenuAuthInforInDto.getLimit(), appMenuAuthInforInDto);
    	Integer recordCount = this.platformAppMenuAuthInforDao.queryAppMenuAuthInforListCount(appMenuAuthInforInDto);
    	// 分页信息放入输出参数中
    	PageUtil.PackOutDto(appMenuAuthInforOutDto, appMenuAuthInforInDto,recordCount);
    	
    	appMenuAuthInforOutDto.setDatas(this.platformAppMenuAuthInforDao.queryAppMenuAuthInforListByPage(appMenuAuthInforInDto)); // 列表结果集返回
    	
        Return<AppMenuAuthInforOutDto> v_ExecRet = new Return<AppMenuAuthInforOutDto>(true);
        return v_ExecRet.paramObj(appMenuAuthInforOutDto);
        
    }


	/**
     * 手机App应用端菜单鉴权接口业务单个对象查询
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       inDto
     * @return      Return.paramStr  异常时返回错误编号
     */
    public Return<AppMenuAuthInforOutDto> queryAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto)
    {
        // 对外接口返回消息主体的封装
    	AppMenuAuthInforOutDto appMenuAuthInforOutDto = new AppMenuAuthInforOutDto(); // 实例化对外dto
    	appMenuAuthInforOutDto.setData(this.platformAppMenuAuthInforDao.queryAppMenuAuthInfor(appMenuAuthInforInDto)); // 检索
    	//appMenuAuthInforOutDto.setData(appMenuAuthInforOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<AppMenuAuthInforOutDto> v_ExecRet = new Return<AppMenuAuthInforOutDto>(true);
        return v_ExecRet.paramObj(appMenuAuthInforOutDto);
        
    }
    
    
    /**
     * 手机App应用端菜单鉴权接口单表插入
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数
     */
	public Return<AppMenuAuthInforOutDto> saveAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto) {
		// 对外接口返回消息主体的封装
    	AppMenuAuthInforOutDto appMenuAuthInforOutDto = new AppMenuAuthInforOutDto(); // 实例化对外dto
    	// 返回影响行数
    	Integer lineCount = this.platformAppMenuAuthInforDao.saveAppMenuAuthInfor(appMenuAuthInforInDto);
    	//appMenuAuthInforOutDto.setData(appMenuAuthInforOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<AppMenuAuthInforOutDto> v_ExecRet = new Return<AppMenuAuthInforOutDto>(true);
        // 行数等于1的时候，插入成功
    	v_ExecRet.set(lineCount == 1);
        return v_ExecRet.paramObj(appMenuAuthInforOutDto);
	};
	
	
	
    /**
     *  手机App应用端菜单鉴权接口单表更新
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数 
     */
	public Return<AppMenuAuthInforOutDto> updateAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto) {
		// 对外接口返回消息主体的封装
    	AppMenuAuthInforOutDto appMenuAuthInforOutDto = new AppMenuAuthInforOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.platformAppMenuAuthInforDao.updateAppMenuAuthInfor(appMenuAuthInforInDto);
    	//appMenuAuthInforOutDto.setData(appMenuAuthInforOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<AppMenuAuthInforOutDto> v_ExecRet = new Return<AppMenuAuthInforOutDto>(true);
        // 行数>0的时候，更新成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(appMenuAuthInforOutDto);
	};
	
	
    /**
     *  手机App应用端菜单鉴权接口单表删除
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param       appMenuAuthInforInDto   输入参数
     * @return      AppMenuAuthInforOutDto  输出参数   
     */
	public Return<AppMenuAuthInforOutDto> deleteAppMenuAuthInfor(AppMenuAuthInforInDto appMenuAuthInforInDto) {
		// 对外接口返回消息主体的封装
    	AppMenuAuthInforOutDto appMenuAuthInforOutDto = new AppMenuAuthInforOutDto(); // 实例化对外dto
    	// 返回影响行数 
    	Integer lineCount = this.platformAppMenuAuthInforDao.deleteAppMenuAuthInfor(appMenuAuthInforInDto);
    	//appMenuAuthInforOutDto.setData(appMenuAuthInforOutDto.getDatas().get(0));
        //if ( this.IsSynchronized() )
        Return<AppMenuAuthInforOutDto> v_ExecRet = new Return<AppMenuAuthInforOutDto>(true);
        // 行数>0的时候，删除成功
    	v_ExecRet.set(lineCount > 0);
        return v_ExecRet.paramObj(appMenuAuthInforOutDto);
	};
	
	
	
	public IAppMenuAuthInforDAO getPlatformAppMenuAuthInforDao() {
		return platformAppMenuAuthInforDao;
	}



	public void setPlatformAppMenuAuthInforDao(IAppMenuAuthInforDAO platformAppMenuAuthInforDao) {
		this.platformAppMenuAuthInforDao = platformAppMenuAuthInforDao;
	}
    
    


}
