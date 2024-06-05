package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.DrawingsData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface DrawingsRepository extends CrudRepository<DrawingsData, Long> {
    List<DrawingsData> findByProjectSection(String projectSection);
}
