package com.fms.report.test;

import static org.junit.Assert.assertTrue;

import org.hy.common.Help;
import org.hy.common.Return;
import org.hy.common.StringHelp;
import org.hy.common.xml.XJSON;
import org.hy.common.xml.plugins.AppMessage;
import org.junit.Assert;
import org.junit.Test;

import com.fms.platform.common.BaseAppMessage;
import com.fms.platform.common.BaseJunit;
import com.fms.report.dao.IExcacessDAO;
import com.fms.report.dto.ExcacessInDto;
import com.fms.report.dto.ExcacessOutDto;
import com.fms.report.model.Excacess;
import com.fms.report.service.IExcacessService;

public class TestExcacess extends BaseJunit {
	
//	public static void main(String[] args) {
//		new BaseJunit();
//		AppMessage<Excacess> app = new AppMessage<Excacess>();
//		app.setSidv("1.0");
//		app.setSysid("plm");
//		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/report/excacess/queryExcacessList" ,app.toString() ,"UTF-8");
//		System.out.println(v_RestResponse);
//	}

	@Test
	public void test_api_queryList(){
		XJSON v_JSON = new XJSON();
		v_JSON.setReturnNVL(false);
		AppMessage<Excacess> app = new AppMessage<Excacess>();
		app.setSidv("1.0");
		app.setSysid("plm");
		Excacess excacess = new Excacess();
		app.setBody(excacess);
		app.setTokenSec(StringHelp.md5(app.getSysid() + "plm"));
		app.setSign(StringHelp.md5(app.bodytoString() + "plm"));
		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/report/excacess/queryExcacessList" ,app.toString(v_JSON) ,"UTF-8");
		assertTrue(v_RestResponse.indexOf("\"result\":\"true\"") >=0);
		System.out.println(v_RestResponse);
	}
	
    @Test
    public void test_dao_queryList_byPage()
    {
        IExcacessDAO v_DAO    = (IExcacessDAO)this.getObject("reportExcacessDao");
        Excacess     v_Params = new Excacess();
        
        v_Params.setCurrentPage(1);
        v_Params.setLimit(2);
        
        Help.print(v_DAO.queryExcacessListByPage(v_Params));
    }
    
    
    @Test
    public void test_service_queryExcacess()
    {
        IExcacessService v_Service    = (IExcacessService)this.getObject("reportExcacessService");
        ExcacessInDto     v_Params = new ExcacessInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        
        Return<ExcacessOutDto> v_return = v_Service.queryExcacess(v_Params);
        System.out.println(v_return.paramObj.getData().toString());
        //Assert.assertEquals(expected, v_return.paramObj.getData());
    }
    
    @Test
    public void test_dao_insert()
    {
        IExcacessDAO v_DAO    = (IExcacessDAO)this.getObject("reportExcacessDao");
        Excacess     v_Params = new Excacess();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Integer count = v_DAO.saveExcacess(v_Params);
        Assert.assertSame(1, count);
    }
    
    @Test
    public void test_dao_update()
    {
        IExcacessDAO v_DAO    = (IExcacessDAO)this.getObject("reportExcacessDao");
        Excacess     v_Params = new Excacess();
        // 更新条件
//        v_Params.setId(5);   
        // 更新的值
//        v_Params.setName("xiaozhang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("的说法拉升的法律框时刻");
//        v_Params.setCompanyAddress("pppdasfewi");
        
        Integer count = v_DAO.updateExcacess(v_Params);
        Assert.assertTrue( count > 0 );
    }
    
    
    @Test
    public void test_service_insert()
    {
        IExcacessService v_Service    = (IExcacessService)this.getObject("reportExcacessService");
        ExcacessInDto     v_Params = new ExcacessInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Return<ExcacessOutDto> v_return = v_Service.saveExcacess(v_Params);
        assertTrue(v_return.booleanValue());
    }
}
