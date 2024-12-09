package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ForgotPasswordController {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgot-password")
    public String postForgotPassword(@RequestParam("email") String email, Model model) {
        Optional<UserData> user = userRepository.findByUsername(email);

        if (user.isEmpty()) {
            model.addAttribute("error", "Указанный email не найден.");
            return "forgotPassword";
        }

        try {
            UserData userData = user.get();
            sendPasswordEmail(userData.getUsername(), userData.getPassword());
            model.addAttribute("success", "Пароль отправлен на указанный email.");
        } catch (Exception e) {
            model.addAttribute("error", "Произошла ошибка при отправке email.");
        }

        return "forgotPassword";
    }

    private void sendPasswordEmail(String recipientEmail, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Ваш пароль");
        message.setText("Ваш пароль: " + password);

        mailSender.send(message);
    }
}
