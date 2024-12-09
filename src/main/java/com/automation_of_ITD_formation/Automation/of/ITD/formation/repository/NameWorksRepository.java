package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.NameWorksData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.UserData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NameWorksRepository extends CrudRepository<NameWorksData, Long> {
}
