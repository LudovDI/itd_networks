package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

@Entity
public class NormativeData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "name_works_id")
    private NameWorksData nameWorksToNormative;

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

    public NameWorksData getNameWorksToNormative() {
        return nameWorksToNormative;
    }

    public void setNameWorksToNormative(NameWorksData nameWorksToNormative) {
        this.nameWorksToNormative = nameWorksToNormative;
    }

    public NormativeData() {
    }

    public NormativeData(String name) {
        this.name = name;
    }
}
