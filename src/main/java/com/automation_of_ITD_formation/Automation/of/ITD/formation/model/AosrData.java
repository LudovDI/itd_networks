package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class AosrData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String typeOfWork, permittedFollowingWork, status;
    private LocalDate startDate, endDate;

    @ManyToMany
    @JoinTable(
            name = "aosr_material",
            joinColumns = @JoinColumn(name = "aosr_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private Set<MaterialsUsedData> aosrToMaterials = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "exec_scheme_id")
    private ExecutiveSchemesData executiveSchemesData;

    @ManyToMany
    @JoinTable(
            name = "aosr_drawings",
            joinColumns = @JoinColumn(name = "aosr_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id")
    )
    private Set<DrawingsData> aosrToDrawings = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "project_documentation_id")
    private ProjectDocumentationData projectDocumentationData;

    @ManyToOne
    @JoinColumn(name = "passport_object_id")
    private PassportObjectData passportObjectData;

    @ManyToOne
    @JoinColumn(name = "excavation_log_id")
    private ExcavationLogData excavationLogData;

    @ManyToOne
    @JoinColumn(name = "general_works_log_id")
    private GeneralWorksLogData generalWorksLogData;

    @OneToMany(mappedBy = "actsToAosrData", cascade = CascadeType.ALL)
    private Set<ActsViCData> actsViCData;

    @OneToOne(mappedBy = "sealingToAosrData", cascade = CascadeType.ALL)
    private SealingProtocolsData sealingProtocolsData;

    @OneToOne(mappedBy = "gnbToAosrData", cascade = CascadeType.ALL)
    private ProtocolsGnbData protocolsGnbData;

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

    public ProtocolsGnbData getProtocolsGnbData() {
        return protocolsGnbData;
    }

    public void setProtocolsGnbData(ProtocolsGnbData protocolsGnbData) {
        this.protocolsGnbData = protocolsGnbData;
    }

    public SealingProtocolsData getSealingProtocolsData() {
        return sealingProtocolsData;
    }

    public void setSealingProtocolsData(SealingProtocolsData sealingProtocolsData) {
        this.sealingProtocolsData = sealingProtocolsData;
    }

    public Set<ActsViCData> getActsViCData() {
        return actsViCData;
    }

    public void setActsViCData(Set<ActsViCData> actsViCData) {
        this.actsViCData = actsViCData;
    }

    public PassportObjectData getPassportObjectData() {
        return passportObjectData;
    }

    public void setPassportObjectData(PassportObjectData passportObjectData) {
        this.passportObjectData = passportObjectData;
    }

    public String getPermittedFollowingWork() {
        return permittedFollowingWork;
    }

    public void setPermittedFollowingWork(String permittedFollowingWork) {
        this.permittedFollowingWork = permittedFollowingWork;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTypeOfWork() {
        return typeOfWork;
    }

    public void setTypeOfWork(String typeOfWork) {
        this.typeOfWork = typeOfWork;
    }

    public Set<DrawingsData> getAosrToDrawings() {
        return aosrToDrawings;
    }

    public void setAosrToDrawings(Set<DrawingsData> aosrToDrawings) {
        this.aosrToDrawings = aosrToDrawings;
    }

    public Set<MaterialsUsedData> getAosrToMaterials() {
        return aosrToMaterials;
    }

    public void setAosrToMaterials(Set<MaterialsUsedData> aosrToMaterials) {
        this.aosrToMaterials = aosrToMaterials;
    }

    public ProjectDocumentationData getProjectDocumentationData() {
        return projectDocumentationData;
    }

    public void setProjectDocumentationData(ProjectDocumentationData projectDocumentationData) {
        this.projectDocumentationData = projectDocumentationData;
    }

    public ExecutiveSchemesData getExecutiveSchemesData() {
        return executiveSchemesData;
    }

    public void setExecutiveSchemesData(ExecutiveSchemesData executiveSchemesData) {
        this.executiveSchemesData = executiveSchemesData;
    }

    public ExcavationLogData getExcavationLogData() {
        return excavationLogData;
    }

    public void setExcavationLogData(ExcavationLogData excavationLogData) {
        this.excavationLogData = excavationLogData;
    }

    public GeneralWorksLogData getGeneralWorksLogData() {
        return generalWorksLogData;
    }

    public void setGeneralWorksLogData(GeneralWorksLogData generalWorksLogData) {
        this.generalWorksLogData = generalWorksLogData;
    }

    public AosrData() {
    }

    public AosrData(String typeOfWork, String permittedFollowingWork, LocalDate startDate, LocalDate endDate) {
        this.typeOfWork = typeOfWork;
        this.permittedFollowingWork = permittedFollowingWork;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
