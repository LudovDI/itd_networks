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

    @GetMapping("/ViC_acts")
    public String ViCActs() {
        return "ViC_acts/ViC_acts";
    }

    @GetMapping("/ViC_add")
    public String ViCActsAdd() {
        return "ViC_acts/ViC_add";
    }

    @GetMapping("/ViC_edit")
    public String ViCActsEdit() {
        return "ViC_acts/ViC_edit";
    }
}
