package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ItdData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ItdRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;

import java.security.Principal;
import java.util.List;

public class ControllersUtils {
    public static void modelAddUserAndItdData(Principal principal, Long itdId, Model model, UserRepository userRepository, ItdRepository itdRepository) {
        String username = principal.getName();
        UserData currentUser  = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        model.addAttribute("userData", currentUser );

        List<ItdData> itdDataList = currentUser .getItdData();
        model.addAttribute("itdList", itdDataList.isEmpty() ? List.of() : itdDataList);

        ItdData itdData = itdRepository.findById(itdId).orElseThrow();
        model.addAttribute("itdData", itdData);
    }
}
