package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ActsViCData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.PassportObjectData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PassportObjectRepository extends CrudRepository<PassportObjectData, Long> {
    PassportObjectData findByItdData_UserData(UserData userData);
}

