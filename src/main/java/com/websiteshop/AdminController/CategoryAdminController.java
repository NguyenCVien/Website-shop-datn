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
import com.websiteshop.entity.Category;
import com.websiteshop.model.CategoryDto;
import com.websiteshop.service.CategoryService;

@Controller
@RequestMapping("admin/categories")
public class CategoryAdminController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping("")
	public String list(Model model) {
		List<Category> list = categoryService.findAll();
		model.addAttribute("category", list);
		return "admin/category/list";
	}

	@GetMapping("add")
	public String add(Model model) {
		CategoryDto dto = new CategoryDto();
		dto.setIsEdit(false);
		model.addAttribute("category", dto);
		return "admin/category/addOrEdit";
	}

	@GetMapping("reset")
	public String reset(Model model) {
		CategoryDto dto = new CategoryDto();
		model.addAttribute("category", dto);

		return "admin/category/addOrEdit";

	}

	@PostMapping("saveOrUpdate")
	public ModelAndView saveOrUpdate(ModelMap model, @ModelAttribute("category") CategoryDto dto,
			BindingResult result) {

		Category entity = new Category();
		BeanUtils.copyProperties(dto, entity);

		try {
			categoryService.save(entity);
			model.addAttribute("message", "Loại hàng đã được lưu");
		} catch (Exception e) {
			model.addAttribute("message", "Invalid category");
		}

		return new ModelAndView("forward:/admin/categories", model);
	}

	@GetMapping("edit/{categoryId}")
	public ModelAndView edit(ModelMap model, @PathVariable("categoryId") Long categoryId) {

		Optional<Category> opt = categoryService.findById(categoryId);
		CategoryDto dto = new CategoryDto();

		if (opt.isPresent()) {
			Category entity = opt.get();
			BeanUtils.copyProperties(entity, dto);
			dto.setIsEdit(true);

			model.addAttribute("category", dto);
			return new ModelAndView("admin/category/addOrEdit", model);
		}

		model.addAttribute("message", "Loại hàng không tồn tại");

		return new ModelAndView("forward:/admin/categories", model);
	}

	@GetMapping("delete/{categoryId}")
	public ModelAndView delete(ModelMap model, @PathVariable("categoryId") Long categoryId) throws IOException {

		try {
			categoryService.deleteById(categoryId);
			model.addAttribute("message", "Loại hàng đã được xóa");
		} catch (Exception e) {
			model.addAttribute("message", "Không thể xóa loại hàng có chứa sản phẩm!");
		}

		return new ModelAndView("forward:/admin/categories", model);
	}
}
