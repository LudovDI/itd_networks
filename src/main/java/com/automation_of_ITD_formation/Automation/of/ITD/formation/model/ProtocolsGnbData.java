package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

@Entity
public class ProtocolsGnbData extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String numberProtocolGnb, dataProtocolGnb, status;

    @OneToOne
    @JoinColumn(name = "aosr_data_id")
    private AosrData gnbToAosrData;

    @ManyToOne
    @JoinColumn(name = "itd_id")
    private ItdData itdToProtocolsGnbData;

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

    public String getNumberProtocolGnb() {
        return numberProtocolGnb;
    }

    public void setNumberProtocolGnb(String numberProtocolGnb) {
        this.numberProtocolGnb = numberProtocolGnb;
    }

    public String getDataProtocolGnb() {
        return dataProtocolGnb;
    }

    public void setDataProtocolGnb(String dataProtocolGnb) {
        this.dataProtocolGnb = dataProtocolGnb;
    }

    public AosrData getGnbToAosrData() {
        return gnbToAosrData;
    }

    public void setGnbToAosrData(AosrData gnbToAosrData) {
        this.gnbToAosrData = gnbToAosrData;
    }

    public ItdData getItdToProtocolsGnbData() {
        return itdToProtocolsGnbData;
    }

    public void setItdToProtocolsGnbData(ItdData itdToProtocolsGnbData) {
        this.itdToProtocolsGnbData = itdToProtocolsGnbData;
    }

    public ProtocolsGnbData() {
    }

    public ProtocolsGnbData(String numberProtocolGNB, String dataProtocolGNB) {
        this.numberProtocolGnb = numberProtocolGNB;
        this.dataProtocolGnb = dataProtocolGNB;
    }
}
