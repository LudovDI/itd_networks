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

    @GetMapping("/aocp_table")
    public String aocpTable() {
        return "AOCP/aocp_table";
    }

    @GetMapping("/aocp_add")
    public String aocpAdd() {
        return "AOCP/aocp_add";
    }

    @GetMapping("/aocp_edit")
    public String aocpEdit() {
        return "AOCP/aocp_edit";
    }
}
