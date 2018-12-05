package com.fms.templateSys.model;

import com.fms.platform.common.BaseModel;
import org.hy.common.xml.annotation.Doc;





/**
 * ${beanDesc}
 *
 * @author      ${author}
 * @createDate  ${createDate}
 * @version     v1.0
 */
public class Template extends BaseModel
{
    private static final long serialVersionUID = 8859982104548101972L;

    
    /** 主键编号(系统采用消息流水号) */
    private String               sid;
    
    
    public String getSid() {
		return sid;
	}


    //${field}

	public void setSid(String sid) {
		this.sid = sid;
	}



    
    public Template()
    {
    }
    
    
    
    @Override
    public String getRowKey()
    { 
        return this.getSid();
    }


    
    
    
    
    

}
