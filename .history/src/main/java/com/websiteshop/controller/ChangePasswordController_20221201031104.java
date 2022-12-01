package com.websiteshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.service.AccountService;

@Controller
public class ChangePasswordController {

	@Autowired
	AccountService accountService;

	@RequestMapping("/changepass")
	public String add(Model model) {

		return "security/changepassword";
	}

}
