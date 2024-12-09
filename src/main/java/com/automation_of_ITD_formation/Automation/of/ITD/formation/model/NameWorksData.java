package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class NameWorksData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, status;

    @OneToMany(mappedBy = "nameWorksToNormative", cascade = CascadeType.ALL)
    private List<NormativeData> normativeList;

    @OneToMany(mappedBy = "nameWorksToAosr", cascade = CascadeType.ALL)
    private List<AosrData> aosrList;

    @OneToMany(mappedBy = "nameWorksNextNameWorks", cascade = CascadeType.ALL)
    private List<NextNameWorksData> nextNameWorksList;

    @ManyToOne
    @JoinColumn(name = "itd_id")
    private ItdData itdToNameWorksData;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AosrData> getAosrList() {
        return aosrList;
    }

    public void setAosrList(List<AosrData> aosrList) {
        this.aosrList = aosrList;
    }

    public ItdData getItdToNameWorksData() {
        return itdToNameWorksData;
    }

    public void setItdToNameWorksData(ItdData itdToNameWorksData) {
        this.itdToNameWorksData = itdToNameWorksData;
    }

    public List<NormativeData> getNormativeList() {
        return normativeList;
    }

    public void setNormativeList(List<NormativeData> normativeList) {
        this.normativeList = normativeList;
    }

    public List<NextNameWorksData> getNextNameWorksList() {
        return nextNameWorksList;
    }

    public void setNextNameWorksList(List<NextNameWorksData> nextNameWorksList) {
        this.nextNameWorksList = nextNameWorksList;
    }

    public NameWorksData() {
    }

    public NameWorksData(String nameWorks) {
        this.name = nameWorks;
    }
}
