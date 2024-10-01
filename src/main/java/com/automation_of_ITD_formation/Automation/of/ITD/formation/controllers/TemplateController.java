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

    @GetMapping("/materials_add")
    public String materialsAdd() {
        return "materials_add";
    }

    @GetMapping("/materials_edit")
    public String materialsEdit() {
        return "materials_edit";
    }

    @GetMapping("/GNB_protocols")
    public String GNBProtocols() {
        return "GNB_protocols/GNB_protocols";
    }

    @GetMapping("/GNB_add")
    public String GNBProtocolsAdd() {
        return "GNB_protocols/GNB_add";
    }

    @GetMapping("/GNB_edit")
    public String GNBProtocolsEdit() {
        return "GNB_protocols/GNB_edit";
    }
}
