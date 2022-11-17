package com.websiteshop.AdminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.websiteshop.entity.Feedback;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.FeedbackService;
import com.websiteshop.service.ProductService;

@Controller
@RequestMapping("admin/feedbacks")
public class FeedbackAdminController {
	@Autowired
	FeedbackService feedbackService;

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@RequestMapping("")
	public String list(Model model) {
		List<Feedback> list = feedbackService.findAll();
		model.addAttribute("feedbacks", list);
		return "admin/feedbacks/list";
	}

	@GetMapping("delete/{feedbackId}")
	public ModelAndView delete(ModelMap model, @PathVariable("feedbackId") Long feedbackId) throws IOException {

		Optional<Feedback> opt = feedbackService.findById(feedbackId);

		if (opt.isPresent()) {
			feedbackService.delete(opt.get());
			model.addAttribute("message", "Bình luận đã được xóa!");
		} else {
			model.addAttribute("message", "Không tìm thấy bình luận!");
		}

		return new ModelAndView("forward:/admin/feedbacks", model);
	}
}
