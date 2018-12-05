package com.fms.platform.appInterfaces.restful;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hy.common.Return;
import org.hy.common.xml.plugins.AppMessage;

import com.fms.platform.common.BaseAppMessage;
import com.fms.platform.appInterfaces.IAppMenuAuthInforApi;
import com.fms.platform.dto.AppMenuAuthInforInDto;
import com.fms.platform.dto.AppMenuAuthInforOutDto;
import com.fms.platform.service.IAppMenuAuthInforService;





/**
 * 手机App应用端菜单鉴权接口业务的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: Restful + Json
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      李浩
 * @createDate  2017-12-12
 * @version     v1.0 
 */
@Path("platform/appMenuAuthInfor")
public class AppMenuAuthInforApiImpl extends BaseAppMessage implements IAppMenuAuthInforApi
{

    private IAppMenuAuthInforService appMenuAuthInforService = (IAppMenuAuthInforService)this.getObject("platformAppMenuAuthInforService");
    
    
    
    /**
     * 手机App应用端菜单鉴权接口业务列表查询
     * 
     * 接口编号：platform.AppMenuAuthInfor.Retrieve.A001
     *
	 * @author      李浩
	 * @createDate  2017-12-12
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryAppMenuAuthInforList")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryAppMenuAuthInforList(AppMessage<AppMenuAuthInforInDto> i_AppMsg) {
    	// 对于传入参数进行校验
    	// 消息体为空
		if ( i_AppMsg == null )
        {
            return error();
        }
		
		// 接口版本号判断 TODO 版本号要判断
        if ( !"1.0".equals(i_AppMsg.getSidv()) )
        {
            return errorVersion(i_AppMsg);
        }
        
        // 将传入的消息体克隆，返回外部时使用
        AppMessage<Object> v_Ret = i_AppMsg.clone();
        // 得到传入参数
        AppMenuAuthInforInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new AppMenuAuthInforInDto();
        } 
        
        try
        {
            // 调用业务层得到返回结果
        	Return<AppMenuAuthInforOutDto> v_ExecRet = this.appMenuAuthInforService.queryAppMenuAuthInforList(inDto);
            
            if ( v_ExecRet.booleanValue() )
            {
            	// 封装返回消息主体的值
                v_Ret.setBody(v_ExecRet.paramObj);
                v_Ret.setResult(true);
            }
            else
            {
                return error(v_ExecRet.paramStr ,v_Ret);
            }
        }
        catch (Exception exce)
        {
        	// TODO 自定义错误编码 
        	/*详见规范要求 以规范要求为依据
        	 * AA   BB   CC   DDD    E
			   ↑    ↑    ↑     ↑     ↑
			    错误编码共10位编码；
	          AA 表示系统名称    ，用数字表示；      例：01表示项目管理平台、02表示；
	          BB 表示模块(类)名称，用数字表示；      例：01表示用户管理、02表示组织管理等；
	          CC 表示业务操作名称，用数字表示；       例：01表示创建、02表示查询；
	          DDD表示信息编号（流水号）   ，用数字和字母表示；  例：A01表示创建时的数据存储服务，A02表示校验错误；建议字母全为大字
	          E  表示错误类别   ，用数字表示； ‘0’和‘1’表示系统类错误和业务类错误；

        	 */
            return error("080101A001" ,v_Ret);
        }
		return v_Ret;
	}
    

}
