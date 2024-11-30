package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

@Entity
public class ActsViCData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numberActViC, dataActViC, nameLaboratory, status;

    @ManyToOne
    @JoinColumn(name = "aosr_data_id")
    private AosrData actsToAosrData;

    @ManyToOne
    @JoinColumn(name = "itd_id")
    private ItdData itdToActsViCData;

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

    public String getNumberActViC() {
        return numberActViC;
    }

    public void setNumberActViC(String numberActViC) {
        this.numberActViC = numberActViC;
    }

    public String getDataActViC() {
        return dataActViC;
    }

    public void setDataActViC(String dataActViC) {
        this.dataActViC = dataActViC;
    }

    public String getNameLaboratory() {
        return nameLaboratory;
    }

    public void setNameLaboratory(String nameLaboratory) {
        this.nameLaboratory = nameLaboratory;
    }

    public AosrData getActsToAosrData() {
        return actsToAosrData;
    }

    public void setActsToAosrData(AosrData actsToAosrData) {
        this.actsToAosrData = actsToAosrData;
    }

    public ItdData getItdToActsViCData() {
        return itdToActsViCData;
    }

    public void setItdToActsViCData(ItdData itdToActsViCData) {
        this.itdToActsViCData = itdToActsViCData;
    }

    public ActsViCData() {
    }

    public ActsViCData(String numberActViC, String dataActViC, String nameLaboratory) {
        this.numberActViC = numberActViC;
        this.dataActViC = dataActViC;
        this.nameLaboratory = nameLaboratory;
    }
}
