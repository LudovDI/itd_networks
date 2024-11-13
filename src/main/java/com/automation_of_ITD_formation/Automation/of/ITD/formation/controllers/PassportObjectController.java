package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PassportObjectController {

    @Autowired
    private PassportObjectRepository passportObjectRepository;
    @Autowired
    private AnotherPersonResponsibleRepository anotherPersonResponsibleRepository;
    @Autowired
    private CustomerResponsibleRepository customerResponsibleRepository;
    @Autowired
    private DesignerResponsibleRepository designerResponsibleRepository;
    @Autowired
    private SubcustomerResponsibleRepository subcustomerResponsibleRepository;
    @Autowired
    private SubcustomerResponsible2Repository subcustomerResponsible2Repository;

    @GetMapping("/passport-object-table")
    public String passportObjectTable(Model model) {
        Iterable<PassportObjectData> passportObjectList = passportObjectRepository.findAll();
        model.addAttribute("passportObjectList", passportObjectList);
        return "passportObjectTable";
    }

    @GetMapping("/passport-object-add")
    public String passportObjectAdd(Model model) {
        return "passportObjectAdd";
    }

    @PostMapping("/passport-object-add")
    public String postPassportObjectAdd(@RequestParam("nameObject") String nameObject,
                                        @RequestParam("addressObject") String addressObject,
                                        @RequestParam("projectCode") String projectCode,
                                        @RequestParam("nameCustomer") String nameCustomer,
                                        @RequestParam("ogrnCustomer") String ogrnCustomer,
                                        @RequestParam("innCustomer") String innCustomer,
                                        @RequestParam("addressCustomer") String addressCustomer,
                                        @RequestParam("phoneCustomer") String phoneCustomer,
                                        @RequestParam("nameOrganizationCustomer") String nameOrganizationCustomer,
                                        @RequestParam("ogrnOrganizationCustomer") String ogrnOrganizationCustomer,
                                        @RequestParam("innOrganizationCustomer") String innOrganizationCustomer,
                                        @RequestParam("nameContractor") String nameContractor,
                                        @RequestParam("ogrnContractor") String ogrnContractor,
                                        @RequestParam("innContractor") String innContractor,
                                        @RequestParam("addressContractor") String addressContractor,
                                        @RequestParam("phoneContractor") String phoneContractor,
                                        @RequestParam("nameOrganizationContractor") String nameOrganizationContractor,
                                        @RequestParam("ogrnOrganizationContractor") String ogrnOrganizationContractor,
                                        @RequestParam("innOrganizationContractor") String innOrganizationContractor,
                                        @RequestParam("nameDesigner") String nameDesigner,
                                        @RequestParam("ogrnDesigner") String ogrnDesigner,
                                        @RequestParam("innDesigner") String innDesigner,
                                        @RequestParam("addressDesigner") String addressDesigner,
                                        @RequestParam("phoneDesigner") String phoneDesigner,
                                        @RequestParam("nameOrganizationDesigner") String nameOrganizationDesigner,
                                        @RequestParam("ogrnOrganizationDesigner") String ogrnOrganizationDesigner,
                                        @RequestParam("innOrganizationDesigner") String innOrganizationDesigner,
                                        @RequestParam Map<String, String> formData) {
        PassportObjectData passportObjectData = new PassportObjectData(nameObject, addressObject,projectCode, nameCustomer,
                ogrnCustomer, innCustomer, addressCustomer, phoneCustomer, nameOrganizationCustomer, ogrnOrganizationCustomer,
                innOrganizationCustomer, nameContractor, ogrnContractor, innContractor, addressContractor, phoneContractor,
                nameOrganizationContractor, ogrnOrganizationContractor, innOrganizationContractor, nameDesigner, ogrnDesigner,
                innDesigner, addressDesigner, phoneDesigner, nameOrganizationDesigner, ogrnOrganizationDesigner,
                innOrganizationDesigner);
        List<AnotherPersonResponsibleData> anotherPersonResponsibleList = new ArrayList<>();
        List<CustomerResponsibleData> customerResponsibleList = new ArrayList<>();
        List<DesignerResponsibleData> designerResponsibleList = new ArrayList<>();
        List<SubcustomerResponsibleData> subcustomerResponsibleList = new ArrayList<>();
        List<SubcustomerResponsible2Data> subcustomerResponsible2List = new ArrayList<>();
        formData.forEach((key, value) -> {
            if (key.startsWith("customerResponsiblePosition_")) {
                int index = Integer.parseInt(key.substring(28));
                String nameKey = "customerResponsibleName_" + index;
                String idKey = "customerResponsibleIdNumber_" + index;
                String nameDocumentKey = "customerResponsibleNameDocument_" + index;
                String numberDocumentKey = "customerResponsibleNumberDocument_" + index;
                String dateDocumentKey = "customerResponsibleDateDocument_" + index;
                String nameCompanyKey = "customerResponsibleNameCompany_" + index;
                String ogrnCompanyKey = "customerResponsibleOgrnCompany_" + index;
                String innCompanyKey = "customerResponsibleInnCompany_" + index;
                String adrCompanyKey = "customerResponsibleAdrCompany_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String id = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                String nameCompany = formData.getOrDefault(nameCompanyKey, "");
                String ogrnCompany = formData.getOrDefault(ogrnCompanyKey, "");
                String innCompany = formData.getOrDefault(innCompanyKey, "");
                String adrCompany = formData.getOrDefault(adrCompanyKey, "");
                CustomerResponsibleData customerResponsibleData = new CustomerResponsibleData(value, name, id, nameDocument, numberDocument, dateDocument, nameCompany, ogrnCompany, innCompany, adrCompany);
                customerResponsibleList.add(customerResponsibleData);
            }
            if (key.startsWith("subcustomerResponsiblePosition_")) {
                int index = Integer.parseInt(key.substring(31));
                String nameKey = "subcustomerResponsibleName_" + index;
                String idKey = "subcustomerResponsibleIdNumber_" + index;
                String nameDocumentKey = "subcustomerResponsibleNameDocument_" + index;
                String numberDocumentKey = "subcustomerResponsibleNumberDocument_" + index;
                String dateDocumentKey = "subcustomerResponsibleDateDocument_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String id = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                SubcustomerResponsibleData subcustomerResponsibleData = new SubcustomerResponsibleData(value, name, id, nameDocument, numberDocument, dateDocument);
                subcustomerResponsibleList.add(subcustomerResponsibleData);
            }
            if (key.startsWith("subcustomerResponsible2Position_")) {
                int index = Integer.parseInt(key.substring(32));
                String nameKey = "subcustomerResponsible2Name_" + index;
                String idKey = "subcustomerResponsible2IdNumber_" + index;
                String nameDocumentKey = "subcustomerResponsible2NameDocument_" + index;
                String numberDocumentKey = "subcustomerResponsible2NumberDocument_" + index;
                String dateDocumentKey = "subcustomerResponsible2DateDocument_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String id = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                SubcustomerResponsible2Data subcustomerResponsible2Data = new SubcustomerResponsible2Data(value, name, id, nameDocument, numberDocument, dateDocument);
                subcustomerResponsible2List.add(subcustomerResponsible2Data);
            }
            if (key.startsWith("designerResponsiblePosition_")) {
                int index = Integer.parseInt(key.substring(28));
                String nameKey = "designerResponsibleName_" + index;
                String idKey = "designerResponsibleIdNumber_" + index;
                String nameDocumentKey = "designerResponsibleNameDocument_" + index;
                String numberDocumentKey = "designerResponsibleNumberDocument_" + index;
                String dateDocumentKey = "designerResponsibleDateDocument_" + index;
                String nameCompanyKey = "designerResponsibleNameCompany_" + index;
                String ogrnCompanyKey = "designerResponsibleOgrnCompany_" + index;
                String innCompanyKey = "designerResponsibleInnCompany_" + index;
                String adrCompanyKey = "designerResponsibleAdrCompany_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String id = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                String nameCompany = formData.getOrDefault(nameCompanyKey, "");
                String ogrnCompany = formData.getOrDefault(ogrnCompanyKey, "");
                String innCompany = formData.getOrDefault(innCompanyKey, "");
                String adrCompany = formData.getOrDefault(adrCompanyKey, "");
                DesignerResponsibleData designerResponsibleData = new DesignerResponsibleData(value, name, id, nameDocument, numberDocument, dateDocument, nameCompany, ogrnCompany, innCompany, adrCompany);
                designerResponsibleList.add(designerResponsibleData);
            }
            if (key.startsWith("anotherPersonPosition_")) {
                int index = Integer.parseInt(key.substring(22));
                String nameKey = "anotherPersonName_" + index;
                String idKey = "anotherPersonIdNumber_" + index;
                String nameDocumentKey = "anotherPersonNameDocument_" + index;
                String numberDocumentKey = "anotherPersonNumberDocument_" + index;
                String dateDocumentKey = "anotherPersonDateDocument_" + index;
                String nameCompanyKey = "anotherPersonNameCompany_" + index;
                String ogrnCompanyKey = "anotherPersonOgrnCompany_" + index;
                String innCompanyKey = "anotherPersonInnCompany_" + index;
                String adrCompanyKey = "anotherPersonAdrCompany_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String id = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                String nameCompany = formData.getOrDefault(nameCompanyKey, "");
                String ogrnCompany = formData.getOrDefault(ogrnCompanyKey, "");
                String innCompany = formData.getOrDefault(innCompanyKey, "");
                String adrCompany = formData.getOrDefault(adrCompanyKey, "");
                AnotherPersonResponsibleData anotherPersonResponsibleData = new AnotherPersonResponsibleData(value, name, id, nameDocument, numberDocument, dateDocument, nameCompany, ogrnCompany, innCompany, adrCompany);
                anotherPersonResponsibleList.add(anotherPersonResponsibleData);
            }
        });
        passportObjectRepository.save(passportObjectData);
        customerResponsibleList.forEach(customerResponsibleData -> customerResponsibleData.setPassportObjectData(passportObjectData));
        customerResponsibleRepository.saveAll(customerResponsibleList);
        subcustomerResponsibleList.forEach(subcustomerResponsibleData -> subcustomerResponsibleData.setPassportObjectData(passportObjectData));
        subcustomerResponsibleRepository.saveAll(subcustomerResponsibleList);
        subcustomerResponsible2List.forEach(subcustomerResponsible2Data -> subcustomerResponsible2Data.setPassportObjectData(passportObjectData));
        subcustomerResponsible2Repository.saveAll(subcustomerResponsible2List);
        designerResponsibleList.forEach(designerResponsibleData -> designerResponsibleData.setPassportObjectData(passportObjectData));
        designerResponsibleRepository.saveAll(designerResponsibleList);
        anotherPersonResponsibleList.forEach(anotherPersonResponsibleData -> anotherPersonResponsibleData.setPassportObjectData(passportObjectData));
        anotherPersonResponsibleRepository.saveAll(anotherPersonResponsibleList);
        return "redirect:/passport-object-table";
    }

    @GetMapping("/passport-object-table/{id}/passport-object-edit")
    public String passportObjectEdit(@PathVariable(value = "id") long id, Model model) {
        if (!passportObjectRepository.existsById(id)) {
            return "redirect:/passport-object-table";
        }
        Optional<PassportObjectData> passportObjectDataOptional = passportObjectRepository.findById(id);
        ArrayList<PassportObjectData> res = new ArrayList<>();
        passportObjectDataOptional.ifPresent(res::add);
        PassportObjectData passportObjectData = res.getFirst();
        model.addAttribute("anotherPersonResponsibles", passportObjectData.getAnotherPersonResponsibleData());
        model.addAttribute("customerResponsibles", passportObjectData.getCustomerResponsibleData());
        model.addAttribute("designerResponsibles", passportObjectData.getDesignerResponsibleData());
        model.addAttribute("subcustomerResponsibles", passportObjectData.getSubcustomerResponsibleData());
        model.addAttribute("subcustomerResponsibles2", passportObjectData.getSubcustomerResponsible2Data());
        model.addAttribute("passportObjectDataOptional", res);
        return "passportObjectEdit";
    }

    @PostMapping("/passport-object-table/{id}/passport-object-edit")
    public String postPassportObjectUpdate(@PathVariable(value = "id") long id,
                                           @RequestParam("nameObject") String nameObject,
                                           @RequestParam("addressObject") String addressObject,
                                           @RequestParam("projectCode") String projectCode,
                                           @RequestParam("nameCustomer") String nameCustomer,
                                           @RequestParam("ogrnCustomer") String ogrnCustomer,
                                           @RequestParam("innCustomer") String innCustomer,
                                           @RequestParam("addressCustomer") String addressCustomer,
                                           @RequestParam("phoneCustomer") String phoneCustomer,
                                           @RequestParam("nameOrganizationCustomer") String nameOrganizationCustomer,
                                           @RequestParam("ogrnOrganizationCustomer") String ogrnOrganizationCustomer,
                                           @RequestParam("innOrganizationCustomer") String innOrganizationCustomer,
                                           @RequestParam("nameContractor") String nameContractor,
                                           @RequestParam("ogrnContractor") String ogrnContractor,
                                           @RequestParam("innContractor") String innContractor,
                                           @RequestParam("addressContractor") String addressContractor,
                                           @RequestParam("phoneContractor") String phoneContractor,
                                           @RequestParam("nameOrganizationContractor") String nameOrganizationContractor,
                                           @RequestParam("ogrnOrganizationContractor") String ogrnOrganizationContractor,
                                           @RequestParam("innOrganizationContractor") String innOrganizationContractor,
                                           @RequestParam("nameDesigner") String nameDesigner,
                                           @RequestParam("ogrnDesigner") String ogrnDesigner,
                                           @RequestParam("innDesigner") String innDesigner,
                                           @RequestParam("addressDesigner") String addressDesigner,
                                           @RequestParam("phoneDesigner") String phoneDesigner,
                                           @RequestParam("nameOrganizationDesigner") String nameOrganizationDesigner,
                                           @RequestParam("ogrnOrganizationDesigner") String ogrnOrganizationDesigner,
                                           @RequestParam("innOrganizationDesigner") String innOrganizationDesigner,
                                           @RequestParam Map<String, String> formData) {
        PassportObjectData passportObjectData = passportObjectRepository.findById(id).orElseThrow();

        Set<Long> anotherPersonResIdsFromForm = new HashSet<>();
        Set<Long> customerResIdsFromForm = new HashSet<>();
        Set<Long> designerResIdsFromForm = new HashSet<>();
        Set<Long> subcustomerResIdsFromForm = new HashSet<>();
        Set<Long> subcustomerRes2IdsFromForm = new HashSet<>();

        formData.forEach((key, value) -> {
            if (key.startsWith("customerResponsiblePosition_")) {
                String customerResIdStr = key.substring("customerResponsiblePosition_".length());

                CustomerResponsibleData customerRes;
                if (!customerResIdStr.isEmpty()) {
                    Long customerResId = Long.parseLong(customerResIdStr);
                    customerResIdsFromForm.add(customerResId);

                    customerRes = customerResponsibleRepository.findById(customerResId).orElse(null);
                    if (customerRes == null) {
                        customerRes = new CustomerResponsibleData();
                    }
                } else {
                    customerRes = new CustomerResponsibleData();
                }
                int index = Integer.parseInt(key.substring(28));
                String nameKey = "customerResponsibleName_" + index;
                String idKey = "customerResponsibleIdNumber_" + index;
                String nameDocumentKey = "customerResponsibleNameDocument_" + index;
                String numberDocumentKey = "customerResponsibleNumberDocument_" + index;
                String dateDocumentKey = "customerResponsibleDateDocument_" + index;
                String nameCompanyKey = "customerResponsibleNameCompany_" + index;
                String ogrnCompanyKey = "customerResponsibleOgrnCompany_" + index;
                String innCompanyKey = "customerResponsibleInnCompany_" + index;
                String adrCompanyKey = "customerResponsibleAdrCompany_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String idNum = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                String nameCompany = formData.getOrDefault(nameCompanyKey, "");
                String ogrnCompany = formData.getOrDefault(ogrnCompanyKey, "");
                String innCompany = formData.getOrDefault(innCompanyKey, "");
                String adrCompany = formData.getOrDefault(adrCompanyKey, "");
                customerRes.setPosition(value);
                customerRes.setName(name);
                customerRes.setIdNumber(idNum);
                customerRes.setNameDocument(nameDocument);
                customerRes.setNumberDocument(numberDocument);
                customerRes.setDateDocument(dateDocument);
                customerRes.setNameCompany(nameCompany);
                customerRes.setOgrnCompany(ogrnCompany);
                customerRes.setInnCompany(innCompany);
                customerRes.setAdrCompany(adrCompany);

                customerRes.setPassportObjectData(passportObjectData);
                passportObjectData.getCustomerResponsibleData().add(customerRes);
            }
            if (key.startsWith("subcustomerResponsiblePosition_")) {
                String subcustomerResIdStr = key.substring("subcustomerResponsiblePosition_".length());

                SubcustomerResponsibleData subcustomerRes;
                if (!subcustomerResIdStr.isEmpty()) {
                    Long subcustomerResId = Long.parseLong(subcustomerResIdStr);
                    subcustomerResIdsFromForm.add(subcustomerResId);

                    subcustomerRes = subcustomerResponsibleRepository.findById(subcustomerResId).orElse(null);
                    if (subcustomerRes == null) {
                        subcustomerRes = new SubcustomerResponsibleData();
                    }
                } else {
                    subcustomerRes = new SubcustomerResponsibleData();
                }
                int index = Integer.parseInt(key.substring(31));
                String nameKey = "subcustomerResponsibleName_" + index;
                String idKey = "subcustomerResponsibleIdNumber_" + index;
                String nameDocumentKey = "subcustomerResponsibleNameDocument_" + index;
                String numberDocumentKey = "subcustomerResponsibleNumberDocument_" + index;
                String dateDocumentKey = "subcustomerResponsibleDateDocument_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String idNum = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                subcustomerRes.setPosition(value);
                subcustomerRes.setName(name);
                subcustomerRes.setIdNumber(idNum);
                subcustomerRes.setNameDocument(nameDocument);
                subcustomerRes.setNumberDocument(numberDocument);
                subcustomerRes.setDateDocument(dateDocument);

                subcustomerRes.setPassportObjectData(passportObjectData);
                passportObjectData.getSubcustomerResponsibleData().add(subcustomerRes);
            }
            if (key.startsWith("subcustomerResponsible2Position_")) {
                String subcustomerRes2IdStr = key.substring("subcustomerResponsible2Position_".length());

                SubcustomerResponsible2Data subcustomerRes2;
                if (!subcustomerRes2IdStr.isEmpty()) {
                    Long subcustomerRes2Id = Long.parseLong(subcustomerRes2IdStr);
                    subcustomerRes2IdsFromForm.add(subcustomerRes2Id);

                    subcustomerRes2 = subcustomerResponsible2Repository.findById(subcustomerRes2Id).orElse(null);
                    if (subcustomerRes2 == null) {
                        subcustomerRes2 = new SubcustomerResponsible2Data();
                    }
                } else {
                    subcustomerRes2 = new SubcustomerResponsible2Data();
                }
                int index = Integer.parseInt(key.substring(32));
                String nameKey = "subcustomerResponsible2Name_" + index;
                String idKey = "subcustomerResponsible2IdNumber_" + index;
                String nameDocumentKey = "subcustomerResponsible2NameDocument_" + index;
                String numberDocumentKey = "subcustomerResponsible2NumberDocument_" + index;
                String dateDocumentKey = "subcustomerResponsible2DateDocument_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String idNum = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                subcustomerRes2.setPosition(value);
                subcustomerRes2.setName(name);
                subcustomerRes2.setIdNumber(idNum);
                subcustomerRes2.setNameDocument(nameDocument);
                subcustomerRes2.setNumberDocument(numberDocument);
                subcustomerRes2.setDateDocument(dateDocument);

                subcustomerRes2.setPassportObjectData(passportObjectData);
                passportObjectData.getSubcustomerResponsible2Data().add(subcustomerRes2);
            }
            if (key.startsWith("designerResponsiblePosition_")) {
                String designerResIdStr = key.substring("designerResponsiblePosition_".length());

                DesignerResponsibleData designerRes;
                if (!designerResIdStr.isEmpty()) {
                    Long designerResId = Long.parseLong(designerResIdStr);
                    designerResIdsFromForm.add(designerResId);

                    designerRes = designerResponsibleRepository.findById(designerResId).orElse(null);
                    if (designerRes == null) {
                        designerRes = new DesignerResponsibleData();
                    }
                } else {
                    designerRes = new DesignerResponsibleData();
                }
                int index = Integer.parseInt(key.substring(28));
                String nameKey = "designerResponsibleName_" + index;
                String idKey = "designerResponsibleIdNumber_" + index;
                String nameDocumentKey = "designerResponsibleNameDocument_" + index;
                String numberDocumentKey = "designerResponsibleNumberDocument_" + index;
                String dateDocumentKey = "designerResponsibleDateDocument_" + index;
                String nameCompanyKey = "designerResponsibleNameCompany_" + index;
                String ogrnCompanyKey = "designerResponsibleOgrnCompany_" + index;
                String innCompanyKey = "designerResponsibleInnCompany_" + index;
                String adrCompanyKey = "designerResponsibleAdrCompany_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String idNum = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                String nameCompany = formData.getOrDefault(nameCompanyKey, "");
                String ogrnCompany = formData.getOrDefault(ogrnCompanyKey, "");
                String innCompany = formData.getOrDefault(innCompanyKey, "");
                String adrCompany = formData.getOrDefault(adrCompanyKey, "");
                designerRes.setPosition(value);
                designerRes.setName(name);
                designerRes.setIdNumber(idNum);
                designerRes.setNameDocument(nameDocument);
                designerRes.setNumberDocument(numberDocument);
                designerRes.setDateDocument(dateDocument);
                designerRes.setNameCompany(nameCompany);
                designerRes.setOgrnCompany(ogrnCompany);
                designerRes.setInnCompany(innCompany);
                designerRes.setAdrCompany(adrCompany);

                designerRes.setPassportObjectData(passportObjectData);
                passportObjectData.getDesignerResponsibleData().add(designerRes);
            }
            if (key.startsWith("anotherPersonPosition_")) {
                String anotherPersonResIdStr = key.substring("anotherPersonPosition_".length());

                AnotherPersonResponsibleData anotherPersonRes;
                if (!anotherPersonResIdStr.isEmpty()) {
                    Long anotherPersonResId = Long.parseLong(anotherPersonResIdStr);
                    anotherPersonResIdsFromForm.add(anotherPersonResId);

                    anotherPersonRes = anotherPersonResponsibleRepository.findById(anotherPersonResId).orElse(null);
                    if (anotherPersonRes == null) {
                        anotherPersonRes = new AnotherPersonResponsibleData();
                    }
                } else {
                    anotherPersonRes = new AnotherPersonResponsibleData();
                }
                int index = Integer.parseInt(key.substring(22));
                String nameKey = "anotherPersonName_" + index;
                String idKey = "anotherPersonIdNumber_" + index;
                String nameDocumentKey = "anotherPersonNameDocument_" + index;
                String numberDocumentKey = "anotherPersonNumberDocument_" + index;
                String dateDocumentKey = "anotherPersonDateDocument_" + index;
                String nameCompanyKey = "anotherPersonNameCompany_" + index;
                String ogrnCompanyKey = "anotherPersonOgrnCompany_" + index;
                String innCompanyKey = "anotherPersonInnCompany_" + index;
                String adrCompanyKey = "anotherPersonAdrCompany_" + index;
                String name = formData.getOrDefault(nameKey, "");
                String idNum = formData.getOrDefault(idKey, "");
                String nameDocument = formData.getOrDefault(nameDocumentKey, "");
                String numberDocument = formData.getOrDefault(numberDocumentKey, "");
                String dateDocument = formData.getOrDefault(dateDocumentKey, "");
                String nameCompany = formData.getOrDefault(nameCompanyKey, "");
                String ogrnCompany = formData.getOrDefault(ogrnCompanyKey, "");
                String innCompany = formData.getOrDefault(innCompanyKey, "");
                String adrCompany = formData.getOrDefault(adrCompanyKey, "");
                anotherPersonRes.setPosition(value);
                anotherPersonRes.setName(name);
                anotherPersonRes.setIdNumber(idNum);
                anotherPersonRes.setNameDocument(nameDocument);
                anotherPersonRes.setNumberDocument(numberDocument);
                anotherPersonRes.setDateDocument(dateDocument);
                anotherPersonRes.setNameCompany(nameCompany);
                anotherPersonRes.setOgrnCompany(ogrnCompany);
                anotherPersonRes.setInnCompany(innCompany);
                anotherPersonRes.setAdrCompany(adrCompany);

                anotherPersonRes.setPassportObjectData(passportObjectData);
                passportObjectData.getAnotherPersonResponsibleData().add(anotherPersonRes);
            }
        });

        List<CustomerResponsibleData> customersToRemove = passportObjectData.getCustomerResponsibleData().stream()
                .filter(customerRes -> customerRes.getId() != null && !customerResIdsFromForm.contains(customerRes.getId()))
                .toList();

        customersToRemove.forEach(customerRes -> {
            passportObjectData.getCustomerResponsibleData().remove(customerRes);
            customerResponsibleRepository.delete(customerRes);
        });

        List<SubcustomerResponsibleData> subcustomersToRemove = passportObjectData.getSubcustomerResponsibleData().stream()
                .filter(subcustomerRes -> subcustomerRes.getId() != null && !subcustomerResIdsFromForm.contains(subcustomerRes.getId()))
                .toList();

        subcustomersToRemove.forEach(subcustomerRes -> {
            passportObjectData.getSubcustomerResponsibleData().remove(subcustomerRes);
            subcustomerResponsibleRepository.delete(subcustomerRes);
        });

        List<SubcustomerResponsible2Data> subcustomers2ToRemove = passportObjectData.getSubcustomerResponsible2Data().stream()
                .filter(subcustomerRes2 -> subcustomerRes2.getId() != null && !subcustomerRes2IdsFromForm.contains(subcustomerRes2.getId()))
                .toList();

        subcustomers2ToRemove.forEach(subcustomerRes2 -> {
            passportObjectData.getSubcustomerResponsible2Data().remove(subcustomerRes2);
            subcustomerResponsible2Repository.delete(subcustomerRes2);
        });

        List<DesignerResponsibleData> designerToRemove = passportObjectData.getDesignerResponsibleData().stream()
                .filter(designerRes -> designerRes.getId() != null && !designerResIdsFromForm.contains(designerRes.getId()))
                .toList();

        designerToRemove.forEach(designerRes -> {
            passportObjectData.getDesignerResponsibleData().remove(designerRes);
            designerResponsibleRepository.delete(designerRes);
        });

        List<AnotherPersonResponsibleData> anotherPersonToRemove = passportObjectData.getAnotherPersonResponsibleData().stream()
                .filter(anotherPersonRes -> anotherPersonRes.getId() != null && !anotherPersonResIdsFromForm.contains(anotherPersonRes.getId()))
                .toList();

        anotherPersonToRemove.forEach(anotherPersonRes -> {
            passportObjectData.getAnotherPersonResponsibleData().remove(anotherPersonRes);
            anotherPersonResponsibleRepository.delete(anotherPersonRes);
        });

        passportObjectData.setNameObject(nameObject);
        passportObjectData.setAddressObject(addressObject);
        passportObjectData.setProjectCode(projectCode);
        passportObjectData.setNameCustomer(nameCustomer);
        passportObjectData.setOgrnCustomer(ogrnCustomer);
        passportObjectData.setInnCustomer(innCustomer);
        passportObjectData.setAddressCustomer(addressCustomer);
        passportObjectData.setPhoneCustomer(phoneCustomer);
        passportObjectData.setNameOrganizationCustomer(nameOrganizationCustomer);
        passportObjectData.setOgrnOrganizationCustomer(ogrnOrganizationCustomer);
        passportObjectData.setInnOrganizationCustomer(innOrganizationCustomer);
        passportObjectData.setNameContractor(nameContractor);
        passportObjectData.setOgrnContractor(ogrnContractor);
        passportObjectData.setInnContractor(innContractor);
        passportObjectData.setAddressContractor(addressContractor);
        passportObjectData.setPhoneContractor(phoneContractor);
        passportObjectData.setNameOrganizationContractor(nameOrganizationContractor);
        passportObjectData.setOgrnOrganizationContractor(ogrnOrganizationContractor);
        passportObjectData.setInnOrganizationContractor(innOrganizationContractor);
        passportObjectData.setNameDesigner(nameDesigner);
        passportObjectData.setOgrnDesigner(ogrnDesigner);
        passportObjectData.setInnDesigner(innDesigner);
        passportObjectData.setAddressDesigner(addressDesigner);
        passportObjectData.setPhoneDesigner(phoneDesigner);
        passportObjectData.setNameOrganizationDesigner(nameOrganizationDesigner);
        passportObjectData.setOgrnOrganizationDesigner(ogrnOrganizationDesigner);
        passportObjectData.setInnOrganizationDesigner(innOrganizationDesigner);

        passportObjectRepository.save(passportObjectData);
        return "redirect:/passport-object-table";
    }

//    @PostMapping("/passport-object-table/{id}/passport-object-remove")
//    public String postPassportObjectDelete(@PathVariable(value = "id") long id, Model model) {
//        PassportObjectData passportObjectData = passportObjectRepository.findById(id).orElseThrow();
//        passportObjectRepository.delete(passportObjectData);
//        return "redirect:/passport-object-table";
//    }

    @PostMapping("/passport-object-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        PassportObjectData passObj = passportObjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        passObj.setStatus(status);
        passportObjectRepository.save(passObj);
        return "redirect:/passport-object-table";
    }
}