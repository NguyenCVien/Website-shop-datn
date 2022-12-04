package com.websiteshop.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.service.OrderService;

@Controller
public class OrderHistoryController {
    @Autowired
    OrderService orderService;

    @RequestMapping("/orderHistory/list")
    public String list(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }
    
    @RequestMapping("/orderHistory/confirmation")
    public String listConfirmation(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }
    
    @RequestMapping("/orderHistory/transported")
    public String listTransported(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }
    
    @RequestMapping("/orderHistory/delivery")
    public String listDelivery(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }

    @RequestMapping("/orderHistory/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "order/detail";
    }
}