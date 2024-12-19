package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ControllersUtils.modelAddUserAndItdData;

@Controller
public class NameWorksController {

    @Autowired
    private NameWorksRepository nameWorksRepository;
    @Autowired
    private NormativeRepository normativeRepository;
    @Autowired
    private NextNameWorksRepository nextNameWorksRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;

    @GetMapping("/name-works-table/{id}")
    public String nameWorksTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        Set<NameWorksData> nameWorksList = itdData.getNameWorksData();
        model.addAttribute("nameWorksList", nameWorksList);
        return "nameWorksTable";
    }

    @GetMapping("/name-works-add/{id}")
    public String nameWorksAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        return "nameWorksAdd";
    }

    @PostMapping("/name-works-add/{id}")
    public String postNameWorksAdd(@PathVariable(value = "id") long id,
                                   @RequestParam(value = "nameWorks") String nameWorks,
                                   @RequestParam Map<String, String> formData) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        NameWorksData nameWorksData = new NameWorksData(nameWorks);
        Set<NormativeData> normativeSet = new HashSet<>();
        List<NextNameWorksData> nextNameWorksList = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("normative")) {
                NormativeData normativeData = new NormativeData(value);
                normativeData.setNameWorksToNormative(nameWorksData);
                normativeSet.add(normativeData);
            }
            if (key.startsWith("nextNameWorks")) {
                NextNameWorksData nextNameWorksData = new NextNameWorksData(value);
                nextNameWorksData.setNameWorksNextNameWorks(nameWorksData);
                nextNameWorksList.add(nextNameWorksData);
            }
        });
        nameWorksData.setStatus("Требует создания");
        nameWorksData.setItdToNameWorksData(itdData);
        nameWorksRepository.save(nameWorksData);
        normativeRepository.saveAll(normativeSet);
        nextNameWorksRepository.saveAll(nextNameWorksList);
        return "redirect:/name-works-table/" + id;
    }

    @GetMapping("/name-works-table/{itdId}/name-works-edit/{nameWorksId}")
    public String nameWorksEdit(@PathVariable(value = "itdId") long itdId,
                              @PathVariable(value = "nameWorksId") long nameWorksId,
                              Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        if (!nameWorksRepository.existsById(nameWorksId)) {
            return "redirect:/name-works-table/" + itdId;
        }

        Optional<NameWorksData> nameWorksDataOptional = nameWorksRepository.findById(nameWorksId);
        ArrayList<NameWorksData> res = new ArrayList<>();
        nameWorksDataOptional.ifPresent(res::add);
        model.addAttribute("nameWorksDataOptional", res);
        return "nameWorksEdit";
    }

    @PostMapping("/name-works-table/{itdId}/name-works-edit/{nameWorksId}")
    public String postNameWorksUpdate(@PathVariable(value = "itdId") long itdId,
                                      @PathVariable(value = "nameWorksId") long nameWorksId,
                                      @RequestParam("nameWorks") String nameWorks,
                                      @RequestParam Map<String, String> formData) {
        NameWorksData nameWorksData = nameWorksRepository.findById(nameWorksId).orElseThrow();

        Set<Long> normativeIdsFromForm = new HashSet<>();
        Set<Long> nextNameWorksIdsFromForm = new HashSet<>();

        formData.forEach((key, value) -> {
            if (key.startsWith("normative")) {
                String normativeIdStr = key.substring("normative".length());

                NormativeData normativeData;
                if (!normativeIdStr.isEmpty()) {
                    Long normativeId = Long.parseLong(normativeIdStr);
                    normativeIdsFromForm.add(normativeId);

                    normativeData = normativeRepository.findById(normativeId).orElse(null);
                    if (normativeData == null) {
                        normativeData = new NormativeData();
                    }
                } else {
                    normativeData = new NormativeData();
                }

                normativeData.setName(value);
                normativeData.setNameWorksToNormative(nameWorksData);
                normativeRepository.save(normativeData);
            }
            if (key.startsWith("nextNameWorks")) {
                String nextNameWorksIdStr = key.substring("nextNameWorks".length());

                NextNameWorksData nextNameWorks;
                if (!nextNameWorksIdStr.isEmpty()) {
                    Long nextNameWorksId = Long.parseLong(nextNameWorksIdStr);
                    nextNameWorksIdsFromForm.add(nextNameWorksId);

                    nextNameWorks = nextNameWorksRepository.findById(nextNameWorksId).orElse(null);
                    if (nextNameWorks == null) {
                        nextNameWorks = new NextNameWorksData();
                    }
                } else {
                    nextNameWorks = new NextNameWorksData();
                }

                nextNameWorks.setName(value);
                nextNameWorks.setNameWorksNextNameWorks(nameWorksData);
                nameWorksData.getNextNameWorksList().add(nextNameWorks);
            }
        });

        List<NormativeData> normativeToRemove = nameWorksData.getNormativeList().stream()
                .filter(normative -> normative.getId() != null && !normativeIdsFromForm.contains(normative.getId()))
                .toList();

        normativeToRemove.forEach(normative -> {
            nameWorksData.getNormativeList().remove(normative);
            normativeRepository.delete(normative);
        });

        List<NextNameWorksData> nextNameWorksToRemove = nameWorksData.getNextNameWorksList().stream()
                .filter(nextNameWorks -> nextNameWorks.getId() != null && !nextNameWorksIdsFromForm.contains(nextNameWorks.getId()))
                .toList();

        nextNameWorksToRemove.forEach(nextNameWorks -> {
            nameWorksData.getNextNameWorksList().remove(nextNameWorks);
            nextNameWorksRepository.delete(nextNameWorks);
        });

        nameWorksData.setName(nameWorks);
        nameWorksRepository.save(nameWorksData);
        return "redirect:/name-works-table/" + itdId;
    }

    @GetMapping("/name-works-add-from-itd/{id}")
    public String nameWorksAddFromItd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        return "nameWorksAddFromItd";
    }

    @PostMapping("/name-works-add-from-itd/{id}")
    public String postNameWorksAddFromItd(@PathVariable(value = "id") long id,
                                   @RequestParam Map<String, String> formData) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        formData.forEach((key, value) -> {
            if (key.startsWith("nameWorks")) {
                Long nameWorksId = Long.parseLong(value);
                Optional<NameWorksData> nameWorksOptional = nameWorksRepository.findById(nameWorksId);
                if (nameWorksOptional.isPresent()) {
                    getNameWorksData(nameWorksOptional, itdData, nameWorksRepository, normativeRepository, nextNameWorksRepository);
                }
            }
        });
        return "redirect:/name-works-table/" + id;
    }

    private static void getNameWorksData(Optional<NameWorksData> nameWorksOptional, ItdData itdData, NameWorksRepository nameWorksRepository, NormativeRepository normativeRepository, NextNameWorksRepository nextNameWorksRepository) {
        NameWorksData oldNameWorksData = nameWorksOptional.get();
        NameWorksData newNameWorksData = new NameWorksData();
        newNameWorksData.setName(oldNameWorksData.getName());
        List<NormativeData> normativeDataList = new ArrayList<>();
        for (NormativeData normativeData : oldNameWorksData.getNormativeList()) {
            NormativeData newNormativeData = new NormativeData();
            newNormativeData.setName(normativeData.getName());
            newNormativeData.setNameWorksToNormative(newNameWorksData);
            normativeDataList.add(newNormativeData);
        }
        newNameWorksData.setNormativeList(normativeDataList);
        List<NextNameWorksData> nextNameWorksDataList = new ArrayList<>();
        for (NextNameWorksData nextNameWorksData : oldNameWorksData.getNextNameWorksList()) {
            NextNameWorksData newNextNameWorksData = new NextNameWorksData();
            newNextNameWorksData.setName(nextNameWorksData.getName());
            newNextNameWorksData.setNameWorksNextNameWorks(newNameWorksData);
            nextNameWorksDataList.add(newNextNameWorksData);
        }
        newNameWorksData.setNextNameWorksList(nextNameWorksDataList);
        newNameWorksData.setStatus("Требует создания");
        newNameWorksData.setItdToNameWorksData(itdData);
        nameWorksRepository.save(newNameWorksData);
        normativeRepository.saveAll(normativeDataList);
        nextNameWorksRepository.saveAll(nextNameWorksDataList);
    }

    @PostMapping("/name-works-table/{itdId}/name-works-remove/{nameWorksId}")
    @Transactional
    public String postActsVicDelete(@PathVariable(value = "itdId") long itdId,
                                    @PathVariable(value = "nameWorksId") long nameWorksId) {
        NameWorksData nameWorksData = nameWorksRepository.findById(nameWorksId).orElseThrow();
        nameWorksRepository.delete(nameWorksData);
        return "redirect:/name-works-table/" + itdId;
    }

    @PostMapping("/name-works-table/{itdId}/update-status/{nameWorksId}")
    public String updateStatus(@PathVariable(value = "itdId") long itdId,
                               @PathVariable(value = "nameWorksId") long nameWorksId,
                               @RequestParam("status") String status) {
        NameWorksData nameWorksData = nameWorksRepository.findById(nameWorksId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + nameWorksId));
        nameWorksData.setStatus(status);
        nameWorksRepository.save(nameWorksData);
        return "redirect:/name-works-table/" + itdId;
    }

}
