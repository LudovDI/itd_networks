package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.PasswordResetTokenData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.PasswordResetTokenRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class ResetPasswordController {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @GetMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, Model model) {
        Optional<PasswordResetTokenData> passwordResetToken = passwordResetTokenRepository.findByToken(token);

        if (passwordResetToken.isPresent()) {
            PasswordResetTokenData passwordResetTokenData = passwordResetToken.get();
            if (passwordResetTokenData.getExpiryDate().isBefore(LocalDateTime.now())) {
                model.addAttribute("error", "Недействительный или истекший токен. Пожалуйста, запросите новый токен.");
                return "forgotPassword";
            }
        }

        model.addAttribute("token", token);
        return "resetPassword";
    }

    @PostMapping("/reset-password")
    public String postResetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword, Model model) {
        Optional<PasswordResetTokenData> passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken.isPresent()) {
            PasswordResetTokenData passwordResetTokenData = passwordResetToken.get();
            UserData user = passwordResetTokenData.getUser();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            model.addAttribute("success", "Пароль успешно обновлен.");
        } else {
            model.addAttribute("error", "Неверный токен.");
        }
        return "resetPassword";
    }
}
