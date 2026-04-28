package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ItdData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ProtocolsGnbData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.SealingProtocolsData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ItdRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.SealingProtocolsRepository;
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
public class SealingProtocolsController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;
    @Autowired
    private SealingProtocolsRepository sealingProtocolsRepository;

    @GetMapping("/sealing-protocols-table/{id}")
    public String sealingProtocolsTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        List<SealingProtocolsData> sealingProtocolsList = new ArrayList<>(itdData.getSealingProtocolsData());
        sealingProtocolsList.sort(Comparator.comparing(SealingProtocolsData::getCreatedDate));
        model.addAttribute("sealingProtocolsList", sealingProtocolsList);
        return "sealingProtocolsTable";
    }

    @GetMapping("/sealing-protocols-add/{id}")
    public String sealingProtocolsAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        return "sealingProtocolsAdd";
    }

    @PostMapping("/sealing-protocols-add/{id}")
    public String postSealingProtocolsAdd(@PathVariable(value = "id") long id,
                                          @RequestParam("numberSealingProtocols") String numberSealingProtocols,
                                          @RequestParam("dataSealingProtocols") String dataSealingProtocols,
                                          @RequestParam("nameLaboratory") String nameLaboratory) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        SealingProtocolsData sealingProtocolsData = new SealingProtocolsData(numberSealingProtocols, dataSealingProtocols, nameLaboratory);
        sealingProtocolsData.setStatus("Требует создания");
        sealingProtocolsData.setItdToSealingProtocolsData(itdData);
        sealingProtocolsRepository.save(sealingProtocolsData);
        return "redirect:/sealing-protocols-table/" + id;
    }

    @GetMapping("/sealing-protocols-table/{itdId}/sealing-protocols-edit/{protocolId}")
    public String sealingProtocolsEdit(@PathVariable(value = "itdId") long itdId,
                                       @PathVariable(value = "protocolId") long protocolId,
                                       Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        if (!sealingProtocolsRepository.existsById(protocolId)) {
            return "redirect:/sealing-protocols-table/" + itdId;
        }

        Optional<SealingProtocolsData> sealingProtocolsDataOptional = sealingProtocolsRepository.findById(protocolId);
        ArrayList<SealingProtocolsData> res = new ArrayList<>();
        sealingProtocolsDataOptional.ifPresent(res::add);
        model.addAttribute("sealingProtocolsDataOptional", res);
        return "sealingProtocolsEdit";
    }

    @PostMapping("/sealing-protocols-table/{itdId}/sealing-protocols-edit/{protocolId}")
    public String postSealingProtocolsUpdate(@PathVariable(value = "itdId") long itdId,
                                             @PathVariable(value = "protocolId") long protocolId,
                                             @RequestParam("numberSealingProtocols") String numberSealingProtocols,
                                             @RequestParam("dataSealingProtocols") String dataSealingProtocols,
                                             @RequestParam("nameLaboratory") String nameLaboratory) {
        SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(protocolId).orElseThrow();
        sealingProtocolsData.setNumberSealingProtocols(numberSealingProtocols);
        sealingProtocolsData.setDataSealingProtocols(dataSealingProtocols);
        sealingProtocolsData.setNameLaboratory(nameLaboratory);
        sealingProtocolsRepository.save(sealingProtocolsData);
        return "redirect:/sealing-protocols-table/" + itdId;
    }

    @PostMapping("/sealing-protocols-table/{itdId}/sealing-protocols-remove/{protocolId}")
    public String postSealingProtocolsDelete(@PathVariable(value = "itdId") long itdId, @PathVariable(value = "protocolId") long protocolId) {
        SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(protocolId).orElseThrow();
        sealingProtocolsRepository.delete(sealingProtocolsData);
        return "redirect:/sealing-protocols-table/" + itdId;
    }

    @PostMapping("/sealing-protocols-table/{itdId}/update-status/{protocolId}")
    public String updateStatus(@PathVariable(value = "itdId") long itdId, @PathVariable(value = "protocolId") long protocolId, @RequestParam("status") String status) {
        SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(protocolId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + protocolId));
        sealingProtocolsData.setStatus(status);
        sealingProtocolsRepository.save(sealingProtocolsData);
        return "redirect:/sealing-protocols-table/" + itdId;
    }
}
