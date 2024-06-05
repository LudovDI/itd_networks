package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.SealingProtocolsData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.SealingProtocolsRepository;
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
public class SealingProtocolsController {

    @Autowired
    private SealingProtocolsRepository sealingProtocolsRepository;

    @GetMapping("/sealing-protocols-table")
    public String sealingProtocolsTable(Model model) {
        Iterable<SealingProtocolsData> sealingProtocolsList = sealingProtocolsRepository.findAll();
        model.addAttribute("sealingProtocolsList", sealingProtocolsList);
        return "sealingProtocolsTable";
    }

    @GetMapping("/sealing-protocols-add")
    public String sealingProtocolsAdd(Model model) {
        return "sealingProtocolsAdd";
    }

    @PostMapping("/sealing-protocols-add")
    public String postSealingProtocolsAdd(@RequestParam("numberSealingProtocols") String numberSealingProtocols,
                               @RequestParam("dataSealingProtocols") String dataSealingProtocols,
                               @RequestParam("nameLaboratory") String nameLaboratory) {
        SealingProtocolsData sealingProtocolsData = new SealingProtocolsData(numberSealingProtocols, dataSealingProtocols, nameLaboratory);
        sealingProtocolsRepository.save(sealingProtocolsData);

        return "redirect:/sealing-protocols-table";
    }

    @GetMapping("/sealing-protocols-table/{id}/sealing-protocols-edit")
    public String sealingProtocolsEdit(@PathVariable(value = "id") long id, Model model) {
        if (!sealingProtocolsRepository.existsById(id)) {
            return "redirect:/sealing-protocols-table";
        }

        Optional<SealingProtocolsData> sealingProtocolsDataOptional = sealingProtocolsRepository.findById(id);
        ArrayList<SealingProtocolsData> res = new ArrayList<>();
        sealingProtocolsDataOptional.ifPresent(res::add);
        model.addAttribute("sealingProtocolsDataOptional", res);
        return "sealingProtocolsEdit";
    }

    @PostMapping("/sealing-protocols-table/{id}/sealing-protocols-edit")
    public String postSealingProtocolsUpdate(@PathVariable(value = "id") long id,
                                             @RequestParam("numberSealingProtocols") String numberSealingProtocols,
                                             @RequestParam("dataSealingProtocols") String dataSealingProtocols,
                                             @RequestParam("nameLaboratory") String nameLaboratory) {
        SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(id).orElseThrow();
        sealingProtocolsData.setNumberSealingProtocols(numberSealingProtocols);
        sealingProtocolsData.setDataSealingProtocols(dataSealingProtocols);
        sealingProtocolsData.setNameLaboratory(nameLaboratory);
        sealingProtocolsRepository.save(sealingProtocolsData);
        return "redirect:/sealing-protocols-table";
    }

    @PostMapping("/sealing-protocols-table/{id}/sealing-protocols-remove")
    public String postSealingProtocolsDelete(@PathVariable(value = "id") long id, Model model) {
        SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(id).orElseThrow();
        sealingProtocolsRepository.delete(sealingProtocolsData);
        return "redirect:/sealing-protocols-table";
    }
}
