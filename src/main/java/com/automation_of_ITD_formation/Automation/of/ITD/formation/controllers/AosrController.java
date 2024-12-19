package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.print.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ControllersUtils.modelAddUserAndItdData;

@Controller
public class AosrController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;
    @Autowired
    private AosrRepository aosrRepository;
    @Autowired
    private AnotherPersonResponsibleRepository anotherPersonResponsibleRepository;
    @Autowired
    private CustomerResponsibleRepository customerResponsibleRepository;
    @Autowired
    private DesignerResponsibleRepository designerResponsibleRepository;
    @Autowired
    private SubcustomerResponsibleRepository subcustomerResponsibleRepository;
    @Autowired
    private SubcustomerResponsible2Repository subcustomerResponsible2Repository;
    @Autowired
    private PassportObjectRepository passportObjectRepository;
    @Autowired
    private ProjectDocumentationRepository projectDocumentationRepository;
    @Autowired
    private DrawingsRepository drawingsRepository;
    @Autowired
    private MaterialsUsedRepository materialsUsedRepository;
    @Autowired
    private ExecutiveSchemesRepository executiveSchemesRepository;
    @Autowired
    private ActsViCRepository actsViCRepository;
    @Autowired
    private SealingProtocolsRepository sealingProtocolsRepository;
    @Autowired
    private ProtocolsGnbRepository protocolsGnbRepository;
    @Autowired
    private NameWorksRepository nameWorksRepository;
    @Autowired
    private NextNameWorksRepository nextNameWorksRepository;

    private void modelAdd(Model model, ItdData itdData) {
        Set<ProjectDocumentationData> projectDocumentationList = itdData.getProjectDocumentationData();
        Set<ExecutiveSchemesData> executiveSchemesList = itdData.getExecutiveSchemesData();
        List<ExecutiveSchemesData> sortedExecutiveSchemesList = new ArrayList<>(executiveSchemesList);
        sortedExecutiveSchemesList.sort(Comparator.comparing(ExecutiveSchemesData::getNameScheme));

        Set<MaterialsUsedData> materialsUsedList = itdData.getMaterialsUsedData();
        Set<ActsViCData> actsViCList = itdData.getActsViCData();
        Set<SealingProtocolsData> sealingProtocolsList = itdData.getSealingProtocolsData();
        Set<ProtocolsGnbData> protocolsGnbList = itdData.getProtocolsGnbData();
        List<DrawingsData> drawingsList = new ArrayList<>();
        for (ProjectDocumentationData projectDocumentationData : projectDocumentationList) {
            drawingsList.addAll(projectDocumentationData.getDrawingsList());
        }
        PassportObjectData passportObjectData = itdData.getPassportObjectData();
        List<AnotherPersonResponsibleData> anotherPersonResponsibleList = passportObjectData.getAnotherPersonResponsibleData();
        List<CustomerResponsibleData> customerResponsibleList = passportObjectData.getCustomerResponsibleData();
        List<DesignerResponsibleData> designerResponsibleList = passportObjectData.getDesignerResponsibleData();
        List<SubcustomerResponsibleData> subcustomerResponsibleList = passportObjectData.getSubcustomerResponsibleData();
        List<SubcustomerResponsible2Data> subcustomerResponsible2List = passportObjectData.getSubcustomerResponsible2Data();
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("actsViCList", actsViCList);
        model.addAttribute("sealingProtocolsList", sealingProtocolsList);
        model.addAttribute("protocolsGnbList", protocolsGnbList);
        model.addAttribute("materialsUsedList", materialsUsedList);
        model.addAttribute("executiveSchemesList", sortedExecutiveSchemesList);
        model.addAttribute("projectDocumentationList", projectDocumentationList);
        model.addAttribute("anotherPersonResponsibleList", anotherPersonResponsibleList);
        model.addAttribute("customerResponsibleList", customerResponsibleList);
        model.addAttribute("designerResponsibleList", designerResponsibleList);
        model.addAttribute("subcustomerResponsibleList", subcustomerResponsibleList);
        model.addAttribute("subcustomerResponsible2List", subcustomerResponsible2List);
        Map<String, Set<String>> nameWorksMap = new HashMap<>();
        Map<String, Set<String>> normativeMap = new HashMap<>();
        itdData.getNameWorksData().forEach(nameWorksData -> {
            Set<String> mySet1 = new HashSet<>();
            nameWorksData.getNextNameWorksList().forEach(nextNameWorksData -> mySet1.add(nextNameWorksData.getId() + "_" + nextNameWorksData.getName()));
            nameWorksMap.put(nameWorksData.getId().toString(), mySet1);
            Set<String> mySet2 = new HashSet<>();
            nameWorksData.getNormativeList().forEach(normativeData -> mySet2.add(normativeData.getName()));
            normativeMap.put(nameWorksData.getId().toString(), mySet2);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonNameWorksMap, jsonNormativeMap;
        try {
            jsonNameWorksMap = objectMapper.writeValueAsString(nameWorksMap);
            jsonNormativeMap = objectMapper.writeValueAsString(normativeMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("nameWorksMap", jsonNameWorksMap);
        model.addAttribute("normativeMap", jsonNormativeMap);
    }

    private void getAosrEditModel(Model model, Optional<AosrData> aosrDataOptional) {
        AosrData aosrData = aosrDataOptional.get();
        List<Long> selectedMaterialsIds = new ArrayList<>();
        aosrData.getAosrToMaterials().forEach(materialsUsedData -> selectedMaterialsIds.add(materialsUsedData.getId()));
        Iterable<MaterialsUsedData> selectedMaterials = materialsUsedRepository.findAllById(selectedMaterialsIds);
        Set<Long> drawingIds = aosrData.getAosrToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
        Set<Long> actsVicIds;
        if (aosrData.getActsViCData() != null) {
            actsVicIds = aosrData.getActsViCData().stream().map(ActsViCData::getId).collect(Collectors.toSet());
        } else {
            actsVicIds = new HashSet<>(0);
        }
        List<String> projectSectionList = new ArrayList<>();
        for (ProjectDocumentationData projectDocumentationData : aosrData.getAosrToProjDocs()) {
            projectSectionList.add(projectDocumentationData.getProjectSection());
        }
        Long protocolGnbId = (aosrData.getProtocolsGnbData() != null) ? aosrData.getProtocolsGnbData().getId() : -1;
        Long protocolSealingId = (aosrData.getSealingProtocolsData() != null) ? aosrData.getSealingProtocolsData().getId() : -1;
        Long customerResId = (aosrData.getCustomerResponsibleData() != null) ? aosrData.getCustomerResponsibleData().getId() : -1;
        Long subcustomerResId = (aosrData.getSubcustomerResponsibleData() != null) ? aosrData.getSubcustomerResponsibleData().getId() : -1;
        Long subcustomerRes2Id = (aosrData.getSubcustomerResponsible2Data() != null) ? aosrData.getSubcustomerResponsible2Data().getId() : -1;
        Long designerResId = (aosrData.getDesignerResponsibleData() != null) ? aosrData.getDesignerResponsibleData().getId() : -1;
        Long anotherPersonResId = (aosrData.getAnotherPersonResponsibleData() != null) ? aosrData.getAnotherPersonResponsibleData().getId() : -1;

        aosrData.setFormattedDates();

        model.addAttribute("projectSectionList", projectSectionList);
        model.addAttribute("drawingIds", drawingIds);
        model.addAttribute("actsVicIds", actsVicIds);
        model.addAttribute("aosrData", aosrData);
        model.addAttribute("selectedMaterialsList", selectedMaterials);
        model.addAttribute("protocolGnbId", protocolGnbId);
        model.addAttribute("protocolSealingId", protocolSealingId);
        model.addAttribute("customerResId", customerResId);
        model.addAttribute("subcustomerResId", subcustomerResId);
        model.addAttribute("subcustomerRes2Id", subcustomerRes2Id);
        model.addAttribute("designerResId", designerResId);
        model.addAttribute("anotherPersonResId", anotherPersonResId);
    }

    @GetMapping("/aosr-table/{id}")
    public String aosrTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        List<AosrData> aosrList = new ArrayList<>(itdData.getAosrData());
        for (AosrData aosrData : aosrList) {
            aosrData.setFormattedDates();
        }
        aosrList.sort(Comparator.comparing(AosrData::getNumber));
        model.addAttribute("aosrList", aosrList);
        return "aosrTable";
    }

    @GetMapping("/aosr-add/{id}")
    public String aosrAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        modelAdd(model, itdData);
        List<String> aosrFormData = new ArrayList<>();
        aosrFormData.add("1");
        model.addAttribute("aosrFormData", aosrFormData);
        return "aosrAdd";
    }

    @GetMapping("/aosr-search-materials/{itdId}")
    public String searchMaterials(@PathVariable(value = "itdId") long itdId,
                                  @RequestParam("query") String query,
                                  @RequestParam(name = "sourcePage", required = false, defaultValue = "aosrAdd") String sourcePage,
                                  @RequestParam(name = "id", required = false) Long id,
                                  @RequestParam Map<String, String> formData,
                                  Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        List<String> aosrFormData = (List<String>) model.asMap().getOrDefault("aosrFormData", new ArrayList<String>());
        formData.forEach((key, value) -> {
            if (key.startsWith("projectSectionHidden") && !aosrFormData.contains(value)) {
                aosrFormData.add(value);
            }
        });
        if (id != null) {
            Optional<AosrData> aosrDataOptional = aosrRepository.findById(id);
            if (aosrDataOptional.isPresent()) {
                getAosrEditModel(model, aosrDataOptional);
            }
        }
        modelAdd(model, itdData);

        List<MaterialsUsedData> foundMaterials = materialsUsedRepository.findByNameMaterialContainingIgnoreCase(query);
        List<Long> selectedMaterialsIds = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("selectedMaterials")) {
                Long selectedMaterialId = Long.parseLong(key.substring("selectedMaterials".length()));
                Optional<MaterialsUsedData> selectedMaterial = materialsUsedRepository.findById(selectedMaterialId);
                if (selectedMaterial.isPresent()) {
                    selectedMaterialsIds.add(selectedMaterialId);
                }
            }
        });
        Iterable<MaterialsUsedData> selectedMaterials = materialsUsedRepository.findAllById(selectedMaterialsIds);
        foundMaterials = foundMaterials.stream()
                .filter(material -> !selectedMaterialsIds.contains(material.getId()))
                .collect(Collectors.toList());
        model.addAttribute("foundMaterialsList", foundMaterials);
        model.addAttribute("selectedMaterialsList", selectedMaterials);
        model.addAttribute("aosrFormData", aosrFormData);
        return sourcePage;
    }

    @PostMapping("/aosr-table/{itdId}/update-status/{aosrId}")
    public String updateStatus(@PathVariable(value = "itdId") long itdId,
                               @PathVariable(value = "aosrId") long aosrId,
                               @RequestParam("status") String status) {
        AosrData aosr = aosrRepository.findById(aosrId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + aosrId));
        aosr.setStatus(status);
        aosrRepository.save(aosr);
        return "redirect:/aosr-table/" + itdId;
    }

    @PostMapping("/aosr-add/{id}")
    public String postAosrAdd(@PathVariable(value = "id") long id,
                              @RequestParam("number") String number,
                              @RequestParam("nameWorks") String nameWorks,
                              @RequestParam("nextNameWorks") String nextNameWorks,
                              @RequestParam("dateStart") String dateStart,
                              @RequestParam("dateEnd") String dateEnd,
                              @RequestParam("execSchemSelect") Long execSchemId,
                              @RequestParam("protocolGnbSelect") Long protocolGnbId,
                              @RequestParam("sealingProtocolSelect") Long sealingProtocolId,
                              @RequestParam("customerResponsibleSelect") Long customerResId,
                              @RequestParam("subcustomerResponsibleSelect") Long subcustomerResId,
                              @RequestParam("subcustomerResponsible2Select") Long subcustomerRes2Id,
                              @RequestParam("designerResponsibleSelect") Long designerResId,
                              @RequestParam("anotherPersonResponsibleSelect") Long anotherPersonResId,
                              @RequestParam Map<String, String> formData) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();

        String[] splitDateStart = dateStart.split("\\.");
        String[] splitDateEnd = dateEnd.split("\\.");

        LocalDate startDate = LocalDate.of(Integer.parseInt(splitDateStart[2]), Integer.parseInt(splitDateStart[1]), Integer.parseInt(splitDateStart[0]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(splitDateEnd[2]), Integer.parseInt(splitDateEnd[1]), Integer.parseInt(splitDateEnd[0]));

        AosrData aosrData = new AosrData(number, startDate, endDate);

        Set<MaterialsUsedData> materials = new HashSet<>();
        Set<DrawingsData> drawingsSet = new HashSet<>();
        Set<ActsViCData> actsViCSet = new HashSet<>();
        Set<ProjectDocumentationData> projectDocumentationSet = new HashSet<>(aosrData.getAosrToProjDocs());

        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                String[] parts = key.split("-");
                if (parts.length >= 2) {
                    Long drawingId = Long.parseLong(parts[0].replace("drawingCheckbox", ""));
                    DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                    drawingsSet.add(drawing);
                }
            }
            if (key.startsWith("actViCCheckbox")) {
                Long actVicId = Long.parseLong(key.replace("actViCCheckbox", ""));
                ActsViCData actsViCData = actsViCRepository.findById(actVicId).orElseThrow();
                actsViCData.setActsToAosrData(aosrData);
                actsViCSet.add(actsViCData);
            }
            if (key.startsWith("selectedMaterials")) {
                Long selectedMaterialId = Long.parseLong(key.substring("selectedMaterials".length()));
                MaterialsUsedData material = materialsUsedRepository.findById(selectedMaterialId).orElseThrow();
                materials.add(material);
            }
            if (key.startsWith("projectSectionSelect")) {
                Long selectedProjectSectionId = Long.parseLong(value);
                ProjectDocumentationData projDoc = projectDocumentationRepository.findById(selectedProjectSectionId).orElseThrow();
                projectDocumentationSet.add(projDoc);
            }
        });
        aosrData.setAosrToMaterials(materials);
        aosrData.setAosrToDrawings(drawingsSet);
        aosrData.setActsViCData(actsViCSet);
        aosrData.setAosrToProjDocs(projectDocumentationSet);

        if (execSchemId != null) {
            ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();
            aosrData.setExecutiveSchemesData(executiveSchemesData);
        }
        if (protocolGnbId != null && protocolGnbId != -1) {
            ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(protocolGnbId).orElseThrow();
            protocolsGnbData.setGnbToAosrData(aosrData);
        }
        if (sealingProtocolId != null && sealingProtocolId != -1) {
            SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(sealingProtocolId).orElseThrow();
            sealingProtocolsData.setSealingToAosrData(aosrData);
        }
        if (customerResId != null && customerResId != -1) {
            CustomerResponsibleData customerResponsibleData = customerResponsibleRepository.findById(customerResId).orElseThrow();
            aosrData.setCustomerResponsibleData(customerResponsibleData);
        }
        if (subcustomerResId != null && subcustomerResId != -1) {
            SubcustomerResponsibleData subcustomerResponsibleData = subcustomerResponsibleRepository.findById(subcustomerResId).orElseThrow();
            aosrData.setSubcustomerResponsibleData(subcustomerResponsibleData);
        }
        if (subcustomerRes2Id != null && subcustomerRes2Id != -1) {
            SubcustomerResponsible2Data subcustomerResponsible2Data = subcustomerResponsible2Repository.findById(subcustomerRes2Id).orElseThrow();
            aosrData.setSubcustomerResponsible2Data(subcustomerResponsible2Data);
        }
        if (designerResId != null && designerResId != -1) {
            DesignerResponsibleData designerResponsibleData = designerResponsibleRepository.findById(designerResId).orElseThrow();
            aosrData.setDesignerResponsibleData(designerResponsibleData);
        }
        if (anotherPersonResId != null && anotherPersonResId != -1) {
            AnotherPersonResponsibleData anotherPersonResponsibleData = anotherPersonResponsibleRepository.findById(anotherPersonResId).orElseThrow();
            aosrData.setAnotherPersonResponsibleData(anotherPersonResponsibleData);
        }

        PassportObjectData passportObject = itdData.getPassportObjectData();
        aosrData.setPassportObjectData(passportObject);
        Optional<NameWorksData> nameWorksData = nameWorksRepository.findById(Long.valueOf(nameWorks));
        nameWorksData.ifPresent(aosrData::setNameWorksToAosr);
        Optional<NextNameWorksData> nextNameWorksData = nextNameWorksRepository.findById(Long.valueOf(nextNameWorks));
        nextNameWorksData.ifPresent(aosrData::setNextNameWorksToAosr);
        aosrData.setStatus("Требует создания");
        aosrData.setItdToAosrData(itdData);
        aosrRepository.save(aosrData);
        return "redirect:/aosr-table/" + id;
    }

    @GetMapping("/aosr-table/{itdId}/aosr-edit/{aosrId}")
    public String aosrEdit(@PathVariable(value = "itdId") long itdId,
                           @PathVariable(value = "aosrId") long aosrId,
                           Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        if (!aosrRepository.existsById(aosrId)) {
            return "redirect:/aosr-table/" + itdId;
        }
        Optional<AosrData> aosrDataOptional = aosrRepository.findById(aosrId);
        if (aosrDataOptional.isPresent()) {
            getAosrEditModel(model, aosrDataOptional);
        }
        modelAdd(model, itdData);
        return "aosrEdit";
    }

    @PostMapping("/aosr-table/{itdId}/aosr-edit/{aosrId}")
    @Transactional
    public String postAosrUpdate(@PathVariable(value = "itdId") long itdId,
                                 @PathVariable(value = "aosrId") long aosrId,
                                 @RequestParam("number") String number,
                                 @RequestParam("nameWorks") String nameWorks,
                                 @RequestParam("nextNameWorks") String nextNameWorks,
                                 @RequestParam("dateStart") String dateStart,
                                 @RequestParam("dateEnd") String dateEnd,
                                 @RequestParam("execSchemSelect") Long execSchemId,
                                 @RequestParam("protocolGnbSelect") Long protocolGnbId,
                                 @RequestParam("sealingProtocolSelect") Long sealingProtocolId,
                                 @RequestParam("customerResponsibleSelect") Long customerResId,
                                 @RequestParam("subcustomerResponsibleSelect") Long subcustomerResId,
                                 @RequestParam("subcustomerResponsible2Select") Long subcustomerRes2Id,
                                 @RequestParam("designerResponsibleSelect") Long designerResId,
                                 @RequestParam("anotherPersonResponsibleSelect") Long anotherPersonResId,
                                 @RequestParam Map<String, String> formData) {
        AosrData aosrData = aosrRepository.findById(aosrId).orElseThrow();

        for (DrawingsData drawing : aosrData.getAosrToDrawings()) {
            drawing.getDrawingsToAosr().remove(aosrData);
            drawingsRepository.save(drawing);
        }
        aosrData.getAosrToDrawings().clear();
        for (ProjectDocumentationData projDoc : aosrData.getAosrToProjDocs()) {
            projDoc.setAosrs(null);
            projectDocumentationRepository.save(projDoc);
        }
        aosrData.getAosrToProjDocs().clear();
        for (ActsViCData act : aosrData.getActsViCData()) {
            act.setActsToAosrData(null);
            actsViCRepository.save(act);
        }
        aosrData.getActsViCData().clear();
        for (MaterialsUsedData material : aosrData.getAosrToMaterials()) {
            material.getMaterialsToAosr().remove(aosrData);
            materialsUsedRepository.save(material);
        }
        aosrData.getAosrToMaterials().clear();

        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                String[] parts = key.split("-");
                if (parts.length >= 2) {
                    Long drawingId = Long.parseLong(parts[0].replace("drawingCheckbox", ""));
                    DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                    drawing.getDrawingsToAosr().add(aosrData);
                    aosrData.getAosrToDrawings().add(drawing);
                    drawingsRepository.save(drawing);
                }
            }
            if (key.startsWith("projectSectionSelect")) {
                Long projDocId = Long.parseLong(value);
                ProjectDocumentationData projDoc = projectDocumentationRepository.findById(projDocId).orElseThrow();
                if (projDoc.getAosrs() == null) {
                    projDoc.setAosrs(new HashSet<>());
                }
                projDoc.getAosrs().add(aosrData);
                aosrData.getAosrToProjDocs().add(projDoc);
                projectDocumentationRepository.save(projDoc);
            }
            if (key.startsWith("actViCCheckbox")) {
                Long actVicId = Long.parseLong(key.replace("actViCCheckbox", ""));
                ActsViCData actsViCData = actsViCRepository.findById(actVicId).orElseThrow();
                actsViCData.setActsToAosrData(aosrData);
                aosrData.getActsViCData().add(actsViCData);
                actsViCRepository.save(actsViCData);
            }
            if (key.startsWith("selectedMaterials")) {
                Long materialId = Long.parseLong(key.substring("selectedMaterials".length()));
                MaterialsUsedData material = materialsUsedRepository.findById(materialId).orElseThrow();
                material.getMaterialsToAosr().add(aosrData);
                aosrData.getAosrToMaterials().add(material);
                materialsUsedRepository.save(material);
            }
        });

        if (protocolGnbId != null) {
            ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
            if (protocolsGnbData != null) {
                protocolsGnbData.setGnbToAosrData(null);
                aosrData.setProtocolsGnbData(null);
                protocolsGnbRepository.save(protocolsGnbData);
            }

            if (protocolGnbId != -1) {
                ProtocolsGnbData newProtocol = protocolsGnbRepository.findById(protocolGnbId).orElseThrow();
                newProtocol.setGnbToAosrData(aosrData);
                aosrData.setProtocolsGnbData(newProtocol);
                protocolsGnbRepository.save(newProtocol);
            }
        }

        if (sealingProtocolId != null) {
            SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
            if (sealingProtocolsData != null) {
                sealingProtocolsData.setSealingToAosrData(null);
                aosrData.setSealingProtocolsData(null);
                sealingProtocolsRepository.save(sealingProtocolsData);
            }

            if (sealingProtocolId != -1) {
                SealingProtocolsData newProtocol = sealingProtocolsRepository.findById(sealingProtocolId).orElseThrow();
                aosrData.setSealingProtocolsData(newProtocol);
                newProtocol.setSealingToAosrData(aosrData);
                sealingProtocolsRepository.save(newProtocol);
            }
        }

        if (customerResId != null) {
            CustomerResponsibleData customerResponsibleData = aosrData.getCustomerResponsibleData();
            if (customerResponsibleData != null) {
                customerResponsibleData.setAosrData(null);
                aosrData.setCustomerResponsibleData(null);
                customerResponsibleRepository.save(customerResponsibleData);
            }

            if (customerResId != -1) {
                CustomerResponsibleData newCustomer = customerResponsibleRepository.findById(customerResId).orElseThrow();
                aosrData.setCustomerResponsibleData(newCustomer);
                if (newCustomer.getAosrData() == null) {
                    newCustomer.setAosrData(new ArrayList<>());
                }
                newCustomer.getAosrData().add(aosrData);
                customerResponsibleRepository.save(newCustomer);
            }
        }

        if (subcustomerResId != null) {
            SubcustomerResponsibleData subcustomerResponsibleData = aosrData.getSubcustomerResponsibleData();
            if (subcustomerResponsibleData != null) {
                subcustomerResponsibleData.setAosrData(null);
                aosrData.setSubcustomerResponsibleData(null);
                subcustomerResponsibleRepository.save(subcustomerResponsibleData);
            }

            if (subcustomerResId != -1) {
                SubcustomerResponsibleData newSubcustomer = subcustomerResponsibleRepository.findById(subcustomerResId).orElseThrow();
                aosrData.setSubcustomerResponsibleData(newSubcustomer);
                if (newSubcustomer.getAosrData() == null) {
                    newSubcustomer.setAosrData(new ArrayList<>());
                }
                newSubcustomer.getAosrData().add(aosrData);
                subcustomerResponsibleRepository.save(newSubcustomer);
            }
        }

        if (subcustomerRes2Id != null) {
            SubcustomerResponsible2Data subcustomerResponsible2Data = aosrData.getSubcustomerResponsible2Data();
            if (subcustomerResponsible2Data != null) {
                subcustomerResponsible2Data.setAosrData(null);
                aosrData.setSubcustomerResponsible2Data(null);
                subcustomerResponsible2Repository.save(subcustomerResponsible2Data);
            }

            if (subcustomerRes2Id != -1) {
                SubcustomerResponsible2Data newSubcustomer2 = subcustomerResponsible2Repository.findById(subcustomerRes2Id).orElseThrow();
                aosrData.setSubcustomerResponsible2Data(newSubcustomer2);
                if (newSubcustomer2.getAosrData() == null) {
                    newSubcustomer2.setAosrData(new ArrayList<>());
                }
                newSubcustomer2.getAosrData().add(aosrData);
                subcustomerResponsible2Repository.save(newSubcustomer2);
            }
        }

        if (designerResId != null) {
            DesignerResponsibleData designerResponsibleData = aosrData.getDesignerResponsibleData();
            if (designerResponsibleData != null) {
                designerResponsibleData.setAosrData(null);
                aosrData.setDesignerResponsibleData(null);
                designerResponsibleRepository.save(designerResponsibleData);
            }

            if (designerResId != -1) {
                DesignerResponsibleData newDesigner = designerResponsibleRepository.findById(designerResId).orElseThrow();
                aosrData.setDesignerResponsibleData(newDesigner);
                if (newDesigner.getAosrData() == null) {
                    newDesigner.setAosrData(new ArrayList<>());
                }
                newDesigner.getAosrData().add(aosrData);
                designerResponsibleRepository.save(newDesigner);
            }
        }

        if (anotherPersonResId != null) {
            AnotherPersonResponsibleData anotherPersonResponsibleData = aosrData.getAnotherPersonResponsibleData();
            if (anotherPersonResponsibleData != null) {
                anotherPersonResponsibleData.setAosrData(null);
                aosrData.setAnotherPersonResponsibleData(null);
                anotherPersonResponsibleRepository.save(anotherPersonResponsibleData);
            }

            if (anotherPersonResId != -1) {
                AnotherPersonResponsibleData newAnotherPerson = anotherPersonResponsibleRepository.findById(anotherPersonResId).orElseThrow();
                aosrData.setAnotherPersonResponsibleData(newAnotherPerson);
                if (newAnotherPerson.getAosrData() == null) {
                    newAnotherPerson.setAosrData(new ArrayList<>());
                }
                newAnotherPerson.getAosrData().add(aosrData);
                anotherPersonResponsibleRepository.save(newAnotherPerson);
            }
        }

        ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();
        aosrData.setExecutiveSchemesData(executiveSchemesData);
        aosrData.setNumber(number);

        String[] splitDateStart = dateStart.split("\\.");
        String[] splitDateEnd = dateEnd.split("\\.");

        LocalDate startDate = LocalDate.of(Integer.parseInt(splitDateStart[2]), Integer.parseInt(splitDateStart[1]), Integer.parseInt(splitDateStart[0]));
        LocalDate endDate = LocalDate.of(Integer.parseInt(splitDateEnd[2]), Integer.parseInt(splitDateEnd[1]), Integer.parseInt(splitDateEnd[0]));

        aosrData.setStartDate(startDate);
        aosrData.setEndDate(endDate);

        Optional<NameWorksData> nameWorksData = nameWorksRepository.findById(Long.valueOf(nameWorks));
        nameWorksData.ifPresent(aosrData::setNameWorksToAosr);
        Optional<NextNameWorksData> nextNameWorksData = nextNameWorksRepository.findById(Long.valueOf(nextNameWorks));
        nextNameWorksData.ifPresent(aosrData::setNextNameWorksToAosr);
        aosrRepository.save(aosrData);

        return "redirect:/aosr-table/" + itdId;
    }

    @PostMapping("/aosr-table/{itdId}/aosr-remove/{aosrId}")
    @Transactional
    public String postAosrDelete(@PathVariable(value = "itdId") long itdId,
                                 @PathVariable(value = "aosrId") long aosrId) {
        AosrData aosrData = aosrRepository.findById(aosrId).orElseThrow();

        Set<DrawingsData> drawings = aosrData.getAosrToDrawings();
        Set<MaterialsUsedData> materialsUsedSet = aosrData.getAosrToMaterials();
        Set<ActsViCData> actsViCSet = aosrData.getActsViCData();
        Set<ProjectDocumentationData> projDocs = aosrData.getAosrToProjDocs();

        if (!drawings.isEmpty()) {
            drawings.forEach(drawing -> drawing.getDrawingsToAosr().remove(aosrData));
            aosrData.getAosrToDrawings().clear();
        }

        if (!materialsUsedSet.isEmpty()) {
            materialsUsedSet.forEach(material -> material.getMaterialsToAosr().remove(aosrData));
            aosrData.getAosrToMaterials().clear();
        }

        if (!actsViCSet.isEmpty()) {
            actsViCSet.forEach(actViC -> {
                actViC.setActsToAosrData(null);
                actsViCRepository.save(actViC);
            });
            aosrData.getActsViCData().clear();
        }

        if (!projDocs.isEmpty()) {
            projDocs.forEach(projDoc -> projDoc.getAosrs().remove(aosrData));
            aosrData.getAosrToProjDocs().clear();
        }

        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
        if (executiveSchemesData != null) {
            executiveSchemesData.getAosrs().remove(aosrData);
            aosrData.setExecutiveSchemesData(null);
        }

        PassportObjectData passportObjectData = aosrData.getPassportObjectData();
        if (passportObjectData != null) {
            passportObjectData.getAosrs().remove(aosrData);
            aosrData.setPassportObjectData(null);
        }

        ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
        if (protocolsGnbData != null) {
            protocolsGnbData.setGnbToAosrData(null);
            aosrData.setProtocolsGnbData(null);
        }

        SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
        if (sealingProtocolsData != null) {
            sealingProtocolsData.setSealingToAosrData(null);
            aosrData.setSealingProtocolsData(null);
        }

        aosrRepository.delete(aosrData);

        return "redirect:/aosr-table/" + itdId;
    }

    @PostMapping("/aosr-table/{itdId}")
    public void postAosrTable(@PathVariable(value = "itdId") long itdId, HttpServletResponse response) {
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        List<AosrData> aosrList = new ArrayList<>(itdData.getAosrData());
        aosrList.sort(Comparator.comparing(AosrData::getNumber));

        try {
            List<File> pdfFiles = new ArrayList<>();
            for (AosrData aosrData : aosrList) {
                File tempFile = GenerateFileUtils.generateAosrFile(aosrData);
                File pdfFile = new File(tempFile.getAbsolutePath().replace(".docx", ".pdf"));
                ProcessBuilder processBuilder = new ProcessBuilder("soffice", "--headless", "--convert-to", "pdf", tempFile.getAbsolutePath(), "--outdir", tempFile.getParent());
                Process process = processBuilder.start();
                process.waitFor();
                pdfFiles.add(pdfFile);
            }

            File mergedPdfFile = File.createTempFile("MergedAOCP", ".pdf");
            mergePdfFiles(pdfFiles, mergedPdfFile);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=AOCP.pdf");
            response.setHeader("Content-Length", String.valueOf(mergedPdfFile.length()));

            try (InputStream in = new FileInputStream(mergedPdfFile)) {
                FileCopyUtils.copy(in, response.getOutputStream());
            }

            response.flushBuffer();

            for (File pdfFile : pdfFiles) {
                if (pdfFile.exists()) {
                    pdfFile.delete();
                }
            }
            mergedPdfFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void mergePdfFiles(List<File> pdfFiles, File mergedPdfFile) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("pdftk");

        for (File pdfFile : pdfFiles) {
            command.add(pdfFile.getAbsolutePath());
        }

        command.add("cat");
        command.add("output");
        command.add(mergedPdfFile.getAbsolutePath());

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        process.waitFor();
    }

    @GetMapping("/aosr-table/{itdId}/aosr-preview/{aosrId}")
    public void getAosrPreview(@PathVariable(value = "itdId") long itdId,
                               @PathVariable(value = "aosrId") long aosrId,
                               HttpServletResponse response) {
        AosrData aosrData = aosrRepository.findById(aosrId).orElseThrow();

        try {
            File tempFile = GenerateFileUtils.generateAosrFile(aosrData);
            File pdfFile = new File(tempFile.getAbsolutePath().replace(".docx", ".pdf"));

            ProcessBuilder processBuilder = new ProcessBuilder("soffice", "--headless", "--convert-to", "pdf", tempFile.getAbsolutePath(), "--outdir", tempFile.getParent());
            Process process = processBuilder.start();
            process.waitFor();

            try (InputStream pdfInputStream = new FileInputStream(pdfFile);
                 OutputStream pdfOutputStream = response.getOutputStream()) {
                response.setContentType("application/pdf");
                response.setHeader("Content-Disposition", "inline; filename=preview.pdf");

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = pdfInputStream.read(buffer)) != -1) {
                    pdfOutputStream.write(buffer, 0, bytesRead);
                }
                pdfOutputStream.flush();
            }

            tempFile.delete();
            pdfFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @PostMapping("/aosr-table/{itdId}/aosr-print/{aosrId}")
    public void postAosrPrint(@PathVariable(value = "itdId") long itdId,
                              @PathVariable(value = "aosrId") long aosrId,
                              HttpServletResponse response) {
        AosrData aosrData = aosrRepository.findById(aosrId).orElseThrow();

        try {
            File tempFile = GenerateFileUtils.generateAosrFile(aosrData);

            PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
            if (printService == null) {
                throw new IllegalStateException("Принтер по умолчанию не найден");
            }

            DocPrintJob printJob = printService.createPrintJob();
            Doc doc = new SimpleDoc(new FileInputStream(tempFile), DocFlavor.INPUT_STREAM.AUTOSENSE, null);

            printJob.print(doc, null);

            tempFile.delete();
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");

            try (OutputStream outputStream = response.getOutputStream();
                 Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                writer.write("<html><body>Документ отправлен на печать.</body></html>");
            }
        } catch (PrintException | IOException e) {
            e.printStackTrace();
            try {
                response.setContentType("text/html; charset=UTF-8");
                response.setCharacterEncoding("UTF-8");

                try (OutputStream outputStream = response.getOutputStream();
                     Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8)) {
                    writer.write("<html><body>Ошибка при отправке на печать: " + e.getMessage() + "</body></html>");
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}