package com.websiteshop.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class authorityController {
    @RequestMapping("/admin/authority")
    public String index() {
        return "/admin/authority/index";
    }

    @RequestMapping("/admin/authority/unauthorized")
    public String unauthorized() {
        return "/admin/authority/unauthorized";
    }

}
