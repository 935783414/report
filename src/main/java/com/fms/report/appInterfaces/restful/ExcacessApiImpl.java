package com.fms.report.appInterfaces.restful;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hy.common.Return;
import org.hy.common.xml.plugins.AppMessage;

import com.fms.platform.common.BaseAppMessage;
import com.fms.report.appInterfaces.IExcacessApi;
import com.fms.report.dto.ExcacessInDto;
import com.fms.report.dto.ExcacessOutDto;
import com.fms.report.service.IExcacessService;





/**
 * 异常出入记录业务的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: Restful + Json
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      陈伟
 * @createDate  2018-12-03
 * @version     v1.0 
 */
@Path("report/excacess")
public class ExcacessApiImpl extends BaseAppMessage implements IExcacessApi
{

    private IExcacessService excacessService = (IExcacessService)this.getObject("reportExcacessService");
    
    
    
    /**
     * 异常出入记录业务列表查询
     * 
     * 接口编号：report.Excacess.Retrieve.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryExcacessList")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryExcacessList(AppMessage<ExcacessInDto> i_AppMsg) {
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
        ExcacessInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new ExcacessInDto();
        } 
        
        try
        {
            // 调用业务层得到返回结果
        	Return<ExcacessOutDto> v_ExecRet = this.excacessService.queryExcacessList(inDto);
            
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
            return error("010102A011" ,v_Ret);
        }
		return v_Ret;
	}
    
    /**
     * 异常出入记录业务列表分页查询
     * 
     * 接口编号：report.Excacess.Retrieve.A002
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryExcacessListByPage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryExcacessListByPage(AppMessage<ExcacessInDto> i_AppMsg) {
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
    	ExcacessInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new ExcacessInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果
        	Return<ExcacessOutDto> v_ExecRet = this.excacessService.queryExcacessListByPage(inDto);
            
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
            return error("010102A021" ,v_Ret);
        }
		return v_Ret;
	}
    
    
    
    /**
     * 异常出入记录查询返回单个对象
     * 
     * 接口编号：report.Excacess.Retrieve.A003
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryExcacess")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryExcacess(AppMessage<ExcacessInDto> i_AppMsg) {
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
        ExcacessInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new ExcacessInDto();
        } 
        
        try
        {
            // 调用业务层得到返回结果
        	Return<ExcacessOutDto> v_ExecRet = this.excacessService.queryExcacess(inDto);
            
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
            return error("010102A031" ,v_Ret);
        }
		return v_Ret;
	}
    
    
    /**
     * 异常出入记录单表插入接口
     *
     * 接口编号：report.Excacess.Insert.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg
     */
    @Path("saveExcacess")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> saveExcacess(AppMessage<ExcacessInDto> i_AppMsg) {
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
    	ExcacessInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new ExcacessInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果 true or false
        	Return<ExcacessOutDto> v_ExecRet = this.excacessService.saveExcacess(inDto);
            
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
            return error("010101B011" ,v_Ret);
        }
		return v_Ret;
    };
	
	
	
    /**
     *  异常出入记录单表更新接口
     *
     * 接口编号：report.Excacess.Update.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg 
     */
    @Path("updateExcacess")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> updateExcacess(AppMessage<ExcacessInDto> i_AppMsg) {
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
    	ExcacessInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new ExcacessInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果 true or false
        	Return<ExcacessOutDto> v_ExecRet = this.excacessService.updateExcacess(inDto);
            
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
            return error("010101C011" ,v_Ret);
        }
		return v_Ret;
    };
	
	
    /**
     *  异常出入记录单表删除接口
     *
     * 接口编号：report.Excacess.Delete.A001
     *
	 * @author      陈伟
	 * @createDate  2018-12-03
     * @version     v1.0
     *
     * @param i_AppMsg   
     */
    @Path("deleteExcacess")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> deleteExcacess(AppMessage<ExcacessInDto> i_AppMsg) {
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
    	ExcacessInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new ExcacessInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果 true or false
        	Return<ExcacessOutDto> v_ExecRet = this.excacessService.deleteExcacess(inDto);
            
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
            return error("010101D011" ,v_Ret);
        }
		return v_Ret;
    };
	
	
	

}
