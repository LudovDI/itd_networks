package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.DrawingsRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ItdRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ProjectDocumentationRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
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
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class ProjectDocumentationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ItdRepository itdRepository;
    @Autowired
    private ProjectDocumentationRepository projectDocumentationRepository;
    @Autowired
    private DrawingsRepository drawingsRepository;

    @GetMapping("/project-documentation-table/{id}")
    public String projectDocumentationTable(@PathVariable(value = "id") long id, Model model, Principal principal) {
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
        Set<ProjectDocumentationData> projectDocumentationList = itdData.getProjectDocumentationData();
        model.addAttribute("projectDocumentationList", projectDocumentationList);
        return "projectDocumentationTable";
    }

    @GetMapping("/project-documentation-add/{id}")
    public String projectDocumentationAdd(@PathVariable(value = "id") long id, Model model, Principal principal) {
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
        return "projectDocumentationAdd";
    }

    @PostMapping("/project-documentation-add/{itdId}")
    public String postProjectDocumentationAdd(@PathVariable(value = "itdId") long itdId,
                                              @RequestParam("projectSection") String projectSection,
                                              @RequestParam("network") String network,
                                              @RequestParam Map<String, String> formData) {
        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        ProjectDocumentationData projectDocumentationData = new ProjectDocumentationData(projectSection, network);
        List<DrawingsData> drawingsForSection = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("drawing")) {
                DrawingsData drawings = new DrawingsData(value);
                drawings.setProjDocToDrawings(projectDocumentationData);
                drawingsForSection.add(drawings);
            }
        });
        projectDocumentationData.setStatus("Требует создания");
        projectDocumentationData.setItdToProjectDocumentationData(itdData);
        projectDocumentationRepository.save(projectDocumentationData);
        drawingsRepository.saveAll(drawingsForSection);
        return "redirect:/project-documentation-table/" + itdId;
    }

    @GetMapping("/project-documentation-table/{itdId}/project-documentation-edit/{projDocId}")
    public String projectDocumentationEdit(@PathVariable(value = "itdId") long itdId,
                                           @PathVariable(value = "projDocId") long projDocId,
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
        if (!projectDocumentationRepository.existsById(projDocId)) {
            return "redirect:/project-documentation-table";
        }
        Optional<ProjectDocumentationData> projectDocumentationDataOptional = projectDocumentationRepository.findById(projDocId);
        ArrayList<ProjectDocumentationData> res = new ArrayList<>();
        projectDocumentationDataOptional.ifPresent(res::add);
        Iterable<DrawingsData> drawingsList = res.getFirst().getDrawingsList();
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("projectDocumentationDataOptional", res);
        return "projectDocumentationEdit";
    }

    @PostMapping("/project-documentation-table/{itdId}/project-documentation-edit/{projDocId}")
    @Transactional
    public String postProjectDocumentationUpdate(@PathVariable(value = "itdId") long itdId,
                                                 @PathVariable(value = "projDocId") long projDocId,
                                                 @RequestParam("projectSection") String projectSection,
                                                 @RequestParam("network") String network,
                                                 @RequestParam Map<String, String> formData) {
        ProjectDocumentationData projectDocumentationData = projectDocumentationRepository.findById(projDocId).orElseThrow();

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

        return "redirect:/project-documentation-table/" + itdId;
    }

    @PostMapping("/project-documentation-table/{itdId}/project-documentation-remove/{projDocId}")
    public String postProjectDocumentationDelete(@PathVariable(value = "itdId") long itdId, @PathVariable(value = "projDocId") long projDocId, Model model) {
        ProjectDocumentationData projectDocumentationData = projectDocumentationRepository.findById(projDocId).orElseThrow();
        List<DrawingsData> drawingsToDelete = projectDocumentationData.getDrawingsList();
        if (!drawingsToDelete.isEmpty()) {
            drawingsRepository.deleteAll(drawingsToDelete);
        }
        projectDocumentationRepository.delete(projectDocumentationData);;
        return "redirect:/project-documentation-table/" + itdId;
    }

    @PostMapping("/project-documentation-table/{itdId}/update-status/{projDocId}")
    public String updateStatus(@PathVariable("itdId") Long itdId, @PathVariable("projDocId") Long projDocId, @RequestParam("status") String status) {
        ProjectDocumentationData projDoc = projectDocumentationRepository.findById(projDocId).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + projDocId));
        projDoc.setStatus(status);
        projectDocumentationRepository.save(projDoc);
        return "redirect:/project-documentation-table/" + itdId;
    }
}
