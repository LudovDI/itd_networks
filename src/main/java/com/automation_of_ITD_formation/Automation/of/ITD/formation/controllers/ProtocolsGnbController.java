package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ProjectDocumentationData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ProtocolsGnbData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ProtocolsGnbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class ProtocolsGnbController {

    @Autowired
    private ProtocolsGnbRepository protocolsGnbRepository;

    @GetMapping("/protocols-gnb-table")
    public String protocolsGnbTable(Model model) {
        Iterable<ProtocolsGnbData> protocolsGnbList = protocolsGnbRepository.findAll();
        model.addAttribute("protocolsGnbList", protocolsGnbList);
        return "protocolsGnbTable";
    }

    @GetMapping("/protocols-gnb-add")
    public String protocolsGnbAdd(Model model) {
        return "protocolsGnbAdd";
    }

    @PostMapping("/protocols-gnb-add")
    public String postProtocolsGnbAdd(@RequestParam("numberProtocolGnb") String numberProtocolGnb,
                               @RequestParam("dataProtocolGnb") String dataProtocolGnb) {
        ProtocolsGnbData protocolsGnbData = new ProtocolsGnbData(numberProtocolGnb, dataProtocolGnb);
        protocolsGnbRepository.save(protocolsGnbData);

        return "redirect:/protocols-gnb-table";
    }

    @GetMapping("/protocols-gnb-table/{id}/protocols-gnb-edit")
    public String protocolsGnbEdit(@PathVariable(value = "id") long id, Model model) {
        if (!protocolsGnbRepository.existsById(id)) {
            return "redirect:/protocols-gnb-table";
        }
        Optional<ProtocolsGnbData> protocolsGnbDataOptional = protocolsGnbRepository.findById(id);
        ArrayList<ProtocolsGnbData> res = new ArrayList<>();
        protocolsGnbDataOptional.ifPresent(res::add);
        model.addAttribute("protocolsGnbDataOptional", res);
        return "protocolsGnbEdit";
    }

    @PostMapping("/protocols-gnb-table/{id}/protocols-gnb-edit")
    public String postProtocolsGnbUpdate(@PathVariable(value = "id") long id,
                                             @RequestParam("numberProtocolGnb") String numberProtocolGnb,
                                             @RequestParam("dataProtocolGnb") String dataProtocolGnb) {
        ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(id).orElseThrow();
        protocolsGnbData.setNumberProtocolGnb(numberProtocolGnb);
        protocolsGnbData.setDataProtocolGnb(dataProtocolGnb);
        protocolsGnbRepository.save(protocolsGnbData);
        return "redirect:/protocols-gnb-table";
    }

    @PostMapping("/protocols-gnb-table/{id}/protocols-gnb-remove")
    public String postProtocolsGnbDelete(@PathVariable(value = "id") long id, Model model) {
        ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(id).orElseThrow();
        protocolsGnbRepository.delete(protocolsGnbData);
        return "redirect:/protocols-gnb-table";
    }

    @PostMapping("/protocols-gnb-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        ProtocolsGnbData protocolGnb = protocolsGnbRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        protocolGnb.setStatus(status);
        protocolsGnbRepository.save(protocolGnb);
        return "redirect:/protocols-gnb-table";
    }
}
