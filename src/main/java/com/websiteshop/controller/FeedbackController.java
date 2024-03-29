package com.websiteshop.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.websiteshop.entity.Feedback;
import com.websiteshop.model.FeedbackDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.FeedbackService;
@Controller
public class FeedbackController {
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	AccountService accountService;
	
	Feedback entity = new Feedback();
	
	@RequestMapping("user/feedback/{username}")
	public String add(Model model, @PathVariable("username")String username) {
		entity.setUsername(username);
		return "user/feedback";
	}
	

	@PostMapping("user/feedback/save")
	public String save(ModelMap model,
			@ModelAttribute("feedback")FeedbackDto dto, BindingResult result) {
		
		BeanUtils.copyProperties(dto, entity);
		
		feedbackService.save(entity);
		model.addAttribute("message", "Cảm ơn bạn đã góp ý!");
		return "user/feedback";
	}
}
