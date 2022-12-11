package com.websiteshop.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import groovyjarjarantlr4.v4.parse.BlockSetTransformer.setAlt_return;
import net.bytebuddy.utility.RandomString;

@Controller
@RequestMapping("/forgotPassword")
public class ForgotPasswordController {
    @GetMapping("")
    public String index(Model model) {
        return "/user/forgotPassword";
    }

    @PostMapping("/forgot_password")
    public String processForgotPasswordForm(HttpServletRequest request) {
        String email = request.getParameter("email");
        String token = RandomString.make(50);

        System.out.println("Email: " + email);
        System.out.println("Token: " + token);

        return "/user/forgotPassword";
    }
}
