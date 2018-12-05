package com.fms.platform.test;

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
import com.fms.platform.dao.IAppMenuAuthInforDAO;
import com.fms.platform.dto.AppMenuAuthInforInDto;
import com.fms.platform.dto.AppMenuAuthInforOutDto;
import com.fms.platform.model.AppMenuAuthInfor;
import com.fms.platform.service.IAppMenuAuthInforService;

public class TestAppMenuAuthInfor extends BaseJunit {
	
//	public static void main(String[] args) {
//		new BaseJunit();
//		AppMessage<AppMenuAuthInfor> app = new AppMessage<AppMenuAuthInfor>();
//		app.setSidv("1.0");
//		app.setSysid("plm");
//		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/platform/appMenuAuthInfor/queryAppMenuAuthInforList" ,app.toString() ,"UTF-8");
//		System.out.println(v_RestResponse);
//	}

	@Test
	public void test_api_queryList(){
		XJSON v_JSON = new XJSON();
		v_JSON.setReturnNVL(false);
		AppMessage<AppMenuAuthInfor> app = new AppMessage<AppMenuAuthInfor>();
		app.setSidv("1.0");
		app.setSysid("plm");
		AppMenuAuthInfor appMenuAuthInfor = new AppMenuAuthInfor();
		app.setBody(appMenuAuthInfor);
		app.setTokenSec(StringHelp.md5(app.getSysid() + "plm"));
		app.setSign(StringHelp.md5(app.bodytoString() + "plm"));
		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/platform/appMenuAuthInfor/queryAppMenuAuthInforList" ,app.toString(v_JSON) ,"UTF-8");
		assertTrue(v_RestResponse.indexOf("\"result\":\"true\"") >=0);
		System.out.println(v_RestResponse);
	}
	
    @Test
    public void test_dao_queryList_byPage()
    {
        IAppMenuAuthInforDAO v_DAO    = (IAppMenuAuthInforDAO)this.getObject("platformAppMenuAuthInforDao");
        AppMenuAuthInfor     v_Params = new AppMenuAuthInfor();
        
        v_Params.setCurrentPage(1);
        v_Params.setLimit(2);
        
        Help.print(v_DAO.queryAppMenuAuthInforListByPage(v_Params));
    }
    
    
    @Test
    public void test_service_queryAppMenuAuthInfor()
    {
        IAppMenuAuthInforService v_Service    = (IAppMenuAuthInforService)this.getObject("platformAppMenuAuthInforService");
        AppMenuAuthInforInDto     v_Params = new AppMenuAuthInforInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        
        Return<AppMenuAuthInforOutDto> v_return = v_Service.queryAppMenuAuthInfor(v_Params);
        System.out.println(v_return.paramObj.getData().toString());
        //Assert.assertEquals(expected, v_return.paramObj.getData());
    }
    
    @Test
    public void test_dao_insert()
    {
        IAppMenuAuthInforDAO v_DAO    = (IAppMenuAuthInforDAO)this.getObject("platformAppMenuAuthInforDao");
        AppMenuAuthInfor     v_Params = new AppMenuAuthInfor();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Integer count = v_DAO.saveAppMenuAuthInfor(v_Params);
        Assert.assertSame(1, count);
    }
    
    @Test
    public void test_dao_update()
    {
        IAppMenuAuthInforDAO v_DAO    = (IAppMenuAuthInforDAO)this.getObject("platformAppMenuAuthInforDao");
        AppMenuAuthInfor     v_Params = new AppMenuAuthInfor();
        // 更新条件
//        v_Params.setId(5);   
        // 更新的值
//        v_Params.setName("xiaozhang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("的说法拉升的法律框时刻");
//        v_Params.setCompanyAddress("pppdasfewi");
        
        Integer count = v_DAO.updateAppMenuAuthInfor(v_Params);
        Assert.assertTrue( count > 0 );
    }
    
    
    @Test
    public void test_service_insert()
    {
        IAppMenuAuthInforService v_Service    = (IAppMenuAuthInforService)this.getObject("platformAppMenuAuthInforService");
        AppMenuAuthInforInDto     v_Params = new AppMenuAuthInforInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Return<AppMenuAuthInforOutDto> v_return = v_Service.saveAppMenuAuthInfor(v_Params);
        assertTrue(v_return.booleanValue());
    }
}
