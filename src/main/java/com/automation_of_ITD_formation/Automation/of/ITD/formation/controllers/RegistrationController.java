package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.Role;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Optional;

@Controller
public class RegistrationController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistration(UserData user, @RequestParam("role") String role, Model model) {
        Optional<UserData> userData = userRepository.findByUsername(user.getUsername());

        if (userData.isPresent()) {
            model.addAttribute("message", "Такой пользователь уже существует");
            return "registration";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(Role.valueOf(role)));
        userRepository.save(user);
        return "redirect:/login";
    }
}
