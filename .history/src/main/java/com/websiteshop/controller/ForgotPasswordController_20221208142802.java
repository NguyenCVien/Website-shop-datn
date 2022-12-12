package com.websiteshop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.websiteshop.Utility;
import com.websiteshop.entity.Account;
import com.websiteshop.service.AccountService;

import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
    @Autowired
    AccountService accountService;
    private JavaMailSender javaMailSender;

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
            accountService.updateResetPasswordToken(token, email);

            String resetPasswordLink = Utility.getSizeURL(request)
                    + "http://localhost:8080/reset_password?token=" + token;

            System.out.println(resetPasswordLink);

            model.addAttribute("message", "success");
        } catch (Exception e) {
            model.addAttribute("message", "Email is not valid!");
        }

        return "/user/forgotPassword";
    }
}
