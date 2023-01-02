package com.websiteshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websiteshop.dao.CategoryDAO;
import com.websiteshop.dao.ProductDAO;
import com.websiteshop.entity.Product;
import com.websiteshop.service.ProductService;

@Controller
public class HomeController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryDAO dao;
    @Autowired
    ProductDAO pdao;

    @RequestMapping("/")
    public String home(Model model, @RequestParam("cid") Optional<Long> cid,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page) {

        try {
            Page<Product> pageProduct = null;
            Pageable pageable = PageRequest.of(page.orElse(0), 30, Sort.by("name"));
            if (StringUtils.hasText(name)) {
                pageProduct = productService.findByNameContaining(name, pageable);
            } else {
                pageProduct = productService.findAll(pageable);
            }
            model.addAttribute("items", pageProduct);
        } catch (Exception e) {
            return "redirect:/home404";
        }

        return "product/list";

    }

    @RequestMapping("/help")
    public String help(Model model) {
        return "help/help";
    }

    @RequestMapping("/home404")
    public String f404(Model model) {
        return "admin/dist/404_home";
    }

}
