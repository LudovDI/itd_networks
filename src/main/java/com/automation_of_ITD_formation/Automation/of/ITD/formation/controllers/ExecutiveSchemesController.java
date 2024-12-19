package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ControllersUtils.modelAddUserAndItdData;

@Controller
public class ExecutiveSchemesController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;
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

    @GetMapping("/executive-schemes-table/{id}")
    public String executivesSchemesTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        Set<ExecutiveSchemesData> executiveSchemesList = itdData.getExecutiveSchemesData();
        model.addAttribute("executiveSchemesList", executiveSchemesList);
        return "executiveSchemesTable";
    }

    private void populateModel(Model model, ItdData itdData) {
        Set<ProjectDocumentationData> projectDocumentationList = itdData.getProjectDocumentationData();
        List<DrawingsData> drawingsList = new ArrayList<>();
        for (ProjectDocumentationData projectDocumentationData : projectDocumentationList) {
            drawingsList.addAll(projectDocumentationData.getDrawingsList());
        }
        Set<MaterialsUsedData> materialsUsedList = itdData.getMaterialsUsedData();
        model.addAttribute("projectDocumentationList", projectDocumentationList);
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("materialsUsedList", materialsUsedList);
    }

    @GetMapping("/executive-schemes-add/{id}")
    public String executivesSchemesAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
        modelAddUserAndItdData(principal, id, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        populateModel(model, itdData);
        return "executiveSchemesAdd";
    }

    @GetMapping("/exec-search-materials/{itdId}")
    public String searchMaterials(@PathVariable(value = "itdId") long itdId,
                                  @RequestParam("query") String query,
                                  @RequestParam(name = "sourcePage", required = false, defaultValue = "executiveSchemesAdd") String sourcePage,
                                  @RequestParam(name = "id", required = false) Long id,
                                  @RequestParam Map<String, String> formData,
                                  Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        if (id != null) {
            Optional<ExecutiveSchemesData> executiveSchemesDataOptional = executiveSchemesRepository.findById(id);
            if (executiveSchemesDataOptional.isPresent()) {
                ExecutiveSchemesData executiveSchemesData = executiveSchemesDataOptional.get();
                model.addAttribute("executiveSchemesData", executiveSchemesData);
                Set<Long> drawingIds = executiveSchemesData.getExecSchemesToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
                model.addAttribute("drawingIds", drawingIds);
            }
        }
        populateModel(model, itdData);

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
        return sourcePage;
    }

    @PostMapping("/executive-schemes-add/{id}")
    public String postExecutiveSchemesAdd(@PathVariable(value = "id") long id,
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
                                          @RequestParam Map<String, String> formData) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        ExecutiveSchemesData executiveSchemesData = new ExecutiveSchemesData(nameScheme, section, startCoordinateX, startCoordinateY, endCoordinateX, endCoordinateY, wellCenterX, wellCenterY, soilDevelopment, sandyBase, shoofing, backfillingShoofing, sand, priming, layingPipeline, layingPipelineGNB, projectSection, startingPoint, endingPoint);

        Set<DrawingsData> drawings = new HashSet<>();
        Set<MaterialsUsedData> materialsUsed = new HashSet<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                Long drawingId = Long.parseLong(key.substring("drawingCheckbox".length()));
                Optional<DrawingsData> drawingOptional = drawingsRepository.findById(drawingId);
                if (drawingOptional.get().getProjDocToDrawings().getProjectSection().equals(projectSection)) {
                    drawingOptional.ifPresent(drawings::add);
                }
            }
            if (key.startsWith("selectedMaterials")) {
                Long selectedMaterialId = Long.parseLong(key.substring("selectedMaterials".length()));
                Optional<MaterialsUsedData> materialOptional = materialsUsedRepository.findById(selectedMaterialId);
                materialOptional.ifPresent(materialsUsed::add);
            }
        });
        executiveSchemesData.setExecSchemesToDrawings(drawings);
        for (DrawingsData drawing : drawings) {
            Set<ExecutiveSchemesData> execSchemes = drawing.getDrawingsToExecSchemes();
            execSchemes.add(executiveSchemesData);
            drawing.setDrawingsToExecSchemes(execSchemes);
        }
        executiveSchemesData.setExecSchemesToMaterials(materialsUsed);
        for (MaterialsUsedData material : materialsUsed) {
            Set<ExecutiveSchemesData> execSchemes = material.getMaterialsToExecSchemes();
            execSchemes.add(executiveSchemesData);
            material.setMaterialsToExecSchemes(execSchemes);
        }
        executiveSchemesData.setItdToExecutiveSchemesData(itdData);
        executiveSchemesData.setStatus("Требует создания");
        executiveSchemesRepository.save(executiveSchemesData);
        return "redirect:/executive-schemes-table/" + id;
    }

    @GetMapping("/executive-schemes-table/{itdId}/executive-schemes-edit/{execSchemId}")
    public String executiveSchemesEdit(@PathVariable(value = "itdId") long itdId,
                                       @PathVariable(value = "execSchemId") long execSchemId,
                                       Model model, Principal principal) {
        modelAddUserAndItdData(principal, itdId, model, userRepository, itdRepository);
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        if (!executiveSchemesRepository.existsById(execSchemId)) {
            return "redirect:/executive-schemes-table/" + itdId;
        }
        Optional<ExecutiveSchemesData> executiveSchemesDataOptional = executiveSchemesRepository.findById(execSchemId);
        if (executiveSchemesDataOptional.isPresent()) {
            ExecutiveSchemesData executiveSchemesData = executiveSchemesDataOptional.get();
            Set<Long> drawingIds = executiveSchemesData.getExecSchemesToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
            model.addAttribute("drawingIds", drawingIds);
            model.addAttribute("executiveSchemesData", executiveSchemesData);
            List<MaterialsUsedData> selectedMaterials = executiveSchemesData.getExecSchemesToMaterials().stream().toList();
            model.addAttribute("selectedMaterialsList", selectedMaterials);
        }
        populateModel(model, itdData);
        return "executiveSchemesEdit";
    }

    @PostMapping("/executive-schemes-table/{itdId}/executive-schemes-edit/{execSchemId}")
    @Transactional
    public String postExecutiveSchemesUpdate(@PathVariable(value = "itdId") long itdId,
                                             @PathVariable(value = "execSchemId") long execSchemId,
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
                                             @RequestParam Map<String, String> formData) {
        ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();

        for (DrawingsData drawing : executiveSchemesData.getExecSchemesToDrawings()) {
            drawing.getDrawingsToExecSchemes().remove(executiveSchemesData);
            drawingsRepository.save(drawing);
        }
        executiveSchemesData.getExecSchemesToDrawings().clear();
        for (MaterialsUsedData material : executiveSchemesData.getExecSchemesToMaterials()) {
            material.getMaterialsToExecSchemes().remove(executiveSchemesData);
            materialsUsedRepository.save(material);
        }
        executiveSchemesData.getExecSchemesToMaterials().clear();

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
            if (key.startsWith("selectedMaterials")) {
                Long materialId = Long.parseLong(key.substring("selectedMaterials".length()));
                MaterialsUsedData material = materialsUsedRepository.findById(materialId).orElseThrow();
                material.getMaterialsToExecSchemes().add(executiveSchemesData);
                executiveSchemesData.getExecSchemesToMaterials().add(material);
                materialsUsedRepository.save(material);
            }
        });

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
        return "redirect:/executive-schemes-table/" + itdId;
    }


    @PostMapping("/executive-schemes-table/{itdId}/executive-schemes-remove/{execSchemId}")
    @Transactional
    public String postExecutiveSchemesDelete(@PathVariable(value = "itdId") long itdId,
                                             @PathVariable(value = "execSchemId") long execSchemId,
                                             Model model) {
        ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();

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

        return "redirect:/executive-schemes-table/" + itdId;
    }

    @PostMapping("/executive-schemes-table/{itdId}/update-status/{execSchemId}")
    public String updateStatus(@PathVariable(value = "itdId") long itdId,
                               @PathVariable(value = "execSchemId") long execSchemId,
                               @RequestParam("status") String status) {
        ExecutiveSchemesData execSchem = executiveSchemesRepository.findById(execSchemId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + execSchemId));
        execSchem.setStatus(status);
        executiveSchemesRepository.save(execSchem);
        return "redirect:/executive-schemes-table/" + itdId;
    }
}
