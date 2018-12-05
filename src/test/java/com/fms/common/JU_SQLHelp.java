package com.fms.common;

import org.hy.common.Help;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.fms.platform.common.BaseJunit;
import com.fms.platform.model.LogInfo;





/**
 * 生成规范的SQL语句
 *
 * @author      ZhengWei(HY)
 * @createDate  2017-06-26
 * @version     v1.0r
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING) 
public class JU_SQLHelp extends BaseJunit
{
    
    @Test
    public void sqlHelp()
    {
        boolean v_FirstUpperCase = false;  // 首字母是否大写
        
        // 生成Insert语句
        System.out.println(Help.toSQLInsert(LogInfo.class ,v_FirstUpperCase));
        System.out.println();
        
        // 生成Update语句
        System.out.println(Help.toSQLUpdate(LogInfo.class ,v_FirstUpperCase));
        System.out.println();
        
        // 生成Select语句
        System.out.println(Help.toSQLSelect(LogInfo.class ,v_FirstUpperCase ));
        System.out.println();
        
        // 生成Select语句。带表别名：A
        System.out.println(Help.toSQLSelect(LogInfo.class ,"A" ,v_FirstUpperCase ));
        System.out.println();
        
        // 生成Select语句。带表别名：A，及子对象名
        System.out.println(Help.toSQLSelect(LogInfo.class ,"A" ,"子对象名" ,v_FirstUpperCase ));
        System.out.println();
    }
    
}
