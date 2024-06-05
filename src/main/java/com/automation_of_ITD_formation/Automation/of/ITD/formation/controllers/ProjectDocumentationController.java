package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.DrawingsRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ProjectDocumentationRepository;
import com.google.common.collect.Lists;
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
    public String postProjectDocumentationAdd(@RequestParam("projectSection1") String projectSection1,
                                              @RequestParam("projectSection2") String projectSection2,
                                              @RequestParam("network") String network,
                                              @RequestParam Map<String, String> formData) {
        ProjectDocumentationData projectDocumentationData = new ProjectDocumentationData(projectSection1, projectSection2, network);
        List<DrawingsData> drawingsForSection1 = new ArrayList<>();
        List<DrawingsData> drawingsForSection2 = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("drawing1_")) {
                DrawingsData drawings1 = new DrawingsData(value);
                drawings1.setProjectSection(projectSection1);
                drawingsForSection1.add(drawings1);
            }
            if (key.startsWith("drawing2_")) {
                DrawingsData drawings2 = new DrawingsData(value);
                drawings2.setProjectSection(projectSection2);
                drawingsForSection2.add(drawings2);
            }
        });
        projectDocumentationRepository.save(projectDocumentationData);
        drawingsForSection1.forEach(drawingsData -> drawingsData.setProjectSection1(projectDocumentationData));
        drawingsRepository.saveAll(drawingsForSection1);
        drawingsForSection2.forEach(drawingsData -> drawingsData.setProjectSection2(projectDocumentationData));
        drawingsRepository.saveAll(drawingsForSection2);
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
        Iterable<DrawingsData> drawingsList = drawingsRepository.findAll();
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("projectDocumentationDataOptional", res);
        return "projectDocumentationEdit";
    }

    @PostMapping("/project-documentation-table/{id}/project-documentation-edit")
    @Transactional
    public String postProjectDocumentationUpdate(@PathVariable(value = "id") long id,
                                                 @RequestParam("projectSection1") String projectSection1,
                                                 @RequestParam("projectSection2") String projectSection2,
                                                 @RequestParam("network") String network,
                                                 @RequestParam Map<String, String> formData) {
        ProjectDocumentationData projectDocumentationData = projectDocumentationRepository.findById(id).orElseThrow();

        List<DrawingsData> drawingsForSection1 = projectDocumentationData.getDrawingsForSection1();
        List<DrawingsData> drawingsForSection2 = projectDocumentationData.getDrawingsForSection2();

        formData.forEach((key, value) -> {
            if (key.startsWith("drawing1_")) {
                Long drawingId = Long.parseLong(key.substring("drawing1_".length()));
                DrawingsData drawing = drawingsRepository.findById(drawingId).orElse(null);
                if (drawing != null) {
                    drawing.setDrawing(value);
                    drawing.setProjectSection(projectSection1);
                } else {
                    drawing = new DrawingsData();
                    drawing.setId(drawingId);
                    drawing.setDrawing(value);
                    drawing.setProjectSection(projectSection1);
                    drawingsForSection1.add(drawing);
                }
            } else if (key.startsWith("drawing2_")) {
                Long drawingId = Long.parseLong(key.substring("drawing2_".length()));
                DrawingsData drawing = drawingsRepository.findById(drawingId).orElse(null);
                if (drawing != null) {
                    drawing.setDrawing(value);
                    drawing.setProjectSection(projectSection2);
                } else {
                    drawing = new DrawingsData();
                    drawing.setId(drawingId);
                    drawing.setDrawing(value);
                    drawing.setProjectSection(projectSection2);
                    drawingsForSection2.add(drawing);
                }
            }
        });

        List<DrawingsData> newDocuments1 = drawingsForSection1.stream()
                .filter(doc -> doc.getId() == null)
                .collect(Collectors.toList());

        newDocuments1.forEach(doc -> entityManager.persist(doc));

        List<DrawingsData> newDocuments2 = drawingsForSection2.stream()
                .filter(doc -> doc.getId() == null)
                .collect(Collectors.toList());

        newDocuments2.forEach(doc -> entityManager.persist(doc));

        drawingsRepository.saveAll(newDocuments1);

        List<DrawingsData> documents1ToRemove = new ArrayList<>();
        drawingsForSection1.forEach(doc -> {
            if (!formData.containsKey("drawing1_" + doc.getId())) {
                documents1ToRemove.add(doc);
            }
        });
        drawingsForSection1.removeAll(documents1ToRemove);
        drawingsRepository.deleteAll(documents1ToRemove);

        drawingsRepository.saveAll(newDocuments2);

        List<DrawingsData> documents2ToRemove = new ArrayList<>();
        drawingsForSection2.forEach(doc -> {
            if (!formData.containsKey("drawing2_" + doc.getId())) {
                documents2ToRemove.add(doc);
            }
        });
        drawingsForSection2.removeAll(documents2ToRemove);
        drawingsRepository.deleteAll(documents2ToRemove);

        projectDocumentationData.setProjectSection1(projectSection1);
        projectDocumentationData.setProjectSection2(projectSection2);
        projectDocumentationData.setNetwork(network);
        projectDocumentationRepository.save(projectDocumentationData);

        return "redirect:/project-documentation-table";
    }

    @PostMapping("/project-documentation-table/{id}/project-documentation-remove")
    public String postProjectDocumentationDelete(@PathVariable(value = "id") long id, Model model) {
        ProjectDocumentationData projectDocumentationData = projectDocumentationRepository.findById(id).orElseThrow();
        List<DrawingsData> drawings1ToDelete = projectDocumentationData.getDrawingsForSection1();
        if (!drawings1ToDelete.isEmpty()) {
            drawingsRepository.deleteAll(drawings1ToDelete);
        }
        List<DrawingsData> drawings2ToDelete = projectDocumentationData.getDrawingsForSection2();
        if (!drawings2ToDelete.isEmpty()) {
            drawingsRepository.deleteAll(drawings2ToDelete);
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
