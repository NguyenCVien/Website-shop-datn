package com.websiteshop.AdminController;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.entity.Order;
import com.websiteshop.entity.Statitics;
import com.websiteshop.service.StatiticService;

@Controller
@RequestMapping("statitic")
public class StatiticAdminController {
	
	@Autowired
	StatiticService statiticService;
	
	Statitics s = new Statitics();
	
	@GetMapping("/turnover")
	public String listTurnover(Model model) {
		
		
		Statitics list = statiticService.SLOrder();
		
		model.addAttribute("statitics" ,list);
		return "admin/statitic/listTurnover";
	}
}
