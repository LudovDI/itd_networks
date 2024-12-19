package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ActsViCData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ItdData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ActsViCRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ItdRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ControllersUtils.modelAddUserAndItdData;

@Controller
public class ActsViCController {

    @Autowired
    private ActsViCRepository actsViCRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;

    @GetMapping("/acts-vic-table/{id}")
    public String actsVicTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        Set<ActsViCData> actsViCList = itdData.getActsViCData();
        model.addAttribute("actsViCList", actsViCList);
        return "actsVicTable";
    }

    @GetMapping("/acts-vic-add/{id}")
    public String actsVicAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        return "actsVicAdd";
    }

    @PostMapping("/acts-vic-add/{id}")
    public String postActsVicAdd(@PathVariable(value = "id") long id,
                                 @RequestParam("numberActViC") String numberActViC,
                                 @RequestParam("dataActViC") String dataActViC,
                                 @RequestParam("nameLaboratory") String nameLaboratory) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        ActsViCData actsViCData = new ActsViCData(numberActViC, dataActViC, nameLaboratory);
        actsViCData.setStatus("Требует создания");
        actsViCData.setItdToActsViCData(itdData);
        actsViCRepository.save(actsViCData);
        return "redirect:/acts-vic-table/" + id;
    }

    @GetMapping("/acts-vic-table/{itdId}/acts-vic-edit/{actId}")
    public String actsVicEdit(@PathVariable(value = "itdId") long itdId,
                              @PathVariable(value = "actId") long actId,
                              Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        if (!actsViCRepository.existsById(actId)) {
            return "redirect:/acts-vic-table/" + itdId;
        }

        Optional<ActsViCData> actsViCDataOptional = actsViCRepository.findById(actId);
        ArrayList<ActsViCData> res = new ArrayList<>();
        actsViCDataOptional.ifPresent(res::add);
        model.addAttribute("actsVicDataOptional", res);
        return "actsVicEdit";
    }

    @PostMapping("/acts-vic-table/{itdId}/acts-vic-edit/{actId}")
    public String postActsVicUpdate(@PathVariable(value = "itdId") long itdId,
                                    @PathVariable(value = "actId") long actId,
                                    @RequestParam("numberActViC") String numberActViC,
                                    @RequestParam("dataActViC") String dataActViC,
                                    @RequestParam("nameLaboratory") String nameLaboratory) {
        ActsViCData actsViCData = actsViCRepository.findById(actId).orElseThrow();
        actsViCData.setNumberActViC(numberActViC);
        actsViCData.setDataActViC(dataActViC);
        actsViCData.setNameLaboratory(nameLaboratory);
        actsViCRepository.save(actsViCData);
        return "redirect:/acts-vic-table/" + itdId;
    }

    @PostMapping("/acts-vic-table/{itdId}/acts-vic-remove/{actId}")
    public String postActsVicDelete(@PathVariable(value = "itdId") long itdId,
                                    @PathVariable(value = "actId") long actId) {
        ActsViCData actsViCData = actsViCRepository.findById(actId).orElseThrow();
        actsViCRepository.delete(actsViCData);
        return "redirect:/acts-vic-table/" + itdId;
    }

    @PostMapping("/acts-vic-table/{itdId}/update-status/{actId}")
    public String updateStatus(@PathVariable(value = "itdId") long itdId,
                               @PathVariable(value = "actId") long actId,
                               @RequestParam("status") String status) {
        ActsViCData act = actsViCRepository.findById(actId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + actId));
        act.setStatus(status);
        actsViCRepository.save(act);
        return "redirect:/acts-vic-table/" + itdId;
    }
}
