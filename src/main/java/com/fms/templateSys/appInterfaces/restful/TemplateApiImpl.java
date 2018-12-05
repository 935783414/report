package com.fms.templateSys.appInterfaces.restful;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.hy.common.Return;
import org.hy.common.xml.plugins.AppMessage;

import com.fms.platform.common.BaseAppMessage;
import com.fms.templateSys.appInterfaces.ITemplateApi;
import com.fms.templateSys.dto.TemplateInDto;
import com.fms.templateSys.dto.TemplateOutDto;
import com.fms.templateSys.service.ITemplateService;





/**
 * ${template}业务的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: Restful + Json
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0 
 */
@Path("${system}/template")
public class TemplateApiImpl extends BaseAppMessage implements ITemplateApi
{

    private ITemplateService templateService = (ITemplateService)this.getObject("${system}TemplateService");
    
    
    
    /**
     * ${template}业务列表查询
     * 
     * 接口编号：${system}.Template.Retrieve.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryTemplateList")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryTemplateList(AppMessage<TemplateInDto> i_AppMsg) {
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
        TemplateInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new TemplateInDto();
        } 
        
        try
        {
            // 调用业务层得到返回结果
        	Return<TemplateOutDto> v_ExecRet = this.templateService.queryTemplateList(inDto);
            
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
     * ${template}业务列表分页查询
     * 
     * 接口编号：${system}.Template.Retrieve.A002
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryTemplateListByPage")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryTemplateListByPage(AppMessage<TemplateInDto> i_AppMsg) {
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
    	TemplateInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new TemplateInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果
        	Return<TemplateOutDto> v_ExecRet = this.templateService.queryTemplateListByPage(inDto);
            
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
     * ${template}查询返回单个对象
     * 
     * 接口编号：${system}.Template.Retrieve.A003
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    @Path("queryTemplate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> queryTemplate(AppMessage<TemplateInDto> i_AppMsg) {
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
        TemplateInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new TemplateInDto();
        } 
        
        try
        {
            // 调用业务层得到返回结果
        	Return<TemplateOutDto> v_ExecRet = this.templateService.queryTemplate(inDto);
            
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
     * ${template}单表插入接口
     *
     * 接口编号：${system}.Template.Insert.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg
     */
    @Path("saveTemplate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> saveTemplate(AppMessage<TemplateInDto> i_AppMsg) {
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
    	TemplateInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new TemplateInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果 true or false
        	Return<TemplateOutDto> v_ExecRet = this.templateService.saveTemplate(inDto);
            
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
     *  ${template}单表更新接口
     *
     * 接口编号：${system}.Template.Update.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg 
     */
    @Path("updateTemplate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> updateTemplate(AppMessage<TemplateInDto> i_AppMsg) {
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
    	TemplateInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new TemplateInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果 true or false
        	Return<TemplateOutDto> v_ExecRet = this.templateService.updateTemplate(inDto);
            
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
     *  ${template}单表删除接口
     *
     * 接口编号：${system}.Template.Delete.A001
     *
	 * @author      ${author}
	 * @createDate  ${createDate}
     * @version     v1.0
     *
     * @param i_AppMsg   
     */
    @Path("deleteTemplate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
	@Override
	public AppMessage<Object> deleteTemplate(AppMessage<TemplateInDto> i_AppMsg) {
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
    	TemplateInDto inDto =  i_AppMsg.getBody();
        if ( inDto == null )
        {
            inDto = new TemplateInDto();
        } 
        
        try
        {
        	// 调用业务层得到返回结果 true or false
        	Return<TemplateOutDto> v_ExecRet = this.templateService.deleteTemplate(inDto);
            
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
