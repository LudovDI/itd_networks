package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ExecutiveSchemesController {

    @Autowired
    private ProjectDocumentationRepository projectDocumentationRepository;

    @Autowired
    private AosrRepository aosrRepository;

    @Autowired
    private DrawingsRepository drawingsRepository;

    @Autowired
    private MaterialsUsedRepository materialsUsedRepository;

    @Autowired
    private ExecutiveSchemesRepository executiveSchemesRepository;

    @GetMapping("/executive-schemes-table")
    public String executivesSchemesTable(Model model) {
        Iterable<ExecutiveSchemesData> executiveSchemesList = executiveSchemesRepository.findAll();
        model.addAttribute("executiveSchemesList", executiveSchemesList);
        return "executiveSchemesTable";
    }

    private void populateModel(Model model) {
        Iterable<ProjectDocumentationData> projectDocumentationList = projectDocumentationRepository.findAll();
        Iterable<DrawingsData> drawingsList = drawingsRepository.findAll();
        Iterable<MaterialsUsedData> materialsUsedList = materialsUsedRepository.findAll();
        model.addAttribute("projectDocumentationList", projectDocumentationList);
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("materialsUsedList", materialsUsedList);
    }

    @GetMapping("/executive-schemes-add")
    public String executivesSchemesAdd(Model model) {
        populateModel(model);
        return "executiveSchemesAdd";
    }

    @GetMapping("/exec-search-materials")
    public String searchMaterials(@RequestParam("query") String query,
                                  @RequestParam(name = "selectedMaterials", required = false) List<Long> selectedMaterialsIds,
                                  @RequestParam(name = "sourcePage", required = false, defaultValue = "executiveSchemesAdd") String sourcePage,
                                  @RequestParam(name = "id", required = false) Long id,
                                  Model model) {
        if (id != null) {
            Optional<ExecutiveSchemesData> executiveSchemesDataOptional = executiveSchemesRepository.findById(id);
            if (executiveSchemesDataOptional.isPresent()) {
                ExecutiveSchemesData executiveSchemesData = executiveSchemesDataOptional.get();
                model.addAttribute("executiveSchemesData", executiveSchemesData);
                Set<Long> drawingIds = executiveSchemesData.getExecSchemesToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
                model.addAttribute("drawingIds", drawingIds);
            }
        }
        populateModel(model);

        List<MaterialsUsedData> foundMaterials = materialsUsedRepository.findByNameMaterialContainingIgnoreCase(query);
        Iterable<MaterialsUsedData> selectedMaterials = new ArrayList<>();
        if (selectedMaterialsIds != null) {
            selectedMaterials = materialsUsedRepository.findAllById(selectedMaterialsIds);
            foundMaterials = foundMaterials.stream()
                    .filter(material -> !selectedMaterialsIds.contains(material.getId()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("foundMaterialsList", foundMaterials);
        model.addAttribute("selectedMaterialsList", selectedMaterials);
        return sourcePage;
    }

    @PostMapping("/executive-schemes-add")
    public String postExecutiveSchemesAdd(@RequestParam("nameScheme") String nameScheme,
                                          @RequestParam("section") String section,
                                          @RequestParam("startCoordinateX") String startCoordinateX,
                                          @RequestParam("startCoordinateY") String startCoordinateY,
                                          @RequestParam("endCoordinateX") String endCoordinateX,
                                          @RequestParam("endCoordinateY") String endCoordinateY,
                                          @RequestParam("wellCenterX") String wellCenterX,
                                          @RequestParam("wellCenterY") String wellCenterY,
                                          @RequestParam("soilDevelopment") String soilDevelopment,
                                          @RequestParam("sandyBase") String sandyBase,
                                          @RequestParam("shoofing") String shoofing,
                                          @RequestParam("backfillingShoofing") String backfillingShoofing,
                                          @RequestParam("sand") String sand,
                                          @RequestParam("priming") String priming,
                                          @RequestParam("layingPipeline") String layingPipeline,
                                          @RequestParam("layingPipelineGNB") String layingPipelineGNB,
                                          @RequestParam("projectSectionSelect") String projectSection,
                                          @RequestParam("startingPoint") String startingPoint,
                                          @RequestParam("endingPoint") String endingPoint,
                                          @RequestParam(value = "selectedMaterials", required = false) List<Long> materialIds,
                                          @RequestParam Map<String, String> formData) {
        ExecutiveSchemesData executiveSchemesData = new ExecutiveSchemesData(nameScheme, section, startCoordinateX, startCoordinateY, endCoordinateX, endCoordinateY, wellCenterX, wellCenterY, soilDevelopment, sandyBase, shoofing, backfillingShoofing, sand, priming, layingPipeline, layingPipelineGNB, projectSection, startingPoint, endingPoint);

        Set<DrawingsData> drawings = new HashSet<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                Long drawingId = Long.parseLong(key.substring("drawingCheckbox".length()));
                Optional<DrawingsData> drawingOptional = drawingsRepository.findById(drawingId);
                if (drawingOptional.get().getProjDocToDrawings().getProjectSection().equals(projectSection)) {
                    drawingOptional.ifPresent(drawings::add);
                }
            }
        });
        executiveSchemesData.setExecSchemesToDrawings(drawings);
        for (DrawingsData drawing : drawings) {
            Set<ExecutiveSchemesData> execSchemes = drawing.getDrawingsToExecSchemes();
            execSchemes.add(executiveSchemesData);
            drawing.setDrawingsToExecSchemes(execSchemes);
        }

        if (materialIds != null) {
            Set<MaterialsUsedData> materialsUsed = new HashSet<>();
            for (Long materialId : materialIds) {
                Optional<MaterialsUsedData> materialOptional = materialsUsedRepository.findById(materialId);
                materialOptional.ifPresent(materialsUsed::add);
            }
            executiveSchemesData.setExecSchemesToMaterials(materialsUsed);
            for (MaterialsUsedData material : materialsUsed) {
                Set<ExecutiveSchemesData> execSchemes = material.getMaterialsToExecSchemes();
                execSchemes.add(executiveSchemesData);
                material.setMaterialsToExecSchemes(execSchemes);
            }
        }
        executiveSchemesRepository.save(executiveSchemesData);

        return "redirect:/executive-schemes-table";
    }

    @GetMapping("/executive-schemes-table/{id}/executive-schemes-edit")
    public String executiveSchemesEdit(@PathVariable(value = "id") long id, Model model) {
        if (!executiveSchemesRepository.existsById(id)) {
            return "redirect:/executive-schemes-table";
        }
        Optional<ExecutiveSchemesData> executiveSchemesDataOptional = executiveSchemesRepository.findById(id);
        if (executiveSchemesDataOptional.isPresent()) {
            ExecutiveSchemesData executiveSchemesData = executiveSchemesDataOptional.get();
            Set<Long> drawingIds = executiveSchemesData.getExecSchemesToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
            model.addAttribute("drawingIds", drawingIds);
            model.addAttribute("executiveSchemesData", executiveSchemesData);
            List<MaterialsUsedData> selectedMaterials = executiveSchemesData.getExecSchemesToMaterials().stream().toList();
            model.addAttribute("selectedMaterialsList", selectedMaterials);
        }
        populateModel(model);
        return "executiveSchemesEdit";
    }

    @PostMapping("/executive-schemes-table/{id}/executive-schemes-edit")
    @Transactional
    public String postExecutiveSchemesUpdate(@PathVariable(value = "id") long id,
                                             @RequestParam("nameScheme") String nameScheme,
                                             @RequestParam("section") String section,
                                             @RequestParam("startCoordinateX") String startCoordinateX,
                                             @RequestParam("startCoordinateY") String startCoordinateY,
                                             @RequestParam("endCoordinateX") String endCoordinateX,
                                             @RequestParam("endCoordinateY") String endCoordinateY,
                                             @RequestParam("wellCenterX") String wellCenterX,
                                             @RequestParam("wellCenterY") String wellCenterY,
                                             @RequestParam("soilDevelopment") String soilDevelopment,
                                             @RequestParam("sandyBase") String sandyBase,
                                             @RequestParam("shoofing") String shoofing,
                                             @RequestParam("backfillingShoofing") String backfillingShoofing,
                                             @RequestParam("sand") String sand,
                                             @RequestParam("priming") String priming,
                                             @RequestParam("layingPipeline") String layingPipeline,
                                             @RequestParam("layingPipelineGNB") String layingPipelineGNB,
                                             @RequestParam("projectSectionSelect") String projectSection,
                                             @RequestParam("startingPoint") String startingPoint,
                                             @RequestParam("endingPoint") String endingPoint,
                                             @RequestParam(value = "material", required = false) List<Long> materialIds,
                                             @RequestParam Map<String, String> formData) {
        ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(id).orElseThrow();

        for (DrawingsData drawing : executiveSchemesData.getExecSchemesToDrawings()) {
            drawing.getDrawingsToExecSchemes().remove(executiveSchemesData);
            drawingsRepository.save(drawing);
        }
        executiveSchemesData.getExecSchemesToDrawings().clear();

        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                Long drawingId = Long.parseLong(key.substring("drawingCheckbox".length()));
                DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                if (drawing.getProjDocToDrawings().getProjectSection().equals(projectSection)) {
                    drawing.getDrawingsToExecSchemes().add(executiveSchemesData);
                    executiveSchemesData.getExecSchemesToDrawings().add(drawing);
                    drawingsRepository.save(drawing);
                }
            }
        });

        for (MaterialsUsedData material : executiveSchemesData.getExecSchemesToMaterials()) {
            material.getMaterialsToExecSchemes().remove(executiveSchemesData);
            materialsUsedRepository.save(material);
        }
        executiveSchemesData.getExecSchemesToMaterials().clear();

        if (materialIds != null) {
            for (Long materialId : materialIds) {
                if (materialId != -1) {
                    MaterialsUsedData material = materialsUsedRepository.findById(materialId).orElseThrow();
                    material.getMaterialsToExecSchemes().add(executiveSchemesData);
                    executiveSchemesData.getExecSchemesToMaterials().add(material);
                    materialsUsedRepository.save(material);
                }
            }
        }

        executiveSchemesData.setNameScheme(nameScheme);
        executiveSchemesData.setSection(section);
        executiveSchemesData.setStartCoordinateX(startCoordinateX);
        executiveSchemesData.setStartCoordinateY(startCoordinateY);
        executiveSchemesData.setEndCoordinateX(endCoordinateX);
        executiveSchemesData.setEndCoordinateY(endCoordinateY);
        executiveSchemesData.setWellCenterX(wellCenterX);
        executiveSchemesData.setWellCenterY(wellCenterY);
        executiveSchemesData.setSoilDevelopment(soilDevelopment);
        executiveSchemesData.setSandyBase(sandyBase);
        executiveSchemesData.setShoofing(shoofing);
        executiveSchemesData.setBackfillingShoofing(backfillingShoofing);
        executiveSchemesData.setSand(sand);
        executiveSchemesData.setPriming(priming);
        executiveSchemesData.setLayingPipeline(layingPipeline);
        executiveSchemesData.setLayingPipelineGNB(layingPipelineGNB);
        executiveSchemesData.setProjectSection(projectSection);
        executiveSchemesData.setStartingPoint(startingPoint);
        executiveSchemesData.setEndingPoint(endingPoint);

        executiveSchemesRepository.save(executiveSchemesData);

        return "redirect:/executive-schemes-table";
    }


    @PostMapping("/executive-schemes-table/{id}/executive-schemes-remove")
    @Transactional
    public String postExecutiveSchemesDelete(@PathVariable(value = "id") long id, Model model) {
        ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(id).orElseThrow();

        Set<DrawingsData> drawings = executiveSchemesData.getExecSchemesToDrawings();
        Set<MaterialsUsedData> materialsUsedData = executiveSchemesData.getExecSchemesToMaterials();
        Set<AosrData> aosrData = executiveSchemesData.getAosrs();

        if (!drawings.isEmpty()) {
            drawings.forEach(drawing -> {
                Set<ExecutiveSchemesData> execSchemes = drawing.getDrawingsToExecSchemes();
                execSchemes.remove(executiveSchemesData);
                drawing.setDrawingsToExecSchemes(execSchemes);
                drawingsRepository.save(drawing);
            });
        }

        if (!materialsUsedData.isEmpty()) {
            materialsUsedData.forEach(material -> {
                Set<ExecutiveSchemesData> execSchemes = material.getMaterialsToExecSchemes();
                execSchemes.remove(executiveSchemesData);
                material.setMaterialsToExecSchemes(execSchemes);
                materialsUsedRepository.save(material);
            });
        }

        if (!aosrData.isEmpty()) {
            aosrData.forEach(aosr -> {
                aosr.setExecutiveSchemesData(null);
                aosrRepository.save(aosr);
            });
            executiveSchemesData.getAosrs().clear();
        }

        executiveSchemesRepository.delete(executiveSchemesData);

        return "redirect:/executive-schemes-table";
    }

    @PostMapping("/executive-schemes-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        ExecutiveSchemesData execSchem = executiveSchemesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        execSchem.setStatus(status);
        executiveSchemesRepository.save(execSchem);
        return "redirect:/executive-schemes-table";
    }
}
