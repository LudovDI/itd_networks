package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ItdData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ItdRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItdRepository itdRepository;

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        String username = principal.getName();

        UserData currentUser = userRepository.findByUsername(username).orElseThrow();

        model.addAttribute("userData", currentUser);

        List<ItdData> itdDataList = currentUser.getItdData();

        if (!itdDataList.isEmpty()) {
            for (ItdData itd : itdDataList) {
                itd.setFormattedDateTime(Integer.toString(itd.getCreatedDate().getYear()));
            }
            model.addAttribute("itdList", itdDataList);
        } else {
            model.addAttribute("itdList", List.of());
        }

        return "index";
    }

    @GetMapping("/index/{id}/itd-info")
    public String itdInfo(@PathVariable(value = "id") long id, Principal principal, Model model) {
        String username = principal.getName();

        UserData currentUser = userRepository.findByUsername(username).orElseThrow();
        model.addAttribute("userData", currentUser);
        userRepository.save(currentUser);

        ItdData itdData = itdRepository.findById(id).orElseThrow();

        model.addAttribute("itdData", itdData);

        return "itdInfo";
    }

    @PostMapping("/index/{id}/itd-remove")
    @Transactional
    public String postItdDelete(@PathVariable(value = "id") long id) {
        ItdData itdData = itdRepository.findById(id).orElseThrow();
        itdRepository.delete(itdData);
        return "redirect:/index";
    }
}
