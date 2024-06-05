package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DrawingsData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String drawing, projectSection;

    @ManyToOne
    @JoinColumn(name = "project_section1_id")
    private ProjectDocumentationData projectSection1;

    @ManyToOne
    @JoinColumn(name = "project_section2_id")
    private ProjectDocumentationData projectSection2;

    @ManyToMany(mappedBy = "execSchemesToDrawings")
    private Set<ExecutiveSchemesData> drawingsToExecSchemes = new HashSet<>();

    @ManyToMany(mappedBy = "aosrToDrawings")
    private Set<AosrData> drawingsToAosr = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProjectDocumentationData getProjectSection1() {
        return projectSection1;
    }

    public void setProjectSection1(ProjectDocumentationData projectSection1) {
        this.projectSection1 = projectSection1;
    }

    public ProjectDocumentationData getProjectSection2() {
        return projectSection2;
    }

    public void setProjectSection2(ProjectDocumentationData projectSection2) {
        this.projectSection2 = projectSection2;
    }

    public Set<AosrData> getDrawingsToAosr() {
        return drawingsToAosr;
    }

    public void setDrawingsToAosr(Set<AosrData> drawingsToAosr) {
        this.drawingsToAosr = drawingsToAosr;
    }

    public Set<ExecutiveSchemesData> getDrawingsToExecSchemes() {
        return drawingsToExecSchemes;
    }

    public void setDrawingsToExecSchemes(Set<ExecutiveSchemesData> drawingsToExecSchemes) {
        this.drawingsToExecSchemes = drawingsToExecSchemes;
    }

    public String getDrawing() {
        return drawing;
    }

    public void setDrawing(String drawing) {
        this.drawing = drawing;
    }

    public String getProjectSection() {
        return projectSection;
    }

    public void setProjectSection(String projectSection) {
        this.projectSection = projectSection;
    }

    public DrawingsData() {
    }

    public DrawingsData(String drawing) {
        this.drawing = drawing;
    }
}
