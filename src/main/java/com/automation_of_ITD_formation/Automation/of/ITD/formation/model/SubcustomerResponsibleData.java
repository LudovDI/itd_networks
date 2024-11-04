package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

@Entity
public class SubcustomerResponsibleData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String position, name, idNumber, nameDocument, numberDocument, dateDocument;

    @ManyToOne
    @JoinColumn(name = "passport_object_id")
    private PassportObjectData passportObjectData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public String getNumberDocument() {
        return numberDocument;
    }

    public void setNumberDocument(String numberDocument) {
        this.numberDocument = numberDocument;
    }

    public String getDateDocument() {
        return dateDocument;
    }

    public void setDateDocument(String dateDocument) {
        this.dateDocument = dateDocument;
    }

    public PassportObjectData getPassportObjectData() {
        return passportObjectData;
    }

    public void setPassportObjectData(PassportObjectData passportObjectData) {
        this.passportObjectData = passportObjectData;
    }

    public SubcustomerResponsibleData() {
    }

    public SubcustomerResponsibleData(String position, String name, String idNumber, String nameDocument, String numberDocument, String dateDocument) {
        this.position = position;
        this.name = name;
        this.idNumber = idNumber;
        this.nameDocument = nameDocument;
        this.numberDocument = numberDocument;
        this.dateDocument = dateDocument;
    }
}
