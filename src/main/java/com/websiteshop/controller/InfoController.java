package com.websiteshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.entity.Account;
import com.websiteshop.service.AccountService;

@Controller
public class InfoController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/info/{username}")
    public String info(Model model, @PathVariable("username") String username) {
        Account acc = accountService.findById(username).get();
        model.addAttribute("info", acc);
        return "user/info";
    }
}
