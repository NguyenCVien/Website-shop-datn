package com.websiteshop.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.websiteshop.entity.Feedback;
import com.websiteshop.model.FeedbackDto;
import com.websiteshop.service.FeedbackService;
@Controller
public class FeedbackController {
	@Autowired
	FeedbackService feedbackService;
	
	@RequestMapping("user/feedback")
	public String add(Model model) {
		FeedbackDto dto = new FeedbackDto();
		model.addAttribute("feedback", dto);
		return "user/feedback";
	}

	@PostMapping("user/feedback/save")
	public ModelAndView save(ModelMap model,
			@ModelAttribute("feedback") FeedbackDto dto, BindingResult result) {
		
		Feedback entity = new Feedback();
		BeanUtils.copyProperties(dto, entity);
		
		feedbackService.save(entity);
		model.addAttribute("message", "Cảm ơn bạn đã góp ý!");
		return new ModelAndView("forward:/user/feedback", model);
	}
}
