package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class DrawingsData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String drawing;

    @ManyToOne
    @JoinColumn(name = "project_documentation_id")
    private ProjectDocumentationData projDocToDrawings;

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

    public ProjectDocumentationData getProjDocToDrawings() {
        return projDocToDrawings;
    }

    public void setProjDocToDrawings(ProjectDocumentationData projDocToDrawings) {
        this.projDocToDrawings = projDocToDrawings;
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

    public DrawingsData() {
    }

    public DrawingsData(String drawing) {
        this.drawing = drawing;
    }
}
