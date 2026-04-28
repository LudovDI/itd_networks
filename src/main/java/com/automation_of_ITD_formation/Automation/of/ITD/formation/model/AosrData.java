package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.time.format.DateTimeFormatter;

@Entity
public class AosrData extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String number, status;
    private LocalDate startDate, endDate;

    @Transient
    private String formattedStartDate;

    @Transient
    private String formattedEndDate;

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

    @ManyToOne
    @JoinColumn(name = "name_works_id")
    private NameWorksData nameWorksToAosr;

    @ManyToOne
    @JoinColumn(name = "next_name_works_id")
    private NextNameWorksData nextNameWorksToAosr;

    @ManyToOne
    @JoinColumn(name = "another_person_res_id")
    private AnotherPersonResponsibleData anotherPersonResponsibleData;

    @ManyToOne
    @JoinColumn(name = "customer_res_id")
    private CustomerResponsibleData customerResponsibleData;

    @ManyToOne
    @JoinColumn(name = "designer_res_id")
    private DesignerResponsibleData designerResponsibleData;

    @ManyToOne
    @JoinColumn(name = "subcustomer_res_id")
    private SubcustomerResponsibleData subcustomerResponsibleData;

    @ManyToOne
    @JoinColumn(name = "subcustomer2_res_id")
    private SubcustomerResponsible2Data subcustomerResponsible2Data;

    @ManyToMany
    @JoinTable(
            name = "aosr_drawings",
            joinColumns = @JoinColumn(name = "aosr_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id")
    )
    private Set<DrawingsData> aosrToDrawings = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "aosr_projdocs",
            joinColumns = @JoinColumn(name = "aosr_id"),
            inverseJoinColumns = @JoinColumn(name = "project_documentation_id")
    )
    private Set<ProjectDocumentationData> aosrToProjDocs = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "passport_object_id")
    private PassportObjectData passportObjectData;

    @ManyToOne
    @JoinColumn(name = "itd_id")
    private ItdData itdToAosrData;

    @OneToMany(mappedBy = "actsToAosrData", cascade = CascadeType.ALL)
    private Set<ActsViCData> actsViCData = new HashSet<>();

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public void setFormattedDates() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        this.formattedStartDate = (startDate != null) ? startDate.format(formatter) : null;
        this.formattedEndDate = (endDate != null) ? endDate.format(formatter) : null;
    }

    public String getFormattedStartDate() {
        return formattedStartDate;
    }

    public void setFormattedStartDate(String formattedStartDate) {
        this.formattedStartDate = formattedStartDate;
    }

    public String getFormattedEndDate() {
        return formattedEndDate;
    }

    public void setFormattedEndDate(String formattedEndDate) {
        this.formattedEndDate = formattedEndDate;
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

    public Set<ProjectDocumentationData> getAosrToProjDocs() {
        return aosrToProjDocs;
    }

    public void setAosrToProjDocs(Set<ProjectDocumentationData> aosrToProjDocs) {
        this.aosrToProjDocs = aosrToProjDocs;
    }

    public ExecutiveSchemesData getExecutiveSchemesData() {
        return executiveSchemesData;
    }

    public void setExecutiveSchemesData(ExecutiveSchemesData executiveSchemesData) {
        this.executiveSchemesData = executiveSchemesData;
    }


    public AnotherPersonResponsibleData getAnotherPersonResponsibleData() {
        return anotherPersonResponsibleData;
    }

    public void setAnotherPersonResponsibleData(AnotherPersonResponsibleData anotherPersonResponsibleData) {
        this.anotherPersonResponsibleData = anotherPersonResponsibleData;
    }

    public CustomerResponsibleData getCustomerResponsibleData() {
        return customerResponsibleData;
    }

    public void setCustomerResponsibleData(CustomerResponsibleData customerResponsibleData) {
        this.customerResponsibleData = customerResponsibleData;
    }

    public DesignerResponsibleData getDesignerResponsibleData() {
        return designerResponsibleData;
    }

    public void setDesignerResponsibleData(DesignerResponsibleData designerResponsibleData) {
        this.designerResponsibleData = designerResponsibleData;
    }

    public SubcustomerResponsibleData getSubcustomerResponsibleData() {
        return subcustomerResponsibleData;
    }

    public void setSubcustomerResponsibleData(SubcustomerResponsibleData subcustomerResponsibleData) {
        this.subcustomerResponsibleData = subcustomerResponsibleData;
    }

    public SubcustomerResponsible2Data getSubcustomerResponsible2Data() {
        return subcustomerResponsible2Data;
    }

    public void setSubcustomerResponsible2Data(SubcustomerResponsible2Data subcustomerResponsible2Data) {
        this.subcustomerResponsible2Data = subcustomerResponsible2Data;
    }

    public ItdData getItdToAosrData() {
        return itdToAosrData;
    }

    public void setItdToAosrData(ItdData itdToAosrData) {
        this.itdToAosrData = itdToAosrData;
    }

    public NameWorksData getNameWorksToAosr() {
        return nameWorksToAosr;
    }

    public void setNameWorksToAosr(NameWorksData nameWorksToAosr) {
        this.nameWorksToAosr = nameWorksToAosr;
    }

    public NextNameWorksData getNextNameWorksToAosr() {
        return nextNameWorksToAosr;
    }

    public void setNextNameWorksToAosr(NextNameWorksData nextNameWorksToAosr) {
        this.nextNameWorksToAosr = nextNameWorksToAosr;
    }

    public AosrData() {
    }

    public AosrData(String number, LocalDate startDate, LocalDate endDate) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
