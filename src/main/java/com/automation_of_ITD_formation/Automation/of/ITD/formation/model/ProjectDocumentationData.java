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

    private String projectSection1, projectSection2, network, status;

    @OneToMany(mappedBy = "projectSection1", cascade = CascadeType.ALL)
    private List<DrawingsData> drawingsForSection1;

    @OneToMany(mappedBy = "projectSection2", cascade = CascadeType.ALL)
    private List<DrawingsData> drawingsForSection2;

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

    public String getProjectSection1() {
        return projectSection1;
    }

    public void setProjectSection1(String projectSection1) {
        this.projectSection1 = projectSection1;
    }

    public String getProjectSection2() {
        return projectSection2;
    }

    public void setProjectSection2(String projectSection2) {
        this.projectSection2 = projectSection2;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public List<DrawingsData> getDrawingsForSection1() {
        return drawingsForSection1;
    }

    public void setDrawingsForSection1(List<DrawingsData> drawingsForSection1) {
        this.drawingsForSection1 = drawingsForSection1;
    }

    public List<DrawingsData> getDrawingsForSection2() {
        return drawingsForSection2;
    }

    public void setDrawingsForSection2(List<DrawingsData> drawingsForSection2) {
        this.drawingsForSection2 = drawingsForSection2;
    }

    public ProjectDocumentationData() {
        this.drawingsForSection1 = new ArrayList<>();
        this.drawingsForSection2 = new ArrayList<>();
    }

    public ProjectDocumentationData(String projectSection1, String projectSection2, String network) {
        this.projectSection1 = projectSection1;
        this.projectSection2 = projectSection2;
        this.network = network;
    }
}
