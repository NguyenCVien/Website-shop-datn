package com.websiteshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websiteshop.dao.CategoryDAO;
import com.websiteshop.entity.Product;
import com.websiteshop.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CategoryDAO dao;

    @RequestMapping("/product/list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid) {
        if (cid.isPresent()) {
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            List<Product> list = productService.findAll();
            model.addAttribute("items", list);
        }
        return "product/list";
    }

    @RequestMapping("/product/detail/{productId}")
    public String detail(Model model, @PathVariable("productId") Integer id) {
        Product item = productService.findById(id);
        model.addAttribute("item", item);
        model.addAttribute("cates", dao.findAll());
        return "product/detail";
    }

}
