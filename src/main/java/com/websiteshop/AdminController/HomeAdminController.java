package com.websiteshop.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAdminController {
    @RequestMapping({ "/admin", "/admin/home/index" })
    public String indexAdmin() {
        // return "/admin/product/list.html";
        return "/admin/dist/index";
    }

    @RequestMapping("/admin/category/list")
    public String cateList() {
        return "/admin/category/list";
    }

    @RequestMapping("/admin/category/add")
    public String cateAdd() {
        return "/admin/category/AddOrEdit";
    }

    @RequestMapping("/401")
    public String S401() {
        return "/admin/dist/401";
    }

    @RequestMapping("/404")
    public String S404() {
        return "/admin/dist/404";
    }

    @RequestMapping("/500")
    public String S500() {
        return "/admin/dist/500";
    }

    @RequestMapping("/ccc")
    public String ccc() {
        return "/admin/dist/tables";
    }

}
