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

import com.websiteshop.dao.OrderDAO;
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

    @Autowired
    OrderDAO odao;

    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page) {
        String username = request.getRemoteUser();
        Pageable pageable = PageRequest.of(page.orElse(0), 11, Sort.by("name"));
        model.addAttribute("orders", orderService.findByUsername(username, pageable));
        return "orderHistory/list";
    }

    @GetMapping("/detail/{orderId}")
    public String detail(@PathVariable("orderId") Long orderId, Model model) {
        model.addAttribute("order", orderService.findById(orderId));
        return "order/detail";
    }

    @GetMapping("/confirmation")
    public String listConfirmation(Model model, HttpServletRequest request,
            @RequestParam("page") Optional<Integer> page) {
        String status = "Đang chờ xác nhận";
        String username = request.getRemoteUser();
        if (!status.isEmpty()) {
            model.addAttribute("orders", odao.findByStatus(status, username));
        } else {
            model.addAttribute("message", "Chua co don hang");
        }

        return "orderHistory/listConfirmation";
    }

    @GetMapping("/transported")
    public String listTransported(Model model,
            HttpServletRequest request) {
        String username = request.getRemoteUser();
        String status = "Đang vận chuyển";
        model.addAttribute("orders", odao.findByStatus(status, username));
        if (status.equals("Đang vận chuyển")) {
            model.addAttribute("orders", odao.findByStatus(status, username));

        }
        model.addAttribute("message", "Chua co don hang");

        return "orderHistory/listConfirmation";
    }

    @GetMapping("/delivery")
    public String listDelivery(Model model, HttpServletRequest request) {
        String status = "Đang giao hàng";
        String username = request.getRemoteUser();
        model.addAttribute("orders", odao.findByStatus(status, username));
        return "orderHistory/listConfirmation";
    }

    @GetMapping("/delivered")
    public String listEvaluate(Model model, HttpServletRequest request) {
        String status = "Đã giao hàng";
        String username = request.getRemoteUser();
        model.addAttribute("orders", odao.findByStatus(status, username));
        return "orderHistory/listConfirmation";
    }

    // @GetMapping("/delivered")
    // public String listEvaluate(Model model, HttpServletRequest request) {
    // String username = request.getRemoteUser();

    // Pageable pageable = null;
    // model.addAttribute("orders", orderService.findByUsername(username,
    // pageable));
    // return "orderHistory/list";
    // }

    @GetMapping("/view/page")
    public String viewPage(Model model, HttpServletRequest request,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam("page") Optional<Integer> page) {

        Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("name"));
        Page<Order> pageProduct = null;
        String username = request.getRemoteUser();

        pageProduct = orderService.findByUsername(username, pageable);
        model.addAttribute("orders", pageProduct);
        return "orderHistory/list";
    }
}
