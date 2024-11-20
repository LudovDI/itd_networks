package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ActsViCData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActsViCRepository extends CrudRepository<ActsViCData, Long> {
    List<ActsViCData> findByItdToActsViCData_UserData(UserData userData);
}
