package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/new_abd")
    public String index() {
        return "new_abd";
    }

    @GetMapping("/materials_used")
    public String materials() {
        return "materials_used";
    }
}
