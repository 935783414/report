package com.fms.platform.common;

import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.codehaus.xfire.XFire;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.hy.common.TablePartition;
import org.hy.common.xml.plugins.AppMessage;

import com.fms.platform.msg.MsgErrorResponse;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;





/**
 * 基础系统间消息处理类
 * 
 * @author ZhengWei(HY)
 * @create 2014-09-22
 * @version     v1.0
 */
public class BaseAppMessage extends Base
{
    
    /** 是否初始化 */
    private static boolean                            $Init  = false;
    
    /** 系统级统一错误(异常)消息 */
    private static AppMessage<Object>                 $ERROR;
    
    /** 系统级统一接口版本错误(异常)消息 */
    private static AppMessage<Object>                 $ERROR_Version;
    
    private static Client                             $Client       = Client.create();
    
    private static Map<String ,WebResource>           $WebResources = new Hashtable<String ,WebResource>();
    
    private static final  Logger logger = Logger.getLogger(BaseAppMessage.class);
    
    
    public BaseAppMessage()
    {
        this.init();
    }
    
    
    
    public synchronized void init()
    {
        if ( !$Init )
        {
            $Init           = true;
            $ERROR          = error("0000000000" ,new AppMessage<Object>());
            $ERROR_Version  = error("0000000010" ,new AppMessage<Object>());
        }
    }
    
    
    
    /**
     * 获取接口统一错误信息
     * 
     * @param i_ErrorCode 错误编码
     * @return
     */
    protected MsgErrorResponse error(String i_ErrorCode)
    {
        MsgErrorResponse v_Ret = (MsgErrorResponse)((Map<? ,?>)this.getObject("AppInterfaces_Error")).get(i_ErrorCode);
        
        if ( v_Ret == null )
        {
            throw new NoClassDefFoundError("Error[" + i_ErrorCode +"] is not find.");
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 获取接口统一错误信息
     * 
     * @param i_ErrorCode    错误编码
     * @param io_AppMessage  系统消息
     * @return
     */
    protected AppMessage<Object> error(String i_ErrorCode ,AppMessage<Object> io_AppMessage)
    {
//        MsgErrorResponse v_Error = (MsgErrorResponse)((Map<? ,?>)this.getObject("AppInterfaces_Error")).get(i_ErrorCode);
//
//        if ( v_Error == null )
//        {
//            throw new NoClassDefFoundError("Error[" + i_ErrorCode +"] is not find.");
//        }
//
//        io_AppMessage.setResult(false);
//        io_AppMessage.setRc(i_ErrorCode);
//        io_AppMessage.setBody(v_Error);
//
//        this.logErrorByCache(io_AppMessage);
//        logger.warn(io_AppMessage.toString());
//        return io_AppMessage;
        return null;
    }
    
    
    
    /**
     * 系统级统一错误(异常)消息
     * 
     * @return
     */
    protected AppMessage<Object> error()
    {
        return $ERROR;
    }
    
    
    
    /**
     * 系统级统一接口版本错误(异常)消息
     * 
     * @return
     */
    protected AppMessage<Object> errorVersion(AppMessage<?> io_AppMessage)
    {
        io_AppMessage.setResult(false);
        io_AppMessage.setRc($ERROR_Version.getRc());
        
        this.logErrorByCache(io_AppMessage);
        
        return $ERROR_Version;
    }
    
    
    
    /**
     * 获取某一接口相关的所有接口错误信息
     * 
     * @author      ZhengWei(HY)
     * @createDate  2014-12-03
     * @version     v1.0
     *
     * @param i_InnerNo  接口内部编号
     * @return
     */
    @SuppressWarnings("unchecked")
    protected List<MsgErrorResponse> getErrors(String i_InnerNo)
    {
        TablePartition<String ,MsgErrorResponse> v_Errors = (TablePartition<String ,MsgErrorResponse>)this.getObject("AppInterfaces_ErrorPart");
        
        return v_Errors.get(i_InnerNo);
    }
    
   
    
    /**
     * 获取客户端ip地址
     *
     * @author      WangZhanBin
     * @createDate  2014-12-10
     * @version     v1.0
     *
     * @param request
     * @return String ip地址信息
     */
    public static String getClientIPAddress(HttpServletRequest request)
    {
        
        String ip = request.getHeader("x-forwarded-for");
        
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))
        {
            ip=request.getHeader("Proxy-Client-IP");
        }
        
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))
        {
            ip=request.getHeader("WL-Proxy-Client-IP");
        }
        
        if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))
        {
            ip=request.getRemoteAddr();
        }
        
        return ip;
    }
    
    
    
    /**
     * Rest服务请求方法
     * 
     * @author      ZhengWei(HY)
     * @createDate  2014-12-30
     * @version     v1.0
     *
     * @param i_URL
     * @param i_Data
     * @param i_DataECode  响应结果的字符类型。如UTF-8
     * @return
     */
    public static String restRequest(String i_URL ,Object i_Data ,String i_DataECode)
    {
        String v_Result = null;
        
        try 
        {
            WebResource v_WebResource = null;
            synchronized ( BaseAppMessage.class )
            {
                v_WebResource = $WebResources.get(i_URL);
                if ( v_WebResource == null )
                {
                    v_WebResource = $Client.resource(i_URL);
                    $WebResources.put(i_URL ,v_WebResource);
                }
            }
            
            ClientResponse v_Response    = v_WebResource.post(ClientResponse.class ,i_Data);
            
            v_Result = v_Response.getEntity(String.class);
            v_Result = URLDecoder.decode(v_Result ,i_DataECode);
        } 
        catch (Exception exce) 
        {
            exce.printStackTrace();
        }
        
        return v_Result;
    }
    
    /**
     * 
     * 利用xfire调用webservice方法实现
     * @param cls    接口类
     * @param i_URL  接口地址
     * @return
     * @throws MalformedURLException 
     */
    @SuppressWarnings("unchecked")
	public static <T> T   webserviceRequest(Class<T> cls, String i_URL) throws MalformedURLException {
    	
    	Service serviceModel = new DotNetServiceFatory().create(cls);    
        
        XFire xfire = XFireFactory.newInstance().getXFire();    
        XFireProxyFactory factory = new XFireProxyFactory(xfire);    
        String serviceUrl = i_URL;//"http://10.1.50.86/wy_doc/WebService/TyDocService.asmx?wsdl";    
   
        T client = null;    
        client = (T) factory.create(serviceModel, serviceUrl);
            // "CL_NAME":"[SJ01]InteCAD设计图纸
//            System.out.println(client.GetDocList("AM31741-205-1r8C","","1","1000"));
        return client;
    }
    
}
