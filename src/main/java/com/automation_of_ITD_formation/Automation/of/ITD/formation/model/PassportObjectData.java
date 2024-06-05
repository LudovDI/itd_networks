package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class PassportObjectData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nameObject, addressObject, projectCode, status;
    private String nameDeveloper, ogrnDeveloper, innDeveloper, addressDeveloper, phoneDeveloper, nameOrganizationDeveloper, ogrnOrganizationDeveloper, innOrganizationDeveloper;
    private String nameBuilder, ogrnBuilder, innBuilder, addressBuilder, phoneBuilder, nameOrganizationBuilder, ogrnOrganizationBuilder, innOrganizationBuilder;
    private String namePreparer, ogrnPreparer, innPreparer, addressPreparer, phonePreparer, nameOrganizationPreparer, ogrnOrganizationPreparer, innOrganizationPreparer;
    private String postCustomer, fioCustomer, idCustomer, nameCustomer, numberCustomer, dataCustomer, nameOrganizationCustomer, ogrnOrganizationCustomer, innOrganizationCustomer, adrOrganizationCustomer;
    private String postContractor, fioContractor, idContractor, nameContractor, numberContractor, dataContractor;
    private String postContractor2, fioContractor2, idContractor2, nameContractor2, numberContractor2, dataContractor2;
    private String postProjector, fioProjector, idProjector, nameProjector, numberProjector, dataProjector, nameOrganizationProjector, ogrnOrganizationProjector, innOrganizationProjector, adrOrganizationProjector;
    private String postAnotherPerson, fioAnotherPerson, idAnotherPerson, nameAnotherPerson, numberAnotherPerson, dataAnotherPerson, nameOrganizationAnotherPerson, ogrnOrganizationAnotherPerson, innOrganizationAnotherPerson, adrOrganizationAnotherPerson;

    @OneToMany(mappedBy = "passportObjectData", cascade = CascadeType.ALL)
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

    public PassportObjectData() {
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

    public String getNameDeveloper() {
        return nameDeveloper;
    }

    public void setNameDeveloper(String nameDeveloper) {
        this.nameDeveloper = nameDeveloper;
    }

    public String getOgrnDeveloper() {
        return ogrnDeveloper;
    }

    public void setOgrnDeveloper(String ogrnDeveloper) {
        this.ogrnDeveloper = ogrnDeveloper;
    }

    public String getInnDeveloper() {
        return innDeveloper;
    }

    public void setInnDeveloper(String innDeveloper) {
        this.innDeveloper = innDeveloper;
    }

    public String getAddressDeveloper() {
        return addressDeveloper;
    }

    public void setAddressDeveloper(String addressDeveloper) {
        this.addressDeveloper = addressDeveloper;
    }

    public String getPhoneDeveloper() {
        return phoneDeveloper;
    }

    public void setPhoneDeveloper(String phoneDeveloper) {
        this.phoneDeveloper = phoneDeveloper;
    }

    public String getNameOrganizationDeveloper() {
        return nameOrganizationDeveloper;
    }

    public void setNameOrganizationDeveloper(String nameOrganizationDeveloper) {
        this.nameOrganizationDeveloper = nameOrganizationDeveloper;
    }

    public String getOgrnOrganizationDeveloper() {
        return ogrnOrganizationDeveloper;
    }

    public void setOgrnOrganizationDeveloper(String ogrnOrganizationDeveloper) {
        this.ogrnOrganizationDeveloper = ogrnOrganizationDeveloper;
    }

    public String getInnOrganizationDeveloper() {
        return innOrganizationDeveloper;
    }

    public void setInnOrganizationDeveloper(String innOrganizationDeveloper) {
        this.innOrganizationDeveloper = innOrganizationDeveloper;
    }

    public String getNameBuilder() {
        return nameBuilder;
    }

    public void setNameBuilder(String nameBuilder) {
        this.nameBuilder = nameBuilder;
    }

    public String getOgrnBuilder() {
        return ogrnBuilder;
    }

    public void setOgrnBuilder(String ogrnBuilder) {
        this.ogrnBuilder = ogrnBuilder;
    }

    public String getInnBuilder() {
        return innBuilder;
    }

    public void setInnBuilder(String innBuilder) {
        this.innBuilder = innBuilder;
    }

    public String getAddressBuilder() {
        return addressBuilder;
    }

    public void setAddressBuilder(String addressBuilder) {
        this.addressBuilder = addressBuilder;
    }

    public String getPhoneBuilder() {
        return phoneBuilder;
    }

    public void setPhoneBuilder(String phoneBuilder) {
        this.phoneBuilder = phoneBuilder;
    }

    public String getNameOrganizationBuilder() {
        return nameOrganizationBuilder;
    }

    public void setNameOrganizationBuilder(String nameOrganizationBuilder) {
        this.nameOrganizationBuilder = nameOrganizationBuilder;
    }

    public String getOgrnOrganizationBuilder() {
        return ogrnOrganizationBuilder;
    }

    public void setOgrnOrganizationBuilder(String ogrnOrganizationBuilder) {
        this.ogrnOrganizationBuilder = ogrnOrganizationBuilder;
    }

    public String getInnOrganizationBuilder() {
        return innOrganizationBuilder;
    }

    public void setInnOrganizationBuilder(String innOrganizationBuilder) {
        this.innOrganizationBuilder = innOrganizationBuilder;
    }

    public String getNamePreparer() {
        return namePreparer;
    }

    public void setNamePreparer(String namePreparer) {
        this.namePreparer = namePreparer;
    }

    public String getOgrnPreparer() {
        return ogrnPreparer;
    }

    public void setOgrnPreparer(String ogrnPreparer) {
        this.ogrnPreparer = ogrnPreparer;
    }

    public String getInnPreparer() {
        return innPreparer;
    }

    public void setInnPreparer(String innPreparer) {
        this.innPreparer = innPreparer;
    }

    public String getAddressPreparer() {
        return addressPreparer;
    }

    public void setAddressPreparer(String addressPreparer) {
        this.addressPreparer = addressPreparer;
    }

    public String getPhonePreparer() {
        return phonePreparer;
    }

    public void setPhonePreparer(String phonePreparer) {
        this.phonePreparer = phonePreparer;
    }

    public String getNameOrganizationPreparer() {
        return nameOrganizationPreparer;
    }

    public void setNameOrganizationPreparer(String nameOrganizationPreparer) {
        this.nameOrganizationPreparer = nameOrganizationPreparer;
    }

    public String getOgrnOrganizationPreparer() {
        return ogrnOrganizationPreparer;
    }

    public void setOgrnOrganizationPreparer(String ogrnOrganizationPreparer) {
        this.ogrnOrganizationPreparer = ogrnOrganizationPreparer;
    }

    public String getInnOrganizationPreparer() {
        return innOrganizationPreparer;
    }

    public void setInnOrganizationPreparer(String innOrganizationPreparer) {
        this.innOrganizationPreparer = innOrganizationPreparer;
    }

    public String getPostCustomer() {
        return postCustomer;
    }

    public void setPostCustomer(String postCustomer) {
        this.postCustomer = postCustomer;
    }

    public String getFioCustomer() {
        return fioCustomer;
    }

    public void setFioCustomer(String fioCustomer) {
        this.fioCustomer = fioCustomer;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNumberCustomer() {
        return numberCustomer;
    }

    public void setNumberCustomer(String numberCustomer) {
        this.numberCustomer = numberCustomer;
    }

    public String getDataCustomer() {
        return dataCustomer;
    }

    public void setDataCustomer(String dataCustomer) {
        this.dataCustomer = dataCustomer;
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

    public String getAdrOrganizationCustomer() {
        return adrOrganizationCustomer;
    }

    public void setAdrOrganizationCustomer(String adrOrganizationCustomer) {
        this.adrOrganizationCustomer = adrOrganizationCustomer;
    }

    public String getPostContractor() {
        return postContractor;
    }

    public void setPostContractor(String postContractor) {
        this.postContractor = postContractor;
    }

    public String getFioContractor() {
        return fioContractor;
    }

    public void setFioContractor(String fioContractor) {
        this.fioContractor = fioContractor;
    }

    public String getIdContractor() {
        return idContractor;
    }

    public void setIdContractor(String idContractor) {
        this.idContractor = idContractor;
    }

    public String getNameContractor() {
        return nameContractor;
    }

    public void setNameContractor(String nameContractor) {
        this.nameContractor = nameContractor;
    }

    public String getNumberContractor() {
        return numberContractor;
    }

    public void setNumberContractor(String numberContractor) {
        this.numberContractor = numberContractor;
    }

    public String getDataContractor() {
        return dataContractor;
    }

    public void setDataContractor(String dataContractor) {
        this.dataContractor = dataContractor;
    }

    public String getPostContractor2() {
        return postContractor2;
    }

    public void setPostContractor2(String postContractor2) {
        this.postContractor2 = postContractor2;
    }

    public String getFioContractor2() {
        return fioContractor2;
    }

    public void setFioContractor2(String fioContractor2) {
        this.fioContractor2 = fioContractor2;
    }

    public String getIdContractor2() {
        return idContractor2;
    }

    public void setIdContractor2(String idContractor2) {
        this.idContractor2 = idContractor2;
    }

    public String getNameContractor2() {
        return nameContractor2;
    }

    public void setNameContractor2(String nameContractor2) {
        this.nameContractor2 = nameContractor2;
    }

    public String getNumberContractor2() {
        return numberContractor2;
    }

    public void setNumberContractor2(String numberContractor2) {
        this.numberContractor2 = numberContractor2;
    }

    public String getDataContractor2() {
        return dataContractor2;
    }

    public void setDataContractor2(String dataContractor2) {
        this.dataContractor2 = dataContractor2;
    }

    public String getPostProjector() {
        return postProjector;
    }

    public void setPostProjector(String postProjector) {
        this.postProjector = postProjector;
    }

    public String getFioProjector() {
        return fioProjector;
    }

    public void setFioProjector(String fioProjector) {
        this.fioProjector = fioProjector;
    }

    public String getIdProjector() {
        return idProjector;
    }

    public void setIdProjector(String idProjector) {
        this.idProjector = idProjector;
    }

    public String getNameProjector() {
        return nameProjector;
    }

    public void setNameProjector(String nameProjector) {
        this.nameProjector = nameProjector;
    }

    public String getNumberProjector() {
        return numberProjector;
    }

    public void setNumberProjector(String numberProjector) {
        this.numberProjector = numberProjector;
    }

    public String getDataProjector() {
        return dataProjector;
    }

    public void setDataProjector(String dataProjector) {
        this.dataProjector = dataProjector;
    }

    public String getNameOrganizationProjector() {
        return nameOrganizationProjector;
    }

    public void setNameOrganizationProjector(String nameOrganizationProjector) {
        this.nameOrganizationProjector = nameOrganizationProjector;
    }

    public String getOgrnOrganizationProjector() {
        return ogrnOrganizationProjector;
    }

    public void setOgrnOrganizationProjector(String ogrnOrganizationProjector) {
        this.ogrnOrganizationProjector = ogrnOrganizationProjector;
    }

    public String getInnOrganizationProjector() {
        return innOrganizationProjector;
    }

    public void setInnOrganizationProjector(String innOrganizationProjector) {
        this.innOrganizationProjector = innOrganizationProjector;
    }

    public String getAdrOrganizationProjector() {
        return adrOrganizationProjector;
    }

    public void setAdrOrganizationProjector(String adrOrganizationProjector) {
        this.adrOrganizationProjector = adrOrganizationProjector;
    }

    public String getPostAnotherPerson() {
        return postAnotherPerson;
    }

    public void setPostAnotherPerson(String postAnotherPerson) {
        this.postAnotherPerson = postAnotherPerson;
    }

    public String getFioAnotherPerson() {
        return fioAnotherPerson;
    }

    public void setFioAnotherPerson(String fioAnotherPerson) {
        this.fioAnotherPerson = fioAnotherPerson;
    }

    public String getIdAnotherPerson() {
        return idAnotherPerson;
    }

    public void setIdAnotherPerson(String idAnotherPerson) {
        this.idAnotherPerson = idAnotherPerson;
    }

    public String getNameAnotherPerson() {
        return nameAnotherPerson;
    }

    public void setNameAnotherPerson(String nameAnotherPerson) {
        this.nameAnotherPerson = nameAnotherPerson;
    }

    public String getNumberAnotherPerson() {
        return numberAnotherPerson;
    }

    public void setNumberAnotherPerson(String numberAnotherPerson) {
        this.numberAnotherPerson = numberAnotherPerson;
    }

    public String getDataAnotherPerson() {
        return dataAnotherPerson;
    }

    public void setDataAnotherPerson(String dataAnotherPerson) {
        this.dataAnotherPerson = dataAnotherPerson;
    }

    public String getNameOrganizationAnotherPerson() {
        return nameOrganizationAnotherPerson;
    }

    public void setNameOrganizationAnotherPerson(String nameOrganizationAnotherPerson) {
        this.nameOrganizationAnotherPerson = nameOrganizationAnotherPerson;
    }

    public String getOgrnOrganizationAnotherPerson() {
        return ogrnOrganizationAnotherPerson;
    }

    public void setOgrnOrganizationAnotherPerson(String ogrnOrganizationAnotherPerson) {
        this.ogrnOrganizationAnotherPerson = ogrnOrganizationAnotherPerson;
    }

    public String getInnOrganizationAnotherPerson() {
        return innOrganizationAnotherPerson;
    }

    public void setInnOrganizationAnotherPerson(String innOrganizationAnotherPerson) {
        this.innOrganizationAnotherPerson = innOrganizationAnotherPerson;
    }

    public String getAdrOrganizationAnotherPerson() {
        return adrOrganizationAnotherPerson;
    }

    public void setAdrOrganizationAnotherPerson(String adrOrganizationAnotherPerson) {
        this.adrOrganizationAnotherPerson = adrOrganizationAnotherPerson;
    }

    public PassportObjectData(String nameObject, String addressObject, String projectCode, String nameDeveloper, String ogrnDeveloper, String innDeveloper, String addressDeveloper, String phoneDeveloper, String nameOrganizationDeveloper, String ogrnOrganizationDeveloper, String innOrganizationDeveloper, String nameBuilder, String ogrnBuilder, String innBuilder, String addressBuilder, String phoneBuilder, String nameOrganizationBuilder, String ogrnOrganizationBuilder, String innOrganizationBuilder, String namePreparer, String ogrnPreparer, String innPreparer, String addressPreparer, String phonePreparer, String nameOrganizationPreparer, String ogrnOrganizationPreparer, String innOrganizationPreparer, String postCustomer, String fioCustomer, String idCustomer, String nameCustomer, String numberCustomer, String dataCustomer, String nameOrganizationCustomer, String ogrnOrganizationCustomer, String innOrganizationCustomer, String adrOrganizationCustomer, String postContractor, String fioContractor, String idContractor, String nameContractor, String numberContractor, String dataContractor, String postContractor2, String fioContractor2, String idContractor2, String nameContractor2, String numberContractor2, String dataContractor2, String postProjector, String fioProjector, String idProjector, String nameProjector, String numberProjector, String dataProjector, String nameOrganizationProjector, String ogrnOrganizationProjector, String innOrganizationProjector, String adrOrganizationProjector, String postAnotherPerson, String fioAnotherPerson, String idAnotherPerson, String nameAnotherPerson, String numberAnotherPerson, String dataAnotherPerson, String nameOrganizationAnotherPerson, String ogrnOrganizationAnotherPerson, String innOrganizationAnotherPerson, String adrOrganizationAnotherPerson) {
        this.nameObject = nameObject;
        this.addressObject = addressObject;
        this.projectCode = projectCode;
        this.nameDeveloper = nameDeveloper;
        this.ogrnDeveloper = ogrnDeveloper;
        this.innDeveloper = innDeveloper;
        this.addressDeveloper = addressDeveloper;
        this.phoneDeveloper = phoneDeveloper;
        this.nameOrganizationDeveloper = nameOrganizationDeveloper;
        this.ogrnOrganizationDeveloper = ogrnOrganizationDeveloper;
        this.innOrganizationDeveloper = innOrganizationDeveloper;
        this.nameBuilder = nameBuilder;
        this.ogrnBuilder = ogrnBuilder;
        this.innBuilder = innBuilder;
        this.addressBuilder = addressBuilder;
        this.phoneBuilder = phoneBuilder;
        this.nameOrganizationBuilder = nameOrganizationBuilder;
        this.ogrnOrganizationBuilder = ogrnOrganizationBuilder;
        this.innOrganizationBuilder = innOrganizationBuilder;
        this.namePreparer = namePreparer;
        this.ogrnPreparer = ogrnPreparer;
        this.innPreparer = innPreparer;
        this.addressPreparer = addressPreparer;
        this.phonePreparer = phonePreparer;
        this.nameOrganizationPreparer = nameOrganizationPreparer;
        this.ogrnOrganizationPreparer = ogrnOrganizationPreparer;
        this.innOrganizationPreparer = innOrganizationPreparer;
        this.postCustomer = postCustomer;
        this.fioCustomer = fioCustomer;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.numberCustomer = numberCustomer;
        this.dataCustomer = dataCustomer;
        this.nameOrganizationCustomer = nameOrganizationCustomer;
        this.ogrnOrganizationCustomer = ogrnOrganizationCustomer;
        this.innOrganizationCustomer = innOrganizationCustomer;
        this.adrOrganizationCustomer = adrOrganizationCustomer;
        this.postContractor = postContractor;
        this.fioContractor = fioContractor;
        this.idContractor = idContractor;
        this.nameContractor = nameContractor;
        this.numberContractor = numberContractor;
        this.dataContractor = dataContractor;
        this.postContractor2 = postContractor2;
        this.fioContractor2 = fioContractor2;
        this.idContractor2 = idContractor2;
        this.nameContractor2 = nameContractor2;
        this.numberContractor2 = numberContractor2;
        this.dataContractor2 = dataContractor2;
        this.postProjector = postProjector;
        this.fioProjector = fioProjector;
        this.idProjector = idProjector;
        this.nameProjector = nameProjector;
        this.numberProjector = numberProjector;
        this.dataProjector = dataProjector;
        this.nameOrganizationProjector = nameOrganizationProjector;
        this.ogrnOrganizationProjector = ogrnOrganizationProjector;
        this.innOrganizationProjector = innOrganizationProjector;
        this.adrOrganizationProjector = adrOrganizationProjector;
        this.postAnotherPerson = postAnotherPerson;
        this.fioAnotherPerson = fioAnotherPerson;
        this.idAnotherPerson = idAnotherPerson;
        this.nameAnotherPerson = nameAnotherPerson;
        this.numberAnotherPerson = numberAnotherPerson;
        this.dataAnotherPerson = dataAnotherPerson;
        this.nameOrganizationAnotherPerson = nameOrganizationAnotherPerson;
        this.ogrnOrganizationAnotherPerson = ogrnOrganizationAnotherPerson;
        this.innOrganizationAnotherPerson = innOrganizationAnotherPerson;
        this.adrOrganizationAnotherPerson = adrOrganizationAnotherPerson;
    }
}
