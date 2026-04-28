package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.PasswordResetTokenData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.PasswordResetTokenRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
public class ForgotPasswordController {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgotPassword";
    }

    @PostMapping("/forgot-password")
    @Transactional
    public String postForgotPassword(@RequestParam("username") String username, Model model) {
        Optional<UserData> user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            model.addAttribute("error", "Указанный email не найден.");
            return "forgotPassword";
        }

        try {
            UserData userData = user.get();
            String token = UUID.randomUUID().toString();
            LocalDateTime expiryDate = LocalDateTime.now().plusHours(1);

            Optional<PasswordResetTokenData> existingTokenOpt = passwordResetTokenRepository.findByUser(userData);

            if (existingTokenOpt.isPresent()) {
                PasswordResetTokenData existingToken = existingTokenOpt.get();
                existingToken.setToken(token);
                existingToken.setExpiryDate(expiryDate);
                passwordResetTokenRepository.save(existingToken);
            } else {
                PasswordResetTokenData passwordResetToken = new PasswordResetTokenData();
                passwordResetToken.setToken(token);
                passwordResetToken.setUser (userData);
                passwordResetToken.setExpiryDate(expiryDate);
                passwordResetTokenRepository.save(passwordResetToken);
            }

            String resetLink = "http://localhost:8080/reset-password?token=" + URLEncoder.encode(token, StandardCharsets.UTF_8);
            sendResetPasswordEmail(userData.getUsername(), resetLink);
            model.addAttribute("success", "Инструкция по сбросу пароля отправлена на указанный email.");
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", "Произошла ошибка при обработке запроса: " + e.getMessage());
        }

        return "forgotPassword";
    }

    private void sendResetPasswordEmail(String recipientEmail, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Сброс пароля");
        message.setText("Перейдите по следующей ссылке для сброса пароля: " + resetLink);

        mailSender.send(message);
    }
}
