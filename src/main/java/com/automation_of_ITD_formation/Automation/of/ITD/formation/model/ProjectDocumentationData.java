package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class ProjectDocumentationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String projectSection, network, status;

    @OneToMany(mappedBy = "projDocToDrawings", cascade = CascadeType.ALL)
    private List<DrawingsData> drawingsList;

    @OneToMany(mappedBy = "projectDocumentationData", cascade = CascadeType.ALL)
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

    public String getProjectSection() {
        return projectSection;
    }

    public void setProjectSection(String projectSection) {
        this.projectSection = projectSection;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public List<DrawingsData> getDrawingsList() {
        return drawingsList;
    }

    public void setDrawingsList(List<DrawingsData> drawingsList) {
        this.drawingsList = drawingsList;
    }

    public ProjectDocumentationData() {
        this.drawingsList = new ArrayList<>();
    }

    public ProjectDocumentationData(String projectSection, String network) {
        this.projectSection = projectSection;
        this.network = network;
    }
}
