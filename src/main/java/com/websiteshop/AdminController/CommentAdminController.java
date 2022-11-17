package com.websiteshop.AdminController;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.websiteshop.entity.Comment;
import com.websiteshop.model.AccountDto;
import com.websiteshop.model.CommentDto;
import com.websiteshop.model.ProductDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.CommentService;
import com.websiteshop.service.ProductService;

@Controller
@RequestMapping("admin/comments")
public class CommentAdminController {
	@Autowired
	CommentService commentService;

	@Autowired
	AccountService accountService;

	@Autowired
	ProductService productService;

	@ModelAttribute("accounts")
	public List<AccountDto> getAccounts() {
		return accountService.findAll().stream().map(item -> {
			AccountDto dto = new AccountDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@ModelAttribute("products")
	public List<ProductDto> getCategories() {
		return productService.findAll().stream().map(item -> {
			ProductDto dto = new ProductDto();
			BeanUtils.copyProperties(item, dto);
			return dto;
		}).toList();
	}

	@RequestMapping("")
	public String list(Model model) {
		List<Comment> list = commentService.findAll();
		model.addAttribute("comments", list);
		return "admin/comments/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		CommentDto dto = new CommentDto();
		dto.setIsEdit(false);
		model.addAttribute("comment", dto);
		return "admin/comments/addOrEdit";
	}

	@GetMapping("edit/{commentId}")
	public ModelAndView edit(ModelMap model, @PathVariable("commentId") Long commentId) {

		Optional<Comment> opt = commentService.findById(commentId);
		CommentDto dto = new CommentDto();

		if (opt.isPresent()) {
			Comment entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("comment", dto);
			return new ModelAndView("admin/comments/addOrEdit", model);
		}

		model.addAttribute("message", "Bình luận không tồn tại");

		return new ModelAndView("redirect:/admin/comments", model);
	}

	@GetMapping("reset")
	public String createComment(Model model) {
		CommentDto p = new CommentDto();
		model.addAttribute("comment", p);

		return "admin/comments/addOrEdit";

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model,
			@ModelAttribute("comment") CommentDto dto, BindingResult result) {

		if (result.hasErrors()) {
			return new ModelAndView("admin/comments/addOrEdit");
		}
		Comment entity = new Comment();
		BeanUtils.copyProperties(dto, entity);

		commentService.save(entity);
		model.addAttribute("message", "Bình luận đã được lưu");
		return new ModelAndView("forward:/admin/comments", model);
	}

	@GetMapping("delete/{commentId}")
	public ModelAndView delete(ModelMap model, @PathVariable("commentId") Long commentId) throws IOException {

		Optional<Comment> opt = commentService.findById(commentId);

		if (opt.isPresent()) {
			commentService.delete(opt.get());
			model.addAttribute("message", "Bình luận đã được xóa!");
		} else {
			model.addAttribute("message", "Không tìm thấy bình luận!");
		}

		return new ModelAndView("forward:/admin/comments", model);
	}
}
