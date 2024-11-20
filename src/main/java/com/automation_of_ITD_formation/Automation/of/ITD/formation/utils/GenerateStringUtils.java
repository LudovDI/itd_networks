package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GenerateStringUtils {
    public static String generateCapitalConstructionProject(PassportObjectData passportObjectData) {
        return passportObjectData.getNameObject() + " " +
                passportObjectData.getNameCustomer() + ", ОГРН " +
                passportObjectData.getOgrnCustomer() + ", ИНН " +
                passportObjectData.getInnCustomer() + ", " +
                passportObjectData.getAddressCustomer();
    }

    public static String generateCustomer(PassportObjectData passportObjectData) {
        return passportObjectData.getNameCustomer() + ", ОГРН " +
                passportObjectData.getOgrnCustomer() + ", ИНН " +
                passportObjectData.getInnCustomer() + ", " +
                passportObjectData.getAddressCustomer() + ", телефон/факс: " +
                passportObjectData.getPhoneCustomer() + "; " +
                passportObjectData.getNameOrganizationCustomer() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationCustomer() + ", ИНН " +
                passportObjectData.getInnOrganizationCustomer();
    }

    public static String generateContractor(PassportObjectData passportObjectData) {
        return passportObjectData.getNameContractor() + ", ОГРН " +
                passportObjectData.getOgrnContractor() + ", ИНН " +
                passportObjectData.getInnContractor() + ", " +
                passportObjectData.getAddressContractor() + ", телефон/факс: " +
                passportObjectData.getPhoneContractor() + "; " +
                passportObjectData.getNameOrganizationContractor() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationContractor() + ", ИНН " +
                passportObjectData.getInnOrganizationContractor();
    }

    public static String generateDesigner(PassportObjectData passportObjectData) {
        return passportObjectData.getNameDesigner() + ", ОГРН " +
                passportObjectData.getOgrnDesigner() + ", ИНН " +
                passportObjectData.getInnDesigner() + ", " +
                passportObjectData.getAddressDesigner() + ", телефон/факс: " +
                passportObjectData.getPhoneDesigner() + "; " +
                passportObjectData.getNameOrganizationDesigner() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationDesigner() + ", ИНН " +
                passportObjectData.getInnOrganizationDesigner();
    }

    public static String generateCustomerResponsible(CustomerResponsibleData customerResponsibleData) {
        return customerResponsibleData.getPosition() + " " +
                customerResponsibleData.getName() + ", " +
                customerResponsibleData.getIdNumber() + ", " +
                customerResponsibleData.getNameDocument() + " № " +
                customerResponsibleData.getNumberDocument() + " от " +
                customerResponsibleData.getDateDocument() + " г., " +
                customerResponsibleData.getNameCompany() + ", ОГРН " +
                customerResponsibleData.getOgrnCompany() + ", ИНН " +
                customerResponsibleData.getInnCompany() + ", " +
                customerResponsibleData.getAdrCompany();
    }

    public static String generateSubcustomerResponsible(SubcustomerResponsibleData subcustomerResponsibleData) {
        return subcustomerResponsibleData.getPosition() + " " +
                subcustomerResponsibleData.getName() + ", " +
                subcustomerResponsibleData.getIdNumber() + ", " +
                subcustomerResponsibleData.getNameDocument() + " № " +
                subcustomerResponsibleData.getNumberDocument() + " от " +
                subcustomerResponsibleData.getDateDocument() + " г.";
    }

    public static String generateSubcustomer2Responsible(SubcustomerResponsible2Data subcustomerResponsible2Data) {
        return subcustomerResponsible2Data.getPosition() + " " +
                subcustomerResponsible2Data.getName() + ", " +
                subcustomerResponsible2Data.getIdNumber() + ", " +
                subcustomerResponsible2Data.getNameDocument() + " № " +
                subcustomerResponsible2Data.getNumberDocument() + " от " +
                subcustomerResponsible2Data.getDateDocument() + " г.";
    }

    public static String generateDesignerResponsible(DesignerResponsibleData designerResponsibleData) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(designerResponsibleData.getPosition()).append(" ")
                .append(designerResponsibleData.getName()).append(", ");
        if (designerResponsibleData.getIdNumber() != null) {
            stringBuilder.append(designerResponsibleData.getIdNumber()).append(", ");
        }
        stringBuilder.append(designerResponsibleData.getNameDocument()).append(" № ")
                .append(designerResponsibleData.getNumberDocument()).append(" от ")
                .append(designerResponsibleData.getDateDocument()).append(" г., ")
                .append(designerResponsibleData.getNameCompany()).append(", ОГРН ")
                .append(designerResponsibleData.getOgrnCompany()).append(", ИНН ")
                .append(designerResponsibleData.getInnCompany()).append(", ")
                .append(designerResponsibleData.getAdrCompany());
        return stringBuilder.toString();
    }

    public static String generateAnotherPersonResponsible(AnotherPersonResponsibleData anotherPersonResponsibleData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (anotherPersonResponsibleData.getPosition() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getPosition()).append(" ");
        }
        if (anotherPersonResponsibleData.getName() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getName()).append(", ");
        }
        if (anotherPersonResponsibleData.getIdNumber() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getIdNumber()).append(", ");
        }
        if (anotherPersonResponsibleData.getNameDocument() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getNameDocument()).append(" № ");
        }
        if (anotherPersonResponsibleData.getNumberDocument() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getNumberDocument()).append(" от ");
        }
        if (anotherPersonResponsibleData.getDateDocument() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getDateDocument()).append(" г., ");
        }
        if (anotherPersonResponsibleData.getNameCompany() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getNameCompany()).append(", ОГРН ");
        }
        if (anotherPersonResponsibleData.getOgrnCompany() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getOgrnCompany()).append(", ИНН ");
        }
        if (anotherPersonResponsibleData.getInnCompany() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getInnCompany()).append(", ");
        }
        if (anotherPersonResponsibleData.getAdrCompany() != null) {
            stringBuilder.append(anotherPersonResponsibleData.getAdrCompany());
        }
        return stringBuilder.toString();
    }

    public static String generateProjectCode(PassportObjectData passportObjectData, int index) {
        return "US-" + passportObjectData.getProjectCode() + "-" + (index < 10 ? "0" + index : index);
    }

    public static String generateNameOrganization(PassportObjectData passportObjectData) {
        return passportObjectData.getNameContractor();
    }

    public static String generateNameOfHiddenWork(String nameNetwork, String work1, ExecutiveSchemesData executiveSchemesData) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(work1);
        if (executiveSchemesData != null) {
            stringBuilder.append(" на участках в районе ");
            stringBuilder.append(executiveSchemesData.getSection()).append(" в координатах X=");
            if (!executiveSchemesData.getStartCoordinateX().isEmpty()) {
                stringBuilder.append(executiveSchemesData.getStartCoordinateX()).append(", Y=")
                        .append(executiveSchemesData.getStartCoordinateY()).append(" / X=")
                        .append(executiveSchemesData.getEndCoordinateX()).append(", Y=")
                        .append(executiveSchemesData.getEndCoordinateY());
            } else {
                stringBuilder.append(executiveSchemesData.getWellCenterX()).append(", Y=")
                        .append(executiveSchemesData.getWellCenterY());
            }
            stringBuilder.append(" в отметках ").append(executiveSchemesData.getStartingPoint()).append(" / ")
                    .append(executiveSchemesData.getEndingPoint()).append("; сеть ")
                    .append(nameNetwork);
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateDrawings(Set<ProjectDocumentationData> projectDocumentationSet, Set<DrawingsData> drawings, PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!drawings.isEmpty()) {
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
        if (!materials.isEmpty()) {
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
        if (executiveSchemesData != null) {
            String code = generateExecutiveSchemesCode(passportObjectData, index);
            stringBuilder.append("Исполнительная схема № ").append(code).append(" ")
                    .append(executiveSchemesData.getNameScheme()).append(" в районе ")
                    .append(executiveSchemesData.getSection()).append(", сеть ")
                    .append(nameNetwork);
        } else {
            stringBuilder.append("---");
        }
        Set<ActsViCData> actsViCDataSet = aosrData.getActsViCData();
        if (!actsViCDataSet.isEmpty()) {
            for (ActsViCData actsViCData : actsViCDataSet) {
                stringBuilder.append("\nАкт ВиК № ").append(actsViCData.getNumberActViC())
                        .append(" от ").append(actsViCData.getDataActViC()).append(" г.");
            }
        }
        ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
        if (protocolsGnbData != null) {
            stringBuilder.append("\nПротокол ГНБ № ").append(protocolsGnbData.getNumberProtocolGnb())
                    .append(" от ").append(protocolsGnbData.getDataProtocolGnb()).append(" г.");
        }
        SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
        if (sealingProtocolsData != null) {
            stringBuilder.append("\nПротокол уплотнения № ").append(sealingProtocolsData.getNumberSealingProtocols())
                    .append(" от ").append(sealingProtocolsData.getDataSealingProtocols()).append(" г.");
        }
        return stringBuilder.toString();
    }

    public static String generateSpAndProjectDocumentations(String work, Set<DrawingsData> drawingsDataSet, Map<List<String>, String> spMap) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!drawingsDataSet.isEmpty()) {
            spMap.forEach((key, value) -> {
                if (key.contains(work)) {
                    stringBuilder.append(value);
                }
            });
            stringBuilder.append(" проект ");
            StringBuilder projects = new StringBuilder();
            for (DrawingsData drawing : drawingsDataSet) {
                if (!projects.toString().contains(drawing.getProjDocToDrawings().getProjectSection())) {
                    projects.append(drawing.getProjDocToDrawings().getProjectSection()).append(", ");
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
            stringBuilder.append(work2).append(" на участке в районе ").append(executiveSchemesData.getSection()).append(", сеть ")
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
            stringBuilder.append(index).append("\n").append(work1).append(" на участках в районе ");
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
            stringBuilder.append("АОСР ").append(work1).append(" на участке в районе ");
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
