package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/bs_index")
    public String bs_index() {
        return "bs_index";
    }

    @GetMapping("/logs")
    public String logs() {
        return "logs";
    }
}
