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
}
