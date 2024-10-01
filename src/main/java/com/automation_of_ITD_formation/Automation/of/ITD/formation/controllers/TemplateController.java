package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TemplateController {

    @GetMapping("/new_abd")
    public String index() {
        return "new_abd/new_abd";
    }

    @GetMapping("/materials_used")
    public String materials() {
        return "materials/materials_used";
    }

    @GetMapping("/materials_add")
    public String materialsAdd() {
        return "materials/materials_add";
    }

    @GetMapping("/asbuilt_drawings")
    public String asbuiltDrawings() {
        return "asbuilt_drawing/asbuilt_drawings";
    }

    @GetMapping("/asbuilt_drawing_edit")
    public String asbuiltEdit() {
        return "asbuilt_drawing/asbuilt_drawing_edit";
    }

    @GetMapping("/asbuilt_drawing_add")
    public String asbuiltAdd() {
        return "asbuilt_drawing/asbuilt_drawing_add";
    }
}
