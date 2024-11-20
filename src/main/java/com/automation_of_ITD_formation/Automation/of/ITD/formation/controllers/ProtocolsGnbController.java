package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ItdData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ProjectDocumentationData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ProtocolsGnbData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ItdRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ProtocolsGnbRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class ProtocolsGnbController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;
    @Autowired
    private ProtocolsGnbRepository protocolsGnbRepository;

    @GetMapping("/protocols-gnb-table/{id}")
    public String protocolsGnbTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
        String username = principal.getName();
        UserData currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("userData", currentUser);
        List<ItdData> itdDataList = currentUser.getItdData();
        if (!itdDataList.isEmpty()) {
            model.addAttribute("itdList", itdDataList);
        } else {
            model.addAttribute("itdList", List.of());
        }
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        model.addAttribute("itdData", itdData);
        Set<ProtocolsGnbData> protocolsGnbList = itdData.getProtocolsGnbData();
        model.addAttribute("protocolsGnbList", protocolsGnbList);
        return "protocolsGnbTable";
    }

    @GetMapping("/protocols-gnb-add/{id}")
    public String protocolsGnbAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        String username = principal.getName();
        UserData currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("userData", currentUser);
        List<ItdData> itdDataList = currentUser.getItdData();
        if (!itdDataList.isEmpty()) {
            model.addAttribute("itdList", itdDataList);
        } else {
            model.addAttribute("itdList", List.of());
        }
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        model.addAttribute("itdData", itdData);
        return "protocolsGnbAdd";
    }

    @PostMapping("/protocols-gnb-add/{id}")
    public String postProtocolsGnbAdd(@PathVariable(value = "id") long id,
                                      @RequestParam("numberProtocolGnb") String numberProtocolGnb,
                                      @RequestParam("dataProtocolGnb") String dataProtocolGnb) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        ProtocolsGnbData protocolsGnbData = new ProtocolsGnbData(numberProtocolGnb, dataProtocolGnb);
        protocolsGnbData.setStatus("Требует создания");
        protocolsGnbData.setItdToProtocolsGnbData(itdData);
        protocolsGnbRepository.save(protocolsGnbData);
        return "redirect:/protocols-gnb-table/" + id;
    }

    @GetMapping("/protocols-gnb-table/{itdId}/protocols-gnb-edit/{protocolId}")
    public String protocolsGnbEdit(@PathVariable(value = "itdId") long itdId,
                                   @PathVariable(value = "protocolId") long protocolId,
                                   Model model, Principal principal) {
        String username = principal.getName();
        UserData currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("userData", currentUser);
        List<ItdData> itdDataList = currentUser.getItdData();
        if (!itdDataList.isEmpty()) {
            model.addAttribute("itdList", itdDataList);
        } else {
            model.addAttribute("itdList", List.of());
        }
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        model.addAttribute("itdData", itdData);
        if (!protocolsGnbRepository.existsById(protocolId)) {
            return "redirect:/protocols-gnb-table/" + itdId;
        }
        Optional<ProtocolsGnbData> protocolsGnbDataOptional = protocolsGnbRepository.findById(protocolId);
        ArrayList<ProtocolsGnbData> res = new ArrayList<>();
        protocolsGnbDataOptional.ifPresent(res::add);
        model.addAttribute("protocolsGnbDataOptional", res);
        return "protocolsGnbEdit";
    }

    @PostMapping("/protocols-gnb-table/{itdId}/protocols-gnb-edit/{protocolId}")
    public String postProtocolsGnbUpdate(@PathVariable(value = "itdId") long itdId,
                                         @PathVariable(value = "protocolId") long protocolId,
                                         @RequestParam("numberProtocolGnb") String numberProtocolGnb,
                                         @RequestParam("dataProtocolGnb") String dataProtocolGnb) {
        ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(protocolId).orElseThrow();
        protocolsGnbData.setNumberProtocolGnb(numberProtocolGnb);
        protocolsGnbData.setDataProtocolGnb(dataProtocolGnb);
        protocolsGnbRepository.save(protocolsGnbData);
        return "redirect:/protocols-gnb-table/" + itdId;
    }

    @PostMapping("/protocols-gnb-table/{itdId}/protocols-gnb-remove/{protocolId}")
    public String postProtocolsGnbDelete(@PathVariable(value = "itdId") long itdId, @PathVariable(value = "protocolId") long protocolId) {
        ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(protocolId).orElseThrow();
        protocolsGnbRepository.delete(protocolsGnbData);
        return "redirect:/protocols-gnb-table/" + itdId;
    }

    @PostMapping("/protocols-gnb-table/{itdId}/update-status/{protocolId}")
    public String updateStatus(@PathVariable(value = "itdId") long itdId, @PathVariable(value = "protocolId") long protocolId, @RequestParam("status") String status) {
        ProtocolsGnbData protocolGnb = protocolsGnbRepository.findById(protocolId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + protocolId));
        protocolGnb.setStatus(status);
        protocolsGnbRepository.save(protocolGnb);
        return "redirect:/protocols-gnb-table/" + itdId;
    }
}
