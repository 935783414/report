package com.fms.platform.service.impl;

import com.fms.platform.common.BaseService;
import com.fms.platform.dao.ILogInfoDAO;
import com.fms.platform.msg.MsgLogInfoRequest;
import com.fms.platform.service.ILogInfoService;

import org.hy.common.Return;
import org.hy.common.thread.Task;
import org.hy.common.thread.TaskPool;
import org.hy.common.xml.annotation.Xjava;
import org.hy.common.xml.plugins.AppMessage;





/**
 * 日志操作的服务类
 * 
 * @author      ZhengWei(HY)
 * @version     v1.0  
 * @createDate  2014-12-12
 */
@Xjava
public class LogInfoService extends BaseService implements ILogInfoService
{
    private static final String                   $TaskType = "LogInfoTask";
    
    private static       int                      $SerialNo = 0;
    
    @Xjava
    private ILogInfoDAO                           logInfoDAO;
    
    
    
    /**
     * 记录日志
     *
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     *
     * @param io_AppMsg
     * @return            Return.paramStr  异常时返回错误编号
     */
    public Return<Object> log(AppMessage<MsgLogInfoRequest> io_AppMsg)
    {
        Return<Object> v_Ret = new Return<Object>(true);
        
        io_AppMsg.getBody().setId(            io_AppMsg.getSerialNo());
        io_AppMsg.getBody().setSysID(         io_AppMsg.getSysid());
        io_AppMsg.getBody().setMsgRequestTime(io_AppMsg.gatCreateTime());
        
        if ( this.IsSynchronized() )
        {
            Return<String> v_ExecRet = this.executeLog(io_AppMsg);
            
            v_Ret.paramStr(v_ExecRet.paramStr);
            v_Ret.set(     v_ExecRet.booleanValue());
        }
        else
        {
            TaskPool.putTask(new LogInfoTask(io_AppMsg));
        }
        
        return v_Ret;
    }
    
    
    
    /**
     * 执行记录日志动作
     * 
     * @author      ZhengWei(HY)
     * @createDate  2014-12-12
     * @version     v1.0
     *
     * @param io_AppMsg
     * @return            Return.paramStr  异常时返回错误编号
     */
    private Return<String> executeLog(AppMessage<MsgLogInfoRequest> io_AppMsg)
    {
        Return<String> v_Ret = new Return<String>();
        
        return v_Ret.set(true);
    }
    
    
    
    public ILogInfoDAO getLogInfoDAO()
    {
        return logInfoDAO;
    }

    

    public void setLogInfoDAO(ILogInfoDAO logInfoDAO)
    {
        this.logInfoDAO = logInfoDAO;
    }


    
    

    class LogInfoTask extends Task<Object>
    {
        private AppMessage<MsgLogInfoRequest> appMsg;
        
        
        
        public LogInfoTask(AppMessage<MsgLogInfoRequest> io_AppMsg)
        {
            super($TaskType);
            
            this.appMsg = io_AppMsg;
        }


        private synchronized int GetSerialNo()
        {
            return ++$SerialNo;
        }
        
        
        @Override
        public void execute()
        {
            Return<String> v_Ret = executeLog(this.appMsg);
            
            if ( !v_Ret.booleanValue() )
            {
                try
                {
                    this.appMsg.setResult(false);
                    this.appMsg.setRc(v_Ret.paramStr);
                    this.appMsg.setBody(null);
                    
                    logErrorByCache(this.appMsg);
                }
                catch (Exception exce)
                {
                    exce.printStackTrace();
                }
            }
            
            this.finishTask();
        }

        
        @Override
        public int getSerialNo()
        {
            return GetSerialNo();
        }
        

        @Override
        public String getTaskDesc()
        {
            return "" + this.getTaskNo();
        }
        
    }

}
