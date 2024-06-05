package com.automation_of_ITD_formation.Automation.of.ITD.formation.repository;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.AccompanyingDocumentData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.MaterialsUsedData;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccompanyingDocumentRepository extends CrudRepository<AccompanyingDocumentData, Long> {
    List<AccompanyingDocumentData> findByMaterialsUsedData(MaterialsUsedData materialsUsedData);
}
