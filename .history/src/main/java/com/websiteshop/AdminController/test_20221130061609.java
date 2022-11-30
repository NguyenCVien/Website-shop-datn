package com.websiteshop.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.entity.Authority;
import com.websiteshop.service.AuthorityService;

@Controller
@RequestMapping("/author")
public class test {
    @Autowired
    AuthorityService authorityService;

    @GetMapping("")
    public String list(Model model) {
        List<Authority> list = authorityService.findAll();
        model.addAttribute("authority", list);
        return "/admin/authority/list";
    }