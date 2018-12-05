package com.fms.ftl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017-10-25.
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/index")
    public String index(ModelMap model) {
        return "redirect:/manager.z";
    }

    @RequestMapping(value = "/admin")
    public String admin(ModelMap model) {
        return "/admin";
    }

    @RequestMapping(value = "/manager")
    public String manager(ModelMap model) {
        return "/manager";
    }


    @RequestMapping(value = "/gk")
    public String dashboard(ModelMap model) {
        return "/dashboard";
    }
}