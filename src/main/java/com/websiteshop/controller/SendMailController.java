package com.websiteshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailController {
    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping("/admin/SendMail")
    public String index() {
        return "/SendMail/index";
    }

    @RequestMapping("/admin/send")
    public String send(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("content") String content) {
        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(content);

            javaMailSender.send(msg);

            return "/SendMail/result";
        } catch (Exception e) {
            return "/404";
        }

    }
}
