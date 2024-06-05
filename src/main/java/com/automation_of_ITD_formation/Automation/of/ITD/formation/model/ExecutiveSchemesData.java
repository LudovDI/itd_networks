package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ExecutiveSchemesData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameScheme, section, startCoordinateX, startCoordinateY, endCoordinateX, endCoordinateY, wellCenterX, 
            wellCenterY, soilDevelopment, sandyBase, shoofing, backfillingShoofing, sand, priming, layingPipeline,
            layingPipelineGNB, projectSection, startingPoint, endingPoint, status;

    @ManyToMany
    @JoinTable(
            name = "exec_schemes_drawing",
            joinColumns = @JoinColumn(name = "exec_schemes_id"),
            inverseJoinColumns = @JoinColumn(name = "drawing_id")
    )
    private Set<DrawingsData> execSchemesToDrawings = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "exec_schemes_material",
            joinColumns = @JoinColumn(name = "exec_schemes_id"),
            inverseJoinColumns = @JoinColumn(name = "material_id")
    )
    private Set<MaterialsUsedData> execSchemesToMaterials = new HashSet<>();

    @OneToMany(mappedBy = "executiveSchemesData", cascade = CascadeType.ALL)
    private Set<AosrData> aosrs;

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

    public Set<AosrData> getAosrs() {
        return aosrs;
    }

    public void setAosrs(Set<AosrData> aosrs) {
        this.aosrs = aosrs;
    }

    public void setExecSchemesToAosr(Set<AosrData> execSchemesToAosr) {
        this.aosrs = execSchemesToAosr;
    }

    public Set<MaterialsUsedData> getExecSchemesToMaterials() {
        return execSchemesToMaterials;
    }

    public void setExecSchemesToMaterials(Set<MaterialsUsedData> execSchemesToMaterials) {
        this.execSchemesToMaterials = execSchemesToMaterials;
    }

    public Set<DrawingsData> getExecSchemesToDrawings() {
        return execSchemesToDrawings;
    }

    public void setExecSchemesToDrawings(Set<DrawingsData> execSchemesToDrawings) {
        this.execSchemesToDrawings = execSchemesToDrawings;
    }

    public String getNameScheme() {
        return nameScheme;
    }

    public void setNameScheme(String nameScheme) {
        this.nameScheme = nameScheme;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getStartCoordinateX() {
        return startCoordinateX;
    }

    public void setStartCoordinateX(String startCoordinateX) {
        this.startCoordinateX = startCoordinateX;
    }

    public String getStartCoordinateY() {
        return startCoordinateY;
    }

    public void setStartCoordinateY(String startCoordinateY) {
        this.startCoordinateY = startCoordinateY;
    }

    public String getEndCoordinateX() {
        return endCoordinateX;
    }

    public void setEndCoordinateX(String endCoordinateX) {
        this.endCoordinateX = endCoordinateX;
    }

    public String getEndCoordinateY() {
        return endCoordinateY;
    }

    public void setEndCoordinateY(String endCoordinateY) {
        this.endCoordinateY = endCoordinateY;
    }

    public String getWellCenterX() {
        return wellCenterX;
    }

    public void setWellCenterX(String wellCenterX) {
        this.wellCenterX = wellCenterX;
    }

    public String getWellCenterY() {
        return wellCenterY;
    }

    public void setWellCenterY(String wellCenterY) {
        this.wellCenterY = wellCenterY;
    }

    public String getSoilDevelopment() {
        return soilDevelopment;
    }

    public void setSoilDevelopment(String soilDevelopment) {
        this.soilDevelopment = soilDevelopment;
    }

    public String getSandyBase() {
        return sandyBase;
    }

    public void setSandyBase(String sandyBase) {
        this.sandyBase = sandyBase;
    }

    public String getShoofing() {
        return shoofing;
    }

    public void setShoofing(String shoofing) {
        this.shoofing = shoofing;
    }

    public String getBackfillingShoofing() {
        return backfillingShoofing;
    }

    public void setBackfillingShoofing(String backfillingShoofing) {
        this.backfillingShoofing = backfillingShoofing;
    }

    public String getSand() {
        return sand;
    }

    public void setSand(String sand) {
        this.sand = sand;
    }

    public String getPriming() {
        return priming;
    }

    public void setPriming(String priming) {
        this.priming = priming;
    }

    public String getLayingPipeline() {
        return layingPipeline;
    }

    public void setLayingPipeline(String layingPipeline) {
        this.layingPipeline = layingPipeline;
    }

    public String getLayingPipelineGNB() {
        return layingPipelineGNB;
    }

    public void setLayingPipelineGNB(String layingPipelineGNB) {
        this.layingPipelineGNB = layingPipelineGNB;
    }

    public String getProjectSection() {
        return projectSection;
    }

    public void setProjectSection(String projectSection) {
        this.projectSection = projectSection;
    }

    public String getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(String startingPoint) {
        this.startingPoint = startingPoint;
    }

    public String getEndingPoint() {
        return endingPoint;
    }

    public void setEndingPoint(String endingPoint) {
        this.endingPoint = endingPoint;
    }

    public ExecutiveSchemesData() {
    }

    public ExecutiveSchemesData(String nameScheme, String section, String startCoordinateX, String startCoordinateY, String endCoordinateX, String endCoordinateY, String wellCenterX, String wellCenterY, String soilDevelopment, String sandyBase, String shoofing, String backfillingShoofing, String sand, String priming, String layingPipeline, String layingPipelineGNB, String projectSection, String startingPoint, String endingPoint) {
        this.nameScheme = nameScheme;
        this.section = section;
        this.startCoordinateX = startCoordinateX;
        this.startCoordinateY = startCoordinateY;
        this.endCoordinateX = endCoordinateX;
        this.endCoordinateY = endCoordinateY;
        this.wellCenterX = wellCenterX;
        this.wellCenterY = wellCenterY;
        this.soilDevelopment = soilDevelopment;
        this.sandyBase = sandyBase;
        this.shoofing = shoofing;
        this.backfillingShoofing = backfillingShoofing;
        this.sand = sand;
        this.priming = priming;
        this.layingPipeline = layingPipeline;
        this.layingPipelineGNB = layingPipelineGNB;
        this.projectSection = projectSection;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
    }
}
