package com.websiteshop.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.websiteshop.entity.Comment;
import com.websiteshop.model.CommentDto;
import com.websiteshop.service.CommentService;

@Controller
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService commentService;

	@GetMapping("add")
	public String addComment(Model model) {
		CommentDto dto = new CommentDto();
		model.addAttribute("comment", dto);
		return "product/comment";
	}

	@GetMapping("reset")
	public String reset(Model model) {
		CommentDto p = new CommentDto();
		p.setDescription("");
		model.addAttribute("comment", p);
		return "product/comment";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap model,
			@ModelAttribute("comment") CommentDto dto, BindingResult result) {

		if (result.hasErrors()) {
			return "product/comment";
		}
		Comment entity = new Comment();
		BeanUtils.copyProperties(dto, entity);

		commentService.save(entity);
		model.addAttribute("message", "Cảm ơn bạn đã đánh giá sản phẩm.");
		return "product/comment";
	}
}
