package com.fms.platform.appInterfaces;

import com.fms.platform.msg.MsgLogInfoRequest;
import org.hy.common.xml.plugins.AppMessage;





/**
 * 日志信息的接口
 * 
 * 接口服务端: 我方
 * 接口客户端: 通用
 * 接口协议为: 通用
 * 对方联调人: -
 * 相关文档有: -
 * 
 * @author      ZhengWei(HY)
 * @createDate  2014-12-12
 * @version     v1.0  
 */
public interface ILogService
{
    
    /**
     * 记录日志
     * 
     * 接口编号：Log.Create.A001
     *
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     *
     * @param i_AppMsg
     * @return
     */
    public AppMessage<Object> log(AppMessage<MsgLogInfoRequest> i_AppMsg);
    
}
