package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.DrawingsRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ProjectDocumentationRepository;
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

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProjectDocumentationController {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProjectDocumentationRepository projectDocumentationRepository;

    @Autowired
    private DrawingsRepository drawingsRepository;

    @GetMapping("/project-documentation-table")
    public String projectDocumentationTable(Model model) {
        Iterable<ProjectDocumentationData> projectDocumentationList = projectDocumentationRepository.findAll();
        Iterable<DrawingsData> drawingsList = drawingsRepository.findAll();
        model.addAttribute("projectDocumentationList", projectDocumentationList);
        model.addAttribute("drawingsList", drawingsList);
        return "projectDocumentationTable";
    }

    @GetMapping("/project-documentation-add")
    public String projectDocumentationAdd(Model model) {
        return "projectDocumentationAdd";
    }

    @PostMapping("/project-documentation-add")
    public String postProjectDocumentationAdd(@RequestParam("projectSection") String projectSection,
                                              @RequestParam("network") String network,
                                              @RequestParam Map<String, String> formData) {
        ProjectDocumentationData projectDocumentationData = new ProjectDocumentationData(projectSection, network);
        List<DrawingsData> drawingsForSection = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("drawing")) {
                DrawingsData drawings = new DrawingsData(value);
                drawings.setProjDocToDrawings(projectDocumentationData);
                drawingsForSection.add(drawings);
            }
        });
        projectDocumentationRepository.save(projectDocumentationData);
        drawingsRepository.saveAll(drawingsForSection);
        return "redirect:/project-documentation-table";
    }

    @GetMapping("/project-documentation-table/{id}/project-documentation-edit")
    public String projectDocumentationEdit(@PathVariable(value = "id") long id, Model model) {
        if (!projectDocumentationRepository.existsById(id)) {
            return "redirect:/project-documentation-table";
        }
        Optional<ProjectDocumentationData> projectDocumentationDataOptional = projectDocumentationRepository.findById(id);
        ArrayList<ProjectDocumentationData> res = new ArrayList<>();
        projectDocumentationDataOptional.ifPresent(res::add);
        Iterable<DrawingsData> drawingsList = res.getFirst().getDrawingsList();
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("projectDocumentationDataOptional", res);
        return "projectDocumentationEdit";
    }

    @PostMapping("/project-documentation-table/{id}/project-documentation-edit")
    @Transactional
    public String postProjectDocumentationUpdate(@PathVariable(value = "id") long id,
                                                 @RequestParam("projectSection") String projectSection,
                                                 @RequestParam("network") String network,
                                                 @RequestParam Map<String, String> formData) {
        ProjectDocumentationData projectDocumentationData = projectDocumentationRepository.findById(id).orElseThrow();

        Set<Long> drawingIdsFromForm = new HashSet<>();

        formData.forEach((key, value) -> {
            if (key.startsWith("drawing")) {
                String drawingIdStr = key.substring("drawing".length());

                DrawingsData drawing;
                if (!drawingIdStr.isEmpty()) {
                    Long drawingId = Long.parseLong(drawingIdStr);
                    drawingIdsFromForm.add(drawingId);

                    drawing = drawingsRepository.findById(drawingId).orElse(null);
                    if (drawing == null) {
                        drawing = new DrawingsData();
                    }
                } else {
                    drawing = new DrawingsData();
                }

                drawing.setDrawing(value);
                drawing.setProjDocToDrawings(projectDocumentationData);
                projectDocumentationData.getDrawingsList().add(drawing);
            }
        });

        List<DrawingsData> drawingsToRemove = projectDocumentationData.getDrawingsList().stream()
                .filter(drawing -> drawing.getId() != null && !drawingIdsFromForm.contains(drawing.getId()))
                .toList();

        drawingsToRemove.forEach(drawing -> {
            projectDocumentationData.getDrawingsList().remove(drawing);
            drawingsRepository.delete(drawing);
        });


        projectDocumentationData.setProjectSection(projectSection);
        projectDocumentationData.setNetwork(network);
        projectDocumentationRepository.save(projectDocumentationData);

        return "redirect:/project-documentation-table";
    }

    @PostMapping("/project-documentation-table/{id}/project-documentation-remove")
    public String postProjectDocumentationDelete(@PathVariable(value = "id") long id, Model model) {
        ProjectDocumentationData projectDocumentationData = projectDocumentationRepository.findById(id).orElseThrow();
        List<DrawingsData> drawingsToDelete = projectDocumentationData.getDrawingsList();
        if (!drawingsToDelete.isEmpty()) {
            drawingsRepository.deleteAll(drawingsToDelete);
        }
        projectDocumentationRepository.delete(projectDocumentationData);;
        return "redirect:/project-documentation-table";
    }

    @PostMapping("/project-documentation-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        ProjectDocumentationData projDoc = projectDocumentationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        projDoc.setStatus(status);
        projectDocumentationRepository.save(projDoc);
        return "redirect:/project-documentation-table";
    }
}
