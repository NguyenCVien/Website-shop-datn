package com.websiteshop.controller;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.websiteshop.entity.Order;
import com.websiteshop.service.OrderDetailService;
import com.websiteshop.service.OrderService;

@Controller
@RequestMapping("orderHistory")
public class OrderHistoryController {
    @Autowired
    OrderService orderService;

    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page) {
        String username = request.getRemoteUser();
        Pageable pageable = PageRequest.of(page.orElse(0), 11, Sort.by("name"));
        model.addAttribute("orders", orderService.findByUsername(username, pageable));
        return "orderHistory/list";
    }

    @GetMapping("/confirmation")
    public String listConfirmation(Model model, HttpServletRequest request,
            @RequestParam("page") Optional<Integer> page) {
        String status = "Đang chờ xác nhận";
        Pageable pageable = PageRequest.of(page.orElse(0), 10);
        Page<Order> pageProduct = null;
        String username = request.getRemoteUser();

        pageProduct = orderService.findByUsername(username, pageable);

        model.addAttribute("orders", orderDetailService.findByStatus(status));
        return "orderHistory/listConfirmation";
    }

    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "order/detail";
    }

    // @GetMapping("/status")
    // public String listConfirmation(Model model,
    // HttpServletRequest request) {
    // String username = request.getRemoteUser();
    // model.addAttribute("orders", orderDetailService.findByStatus(username));
    // return "orderHistory/listConfirmation";
    // }

    @GetMapping("/transported")
    public String listTransported(Model model) {
        String status = "Đang vận chuyển";
        model.addAttribute("orders", orderDetailService.findByStatus(status));
        return "orderHistory/listConfirmation";
    }

    @GetMapping("/delivery")
    public String listDelivery(Model model) {
        String status = "Đang giao hàng";
        model.addAttribute("orders", orderDetailService.findByStatus(status));
        return "orderHistory/listConfirmation";
    }

    @GetMapping("/evaluate")
    public String listEvaluate(Model model) {
        String status = "Đang chờ đánh giá";
        model.addAttribute("orders", orderDetailService.findByStatus(status));
        return "orderHistory/listConfirmation";
    }

    @GetMapping("/view/page")
    public String viewPage(Model model, HttpServletRequest request,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("name"));
        Page<Order> pageProduct = null;
        String username = request.getRemoteUser();
        if (StringUtils.hasText(name)) {
            pageProduct = orderService.findByUsername(username, pageable);
        } else {
            pageProduct = orderService.findByUsername(username, pageable);
        }
        model.addAttribute("orders", pageProduct);
        return "orderHistory/list";
    }
}
