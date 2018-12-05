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
import com.fms.report.dao.IRoleDAO;
import com.fms.report.dto.RoleInDto;
import com.fms.report.dto.RoleOutDto;
import com.fms.report.model.Role;
import com.fms.report.service.IRoleService;

public class TestRole extends BaseJunit {
	
//	public static void main(String[] args) {
//		new BaseJunit();
//		AppMessage<Role> app = new AppMessage<Role>();
//		app.setSidv("1.0");
//		app.setSysid("plm");
//		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/report/role/queryRoleList" ,app.toString() ,"UTF-8");
//		System.out.println(v_RestResponse);
//	}

	@Test
	public void test_api_queryList(){
		XJSON v_JSON = new XJSON();
		v_JSON.setReturnNVL(false);
		AppMessage<Role> app = new AppMessage<Role>();
		app.setSidv("1.0");
		app.setSysid("plm");
		Role role = new Role();
		app.setBody(role);
		app.setTokenSec(StringHelp.md5(app.getSysid() + "plm"));
		app.setSign(StringHelp.md5(app.bodytoString() + "plm"));
		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/report/role/queryRoleList" ,app.toString(v_JSON) ,"UTF-8");
		assertTrue(v_RestResponse.indexOf("\"result\":\"true\"") >=0);
		System.out.println(v_RestResponse);
	}
	
    @Test
    public void test_dao_queryList_byPage()
    {
        IRoleDAO v_DAO    = (IRoleDAO)this.getObject("reportRoleDao");
        Role     v_Params = new Role();
        
        v_Params.setCurrentPage(1);
        v_Params.setLimit(2);
        
        Help.print(v_DAO.queryRoleListByPage(v_Params));
    }
    
    
    @Test
    public void test_service_queryRole()
    {
        IRoleService v_Service    = (IRoleService)this.getObject("reportRoleService");
        RoleInDto     v_Params = new RoleInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        
        Return<RoleOutDto> v_return = v_Service.queryRole(v_Params);
        System.out.println(v_return.paramObj.getData().toString());
        //Assert.assertEquals(expected, v_return.paramObj.getData());
    }
    
    @Test
    public void test_dao_insert()
    {
        IRoleDAO v_DAO    = (IRoleDAO)this.getObject("reportRoleDao");
        Role     v_Params = new Role();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Integer count = v_DAO.saveRole(v_Params);
        Assert.assertSame(1, count);
    }
    
    @Test
    public void test_dao_update()
    {
        IRoleDAO v_DAO    = (IRoleDAO)this.getObject("reportRoleDao");
        Role     v_Params = new Role();
        // 更新条件
//        v_Params.setId(5);   
        // 更新的值
//        v_Params.setName("xiaozhang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("的说法拉升的法律框时刻");
//        v_Params.setCompanyAddress("pppdasfewi");
        
        Integer count = v_DAO.updateRole(v_Params);
        Assert.assertTrue( count > 0 );
    }
    
    
    @Test
    public void test_service_insert()
    {
        IRoleService v_Service    = (IRoleService)this.getObject("reportRoleService");
        RoleInDto     v_Params = new RoleInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Return<RoleOutDto> v_return = v_Service.saveRole(v_Params);
        assertTrue(v_return.booleanValue());
    }
}
