package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class MaterialsUsedData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameMaterial, status;

    @OneToMany(mappedBy = "materialsUsedData", cascade = CascadeType.ALL)
    private List<AccompanyingDocumentData> accompanyingDocuments;

    @ManyToMany(mappedBy = "execSchemesToMaterials")
    private Set<ExecutiveSchemesData> materialsToExecSchemes = new HashSet<>();

    @ManyToMany(mappedBy = "aosrToMaterials")
    private Set<AosrData> materialsToAosr = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "itd_id")
    private ItdData itdToMaterialsUsedData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<ExecutiveSchemesData> getMaterialsToExecSchemes() {
        return materialsToExecSchemes;
    }

    public void setMaterialsToExecSchemes(Set<ExecutiveSchemesData> materialsToExecSchemes) {
        this.materialsToExecSchemes = materialsToExecSchemes;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    public List<AccompanyingDocumentData> getAccompanyingDocuments() {
        return accompanyingDocuments;
    }

    public void setAccompanyingDocuments(List<AccompanyingDocumentData> accompanyingDocuments) {
        this.accompanyingDocuments = accompanyingDocuments;
    }

    public Set<AosrData> getMaterialsToAosr() {
        return materialsToAosr;
    }

    public void setMaterialsToAosr(Set<AosrData> materialsToAosr) {
        this.materialsToAosr = materialsToAosr;
    }

    public ItdData getItdToMaterialsUsedData() {
        return itdToMaterialsUsedData;
    }

    public void setItdToMaterialsUsedData(ItdData itdToMaterialsUsedData) {
        this.itdToMaterialsUsedData = itdToMaterialsUsedData;
    }

    public MaterialsUsedData(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    public MaterialsUsedData() {
    }
}
