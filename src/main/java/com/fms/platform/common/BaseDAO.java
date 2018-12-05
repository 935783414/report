package com.fms.platform.common;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.hy.common.Date;
import org.hy.common.Help;
import org.hy.common.StringHelp;
import org.hy.common.xml.XJava;

import com.fms.platform.common.util.PageUtil;
import org.hy.common.xml.XSQL;


/**
 * 数据库操作层--基类
 * 
 * @author      ZhengWei(HY)
 * @createDate  2014-09-23
 * @version     v1.0  
 */
public class BaseDAO<T> extends Base
{
    public  static final Date                $MaxDate         = new Date("2100-01-01 00:00:00");
    
    public  static final Date                $MinDate         = new Date("2000-01-01 00:00:00");
    
    private static final int                 $RandomMax       = 999999;
    
    private static final int                 $RandomLen       = ("" + $RandomMax).length();
    
    
    
    protected String makeID()
    {
        return UUID.randomUUID().toString().replace("-" ,"").toUpperCase();
    }
    
    

    
    /**
     * 生成主键(按时间顺序)
     * 
     * @return
     */
    protected synchronized String makeID_Time()
    {
        return this.makeID_Time("");
    }
    
    
    
    /**
     * 生成主键(按时间顺序)
     * 
     * @return
     */
    protected synchronized String makeID_Time(String i_OrderBy)
    {
        Date   v_Date   = Date.getNowTime();
        Random v_Random = new Random();
        
        return v_Date.getFullMilli_ID() + i_OrderBy + StringHelp.lpad(v_Random.nextInt($RandomMax) ,$RandomLen ,"0");
    }
    
    
    
    /**
     * 生成主键(按时间倒序)
     * 
     * @return
     */
    protected synchronized String makeID_ReverseTime()
    {
        return this.makeID_ReverseTime("");
    }
    
    
    
    /**
     * 生成主键(按时间倒序)
     * 
     * @return
     */
    protected synchronized String makeID_ReverseTime(String i_OrderBy)
    {
        Date   v_Date   = Date.getNowTime();
        Random v_Random = new Random();
        
        v_Date = new Date($MaxDate.getTime() - v_Date.getTime() + $MinDate.getTime());
        
        return v_Date.getFullMilli_ID() + i_OrderBy + StringHelp.lpad(v_Random.nextInt($RandomMax) ,$RandomLen ,"0");
    }
    
    
    /**
     * 通用的查询封装方法
     * 得到数据列表
     * 
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<T> queryRecords(String i_xid ,BaseModel i_Params)
    {
        System.out.println(XJava.getXSQL(i_xid).getContent().getSQL(i_Params));
        // 调用WEB-INF/db/*.xml  SQL查询数据库
    	return (List<T>)this.getXSQL(i_xid).query(i_Params);
    }
    
    /**
     * 通用的查询封装方法
     * 得到数据列表
     * 
     * 
     * @param i_xid 组装的sql标识
     * @return
     */
    @SuppressWarnings("unchecked")
	protected List<T> queryRecords(String i_xid)
    {
        // 调用WEB-INF/db/*.xml  SQL查询数据库
    	return (List<T>)this.getXSQL(i_xid).query();
    }
    
    /**
     * 对于要进行分页查询的数据进行查询
     * 获得数据列表
     * XSQL_Platform_QueryByPage 标识是预先定义好的通用分页查询语句
     * 还需要开发人员根据业务表拼写部分片段
     * 需要注意不同数据库分页sql不同
     * 
     * @param i_XSQLID 具体业务模块sql标识
     * @param i_Params 检索条件
     * @return 数据列表
     */
    
    @SuppressWarnings("unchecked")
	protected synchronized List<T> queryByPage(String i_XSQLID ,BaseModel i_Params)
    {
        i_Params.setStart(PageUtil.getFromIndex(i_Params.getCurrentPage() ,i_Params.getLimit()));
    	// 调用WEB-INF/db/*.xml  SQL查询数据库
    	return (List<T>) XSQL.queryPaging(i_XSQLID).query(i_Params);
    }
    
    
    /**
     * 通用查询记录数方法
     * 通常分页使用，或者报知记录数
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
    @SuppressWarnings("unchecked")
	protected int QueryRecordsCount(String i_xid ,BaseModel i_Params)
    {
        System.out.println(XJava.getXSQL(i_xid).getContent().getSQL(i_Params));
    	// 调用WEB-INF/db/*.xml  SQL进行保存操作
    	List<List<Integer>> list =  (List<List<Integer>>)this.getXSQL(i_xid).query(i_Params);
    	// 返回影响结果行数
    	return list.get(0).get(0);
    }
    
    /**
     * 通用的查询封装方法
     * 得到查询单个数据对象
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
    @SuppressWarnings("unchecked")
	protected T queryResult(String i_xid ,BaseModel i_Params)
    {
    	// 调用WEB-INF/db/*.xml  SQL查询数据库
    	List<T> v_list = (List<T>)this.getXSQL(i_xid).query(i_Params);
    	// 返回结果集第一个数据
    	return Help.isNull(v_list)?null:v_list.get(0);
    }
    
    /**
     * 通用的save方法
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
	protected int save(String i_xid ,BaseModel i_Params)
    {
    	// 调用WEB-INF/db/*.xml  SQL进行保存操作
    	int line =  this.getXSQL(i_xid).executeUpdate(i_Params);
    	// 返回影响结果行数
    	return line;
    }
    
    /**
     * 通用的save方法
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
	protected int batchSave(String i_xid ,List<BaseModel> i_Params)
    {
    	// 调用WEB-INF/db/*.xml  SQL进行保存操作
    	int line =  this.getXSQL(i_xid).executeUpdates(i_Params);
    	// 返回影响结果行数
    	return line;
    }
    
    /**
     * 通用的删除方法
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
	protected int update(String i_xid ,BaseModel i_Params)
    {
	    System.out.println(XJava.getXSQL(i_xid).getContent().getSQL(i_Params));
    	// 调用WEB-INF/db/*.xml  SQL进行保存操作
    	int line =  this.getXSQL(i_xid).executeUpdate(i_Params);
    	// 返回影响结果行数
    	return line;
    }
   
    
    /**
     * 通用的delete方法
     * 
     * @param i_xid 组装的sql标识
     * @param i_Params 封装的sql条件
     * @return
     */
	protected int delete(String i_xid ,BaseModel i_Params)
    {
    	// 调用WEB-INF/db/*.xml  SQL进行保存操作
    	int line =  this.getXSQL(i_xid).executeUpdate(i_Params);
    	// 返回影响结果行数
    	return line;
    }
    
}
