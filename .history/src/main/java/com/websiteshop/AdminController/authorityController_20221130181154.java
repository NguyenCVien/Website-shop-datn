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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.websiteshop.entity.Account;
import com.websiteshop.entity.Authority;
import com.websiteshop.model.AuthorityDto;
import com.websiteshop.service.AccountService;
import com.websiteshop.service.AuthorityService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping({ "/authority", "/admin/authority" })
public class authorityController {
    @Autowired
    AuthorityService authorityService;

    @GetMapping("")
    public String list(Model model) {
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
        // return new ModelAndView("forward:/authority", model);
        return new ModelAndView("forward:/admin", model);
    }

    @GetMapping("delete/{id}")
    public ModelAndView delete(ModelMap model,
            @PathVariable("id") Integer id) {
        Optional<Authority> opt = authorityService.findById(id);

        if (opt.isPresent()) {
            authorityService.delete(opt.get());
            model.addAttribute("message", "Tài khoản đã bị tướt quyền!");
        } else {
            model.addAttribute("message", "Không tìm thấy tài khoản!");
        }

        return new ModelAndView("forward:/authority", model);
    }

    // @GetMapping("/authority/list")
    // public String findAll(@RequestParam("admin") Optional<Boolean> admin,
    // Model model) {
    // if (admin.orElse(false)) {
    // List<Authority> list = authorityService.findAuthoritiesOfAdministrators();
    // }
    // List<Authority> list = authorityService.findAll();
    // model.addAttribute("author", list);
    // return "/admin/authority/list";
    // }

}
