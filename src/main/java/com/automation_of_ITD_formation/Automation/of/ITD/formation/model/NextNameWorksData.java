package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class NextNameWorksData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "nextNameWorksToAosr", cascade = CascadeType.ALL)
    private List<AosrData> aosrList;

    @ManyToOne
    @JoinColumn(name = "name_works_id")
    private NameWorksData nameWorksNextNameWorks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NameWorksData getNameWorksNextNameWorks() {
        return nameWorksNextNameWorks;
    }

    public void setNameWorksNextNameWorks(NameWorksData nameWorksNextNameWorks) {
        this.nameWorksNextNameWorks = nameWorksNextNameWorks;
    }

    public List<AosrData> getAosrList() {
        return aosrList;
    }

    public void setAosrList(List<AosrData> aosrList) {
        this.aosrList = aosrList;
    }

    public NextNameWorksData() {
    }

    public NextNameWorksData(String name) {
        this.name = name;
    }
}
