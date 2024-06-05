package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserDocumentsData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDocumentsRepository extends CrudRepository<UserDocumentsData, Long> {
    Optional<UserDocumentsData> findByUserData(UserData userData);
}
