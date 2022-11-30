package com.websiteshop.AdminController;

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

import com.websiteshop.entity.Authority;
import com.websiteshop.model.AuthorityDto;
import com.websiteshop.service.AuthorityService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/authority")
public class authorityController {
    @Autowired
    AuthorityService authorityService;

    @GetMapping("")
    public String a(Model model) {
        List<Authority> list = authorityService.findAll();
        model.addAttribute("authority", list);
        return "/admin/authority/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        AuthorityDto dto = new AuthorityDto();
        dto.setIsEdit(false);
        model.addAttribute("authority", dto);
        return "admin/authority/addOrEdit";
    }

    @GetMapping("edit/{id}")
    public ModelAndView edit(ModelMap model, @PathVariable("id") Integer id) {

        Optional<Authority> opt = authorityService.findById(id);
        AuthorityDto dto = new AuthorityDto();

        if (opt.isPresent()) {
            Authority au = opt.get();
            BeanUtils.copyProperties(au, dto);
            dto.setIsEdit(true);

            model.addAttribute("authority", dto);
            return new ModelAndView("/admin/authority/addOrEdit", model);
        }

        model.addAttribute("message", "Tài khoản không tồn tại");

        return new ModelAndView("forward:/authority", model);
    }

    @GetMapping("reset")
    public String createAccount(Model model) {
        AuthorityDto p = new AuthorityDto();
        model.addAttribute("authority", p);

        return "admin/authority/addOrEdit";

    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
            @ModelAttribute("authority") AuthorityDto dto, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("message", "Không tìm thấy tài khoản!");
            return new ModelAndView("admin/authority/addOrEdit", model);
        }
        Authority au = new Authority();
        BeanUtils.copyProperties(dto, au);

        authorityService.save(au);
        model.addAttribute("message", "Tài khoản đã được cấp quyền");
        return new ModelAndView("forward:/authority/", model);
    }

    // @RequestMapping("/admin/authority")
    // public String index() {
    // return "/admin/admin";
    // }

    // @RequestMapping("/authority/delete/{id}")
    // public String delete(Model model,
    // @PathVariable("id") Integer id) {
    // Authority au = authorityService.delete(id);
    // return "/admin/authority/index";
    // }
    //
    // @RequestMapping("/authority/list")
    // public String findAll(@RequestParam("admin") Optional<Boolean> admin,
    // Model model) {
    // if (admin.orElse(false)) {
    // List<Authority> list = authorityService.findAuthoritiesOfAdministrators();
    // }
    // List<Authority> list = authorityService.findAll();
    // model.addAttribute("author", list);
    // return "/admin/authority/index";
    // }
    //
    // @RequestMapping("/admin/authority/unauthorized")
    // public String unauthorized() {
    // return "/admin/authority/unauthorized";
    // }

}
