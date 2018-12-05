package com.fms.ftl.controller;


import com.fms.platform.common.BaseAppMessage;
import com.fms.report.dto.DepartmentOutDto;
import com.fms.report.model.Department;
import com.fms.report.model.Excacess;
import com.fms.report.model.Role;
import com.fms.report.service.IDepartmentService;
import com.fms.report.service.IExcacessService;
import com.fms.report.service.IRoleService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.hy.common.Return;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.enterprise.inject.New;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping(value="/report/kaoqin")
public class DepartmentController extends BaseAppMessage {
     //卡类别接口调用
     private IRoleService roleService =(IRoleService) this.getObject("reportRoleService");
    //部门选择接口调用
    private  IDepartmentService iDepartmentService =(IDepartmentService) this.getObject("reportDepartmentService");

    /*
     * 完成部门查询相关的业务
     * */


    @RequestMapping(value="/tankuang")
    public String BName(String reportdate, String reportdate1, ModelMap model) throws ParseException {
        System.out.println("进入部门帅选页面");
        return "/reports/department";
    }
    @RequestMapping(value = "/departmentList", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Department> departmentList() {
        System.out.println("进入部门选择页面");
        List<Department> departments = iDepartmentService.queryDepartmentList2();

        for (Department str :departments){
            System.out.println(str.toString());
        }
        return departments;
    }

    /*卡类别
    *
    * */


   @RequestMapping(value = "/role",produces = "application/json;charset=UTF-8")
    public List<Role> roleList(){
       List<Role> roleList = roleService.queryRoleList();
       for (Role role :roleList){
           System.out.println(role.toString());
       }
       return  null;
   }



}
