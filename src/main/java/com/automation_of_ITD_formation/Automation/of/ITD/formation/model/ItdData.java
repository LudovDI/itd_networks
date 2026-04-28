package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class ItdData extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, status;

    @Transient
    private String formattedDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userData;

    @OneToOne(mappedBy = "itdData", cascade = CascadeType.ALL)
    private PassportObjectData passportObjectData;

    @OneToMany(mappedBy = "itdToProjectDocumentationData", cascade = CascadeType.ALL)
    private Set<ProjectDocumentationData> projectDocumentationData = new HashSet<>();

    @OneToMany(mappedBy = "itdToMaterialsUsedData", cascade = CascadeType.ALL)
    private Set<MaterialsUsedData> materialsUsedData = new HashSet<>();

    @OneToMany(mappedBy = "itdToExecutiveSchemesData", cascade = CascadeType.ALL)
    private Set<ExecutiveSchemesData> executiveSchemesData = new HashSet<>();

    @OneToMany(mappedBy = "itdToActsViCData", cascade = CascadeType.ALL)
    private Set<ActsViCData> actsViCData = new HashSet<>();

    @OneToMany(mappedBy = "itdToProtocolsGnbData", cascade = CascadeType.ALL)
    private Set<ProtocolsGnbData> protocolsGnbData = new HashSet<>();

    @OneToMany(mappedBy = "itdToSealingProtocolsData", cascade = CascadeType.ALL)
    private Set<SealingProtocolsData> sealingProtocolsData = new HashSet<>();

    @OneToMany(mappedBy = "itdToAosrData", cascade = CascadeType.ALL)
    private Set<AosrData> aosrData = new HashSet<>();

    @OneToMany(mappedBy = "itdToNameWorksData", cascade = CascadeType.ALL)
    private Set<NameWorksData> nameWorksData = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFormattedDateTime() {
        return formattedDateTime;
    }

    public void setFormattedDateTime(String formattedDateTime) {
        this.formattedDateTime = formattedDateTime;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PassportObjectData getPassportObjectData() {
        return passportObjectData;
    }

    public void setPassportObjectData(PassportObjectData passportObjectData) {
        this.passportObjectData = passportObjectData;
    }

    public Set<ProjectDocumentationData> getProjectDocumentationData() {
        return projectDocumentationData;
    }

    public void setProjectDocumentationData(Set<ProjectDocumentationData> projectDocumentationData) {
        this.projectDocumentationData = projectDocumentationData;
    }

    public Set<MaterialsUsedData> getMaterialsUsedData() {
        return materialsUsedData;
    }

    public void setMaterialsUsedData(Set<MaterialsUsedData> materialsUsedData) {
        this.materialsUsedData = materialsUsedData;
    }

    public Set<ExecutiveSchemesData> getExecutiveSchemesData() {
        return executiveSchemesData;
    }

    public void setExecutiveSchemesData(Set<ExecutiveSchemesData> executiveSchemesData) {
        this.executiveSchemesData = executiveSchemesData;
    }

    public Set<ActsViCData> getActsViCData() {
        return actsViCData;
    }

    public void setActsViCData(Set<ActsViCData> actsViCData) {
        this.actsViCData = actsViCData;
    }

    public Set<ProtocolsGnbData> getProtocolsGnbData() {
        return protocolsGnbData;
    }

    public void setProtocolsGnbData(Set<ProtocolsGnbData> protocolsGnbData) {
        this.protocolsGnbData = protocolsGnbData;
    }

    public Set<SealingProtocolsData> getSealingProtocolsData() {
        return sealingProtocolsData;
    }

    public void setSealingProtocolsData(Set<SealingProtocolsData> sealingProtocolsData) {
        this.sealingProtocolsData = sealingProtocolsData;
    }

    public Set<AosrData> getAosrData() {
        return aosrData;
    }

    public void setAosrData(Set<AosrData> aosrData) {
        this.aosrData = aosrData;
    }

    public Set<NameWorksData> getNameWorksData() {
        return nameWorksData;
    }

    public void setNameWorksData(Set<NameWorksData> nameWorksData) {
        this.nameWorksData = nameWorksData;
    }

    public ItdData() {
    }
}
