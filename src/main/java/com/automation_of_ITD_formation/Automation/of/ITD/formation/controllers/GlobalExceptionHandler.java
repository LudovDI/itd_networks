package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUserNotFound() {
        return "redirect:/login"; // Перенаправление на страницу входа
    }
}
