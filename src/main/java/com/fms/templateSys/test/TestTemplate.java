package com.fms.templateSys.test;

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
import com.fms.templateSys.dao.ITemplateDAO;
import com.fms.templateSys.dto.TemplateInDto;
import com.fms.templateSys.dto.TemplateOutDto;
import com.fms.templateSys.model.Template;
import com.fms.templateSys.service.ITemplateService;

public class TestTemplate extends BaseJunit {
	
//	public static void main(String[] args) {
//		new BaseJunit();
//		AppMessage<Template> app = new AppMessage<Template>();
//		app.setSidv("1.0");
//		app.setSysid("plm");
//		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/${system}/template/queryTemplateList" ,app.toString() ,"UTF-8");
//		System.out.println(v_RestResponse);
//	}

	@Test
	public void test_api_queryList(){
		XJSON v_JSON = new XJSON();
		v_JSON.setReturnNVL(false);
		AppMessage<Template> app = new AppMessage<Template>();
		app.setSidv("1.0");
		app.setSysid("plm");
		Template template = new Template();
		app.setBody(template);
		app.setTokenSec(StringHelp.md5(app.getSysid() + "plm"));
		app.setSign(StringHelp.md5(app.bodytoString() + "plm"));
		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/${system}/template/queryTemplateList" ,app.toString(v_JSON) ,"UTF-8");
		assertTrue(v_RestResponse.indexOf("\"result\":\"true\"") >=0);
		System.out.println(v_RestResponse);
	}
	
    @Test
    public void test_dao_queryList_byPage()
    {
        ITemplateDAO v_DAO    = (ITemplateDAO)this.getObject("${system}TemplateDao");
        Template     v_Params = new Template();
        
        v_Params.setCurrentPage(1);
        v_Params.setLimit(2);
        
        Help.print(v_DAO.queryTemplateListByPage(v_Params));
    }
    
    
    @Test
    public void test_service_queryTemplate()
    {
        ITemplateService v_Service    = (ITemplateService)this.getObject("${system}TemplateService");
        TemplateInDto     v_Params = new TemplateInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        
        Return<TemplateOutDto> v_return = v_Service.queryTemplate(v_Params);
        System.out.println(v_return.paramObj.getData().toString());
        //Assert.assertEquals(expected, v_return.paramObj.getData());
    }
    
    @Test
    public void test_dao_insert()
    {
        ITemplateDAO v_DAO    = (ITemplateDAO)this.getObject("${system}TemplateDao");
        Template     v_Params = new Template();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Integer count = v_DAO.saveTemplate(v_Params);
        Assert.assertSame(1, count);
    }
    
    @Test
    public void test_dao_update()
    {
        ITemplateDAO v_DAO    = (ITemplateDAO)this.getObject("${system}TemplateDao");
        Template     v_Params = new Template();
        // 更新条件
//        v_Params.setId(5);   
        // 更新的值
//        v_Params.setName("xiaozhang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("的说法拉升的法律框时刻");
//        v_Params.setCompanyAddress("pppdasfewi");
        
        Integer count = v_DAO.updateTemplate(v_Params);
        Assert.assertTrue( count > 0 );
    }
    
    
    @Test
    public void test_service_insert()
    {
        ITemplateService v_Service    = (ITemplateService)this.getObject("${system}TemplateService");
        TemplateInDto     v_Params = new TemplateInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Return<TemplateOutDto> v_return = v_Service.saveTemplate(v_Params);
        assertTrue(v_return.booleanValue());
    }
}
