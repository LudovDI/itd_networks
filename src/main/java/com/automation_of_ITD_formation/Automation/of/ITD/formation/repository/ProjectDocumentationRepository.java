package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ProjectDocumentationData;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProjectDocumentationRepository extends CrudRepository<ProjectDocumentationData, Long> {
    Optional<ProjectDocumentationData> findByProjectSection(String projectSection);
}
