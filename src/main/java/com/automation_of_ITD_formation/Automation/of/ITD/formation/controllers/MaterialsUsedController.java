package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.stream.Collectors;

import java.util.*;

@Controller
public class MaterialsUsedController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;
    @Autowired
    private MaterialsUsedRepository materialUsedRepository;
    @Autowired
    private AccompanyingDocumentRepository accompanyingDocumentRepository;

    @GetMapping("/materials-used-table/{id}")
    public String materialsUsedTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
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
        Set<MaterialsUsedData> materialsUsedList = itdData.getMaterialsUsedData();
        model.addAttribute("materialsUsedList", materialsUsedList);
        Map<Long, List<AccompanyingDocumentData>> accompanyingDocumentsMap = new HashMap<>();
        for (MaterialsUsedData material : materialsUsedList) {
            List<AccompanyingDocumentData> accompanyingDocumentList = accompanyingDocumentRepository.findByMaterialsUsedData(material);
            accompanyingDocumentsMap.put(material.getId(), accompanyingDocumentList);
        }
        model.addAttribute("accompanyingDocumentsMap", accompanyingDocumentsMap);
        return "materialsUsedTable";
    }

    @GetMapping("/materials-used-add/{id}")
    public String materialsUsedAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
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
        return "materialsUsedAdd";
    }

    @PostMapping("/materials-used-add/{id}")
    public String postMaterialsUsedAdd(@PathVariable(value = "id") long id,
                                       @RequestParam("material") String nameMaterial,
                                       @RequestParam Map<String, String> formData) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        List<AccompanyingDocumentData> accompanyingDocumentList = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("name")) {
                int index = Integer.parseInt(key.substring(4));
                String numberKey = "number" + index;
                String dataKey = "date" + index;
                String orgKey = "org" + index;
                String number = formData.getOrDefault(numberKey, "");
                String data = formData.getOrDefault(dataKey, "");
                String org = formData.getOrDefault(orgKey, "");
                AccompanyingDocumentData accompanyingDocument = new AccompanyingDocumentData(value, number, data, org);
                accompanyingDocumentList.add(accompanyingDocument);
            }
        });
        MaterialsUsedData materialUsedData = new MaterialsUsedData(nameMaterial);
        materialUsedData.setStatus("Требует создания");
        materialUsedData.setItdToMaterialsUsedData(itdData);
        materialUsedRepository.save(materialUsedData);
        accompanyingDocumentList.forEach(accompanyingDocument -> accompanyingDocument.setMaterialsUsedData(materialUsedData));
        accompanyingDocumentRepository.saveAll(accompanyingDocumentList);
        return "redirect:/materials-used-table/" + id;
    }

    @GetMapping("/materials-used-table/{itdId}/materials-used-edit/{materialId}")
    public String materialsUsedEdit(@PathVariable(value = "itdId") long itdId,
                                    @PathVariable(value = "materialId") long materialId,
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
        if (!materialUsedRepository.existsById(materialId)) {
            return "redirect:/materials-used-table";
        }
        Optional<MaterialsUsedData> materialsUsedDataOptional = materialUsedRepository.findById(materialId);
        MaterialsUsedData materialsUsedData = materialsUsedDataOptional.get();
        model.addAttribute("materialsUsedData", materialsUsedData);
        return "materialsUsedEdit";
    }

    @PostMapping("/materials-used-table/{itdId}/materials-used-edit/{materialId}")
    @Transactional
    public String postMaterialsUsedUpdate(@PathVariable(value = "itdId") long itdId,
                                          @PathVariable(value = "materialId") long materialId,
                                          @RequestParam("material") String nameMaterial,
                                          @RequestParam Map<String, String> formData) {
        MaterialsUsedData materialsUsedData = materialUsedRepository.findById(materialId).orElseThrow();

        Set<Long> accDocIdsFromForm = new HashSet<>();
        Map<Long, AccompanyingDocumentData> accompanyingDocumentsMap = new HashMap<>();

        formData.forEach((key, value) -> {
            Long accDocId = null;
            if (key.startsWith("name") || key.startsWith("number") || key.startsWith("date") || key.startsWith("org")) {
                String accDocIdStr = key.replaceAll("[^0-9]", "");
                if (!accDocIdStr.isEmpty()) {
                    accDocId = Long.parseLong(accDocIdStr);
                }
            }

            if (accDocId == null) {
                return;
            }

            AccompanyingDocumentData accompanyingDocument = accompanyingDocumentsMap.getOrDefault(accDocId,
                    accompanyingDocumentRepository.findById(accDocId).orElse(new AccompanyingDocumentData()));

            accDocIdsFromForm.add(accDocId);

            if (key.startsWith("name")) {
                accompanyingDocument.setName(value);
            } else if (key.startsWith("number")) {
                accompanyingDocument.setNumber(value);
            } else if (key.startsWith("date")) {
                accompanyingDocument.setDate(value);
            } else if (key.startsWith("org")) {
                accompanyingDocument.setOrg(value);
            }

            accompanyingDocument.setMaterialsUsedData(materialsUsedData);
            accompanyingDocumentsMap.put(accDocId, accompanyingDocument);
            materialsUsedData.getAccompanyingDocuments().add(accompanyingDocument);
        });

        List<AccompanyingDocumentData> accDocsToRemove = materialsUsedData.getAccompanyingDocuments().stream()
                .filter(accDoc -> accDoc.getId() != null && !accDocIdsFromForm.contains(accDoc.getId()))
                .toList();

        accDocsToRemove.forEach(accDoc -> {
            materialsUsedData.getAccompanyingDocuments().remove(accDoc);
            accompanyingDocumentRepository.delete(accDoc);
        });

        materialsUsedData.setNameMaterial(nameMaterial);
        materialUsedRepository.save(materialsUsedData);
        return "redirect:/materials-used-table/" + itdId;
    }

    @PostMapping("/materials-used-table/{itdId}/materials-used-remove/{materialId}")
    public String postMaterialsUsedDelete(@PathVariable(value = "itdId") long itdId, @PathVariable(value = "materialId") long materialId, Model model) {
        MaterialsUsedData materialsUsedData = materialUsedRepository.findById(materialId).orElseThrow();
        List<AccompanyingDocumentData> accompanyingDocumentDataList = accompanyingDocumentRepository.findByMaterialsUsedData(materialsUsedData);
        accompanyingDocumentRepository.deleteAll(accompanyingDocumentDataList);
        materialUsedRepository.delete(materialsUsedData);
        return "redirect:/materials-used-table/" + itdId;
    }

    @PostMapping("/materials-used-table/{itdId}/update-status/{materialId}")
    public String updateStatus(@PathVariable("itdId") Long itdId, @PathVariable("materialId") Long materialId, @RequestParam("status") String status) {
        MaterialsUsedData material = materialUsedRepository.findById(materialId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + materialId));
        material.setStatus(status);
        materialUsedRepository.save(material);
        return "redirect:/materials-used-table/" + itdId;
    }
}
