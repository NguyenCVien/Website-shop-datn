package com.websiteshop.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
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

    @Autowired
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
                    + "http://localhost:8080/forgotPassword/forgot_password/reset_password?token=" + token;

            sendEmail(email, resetPasswordLink);

            model.addAttribute("message", "Vui lòng check Email!");
        } catch (Exception e) {
            model.addAttribute("message", "Email is not valid!");
        }

        return "/user/forgotPassword";
    }

    private void sendEmail(String email, String resetPasswordLink)
            throws UnsupportedEncodingException, MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("ShopNow@gmail.com", "ShopNow support");
        helper.setTo(email);

        String subject = "Đây là link reset mật khẩu của bạn!";

        String content = "<p>Hello,</p>"
                + "Bạn có yêu cầu về thay đổi mật khẩu của bạn."
                + "Nhấn vào link để thay đổi mật khẩu của bạn:"
                + "<a href=\"" + resetPasswordLink + "\">Đổi mật khẩu</a>";

        helper.setSubject(subject);
        helper.setText(content, true);

        javaMailSender.send(message);

    }
}
