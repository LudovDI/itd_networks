package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ItdData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItdRepository extends CrudRepository<ItdData, Long> {
    long countByUserData(UserData userData);
}
