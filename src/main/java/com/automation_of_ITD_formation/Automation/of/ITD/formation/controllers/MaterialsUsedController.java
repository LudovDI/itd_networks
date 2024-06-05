package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.stream.Collectors;

import java.util.*;

@Controller
public class MaterialsUsedController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MaterialsUsedRepository materialUsedRepository;

    @Autowired
    private AccompanyingDocumentRepository accompanyingDocumentRepository;

    @GetMapping("/materials-used-table")
    public String materialsUsedTable(Model model) {
        Iterable<MaterialsUsedData> materialsUsedList = materialUsedRepository.findAll();
        model.addAttribute("materialsUsedList", materialsUsedList);

        Map<Long, List<AccompanyingDocumentData>> accompanyingDocumentsMap = new HashMap<>();
        for (MaterialsUsedData material : materialsUsedList) {
            List<AccompanyingDocumentData> accompanyingDocumentList = accompanyingDocumentRepository.findByMaterialsUsedData(material);
            accompanyingDocumentsMap.put(material.getId(), accompanyingDocumentList);
        }
        model.addAttribute("accompanyingDocumentsMap", accompanyingDocumentsMap);
        return "materialsUsedTable";
    }

    @GetMapping("/materials-used-add")
    public String materialsUsedAdd(Model model) {
        return "materialsUsedAdd";
    }

    @PostMapping("/materials-used-add")
    public String postMaterialsUsedAdd(@RequestParam("material") String nameMaterial,
                                       @RequestParam Map<String, String> formData) {
        List<AccompanyingDocumentData> accompanyingDocumentList = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("name")) {
                int index = Integer.parseInt(key.substring(4));
                String numberKey = "number" + index;
                String dataKey = "date" + index;
                String number = formData.getOrDefault(numberKey, "");
                String data = formData.getOrDefault(dataKey, "");
                AccompanyingDocumentData accompanyingDocument = new AccompanyingDocumentData(value, number, data);
                accompanyingDocumentList.add(accompanyingDocument);
            }
        });
        MaterialsUsedData materialUsedData = new MaterialsUsedData(nameMaterial);
        materialUsedRepository.save(materialUsedData);
        accompanyingDocumentList.forEach(accompanyingDocument -> accompanyingDocument.setMaterialsUsedData(materialUsedData));
        accompanyingDocumentRepository.saveAll(accompanyingDocumentList);
        return "redirect:/materials-used-table";
    }

    @GetMapping("/materials-used-table/{id}/materials-used-edit")
    public String materialsUsedEdit(@PathVariable(value = "id") long id, Model model) {
        Optional<MaterialsUsedData> materialsUsedDataOptional = materialUsedRepository.findById(id);
        if (!materialsUsedDataOptional.isPresent()) {
            return "redirect:/materials-used-table";
        }
        MaterialsUsedData materialsUsedData = materialsUsedDataOptional.get();
        model.addAttribute("materialsUsedData", materialsUsedData); // Добавляем materialsUsedData в модель
        return "materialsUsedEdit";
    }

    @PostMapping("/materials-used-table/{id}/materials-used-edit")
    @Transactional
    public String postMaterialsUsedUpdate(@PathVariable(value = "id") long id,
                                          @RequestParam("material") String nameMaterial,
                                          @RequestParam Map<String, String> formData) {
        MaterialsUsedData materialsUsedData = materialUsedRepository.findById(id).orElseThrow();

        List<AccompanyingDocumentData> accompanyingDocumentList = materialsUsedData.getAccompanyingDocuments();

        formData.forEach((key, value) -> {
            if (key.startsWith("name_")) {
                Long accDocId = Long.parseLong(key.substring("name_".length()));
                AccompanyingDocumentData accompanyingDocument = accompanyingDocumentRepository.findById(accDocId).orElse(null);
                if (accompanyingDocument != null) {
                    accompanyingDocument.setName(value);
                } else {
                    accompanyingDocument = new AccompanyingDocumentData();
                    accompanyingDocument.setId(accDocId);
                    accompanyingDocument.setName(value);
                    accompanyingDocument.setMaterialsUsedData(materialsUsedData);
                    accompanyingDocument.setNumber(formData.get("number_" + accDocId));
                    accompanyingDocument.setDate(formData.get("date_" + accDocId));
                    accompanyingDocumentList.add(accompanyingDocument);
                }
            } else if (key.startsWith("number_")) {
                Long accDocId = Long.parseLong(key.substring("number_".length()));
                accompanyingDocumentRepository.findById(accDocId).ifPresent(accompanyingDocument -> {
                    accompanyingDocument.setNumber(value);
                    accompanyingDocumentList.add(accompanyingDocument);

                });
            } else if (key.startsWith("date_")) {
                Long accDocId = Long.parseLong(key.substring("date_".length()));
                accompanyingDocumentRepository.findById(accDocId).ifPresent(accompanyingDocument -> {
                    accompanyingDocument.setDate(value);
                    accompanyingDocumentList.add(accompanyingDocument);
                });
            }
        });

        List<AccompanyingDocumentData> newDocuments = accompanyingDocumentList.stream()
                .filter(doc -> doc.getId() == null)
                .collect(Collectors.toList());

        newDocuments.forEach(doc -> entityManager.persist(doc));

        accompanyingDocumentRepository.saveAll(newDocuments);

        List<AccompanyingDocumentData> documentsToRemove = new ArrayList<>();
        accompanyingDocumentList.forEach(doc -> {
            if (!formData.containsKey("name_" + doc.getId())) {
                documentsToRemove.add(doc);
            }
        });
        accompanyingDocumentList.removeAll(documentsToRemove);
        accompanyingDocumentRepository.deleteAll(documentsToRemove);

        materialsUsedData.setNameMaterial(nameMaterial);
        materialUsedRepository.save(materialsUsedData);
        return "redirect:/materials-used-table";
    }

    @PostMapping("/materials-used-table/{id}/materials-used-remove")
    public String postMaterialsUsedDelete(@PathVariable(value = "id") long id, Model model) {
        MaterialsUsedData materialsUsedData = materialUsedRepository.findById(id).orElseThrow();
        List<AccompanyingDocumentData> accompanyingDocumentDataList = accompanyingDocumentRepository.findByMaterialsUsedData(materialsUsedData);
        accompanyingDocumentRepository.deleteAll(accompanyingDocumentDataList);
        materialUsedRepository.delete(materialsUsedData);
        return "redirect:/materials-used-table";
    }

    @PostMapping("/materials-used-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        MaterialsUsedData material = materialUsedRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        material.setStatus(status);
        materialUsedRepository.save(material);
        return "redirect:/materials-used-table";
    }
}
