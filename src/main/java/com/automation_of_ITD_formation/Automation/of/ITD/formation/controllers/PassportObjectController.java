package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ActsViCData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.MaterialsUsedData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.PassportObjectData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.PassportObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PassportObjectController {

    @Autowired
    private PassportObjectRepository passportObjectRepository;

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
                                     @RequestParam("nameDeveloper") String nameDeveloper,
                                     @RequestParam("ogrnDeveloper") String ogrnDeveloper,
                                     @RequestParam("innDeveloper") String innDeveloper,
                                     @RequestParam("addressDeveloper") String addressDeveloper,
                                     @RequestParam("phoneDeveloper") String phoneDeveloper,
                                     @RequestParam("nameOrganizationDeveloper") String nameOrganizationDeveloper,
                                     @RequestParam("ogrnOrganizationDeveloper") String ogrnOrganizationDeveloper,
                                     @RequestParam("innOrganizationDeveloper") String innOrganizationDeveloper,
                                     @RequestParam("nameBuilder") String nameBuilder,
                                     @RequestParam("ogrnBuilder") String ogrnBuilder,
                                     @RequestParam("innBuilder") String innBuilder,
                                     @RequestParam("addressBuilder") String addressBuilder,
                                     @RequestParam("phoneBuilder") String phoneBuilder,
                                     @RequestParam("nameOrganizationBuilder") String nameOrganizationBuilder,
                                     @RequestParam("ogrnOrganizationBuilder") String ogrnOrganizationBuilder,
                                     @RequestParam("innOrganizationBuilder") String innOrganizationBuilder,
                                     @RequestParam("namePreparer") String namePreparer,
                                     @RequestParam("ogrnPreparer") String ogrnPreparer,
                                     @RequestParam("innPreparer") String innPreparer,
                                     @RequestParam("addressPreparer") String addressPreparer,
                                     @RequestParam("phonePreparer") String phonePreparer,
                                     @RequestParam("nameOrganizationPreparer") String nameOrganizationPreparer,
                                     @RequestParam("ogrnOrganizationPreparer") String ogrnOrganizationPreparer,
                                     @RequestParam("innOrganizationPreparer") String innOrganizationPreparer,
                                     @RequestParam("postCustomer") String postCustomer,
                                     @RequestParam("fioCustomer") String fioCustomer,
                                     @RequestParam("idCustomer") String idCustomer,
                                     @RequestParam("nameCustomer") String nameCustomer,
                                     @RequestParam("numberCustomer") String numberCustomer,
                                     @RequestParam("dataCustomer") String dataCustomer,
                                     @RequestParam("nameOrganizationCustomer") String nameOrganizationCustomer,
                                     @RequestParam("ogrnOrganizationCustomer") String ogrnOrganizationCustomer,
                                     @RequestParam("innOrganizationCustomer") String innOrganizationCustomer,
                                     @RequestParam("adrOrganizationCustomer") String adrOrganizationCustomer,
                                     @RequestParam("postContractor") String postContractor,
                                     @RequestParam("fioContractor") String fioContractor,
                                     @RequestParam("idContractor") String idContractor,
                                     @RequestParam("nameContractor") String nameContractor,
                                     @RequestParam("numberContractor") String numberContractor,
                                     @RequestParam("dataContractor") String dataContractor,
                                     @RequestParam("postContractor2") String postContractor2,
                                     @RequestParam("fioContractor2") String fioContractor2,
                                     @RequestParam("idContractor2") String idContractor2,
                                     @RequestParam("nameContractor2") String nameContractor2,
                                     @RequestParam("numberContractor2") String numberContractor2,
                                     @RequestParam("dataContractor2") String dataContractor2,
                                     @RequestParam("postProjector") String postProjector,
                                     @RequestParam("fioProjector") String fioProjector,
                                     @RequestParam("idProjector") String idProjector,
                                     @RequestParam("nameProjector") String nameProjector,
                                     @RequestParam("numberProjector") String numberProjector,
                                     @RequestParam("dataProjector") String dataProjector,
                                     @RequestParam("nameOrganizationProjector") String nameOrganizationProjector,
                                     @RequestParam("ogrnOrganizationProjector") String ogrnOrganizationProjector,
                                     @RequestParam("innOrganizationProjector") String innOrganizationProjector,
                                     @RequestParam("adrOrganizationProjector") String adrOrganizationProjector,
                                     @RequestParam("postAnotherPerson") String postAnotherPerson,
                                     @RequestParam("fioAnotherPerson") String fioAnotherPerson,
                                     @RequestParam("idAnotherPerson") String idAnotherPerson,
                                     @RequestParam("nameAnotherPerson") String nameAnotherPerson,
                                     @RequestParam("numberAnotherPerson") String numberAnotherPerson,
                                     @RequestParam("dataAnotherPerson") String dataAnotherPerson,
                                     @RequestParam("nameOrganizationAnotherPerson") String nameOrganizationAnotherPerson,
                                     @RequestParam("ogrnOrganizationAnotherPerson") String ogrnOrganizationAnotherPerson,
                                     @RequestParam("innOrganizationAnotherPerson") String innOrganizationAnotherPerson,
                                     @RequestParam("adrOrganizationAnotherPerson") String adrOrganizationAnotherPerson) {
        PassportObjectData passportObjectData = new PassportObjectData(nameObject, addressObject, projectCode, nameDeveloper, ogrnDeveloper, innDeveloper, addressDeveloper, phoneDeveloper, nameOrganizationDeveloper, ogrnOrganizationDeveloper, innOrganizationDeveloper, nameBuilder, ogrnBuilder, innBuilder, addressBuilder, phoneBuilder, nameOrganizationBuilder, ogrnOrganizationBuilder, innOrganizationBuilder, namePreparer, ogrnPreparer, innPreparer, addressPreparer, phonePreparer, nameOrganizationPreparer, ogrnOrganizationPreparer, innOrganizationPreparer,  postCustomer, fioCustomer, idCustomer, nameCustomer,  numberCustomer, dataCustomer, nameOrganizationCustomer, ogrnOrganizationCustomer, innOrganizationCustomer, adrOrganizationCustomer, postContractor, fioContractor, idContractor, nameContractor, numberContractor, dataContractor, postContractor2, fioContractor2, idContractor2, nameContractor2, numberContractor2, dataContractor2, postProjector, fioProjector, idProjector, nameProjector, numberProjector, dataProjector, nameOrganizationProjector, ogrnOrganizationProjector, innOrganizationProjector, adrOrganizationProjector, postAnotherPerson, fioAnotherPerson, idAnotherPerson, nameAnotherPerson, numberAnotherPerson, dataAnotherPerson, nameOrganizationAnotherPerson, ogrnOrganizationAnotherPerson, innOrganizationAnotherPerson, adrOrganizationAnotherPerson);
        passportObjectRepository.save(passportObjectData);

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
        model.addAttribute("passportObjectDataOptional", res);
        return "passportObjectEdit";
    }

    @PostMapping("/passport-object-table/{id}/passport-object-edit")
    public String postPassportObjectUpdate(@PathVariable(value = "id") long id,
                                           @RequestParam("nameObject") String nameObject,
                                           @RequestParam("addressObject") String addressObject,
                                           @RequestParam("projectCode") String projectCode,
                                           @RequestParam("nameDeveloper") String nameDeveloper,
                                           @RequestParam("ogrnDeveloper") String ogrnDeveloper,
                                           @RequestParam("innDeveloper") String innDeveloper,
                                           @RequestParam("addressDeveloper") String addressDeveloper,
                                           @RequestParam("phoneDeveloper") String phoneDeveloper,
                                           @RequestParam("nameOrganizationDeveloper") String nameOrganizationDeveloper,
                                           @RequestParam("ogrnOrganizationDeveloper") String ogrnOrganizationDeveloper,
                                           @RequestParam("innOrganizationDeveloper") String innOrganizationDeveloper,
                                           @RequestParam("nameBuilder") String nameBuilder,
                                           @RequestParam("ogrnBuilder") String ogrnBuilder,
                                           @RequestParam("innBuilder") String innBuilder,
                                           @RequestParam("addressBuilder") String addressBuilder,
                                           @RequestParam("phoneBuilder") String phoneBuilder,
                                           @RequestParam("nameOrganizationBuilder") String nameOrganizationBuilder,
                                           @RequestParam("ogrnOrganizationBuilder") String ogrnOrganizationBuilder,
                                           @RequestParam("innOrganizationBuilder") String innOrganizationBuilder,
                                           @RequestParam("namePreparer") String namePreparer,
                                           @RequestParam("ogrnPreparer") String ogrnPreparer,
                                           @RequestParam("innPreparer") String innPreparer,
                                           @RequestParam("addressPreparer") String addressPreparer,
                                           @RequestParam("phonePreparer") String phonePreparer,
                                           @RequestParam("nameOrganizationPreparer") String nameOrganizationPreparer,
                                           @RequestParam("ogrnOrganizationPreparer") String ogrnOrganizationPreparer,
                                           @RequestParam("innOrganizationPreparer") String innOrganizationPreparer,
                                           @RequestParam("postCustomer") String postCustomer,
                                           @RequestParam("fioCustomer") String fioCustomer,
                                           @RequestParam("idCustomer") String idCustomer,
                                           @RequestParam("nameCustomer") String nameCustomer,
                                           @RequestParam("numberCustomer") String numberCustomer,
                                           @RequestParam("dataCustomer") String dataCustomer,
                                           @RequestParam("nameOrganizationCustomer") String nameOrganizationCustomer,
                                           @RequestParam("ogrnOrganizationCustomer") String ogrnOrganizationCustomer,
                                           @RequestParam("innOrganizationCustomer") String innOrganizationCustomer,
                                           @RequestParam("adrOrganizationCustomer") String adrOrganizationCustomer,
                                           @RequestParam("postContractor") String postContractor,
                                           @RequestParam("fioContractor") String fioContractor,
                                           @RequestParam("idContractor") String idContractor,
                                           @RequestParam("nameContractor") String nameContractor,
                                           @RequestParam("numberContractor") String numberContractor,
                                           @RequestParam("dataContractor") String dataContractor,
                                           @RequestParam("postContractor2") String postContractor2,
                                           @RequestParam("fioContractor2") String fioContractor2,
                                           @RequestParam("idContractor2") String idContractor2,
                                           @RequestParam("nameContractor2") String nameContractor2,
                                           @RequestParam("numberContractor2") String numberContractor2,
                                           @RequestParam("dataContractor2") String dataContractor2,
                                           @RequestParam("postProjector") String postProjector,
                                           @RequestParam("fioProjector") String fioProjector,
                                           @RequestParam("idProjector") String idProjector,
                                           @RequestParam("nameProjector") String nameProjector,
                                           @RequestParam("numberProjector") String numberProjector,
                                           @RequestParam("dataProjector") String dataProjector,
                                           @RequestParam("nameOrganizationProjector") String nameOrganizationProjector,
                                           @RequestParam("ogrnOrganizationProjector") String ogrnOrganizationProjector,
                                           @RequestParam("innOrganizationProjector") String innOrganizationProjector,
                                           @RequestParam("adrOrganizationProjector") String adrOrganizationProjector,
                                           @RequestParam("postAnotherPerson") String postAnotherPerson,
                                           @RequestParam("fioAnotherPerson") String fioAnotherPerson,
                                           @RequestParam("idAnotherPerson") String idAnotherPerson,
                                           @RequestParam("nameAnotherPerson") String nameAnotherPerson,
                                           @RequestParam("numberAnotherPerson") String numberAnotherPerson,
                                           @RequestParam("dataAnotherPerson") String dataAnotherPerson,
                                           @RequestParam("nameOrganizationAnotherPerson") String nameOrganizationAnotherPerson,
                                           @RequestParam("ogrnOrganizationAnotherPerson") String ogrnOrganizationAnotherPerson,
                                           @RequestParam("innOrganizationAnotherPerson") String innOrganizationAnotherPerson,
                                           @RequestParam("adrOrganizationAnotherPerson") String adrOrganizationAnotherPerson) {
        PassportObjectData passportObjectData = passportObjectRepository.findById(id).orElseThrow();
        passportObjectData.setNameObject(nameObject);
        passportObjectData.setAddressObject(addressObject);
        passportObjectData.setProjectCode(projectCode);
        passportObjectData.setNameDeveloper(nameDeveloper);
        passportObjectData.setOgrnDeveloper(ogrnDeveloper);
        passportObjectData.setInnDeveloper(innDeveloper);
        passportObjectData.setAddressDeveloper(addressDeveloper);
        passportObjectData.setPhoneDeveloper(phoneDeveloper);
        passportObjectData.setNameOrganizationDeveloper(nameOrganizationDeveloper);
        passportObjectData.setOgrnOrganizationDeveloper(ogrnOrganizationDeveloper);
        passportObjectData.setInnOrganizationDeveloper(innOrganizationDeveloper);
        passportObjectData.setNameBuilder(nameBuilder);
        passportObjectData.setOgrnBuilder(ogrnBuilder);
        passportObjectData.setInnBuilder(innBuilder);
        passportObjectData.setAddressBuilder(addressBuilder);
        passportObjectData.setPhoneBuilder(phoneBuilder);
        passportObjectData.setNameOrganizationBuilder(nameOrganizationBuilder);
        passportObjectData.setOgrnOrganizationBuilder(ogrnOrganizationBuilder);
        passportObjectData.setInnOrganizationBuilder(innOrganizationBuilder);
        passportObjectData.setNamePreparer(namePreparer);
        passportObjectData.setOgrnPreparer(ogrnPreparer);
        passportObjectData.setInnPreparer(innPreparer);
        passportObjectData.setAddressPreparer(addressPreparer);
        passportObjectData.setPhonePreparer(phonePreparer);
        passportObjectData.setNameOrganizationPreparer(nameOrganizationPreparer);
        passportObjectData.setOgrnOrganizationPreparer(ogrnOrganizationPreparer);
        passportObjectData.setInnOrganizationPreparer(innOrganizationPreparer);
        passportObjectData.setPostCustomer(postCustomer);
        passportObjectData.setFioCustomer(fioCustomer);
        passportObjectData.setIdCustomer(idCustomer);
        passportObjectData.setNameCustomer(nameCustomer);
        passportObjectData.setNumberCustomer(numberCustomer);
        passportObjectData.setDataCustomer(dataCustomer);
        passportObjectData.setNameOrganizationCustomer(nameOrganizationCustomer);
        passportObjectData.setOgrnOrganizationCustomer(ogrnOrganizationCustomer);
        passportObjectData.setInnOrganizationCustomer(innOrganizationCustomer);
        passportObjectData.setAdrOrganizationCustomer(adrOrganizationCustomer);
        passportObjectData.setPostContractor(postContractor);
        passportObjectData.setFioContractor(fioContractor);
        passportObjectData.setIdContractor(idContractor);
        passportObjectData.setNameContractor(nameContractor);
        passportObjectData.setNumberContractor(numberContractor);
        passportObjectData.setDataContractor(dataContractor);
        passportObjectData.setPostContractor2(postContractor2);
        passportObjectData.setFioContractor2(fioContractor2);
        passportObjectData.setIdContractor2(idContractor2);
        passportObjectData.setNameContractor2(nameContractor2);
        passportObjectData.setNumberContractor2(numberContractor2);
        passportObjectData.setDataContractor2(dataContractor2);
        passportObjectData.setPostProjector(postProjector);
        passportObjectData.setFioProjector(fioProjector);
        passportObjectData.setIdProjector(idProjector);
        passportObjectData.setNameProjector(nameProjector);
        passportObjectData.setNumberProjector(numberProjector);
        passportObjectData.setDataProjector(dataProjector);
        passportObjectData.setNameOrganizationProjector(nameOrganizationProjector);
        passportObjectData.setOgrnOrganizationProjector(ogrnOrganizationProjector);
        passportObjectData.setInnOrganizationProjector(innOrganizationProjector);
        passportObjectData.setAdrOrganizationProjector(adrOrganizationProjector);
        passportObjectData.setPostAnotherPerson(postAnotherPerson);
        passportObjectData.setFioAnotherPerson(fioAnotherPerson);
        passportObjectData.setIdAnotherPerson(idAnotherPerson);
        passportObjectData.setNameAnotherPerson(nameAnotherPerson);
        passportObjectData.setNumberAnotherPerson(numberAnotherPerson);
        passportObjectData.setDataAnotherPerson(dataAnotherPerson);
        passportObjectData.setNameOrganizationAnotherPerson(nameOrganizationAnotherPerson);
        passportObjectData.setOgrnOrganizationAnotherPerson(ogrnOrganizationAnotherPerson);
        passportObjectData.setInnOrganizationAnotherPerson(innOrganizationAnotherPerson);
        passportObjectData.setAdrOrganizationAnotherPerson(adrOrganizationAnotherPerson);
        passportObjectRepository.save(passportObjectData);
        return "redirect:/passport-object-table";
    }

    @PostMapping("/passport-object-table/{id}/passport-object-remove")
    public String postPassportObjectDelete(@PathVariable(value = "id") long id, Model model) {
        PassportObjectData passportObjectData = passportObjectRepository.findById(id).orElseThrow();
        passportObjectRepository.delete(passportObjectData);
        return "redirect:/passport-object-table";
    }

    @PostMapping("/passport-object-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        PassportObjectData passObj = passportObjectRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        passObj.setStatus(status);
        passportObjectRepository.save(passObj);
        return "redirect:/passport-object-table";
    }
}