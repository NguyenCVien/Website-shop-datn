package com.websiteshop.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeAdminController {
    @RequestMapping({ "/admin", "/admin/home/index" })
    public String indexAdmin() {
        return "/admin/product/list.html";
        // return "/admin/dist/index";
    }

}
