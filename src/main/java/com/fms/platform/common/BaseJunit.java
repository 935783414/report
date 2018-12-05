package com.fms.platform.common;

import org.hy.common.Help;

import com.fms.platform.common.InitConfig;






/**
 * 基础测试类
 * 
 * @author      ZhengWei(HY)
 * @createDate  2014-09-23
 * @version     v1.0
 */
public class BaseJunit extends Base
{
    private static boolean $IsInit = false;
    
    
    
    public BaseJunit()
    {
        synchronized ( this )
        {
            if ( !$IsInit )
            {
                $IsInit = true;
                new InitConfig(Help.getWebClassPath());
            }
        }
    }
    
}
