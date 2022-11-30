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

import com.websiteshop.entity.Cmt;
import com.websiteshop.entity.Comment;
import com.websiteshop.model.CmtDto;
import com.websiteshop.model.CommentDto;
import com.websiteshop.service.CmtService;
import com.websiteshop.service.CommentService;

@Controller
@RequestMapping("/cmt")
public class CmtController {
	@Autowired
	CmtService cmtService;

	@GetMapping("add")
	public String addComment(Model model) {
		CommentDto dto = new CommentDto();
		model.addAttribute("comment", dto);
		return "product/detail";
	}

	@GetMapping("reset")
	public String reset(Model model) {
		CommentDto p = new CommentDto();
		p.setDescription("");
		model.addAttribute("comment", p);
		return "product/detail";
	}

	@PostMapping("saveOrUpdate")
	public String saveOrUpdate(ModelMap model,
			BindingResult result) {

		if (result.hasErrors()) {
			return "product/detail";
		}
		CmtDto dto = new CmtDto();
		Cmt entity = new Cmt();
		BeanUtils.copyProperties(dto, entity);

		model.addAttribute("cmt", entity);
		cmtService.save(entity);
		model.addAttribute("message", "Bình luận đã được lưu");
		return "product/detail";
	}
}
