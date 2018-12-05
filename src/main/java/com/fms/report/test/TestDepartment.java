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
import com.fms.report.dao.IDepartmentDAO;
import com.fms.report.dto.DepartmentInDto;
import com.fms.report.dto.DepartmentOutDto;
import com.fms.report.model.Department;
import com.fms.report.service.IDepartmentService;

public class TestDepartment extends BaseJunit {
	
//	public static void main(String[] args) {
//		new BaseJunit();
//		AppMessage<Department> app = new AppMessage<Department>();
//		app.setSidv("1.0");
//		app.setSysid("plm");
//		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/report/department/queryDepartmentList" ,app.toString() ,"UTF-8");
//		System.out.println(v_RestResponse);
//	}

	@Test
	public void test_api_queryList(){
		XJSON v_JSON = new XJSON();
		v_JSON.setReturnNVL(false);
		AppMessage<Department> app = new AppMessage<Department>();
		app.setSidv("1.0");
		app.setSysid("plm");
		Department department = new Department();
		app.setBody(department);
		app.setTokenSec(StringHelp.md5(app.getSysid() + "plm"));
		app.setSign(StringHelp.md5(app.bodytoString() + "plm"));
		String v_RestResponse = BaseAppMessage.restRequest("http://localhost:8080/brp/services/report/department/queryDepartmentList" ,app.toString(v_JSON) ,"UTF-8");
		assertTrue(v_RestResponse.indexOf("\"result\":\"true\"") >=0);
		System.out.println(v_RestResponse);
	}
	
    @Test
    public void test_dao_queryList_byPage()
    {
        IDepartmentDAO v_DAO    = (IDepartmentDAO)this.getObject("reportDepartmentDao");
        Department     v_Params = new Department();
        
        v_Params.setCurrentPage(1);
        v_Params.setLimit(2);
        
        Help.print(v_DAO.queryDepartmentListByPage(v_Params));
    }
    
    
    @Test
    public void test_service_queryDepartment()
    {
        IDepartmentService v_Service    = (IDepartmentService)this.getObject("reportDepartmentService");
        DepartmentInDto     v_Params = new DepartmentInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        
        Return<DepartmentOutDto> v_return = v_Service.queryDepartment(v_Params);
        System.out.println(v_return.paramObj.getData().toString());
        //Assert.assertEquals(expected, v_return.paramObj.getData());
    }
    
    @Test
    public void test_dao_insert()
    {
        IDepartmentDAO v_DAO    = (IDepartmentDAO)this.getObject("reportDepartmentDao");
        Department     v_Params = new Department();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Integer count = v_DAO.saveDepartment(v_Params);
        Assert.assertSame(1, count);
    }
    
    @Test
    public void test_dao_update()
    {
        IDepartmentDAO v_DAO    = (IDepartmentDAO)this.getObject("reportDepartmentDao");
        Department     v_Params = new Department();
        // 更新条件
//        v_Params.setId(5);   
        // 更新的值
//        v_Params.setName("xiaozhang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("的说法拉升的法律框时刻");
//        v_Params.setCompanyAddress("pppdasfewi");
        
        Integer count = v_DAO.updateDepartment(v_Params);
        Assert.assertTrue( count > 0 );
    }
    
    
    @Test
    public void test_service_insert()
    {
        IDepartmentService v_Service    = (IDepartmentService)this.getObject("reportDepartmentService");
        DepartmentInDto     v_Params = new DepartmentInDto();
        
//        v_Params.setName("xiaowang");
//        v_Params.setSex("男");
//        v_Params.setFamilyAddress("中国");
        
        Return<DepartmentOutDto> v_return = v_Service.saveDepartment(v_Params);
        assertTrue(v_return.booleanValue());
    }
}
