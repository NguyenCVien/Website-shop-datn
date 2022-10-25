package com.websiteshop.AdminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.entity.Product;
import com.websiteshop.service.ProductService;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {
    @Autowired
    ProductService productService;

    @GetMapping("list")
    public String list(Model model) {
        List<Product> list = productService.findAll();
        model.addAttribute("products", list);
        return "admin/product/list";
    }

    @GetMapping("add")
    public String add(Model model) {
        return "admin/product/AddOrEdit";
    }

    @GetMapping("new")
    public String createProduct(Model model) {
        Product p = new Product();
        model.addAttribute("product", p);

        return "admin/product/AddOrEdit";

    }

    @PostMapping("products")
    public String save(@ModelAttribute("product") Product product) {
        productService.create(product);
        return "redirect:/admin/product/AddOrEdit";

    }

}
