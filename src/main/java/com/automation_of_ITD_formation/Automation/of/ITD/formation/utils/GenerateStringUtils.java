package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateStringUtils {
    public static String generateCapitalConstructionProject(PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passportObjectData.getNameObject() != null && !passportObjectData.getNameObject().isEmpty()) {
            stringBuilder.append(passportObjectData.getNameObject());
        }
        if (passportObjectData.getNameCustomer() != null && !passportObjectData.getNameCustomer().isEmpty()) {
            stringBuilder.append(" ").append(passportObjectData.getNameCustomer());
        }
        if (passportObjectData.getOgrnCustomer() != null && !passportObjectData.getOgrnCustomer().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnCustomer());
        }
        if (passportObjectData.getInnCustomer() != null && !passportObjectData.getInnCustomer().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnCustomer());
        }
        if (passportObjectData.getAddressCustomer() != null && !passportObjectData.getAddressCustomer().isEmpty()) {
            stringBuilder.append(", ").append(passportObjectData.getAddressCustomer());
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateCustomer(PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passportObjectData.getNameCustomer() != null && !passportObjectData.getNameCustomer().isEmpty()) {
            stringBuilder.append(passportObjectData.getNameCustomer());
        }
        if (passportObjectData.getOgrnCustomer() != null && !passportObjectData.getOgrnCustomer().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnCustomer());
        }
        if (passportObjectData.getInnCustomer() != null && !passportObjectData.getInnCustomer().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnCustomer());
        }
        if (passportObjectData.getAddressCustomer() != null && !passportObjectData.getAddressCustomer().isEmpty()) {
            stringBuilder.append(", ").append(passportObjectData.getAddressCustomer());
        }
        if (passportObjectData.getPhoneCustomer() != null && !passportObjectData.getPhoneCustomer().isEmpty()) {
            stringBuilder.append(", телефон/факс: ").append(passportObjectData.getPhoneCustomer());
        }
        if (passportObjectData.getNameOrganizationCustomer() != null && !passportObjectData.getNameOrganizationCustomer().isEmpty()) {
            stringBuilder.append("; ").append(passportObjectData.getNameOrganizationCustomer());
        }
        if (passportObjectData.getOgrnOrganizationCustomer() != null && !passportObjectData.getOgrnOrganizationCustomer().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnOrganizationCustomer());
        }
        if (passportObjectData.getInnOrganizationCustomer() != null && !passportObjectData.getInnOrganizationCustomer().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnOrganizationCustomer());
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateContractor(PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passportObjectData.getNameContractor() != null && !passportObjectData.getNameContractor().isEmpty()) {
            stringBuilder.append(passportObjectData.getNameContractor());
        }
        if (passportObjectData.getOgrnContractor() != null && !passportObjectData.getOgrnContractor().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnContractor());
        }
        if (passportObjectData.getInnContractor() != null && !passportObjectData.getInnContractor().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnContractor());
        }
        if (passportObjectData.getAddressContractor() != null && !passportObjectData.getAddressContractor().isEmpty()) {
            stringBuilder.append(", ").append(passportObjectData.getAddressContractor());
        }
        if (passportObjectData.getPhoneContractor() != null && !passportObjectData.getPhoneContractor().isEmpty()) {
            stringBuilder.append(", телефон/факс: ").append(passportObjectData.getPhoneContractor());
        }
        if (passportObjectData.getNameOrganizationContractor() != null && !passportObjectData.getNameOrganizationContractor().isEmpty()) {
            stringBuilder.append("; ").append(passportObjectData.getNameOrganizationContractor());
        }
        if (passportObjectData.getOgrnOrganizationContractor() != null && !passportObjectData.getOgrnOrganizationContractor().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnOrganizationContractor());
        }
        if (passportObjectData.getInnOrganizationContractor() != null && !passportObjectData.getInnOrganizationContractor().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnOrganizationContractor());
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateDesigner(PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passportObjectData.getNameDesigner() != null && !passportObjectData.getNameDesigner().isEmpty()) {
            stringBuilder.append(passportObjectData.getNameDesigner());
        }
        if (passportObjectData.getOgrnDesigner() != null && !passportObjectData.getOgrnDesigner().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnDesigner());
        }
        if (passportObjectData.getInnDesigner() != null && !passportObjectData.getInnDesigner().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnDesigner());
        }
        if (passportObjectData.getAddressDesigner() != null && !passportObjectData.getAddressDesigner().isEmpty()) {
            stringBuilder.append(", ").append(passportObjectData.getAddressDesigner());
        }
        if (passportObjectData.getPhoneDesigner() != null && !passportObjectData.getPhoneDesigner().isEmpty()) {
            stringBuilder.append(", телефон/факс: ").append(passportObjectData.getPhoneDesigner());
        }
        if (passportObjectData.getNameOrganizationDesigner() != null && !passportObjectData.getNameOrganizationDesigner().isEmpty()) {
            stringBuilder.append("; ").append(passportObjectData.getNameOrganizationDesigner());
        }
        if (passportObjectData.getOgrnOrganizationDesigner() != null && !passportObjectData.getOgrnOrganizationDesigner().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(passportObjectData.getOgrnOrganizationDesigner());
        }
        if (passportObjectData.getInnOrganizationDesigner() != null && !passportObjectData.getInnOrganizationDesigner().isEmpty()) {
            stringBuilder.append(", ИНН ").append(passportObjectData.getInnOrganizationDesigner());
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateCustomerResponsible(CustomerResponsibleData customerResponsibleData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (customerResponsibleData.getPosition() != null && !customerResponsibleData.getPosition().isEmpty()) {
            stringBuilder.append(customerResponsibleData.getPosition());
        }
        if (customerResponsibleData.getName() != null && !customerResponsibleData.getName().isEmpty()) {
            stringBuilder.append(" ").append(customerResponsibleData.getName());
        }
        if (customerResponsibleData.getIdNumber() != null && !customerResponsibleData.getIdNumber().isEmpty()) {
            stringBuilder.append(", ").append(customerResponsibleData.getIdNumber());
        }
        if (customerResponsibleData.getNameDocument() != null && !customerResponsibleData.getNameDocument().isEmpty()) {
            stringBuilder.append(", ").append(customerResponsibleData.getNameDocument());
        }
        if (customerResponsibleData.getNumberDocument() != null && !customerResponsibleData.getNumberDocument().isEmpty()) {
            stringBuilder.append(", № ").append(customerResponsibleData.getNumberDocument());
        }
        if (customerResponsibleData.getDateDocument() != null && !customerResponsibleData.getDateDocument().isEmpty()) {
            stringBuilder.append(" от ").append(customerResponsibleData.getDateDocument()).append(" г.");
        }
        if (customerResponsibleData.getNameCompany() != null && !customerResponsibleData.getNameCompany().isEmpty()) {
            stringBuilder.append(", ").append(customerResponsibleData.getNameCompany());
        }
        if (customerResponsibleData.getOgrnCompany() != null && !customerResponsibleData.getOgrnCompany().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(customerResponsibleData.getOgrnCompany());
        }
        if (customerResponsibleData.getInnCompany() != null && !customerResponsibleData.getInnCompany().isEmpty()) {
            stringBuilder.append(", ИНН ").append(customerResponsibleData.getInnCompany());
        }
        if (customerResponsibleData.getAdrCompany() != null && !customerResponsibleData.getAdrCompany().isEmpty()) {
            stringBuilder.append(", ").append(customerResponsibleData.getAdrCompany());
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateSubcustomerResponsible(SubcustomerResponsibleData subcustomerResponsibleData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (subcustomerResponsibleData.getPosition() != null && !subcustomerResponsibleData.getPosition().isEmpty()) {
            stringBuilder.append(subcustomerResponsibleData.getPosition());
        }
        if (subcustomerResponsibleData.getName() != null && !subcustomerResponsibleData.getName().isEmpty()) {
            stringBuilder.append(" ").append(subcustomerResponsibleData.getName());
        }
        if (subcustomerResponsibleData.getIdNumber() != null && !subcustomerResponsibleData.getIdNumber().isEmpty()) {
            stringBuilder.append(", ").append(subcustomerResponsibleData.getIdNumber());
        }
        if (subcustomerResponsibleData.getNameDocument() != null && !subcustomerResponsibleData.getNameDocument().isEmpty()) {
            stringBuilder.append(", ").append(subcustomerResponsibleData.getNameDocument());
        }
        if (subcustomerResponsibleData.getNumberDocument() != null && !subcustomerResponsibleData.getNumberDocument().isEmpty()) {
            stringBuilder.append(", № ").append(subcustomerResponsibleData.getNumberDocument());
        }
        if (subcustomerResponsibleData.getDateDocument() != null && !subcustomerResponsibleData.getDateDocument().isEmpty()) {
            stringBuilder.append(" от ").append(subcustomerResponsibleData.getDateDocument()).append(" г.");
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateSubcustomer2Responsible(SubcustomerResponsible2Data subcustomerResponsible2Data) {
        StringBuilder stringBuilder = new StringBuilder();
        if (subcustomerResponsible2Data.getPosition() != null && !subcustomerResponsible2Data.getPosition().isEmpty()) {
            stringBuilder.append(subcustomerResponsible2Data.getPosition());
        }
        if (subcustomerResponsible2Data.getName() != null && !subcustomerResponsible2Data.getName().isEmpty()) {
            stringBuilder.append(" ").append(subcustomerResponsible2Data.getName());
        }
        if (subcustomerResponsible2Data.getIdNumber() != null && !subcustomerResponsible2Data.getIdNumber().isEmpty()) {
            stringBuilder.append(", ").append(subcustomerResponsible2Data.getIdNumber());
        }
        if (subcustomerResponsible2Data.getNameDocument() != null && !subcustomerResponsible2Data.getNameDocument().isEmpty()) {
            stringBuilder.append(", ").append(subcustomerResponsible2Data.getNameDocument());
        }
        if (subcustomerResponsible2Data.getNumberDocument() != null && !subcustomerResponsible2Data.getNumberDocument().isEmpty()) {
            stringBuilder.append(", № ").append(subcustomerResponsible2Data.getNumberDocument());
        }
        if (subcustomerResponsible2Data.getDateDocument() != null && !subcustomerResponsible2Data.getDateDocument().isEmpty()) {
            stringBuilder.append(" от ").append(subcustomerResponsible2Data.getDateDocument()).append(" г.");
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateDesignerResponsible(DesignerResponsibleData designerResponsibleData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (designerResponsibleData.getPosition() != null && !designerResponsibleData.getPosition().isEmpty()) {
            stringBuilder.append(designerResponsibleData.getPosition());
        }
        if (designerResponsibleData.getName() != null && !designerResponsibleData.getName().isEmpty()) {
            stringBuilder.append(" ").append(designerResponsibleData.getName());
        }
        if (designerResponsibleData.getIdNumber() != null && !designerResponsibleData.getIdNumber().isEmpty()) {
            stringBuilder.append(", ").append(designerResponsibleData.getIdNumber());
        }
        if (designerResponsibleData.getNameDocument() != null && !designerResponsibleData.getNameDocument().isEmpty()) {
            stringBuilder.append(", ").append(designerResponsibleData.getNameDocument());
        }
        if (designerResponsibleData.getNumberDocument() != null && !designerResponsibleData.getNumberDocument().isEmpty()) {
            stringBuilder.append(", № ").append(designerResponsibleData.getNumberDocument());
        }
        if (designerResponsibleData.getDateDocument() != null && !designerResponsibleData.getDateDocument().isEmpty()) {
            stringBuilder.append(" от ").append(designerResponsibleData.getDateDocument()).append(" г.");
        }
        if (designerResponsibleData.getNameCompany() != null && !designerResponsibleData.getNameCompany().isEmpty()) {
            stringBuilder.append(", ").append(designerResponsibleData.getNameCompany());
        }
        if (designerResponsibleData.getOgrnCompany() != null && !designerResponsibleData.getOgrnCompany().isEmpty()) {
            stringBuilder.append(", ОГРН ").append(designerResponsibleData.getOgrnCompany());
        }
        if (designerResponsibleData.getInnCompany() != null && !designerResponsibleData.getInnCompany().isEmpty()) {
            stringBuilder.append(", ИНН ").append(designerResponsibleData.getInnCompany());
        }
        if (designerResponsibleData.getAdrCompany() != null && !designerResponsibleData.getAdrCompany().isEmpty()) {
            stringBuilder.append(", ").append(designerResponsibleData.getAdrCompany());
        }
        return stringBuilder.isEmpty() ? "" : stringBuilder.toString();
    }

    public static String generateAnotherPersonResponsible(AnotherPersonResponsibleData anotherPersonResponsibleData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (anotherPersonResponsibleData.getPosition() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getPosition());
        }
        if (anotherPersonResponsibleData.getName() != null) {
            stringBuilder.append(" ").append(anotherPersonResponsibleData.getName());
        }
        if (anotherPersonResponsibleData.getIdNumber() != null) {
            stringBuilder.append(", ").append(anotherPersonResponsibleData.getIdNumber());
        }
        if (anotherPersonResponsibleData.getNameDocument() != null) {
            stringBuilder.append(", ").append(anotherPersonResponsibleData.getNameDocument());
        }
        if (anotherPersonResponsibleData.getNumberDocument() != null) {
            stringBuilder.append(" № ").append(anotherPersonResponsibleData.getNumberDocument());
        }
        if (anotherPersonResponsibleData.getDateDocument() != null) {
            stringBuilder.append(" от ").append(anotherPersonResponsibleData.getDateDocument()).append(" г.");
        }
        if (anotherPersonResponsibleData.getNameCompany() != null) {
            stringBuilder.append(", ").append(anotherPersonResponsibleData.getNameCompany());
        }
        if (anotherPersonResponsibleData.getOgrnCompany() != null) {
            stringBuilder.append(", ОГРН ").append(anotherPersonResponsibleData.getOgrnCompany());
        }
        if (anotherPersonResponsibleData.getInnCompany() != null) {
            stringBuilder.append(", ИНН ").append(anotherPersonResponsibleData.getInnCompany());
        }
        if (anotherPersonResponsibleData.getAdrCompany() != null) {
            stringBuilder.append(", ").append(anotherPersonResponsibleData.getAdrCompany());
        }
        return stringBuilder.toString();
    }

    public static String generateProjectCode(PassportObjectData passportObjectData, int number) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passportObjectData.getProjectCode() != null && !passportObjectData.getProjectCode().isEmpty()) {
            stringBuilder.append(passportObjectData.getProjectCode()).append("-");
        }
        if (number < 10) {
            stringBuilder.append("0");
        }
        stringBuilder.append(number);
        return stringBuilder.toString();
    }

    public static String generateNameOrganization(PassportObjectData passportObjectData) {
        return passportObjectData.getNameContractor() != null && !passportObjectData.getNameContractor().isEmpty() ? passportObjectData.getNameContractor() : "";
    }

    public static String generateNameOfHiddenWork(String nameNetwork, String work1, ExecutiveSchemesData executiveSchemesData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (work1 != null && work1.isEmpty()) {
            stringBuilder.append(work1);
        }
        if (executiveSchemesData.getSection() != null && !executiveSchemesData.getSection().isEmpty()) {
            stringBuilder.append(" на участках ").append(executiveSchemesData.getSection());
        }
        if (executiveSchemesData.getStartCoordinateX() != null && !executiveSchemesData.getStartCoordinateX().isEmpty()) {
            stringBuilder.append(" в координатах X=").append(executiveSchemesData.getStartCoordinateX());
        }
        if (executiveSchemesData.getStartCoordinateY() != null && !executiveSchemesData.getStartCoordinateY().isEmpty()) {
            stringBuilder.append(", Y=").append(executiveSchemesData.getStartCoordinateY());
        }
        if (executiveSchemesData.getEndCoordinateX() != null && !executiveSchemesData.getEndCoordinateX().isEmpty()) {
            stringBuilder.append(" / X=").append(executiveSchemesData.getEndCoordinateX());
        }
        if (executiveSchemesData.getEndCoordinateY() != null && !executiveSchemesData.getEndCoordinateY().isEmpty()) {
            stringBuilder.append(", Y=").append(executiveSchemesData.getEndCoordinateY());
        }
        if (executiveSchemesData.getWellCenterX() != null && !executiveSchemesData.getWellCenterX().isEmpty()) {
            stringBuilder.append(" в координатах X=").append(executiveSchemesData.getWellCenterX());
        }
        if (executiveSchemesData.getWellCenterY() != null && !executiveSchemesData.getWellCenterY().isEmpty()) {
            stringBuilder.append(", Y=").append(executiveSchemesData.getWellCenterY());
        }
        if (executiveSchemesData.getStartingPoint() != null && !executiveSchemesData.getStartingPoint().isEmpty()) {
            stringBuilder.append(" в отметках ").append(executiveSchemesData.getStartingPoint()).append(" / ");
        }
        if (executiveSchemesData.getEndingPoint() != null && !executiveSchemesData.getEndingPoint().isEmpty()) {
            stringBuilder.append(executiveSchemesData.getEndingPoint()).append(";");
        }
        if (nameNetwork != null && !nameNetwork.isEmpty()) {
            stringBuilder.append(" сеть ").append(nameNetwork);
        }
        return !stringBuilder.isEmpty() ? stringBuilder.toString() : "";
    }

    public static String generateDrawings(Set<ProjectDocumentationData> projectDocumentationSet, Set<DrawingsData> drawings, PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (drawings != null && !drawings.isEmpty()) {
            stringBuilder.append("чертеж ");
            for (DrawingsData drawing : drawings) {
                for (ProjectDocumentationData projectDocumentationData : projectDocumentationSet) {
                    if (projectDocumentationData.getProjectSection().equals(drawing.getProjDocToDrawings().getProjectSection())) {
                        stringBuilder.append(drawing.getProjDocToDrawings().getProjectSection()).append(" ").append(drawing.getDrawing()).append(", ");
                    }
                }
            }
            stringBuilder.append(passportObjectData.getNameDesigner());
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateMaterials(Set<MaterialsUsedData> materials) {
        StringBuilder stringBuilder = new StringBuilder();
        if (materials != null && !materials.isEmpty()) {
            for (MaterialsUsedData material : materials) {
                stringBuilder.append(material.getNameMaterial()).append(" ‒ ");
                for (AccompanyingDocumentData accDoc : material.getAccompanyingDocuments()) {
                    stringBuilder.append(accDoc.getName()).append(" № ").append(accDoc.getNumber()).append(" от ")
                            .append(accDoc.getDate()).append(" г., ");
                }
                stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append(" ");
            }
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateExecSchemesActsProtocols(AosrData aosrData, ExecutiveSchemesData executiveSchemesData, String nameNetwork, PassportObjectData passportObjectData, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<ActsViCData> actsViCDataSet = aosrData.getActsViCData();
        ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
        SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
        if (executiveSchemesData != null) {
            String code = generateExecutiveSchemesCode(passportObjectData, index);
            stringBuilder.append("Исполнительная схема № ").append(code).append(" ")
                    .append(executiveSchemesData.getNameScheme()).append(" ")
                    .append(executiveSchemesData.getSection()).append(", сеть ")
                    .append(nameNetwork);
            if (!actsViCDataSet.isEmpty()) {
                for (ActsViCData actsViCData : actsViCDataSet) {
                    stringBuilder.append("\nАкт ВиК № ").append(actsViCData.getNumberActViC())
                            .append(" от ").append(actsViCData.getDataActViC()).append(" г.");
                }
            }
            if (protocolsGnbData != null) {
                stringBuilder.append("\nПротокол ГНБ № ").append(protocolsGnbData.getNumberProtocolGnb())
                        .append(" от ").append(protocolsGnbData.getDataProtocolGnb()).append(" г.");
            }
            if (sealingProtocolsData != null) {
                stringBuilder.append("\nПротокол уплотнения № ").append(sealingProtocolsData.getNumberSealingProtocols())
                        .append(" от ").append(sealingProtocolsData.getDataSealingProtocols()).append(" г.");
            }
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateSpAndProjectDocumentations(String work, Set<ProjectDocumentationData> projDocs, Map<List<String>, String> spMap) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!projDocs.isEmpty()) {
            spMap.forEach((key, value) -> {
                if (key.contains(work)) {
                    stringBuilder.append(value);
                }
            });
            stringBuilder.append(" проект ");
            StringBuilder projects = new StringBuilder();
            for (ProjectDocumentationData projDoc : projDocs) {
                if (!projects.toString().contains(projDoc.getProjectSection())) {
                    projects.append(projDoc.getProjectSection()).append(", ");
                }
            }
            projects.delete(projects.length() - 2, projects.length());
            stringBuilder.append(projects);
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateEngineeringSupportNetworks(String work2, ExecutiveSchemesData executiveSchemesData, String nameNetwork) {
        StringBuilder stringBuilder = new StringBuilder();
        if (executiveSchemesData != null) {
            stringBuilder.append(work2).append(" на участке ").append(executiveSchemesData.getSection()).append(", сеть ")
                    .append(nameNetwork);
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateFioCustomerRes(CustomerResponsibleData customerResponsibleData) {
        return customerResponsibleData.getName() != null ? customerResponsibleData.getName() : "";
    }

    public static String generateFioSubcustomerRes(SubcustomerResponsibleData subcustomerResponsibleData) {
        return subcustomerResponsibleData.getName() != null ? subcustomerResponsibleData.getName() : "";
    }

    public static String generateFioSubcustomer2Res(SubcustomerResponsible2Data subcustomerResponsible2Data) {
        return subcustomerResponsible2Data.getName() != null ? subcustomerResponsible2Data.getName() : "";
    }

    public static String generateFioDesignerRes(DesignerResponsibleData designerResponsibleData) {
        return designerResponsibleData.getName() != null ? designerResponsibleData.getName() : "";
    }

    public static String generateFioAnotherPersonRes(AnotherPersonResponsibleData anotherPersonResponsibleData) {
        return anotherPersonResponsibleData.getName() != null ? anotherPersonResponsibleData.getName() : "";
    }

//    public static String generateDescription(AosrData aosrData) {
//        StringBuilder stringBuilder = new StringBuilder();
//        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
//        ProjectDocumentationData projectDocumentationData = aosrData.getProjectDocumentationData();
//        stringBuilder.append(aosrData.getTypeOfWork()).append(" на участке в районе ").append(executiveSchemesData.getSection())
//                .append(" в отметках ").append(executiveSchemesData.getStartingPoint()).append(" / ").append(executiveSchemesData.getEndingPoint())
//                .append(", \n").append("V = ");
//        switch (aosrData.getTypeOfWork()) {
//            case "Механизированная разработка грунта и шурфовка на наличие существующих сетей":
//                stringBuilder.append(executiveSchemesData.getShoofing()).append(" м³");
//                break;
//            case "Механизированная разработка грунта":
//                stringBuilder.append(executiveSchemesData.getSoilDevelopment()).append(" м³");
//                break;
//            case "Устройство песчаного основания под трубопровод", "Устройство песчаного основания под колодец":
//                stringBuilder.append(executiveSchemesData.getSandyBase()).append(" м³");
//                break;
//            case "Обратная засыпка грунтом мест шурфовок":
//                stringBuilder.append(executiveSchemesData.getBackfillingShoofing()).append(" м³");
//                break;
//            case "Обратная засыпка грунтом":
//                stringBuilder.append(executiveSchemesData.getSand()).append(" м³");
//                break;
//            case "Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом":
//                stringBuilder.append(executiveSchemesData.getSand()).append(" м³ (песок)\n").append("V = ")
//                        .append(executiveSchemesData.getPriming()).append(" м³ (грунт)");
//                break;
//            default:
//                break;
//        }
//        stringBuilder.append("\n").append("сеть ").append(projectDocumentationData.getNetwork());
//        return stringBuilder.toString();
//    }
  
    public static String generateBoundaries(AosrData aosrData) {
        StringBuilder stringBuilder = new StringBuilder();
        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
        stringBuilder.append("X=").append(executiveSchemesData.getStartCoordinateX()).append(",\nY=")
                .append(executiveSchemesData.getStartCoordinateY()).append(" /\nX=")
                .append(executiveSchemesData.getEndCoordinateX()).append(",\nY=")
                .append(executiveSchemesData.getEndCoordinateY());
        return stringBuilder.toString();
    }

    public static String generateSoilType(AosrData aosrData) {
        return switch (aosrData.getTypeOfWork()) {
            case "Механизированная разработка грунта и шурфовка на наличие существующих сетей",
                 "Механизированная разработка грунта", "Обратная засыпка грунтом мест шурфовок",
                 "Обратная засыпка грунтом" -> "Грунт";
            case "Устройство песчаного основания под трубопровод", "Устройство песчаного основания под колодец" ->
                    "Песок";
            case "Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом" -> "Песок\nГрунт";
            default -> "---";
        };
    }

    public static String generateAosrForGenLogString(ProjectDocumentationData projDoc, String work1, ExecutiveSchemesData execSchemes, PassportObjectData passObj, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        if (execSchemes != null) {
            stringBuilder.append("АОСР № US-").append(passObj.getProjectCode()).append("-");
            if (index < 10) {
                stringBuilder.append("0");
            }
            stringBuilder.append(index).append("\n").append(work1).append(" на участках ");
            stringBuilder.append(execSchemes.getSection()).append(" в координатах X=");
            if (!execSchemes.getStartCoordinateX().isEmpty()) {
                stringBuilder.append(execSchemes.getStartCoordinateX()).append(", Y=")
                        .append(execSchemes.getStartCoordinateY()).append(" / X=")
                        .append(execSchemes.getEndCoordinateX()).append(", Y=")
                        .append(execSchemes.getEndCoordinateY());
            } else {
                stringBuilder.append(execSchemes.getWellCenterX()).append(", Y=")
                        .append(execSchemes.getWellCenterY());
            }
            stringBuilder.append(" в отметках ").append(execSchemes.getStartingPoint()).append(" / ")
                    .append(execSchemes.getEndingPoint()).append("; сеть ")
                    .append(projDoc.getNetwork());
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateFioPersonsString(String date, PassportObjectData passObj, CustomerResponsibleData customerRes, SubcustomerResponsibleData subcustomerRes, SubcustomerResponsible2Data subcustomer2Res, DesignerResponsibleData designerRes, AnotherPersonResponsibleData anotherPersonRes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passObj != null) {
            stringBuilder.append(date).append("\n");
            if (customerRes.getName() != null) {
                stringBuilder.append(customerRes.getPosition()).append(" ").append(customerRes.getName()).append("\n");
            }
            if (subcustomerRes.getName() != null) {
                stringBuilder.append(subcustomerRes.getPosition()).append(" ").append(subcustomerRes.getName()).append("\n");
            }
            if (subcustomer2Res.getName() != null) {
                stringBuilder.append(subcustomer2Res.getPosition()).append(" ").append(subcustomer2Res.getName()).append("\n");
            }
            if (designerRes.getName() != null) {
                stringBuilder.append(designerRes.getPosition()).append(" ").append(designerRes.getName()).append("\n");
            }
            if (anotherPersonRes.getName() != null) {
                stringBuilder.append(anotherPersonRes.getPosition()).append(" ").append(anotherPersonRes.getName());
            }
            stringBuilder.delete(stringBuilder.length() - 3, stringBuilder.length() - 1);
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateAosrForRegistryString(ProjectDocumentationData projDoc, String work1, ExecutiveSchemesData execSchemes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (execSchemes != null) {
            stringBuilder.append("АОСР ").append(work1).append(" на участке ");
            stringBuilder.append(execSchemes.getSection()).append(" в координатах X=");
            if (!execSchemes.getStartCoordinateX().isEmpty()) {
                stringBuilder.append(execSchemes.getStartCoordinateX()).append(", Y=")
                        .append(execSchemes.getStartCoordinateY()).append(" / X=")
                        .append(execSchemes.getEndCoordinateX()).append(", Y=")
                        .append(execSchemes.getEndCoordinateY());
            } else {
                stringBuilder.append(execSchemes.getWellCenterX()).append(", Y=")
                        .append(execSchemes.getWellCenterY());
            }
            stringBuilder.append(" в отметках ").append(execSchemes.getStartingPoint()).append(" / ")
                    .append(execSchemes.getEndingPoint()).append("; сеть ")
                    .append(projDoc.getNetwork());
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateExecutiveSchemesCode(PassportObjectData passObj, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        String projectCode = generateProjectCode(passObj, index);
        stringBuilder.append(projectCode).append("/1");
        return stringBuilder.toString();
    }
}
