package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

@Entity
public class AccompanyingDocumentData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name, number, date;

    @ManyToOne
    @JoinColumn(name = "materials_used_id")
    private MaterialsUsedData materialsUsedData;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MaterialsUsedData getMaterialsUsedData() {
        return materialsUsedData;
    }

    public void setMaterialsUsedData(MaterialsUsedData materialsUsedData) {
        this.materialsUsedData = materialsUsedData;
    }

    public AccompanyingDocumentData() {
    }

    public AccompanyingDocumentData(String name, String number, String date) {
        this.name = name;
        this.number = number;
        this.date = date;
    }
}
