package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserDocumentsData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserDocumentsRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class MainController {
    @Autowired
    private UserDocumentsRepository userDocumentsRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/bs_index")
    public String bs_index() {
        return "bs_index";
    }

    @GetMapping("/logs")
    public String logs() {
        return "logs";
    }

    @PostMapping("/index")
    @Transactional
    public String postIndex(@RequestParam(value = "itdCheckbox", required = false) List<String> itdCheckboxes,
                                  @AuthenticationPrincipal UserDetails userDetails) {
        Optional<UserData> user = userRepository.findByUsername(userDetails.getUsername());
        Iterator<UserData> iterator = user.stream().iterator();
        Optional<UserDocumentsData> optionalDocuments;
        if (iterator.hasNext()) {
            optionalDocuments = userDocumentsRepository.findByUserData(iterator.next());
            UserDocumentsData userDocumentsData;
            if (optionalDocuments.isPresent()) {
                userDocumentsData = optionalDocuments.get();
            } else {
                userDocumentsData = new UserDocumentsData();
                userDocumentsData.setUserData(iterator.next());
            }

            Set<String> documents = new HashSet<>();
            if (itdCheckboxes != null) {
                documents.addAll(itdCheckboxes);
            }
            userDocumentsData.setDocuments(documents);

            userDocumentsRepository.save(userDocumentsData);
        }

        return "redirect:/index";
    }
}
