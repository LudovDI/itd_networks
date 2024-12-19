package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class PassportObjectData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameObject, addressObject, projectCode, status,
            nameCustomer, ogrnCustomer, innCustomer, addressCustomer, phoneCustomer, nameOrganizationCustomer, ogrnOrganizationCustomer, innOrganizationCustomer,
            nameContractor, ogrnContractor, innContractor, addressContractor, phoneContractor, nameOrganizationContractor, ogrnOrganizationContractor, innOrganizationContractor,
            nameDesigner, ogrnDesigner, innDesigner, addressDesigner, phoneDesigner, nameOrganizationDesigner, ogrnOrganizationDesigner, innOrganizationDesigner;

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private Set<AosrData> aosrs = new HashSet<>();

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private List<AnotherPersonResponsibleData> anotherPersonResponsibleData;

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private List<CustomerResponsibleData> customerResponsibleData;

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private List<DesignerResponsibleData> designerResponsibleData;

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private List<SubcustomerResponsibleData> subcustomerResponsibleData;

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private List<SubcustomerResponsible2Data> subcustomerResponsible2Data;

    @OneToOne(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
    private ItdData itdData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameObject() {
        return nameObject;
    }

    public void setNameObject(String nameObject) {
        this.nameObject = nameObject;
    }

    public String getAddressObject() {
        return addressObject;
    }

    public void setAddressObject(String addressObject) {
        this.addressObject = addressObject;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getOgrnCustomer() {
        return ogrnCustomer;
    }

    public void setOgrnCustomer(String ogrnCustomer) {
        this.ogrnCustomer = ogrnCustomer;
    }

    public String getInnCustomer() {
        return innCustomer;
    }

    public void setInnCustomer(String innCustomer) {
        this.innCustomer = innCustomer;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getNameOrganizationCustomer() {
        return nameOrganizationCustomer;
    }

    public void setNameOrganizationCustomer(String nameOrganizationCustomer) {
        this.nameOrganizationCustomer = nameOrganizationCustomer;
    }

    public String getOgrnOrganizationCustomer() {
        return ogrnOrganizationCustomer;
    }

    public void setOgrnOrganizationCustomer(String ogrnOrganizationCustomer) {
        this.ogrnOrganizationCustomer = ogrnOrganizationCustomer;
    }

    public String getInnOrganizationCustomer() {
        return innOrganizationCustomer;
    }

    public void setInnOrganizationCustomer(String innOrganizationCustomer) {
        this.innOrganizationCustomer = innOrganizationCustomer;
    }

    public String getNameContractor() {
        return nameContractor;
    }

    public void setNameContractor(String nameContractor) {
        this.nameContractor = nameContractor;
    }

    public String getOgrnContractor() {
        return ogrnContractor;
    }

    public void setOgrnContractor(String ogrnContractor) {
        this.ogrnContractor = ogrnContractor;
    }

    public String getInnContractor() {
        return innContractor;
    }

    public void setInnContractor(String innContractor) {
        this.innContractor = innContractor;
    }

    public String getAddressContractor() {
        return addressContractor;
    }

    public void setAddressContractor(String addressContractor) {
        this.addressContractor = addressContractor;
    }

    public String getPhoneContractor() {
        return phoneContractor;
    }

    public void setPhoneContractor(String phoneContractor) {
        this.phoneContractor = phoneContractor;
    }

    public String getNameOrganizationContractor() {
        return nameOrganizationContractor;
    }

    public void setNameOrganizationContractor(String nameOrganizationContractor) {
        this.nameOrganizationContractor = nameOrganizationContractor;
    }

    public String getOgrnOrganizationContractor() {
        return ogrnOrganizationContractor;
    }

    public void setOgrnOrganizationContractor(String ogrnOrganizationContractor) {
        this.ogrnOrganizationContractor = ogrnOrganizationContractor;
    }

    public String getInnOrganizationContractor() {
        return innOrganizationContractor;
    }

    public void setInnOrganizationContractor(String innOrganizationContractor) {
        this.innOrganizationContractor = innOrganizationContractor;
    }

    public String getNameDesigner() {
        return nameDesigner;
    }

    public void setNameDesigner(String nameDesigner) {
        this.nameDesigner = nameDesigner;
    }

    public String getOgrnDesigner() {
        return ogrnDesigner;
    }

    public void setOgrnDesigner(String ogrnDesigner) {
        this.ogrnDesigner = ogrnDesigner;
    }

    public String getInnDesigner() {
        return innDesigner;
    }

    public void setInnDesigner(String innDesigner) {
        this.innDesigner = innDesigner;
    }

    public String getAddressDesigner() {
        return addressDesigner;
    }

    public void setAddressDesigner(String addressDesigner) {
        this.addressDesigner = addressDesigner;
    }

    public String getPhoneDesigner() {
        return phoneDesigner;
    }

    public void setPhoneDesigner(String phoneDesigner) {
        this.phoneDesigner = phoneDesigner;
    }

    public String getNameOrganizationDesigner() {
        return nameOrganizationDesigner;
    }

    public void setNameOrganizationDesigner(String nameOrganizationDesigner) {
        this.nameOrganizationDesigner = nameOrganizationDesigner;
    }

    public String getOgrnOrganizationDesigner() {
        return ogrnOrganizationDesigner;
    }

    public void setOgrnOrganizationDesigner(String ogrnOrganizationDesigner) {
        this.ogrnOrganizationDesigner = ogrnOrganizationDesigner;
    }

    public String getInnOrganizationDesigner() {
        return innOrganizationDesigner;
    }

    public void setInnOrganizationDesigner(String innOrganizationDesigner) {
        this.innOrganizationDesigner = innOrganizationDesigner;
    }

    public Set<AosrData> getAosrs() {
        return aosrs;
    }

    public void setAosrs(Set<AosrData> aosrs) {
        this.aosrs = aosrs;
    }

    public List<AnotherPersonResponsibleData> getAnotherPersonResponsibleData() {
        return anotherPersonResponsibleData;
    }

    public void setAnotherPersonResponsibleData(List<AnotherPersonResponsibleData> anotherPersonResponsibleData) {
        this.anotherPersonResponsibleData = anotherPersonResponsibleData;
    }

    public List<CustomerResponsibleData> getCustomerResponsibleData() {
        return customerResponsibleData;
    }

    public void setCustomerResponsibleData(List<CustomerResponsibleData> customerResponsibleData) {
        this.customerResponsibleData = customerResponsibleData;
    }

    public List<DesignerResponsibleData> getDesignerResponsibleData() {
        return designerResponsibleData;
    }

    public void setDesignerResponsibleData(List<DesignerResponsibleData> designerResponsibleData) {
        this.designerResponsibleData = designerResponsibleData;
    }

    public List<SubcustomerResponsibleData> getSubcustomerResponsibleData() {
        return subcustomerResponsibleData;
    }

    public void setSubcustomerResponsibleData(List<SubcustomerResponsibleData> subcustomerResponsibleData) {
        this.subcustomerResponsibleData = subcustomerResponsibleData;
    }

    public List<SubcustomerResponsible2Data> getSubcustomerResponsible2Data() {
        return subcustomerResponsible2Data;
    }

    public void setSubcustomerResponsible2Data(List<SubcustomerResponsible2Data> subcustomerResponsible2Data) {
        this.subcustomerResponsible2Data = subcustomerResponsible2Data;
    }

    public PassportObjectData() {
    }

    public PassportObjectData(String nameObject, String addressObject, String projectCode, String nameCustomer, String ogrnCustomer, String innCustomer, String addressCustomer, String phoneCustomer, String nameOrganizationCustomer, String ogrnOrganizationCustomer, String innOrganizationCustomer, String nameContractor, String ogrnContractor, String innContractor, String addressContractor, String phoneContractor, String nameOrganizationContractor, String ogrnOrganizationContractor, String innOrganizationContractor, String nameDesigner, String ogrnDesigner, String innDesigner, String addressDesigner, String phoneDesigner, String nameOrganizationDesigner, String ogrnOrganizationDesigner, String innOrganizationDesigner) {
        this.nameObject = nameObject;
        this.addressObject = addressObject;
        this.projectCode = projectCode;
        this.nameCustomer = nameCustomer;
        this.ogrnCustomer = ogrnCustomer;
        this.innCustomer = innCustomer;
        this.addressCustomer = addressCustomer;
        this.phoneCustomer = phoneCustomer;
        this.nameOrganizationCustomer = nameOrganizationCustomer;
        this.ogrnOrganizationCustomer = ogrnOrganizationCustomer;
        this.innOrganizationCustomer = innOrganizationCustomer;
        this.nameContractor = nameContractor;
        this.ogrnContractor = ogrnContractor;
        this.innContractor = innContractor;
        this.addressContractor = addressContractor;
        this.phoneContractor = phoneContractor;
        this.nameOrganizationContractor = nameOrganizationContractor;
        this.ogrnOrganizationContractor = ogrnOrganizationContractor;
        this.innOrganizationContractor = innOrganizationContractor;
        this.nameDesigner = nameDesigner;
        this.ogrnDesigner = ogrnDesigner;
        this.innDesigner = innDesigner;
        this.addressDesigner = addressDesigner;
        this.phoneDesigner = phoneDesigner;
        this.nameOrganizationDesigner = nameOrganizationDesigner;
        this.ogrnOrganizationDesigner = ogrnOrganizationDesigner;
        this.innOrganizationDesigner = innOrganizationDesigner;
    }

    public ItdData getItdData() {
        return itdData;
    }

    public void setItdData(ItdData itdData) {
        this.itdData = itdData;
    }
}
