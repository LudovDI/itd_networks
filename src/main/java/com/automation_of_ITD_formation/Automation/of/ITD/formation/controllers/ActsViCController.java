package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ActsViCData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ActsViCRepository;
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
public class ActsViCController {

    @Autowired
    private ActsViCRepository actsViCRepository;

    @GetMapping("/acts-vic-table")
    public String actsVicTable(Model model) {
        Iterable<ActsViCData> actsViCList = actsViCRepository.findAll();
        model.addAttribute("actsViCList", actsViCList);
        return "actsVicTable";
    }

    @GetMapping("/acts-vic-add")
    public String actsVicAdd(Model model) {
        return "actsVicAdd";
    }

    @PostMapping("/acts-vic-add")
    public String postActsVicAdd(@RequestParam("numberActViC") String numberActViC,
                               @RequestParam("dataActViC") String dataActViC,
                               @RequestParam("nameLaboratory") String nameLaboratory) {
        ActsViCData actsViCData = new ActsViCData(numberActViC, dataActViC, nameLaboratory);
        actsViCRepository.save(actsViCData);
        return "redirect:/acts-vic-table";
    }

    @GetMapping("/acts-vic-table/{id}/acts-vic-edit")
    public String actsVicEdit(@PathVariable(value = "id") long id, Model model) {
        if (!actsViCRepository.existsById(id)) {
            return "redirect:/acts-vic-table";
        }

        Optional<ActsViCData> actsViCDataOptional = actsViCRepository.findById(id);
        ArrayList<ActsViCData> res = new ArrayList<>();
        actsViCDataOptional.ifPresent(res::add);
        model.addAttribute("actsVicDataOptional", res);
        return "actsVicEdit";
    }

    @PostMapping("/acts-vic-table/{id}/acts-vic-edit")
    public String postActsVicUpdate(@PathVariable(value = "id") long id,
                                    @RequestParam("numberActViC") String numberActViC,
                                    @RequestParam("dataActViC") String dataActViC,
                                    @RequestParam("nameLaboratory") String nameLaboratory) {
        ActsViCData actsViCData = actsViCRepository.findById(id).orElseThrow();
        actsViCData.setNumberActViC(numberActViC);
        actsViCData.setDataActViC(dataActViC);
        actsViCData.setNameLaboratory(nameLaboratory);
        actsViCRepository.save(actsViCData);
        return "redirect:/acts-vic-table";
    }

    @PostMapping("/acts-vic-table/{id}/acts-vic-remove")
    public String postActsVicDelete(@PathVariable(value = "id") long id, Model model) {
        ActsViCData actsViCData = actsViCRepository.findById(id).orElseThrow();
        actsViCRepository.delete(actsViCData);
        return "redirect:/acts-vic-table";
    }

    @PostMapping("/acts-vic-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        ActsViCData act = actsViCRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        act.setStatus(status);
        actsViCRepository.save(act);
        return "redirect:/acts-vic-table";
    }
}
