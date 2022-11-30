package com.websiteshop.AdminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websiteshop.entity.Authority;
import com.websiteshop.service.AuthorityService;

import java.util.List;
import java.util.Optional;

@Controller
public class authorityController {
    @Autowired
    AuthorityService authorityService;

    @RequestMapping("/authority")
    public String a(Model model) {
        List<Authority> list = authorityService.findAll();
        model.addAttribute("author", list);
        return "/admin/authority/list";
    }

    public String saveOrUpdate() {

        return "/admin/authority/addOrEdit";
    }

    @GetMapping("add")
    public String add(Model model) {
        AccountDto dto = new AccountDto();
        dto.setIsEdit(false);
        model.addAttribute("account", dto);
        return "admin/accounts/addOrEdit";
    }

    @GetMapping("edit/{username}")
    public ModelAndView edit(ModelMap model, @PathVariable("username") String username) {

        Optional<Account> opt = accountService.findById(username);
        AccountDto dto = new AccountDto();

        if (opt.isPresent()) {
            Account entity = opt.get();
            BeanUtils.copyProperties(entity, dto);
            dto.setIsEdit(true);

            model.addAttribute("account", dto);
            return new ModelAndView("admin/accounts/addOrEdit", model);
        }

        model.addAttribute("message", "Tài khoản không tồn tại");

        return new ModelAndView("forward:/admin/accounts", model);
    }

    @GetMapping("reset")
    public String createAccount(Model model) {
        AccountDto p = new AccountDto();
        model.addAttribute("account", p);

        return "admin/accounts/addOrEdit";

    }

    @PostMapping("saveOrUpdate")
    public ModelAndView saveOrUpdate(ModelMap model,
            @ModelAttribute("account") AccountDto dto, BindingResult result) {

        if (result.hasErrors()) {
            return new ModelAndView("admin/accounts/addOrEdit");
        }
        Account entity = new Account();
        BeanUtils.copyProperties(dto, entity);

        if (!dto.getImageFile().isEmpty()) {
            entity.setImage(storageService.getStoredFilename(dto.getImageFile(), null));
            storageService.store(dto.getImageFile(), entity.getImage());
        }

        accountService.save(entity);
        model.addAttribute("message", "Tài khoản đã được lưu");
        return new ModelAndView("forward:/admin/accounts", model);
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
