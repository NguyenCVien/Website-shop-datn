package com.websiteshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("help")
public class HelpController {
	
	@GetMapping("help1")
	public String help1() {
		return "help/help_1";
	}
	
	@GetMapping("help2")
	public String help2() {
		return "help/help_2";
	}
	
	@GetMapping("help3")
	public String help3() {
		return "help/help_3";
	}
	
	@GetMapping("help4")
	public String help4() {
		return "help/help_4";
	}
	
	@GetMapping("help5")
	public String help5() {
		return "help/help_5";
	}
	
	@GetMapping("help6")
	public String help6() {
		return "help/help_6";
	}
	
	@GetMapping("help7")
	public String help7() {
		return "help/help_7";
	}
	
	@GetMapping("help8")
	public String help8() {
		return "help/help_8";
	}
	
	@GetMapping("help9")
	public String help9() {
		return "help/help_9";
	}
}
