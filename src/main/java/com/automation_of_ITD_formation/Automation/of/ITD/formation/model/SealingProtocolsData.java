package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

@Entity
public class SealingProtocolsData extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numberSealingProtocols, dataSealingProtocols, nameLaboratory, status;

    @OneToOne
    @JoinColumn(name = "aosr_data_id")
    private AosrData sealingToAosrData;

    @ManyToOne
    @JoinColumn(name = "itd_id")
    private ItdData itdToSealingProtocolsData;

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

    public String getNumberSealingProtocols() {
        return numberSealingProtocols;
    }

    public void setNumberSealingProtocols(String numberSealingProtocols) {
        this.numberSealingProtocols = numberSealingProtocols;
    }

    public String getDataSealingProtocols() {
        return dataSealingProtocols;
    }

    public void setDataSealingProtocols(String dataSealingProtocols) {
        this.dataSealingProtocols = dataSealingProtocols;
    }

    public String getNameLaboratory() {
        return nameLaboratory;
    }

    public void setNameLaboratory(String nameLaboratory) {
        this.nameLaboratory = nameLaboratory;
    }

    public AosrData getSealingToAosrData() {
        return sealingToAosrData;
    }

    public void setSealingToAosrData(AosrData sealingToAosrData) {
        this.sealingToAosrData = sealingToAosrData;
    }

    public ItdData getItdToSealingProtocolsData() {
        return itdToSealingProtocolsData;
    }

    public void setItdToSealingProtocolsData(ItdData itdToSealingProtocolsData) {
        this.itdToSealingProtocolsData = itdToSealingProtocolsData;
    }

    public SealingProtocolsData() {
    }

    public SealingProtocolsData(String numberSealingProtocols, String dataSealingProtocols, String nameLaboratory) {
        this.numberSealingProtocols = numberSealingProtocols;
        this.dataSealingProtocols = dataSealingProtocols;
        this.nameLaboratory = nameLaboratory;
    }
}
