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
