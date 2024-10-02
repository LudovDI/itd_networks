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

    @GetMapping("/compaction_protocols")
    public String compactionProtocols() {
        return "compaction_protocols/compaction_protocols";
    }

    @GetMapping("/compaction_add")
    public String compactionProtocolsAdd() {
        return "compaction_protocols/compaction_add";
    }

    @GetMapping("/compaction_edit")
    public String compactionProtocolsEdit() {
        return "compaction_protocols/compaction_edit";
    }
}
