package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.MaterialsUsedData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MaterialsUsedRepository extends CrudRepository<MaterialsUsedData, Long> {
    List<MaterialsUsedData> findByNameMaterialContainingIgnoreCase(String nameMaterial);
}
