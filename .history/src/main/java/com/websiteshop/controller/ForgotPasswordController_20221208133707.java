package com.websiteshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.service.AccountService;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
    @Autowired
    AccountService accountService;

    @GetMapping("")
    public String index(Model model) {
        return "/user/forgotPassword";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request,
            Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(50);

        try {
            if (!email.isEmpty()) {
                accountService.updateResetPasswordToken(token, email);
            }

        } catch (Exception e) {
            model.addAttribute("message", "Tài khoản của bạn chưa có Email!");
        }

        return "/user/forgotPassword";
    }
}
