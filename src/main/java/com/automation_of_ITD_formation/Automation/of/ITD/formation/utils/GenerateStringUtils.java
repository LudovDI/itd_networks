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

    public static String generateDeveloper(PassportObjectData passportObjectData) {
        return passportObjectData.getNameCustomer() + ", ОГРН " +
                passportObjectData.getOgrnCustomer() + ", ИНН " +
                passportObjectData.getInnCustomer() + ", " +
                passportObjectData.getAddressCustomer() + ", телефон/факс: " +
                passportObjectData.getPhoneCustomer() + "; " +
                passportObjectData.getNameOrganizationCustomer() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationCustomer() + ", ИНН " +
                passportObjectData.getInnOrganizationCustomer();
    }

    public static String generateBuilder(PassportObjectData passportObjectData) {
        return passportObjectData.getNameContractor() + ", ОГРН " +
                passportObjectData.getOgrnContractor() + ", ИНН " +
                passportObjectData.getInnContractor() + ", " +
                passportObjectData.getAddressContractor() + ", телефон/факс: " +
                passportObjectData.getPhoneContractor() + "; " +
                passportObjectData.getNameOrganizationContractor() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationContractor() + ", ИНН " +
                passportObjectData.getInnOrganizationContractor();
    }

    public static String generatePreparer(PassportObjectData passportObjectData) {
        return passportObjectData.getNameDesigner() + ", ОГРН " +
                passportObjectData.getOgrnDesigner() + ", ИНН " +
                passportObjectData.getInnDesigner() + ", " +
                passportObjectData.getAddressDesigner() + ", телефон/факс: " +
                passportObjectData.getPhoneDesigner() + "; " +
                passportObjectData.getNameOrganizationDesigner() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationDesigner() + ", ИНН " +
                passportObjectData.getInnOrganizationDesigner();
    }

    public static String generateCustomer(PassportObjectData passportObjectData) {
        return passportObjectData.getPostCustomer() + " " +
                passportObjectData.getFioCustomer() + ", " +
                passportObjectData.getIdCustomer() + ", " +
                passportObjectData.getNameCustomer() + " № " +
                passportObjectData.getNumberCustomer() + " от " +
                passportObjectData.getDataCustomer() + " г., " +
                passportObjectData.getNameOrganizationCustomer() + ", ОГРН " +
                passportObjectData.getOgrnOrganizationCustomer() + ", ИНН " +
                passportObjectData.getInnOrganizationCustomer() + ", " +
                passportObjectData.getAdrOrganizationCustomer();
    }

    public static String generateContractor(PassportObjectData passportObjectData) {
        return passportObjectData.getPostContractor() + " " +
                passportObjectData.getFioContractor() + ", " +
                passportObjectData.getIdContractor() + ", " +
                passportObjectData.getNameContractor() + " № " +
                passportObjectData.getNumberContractor() + " от " +
                passportObjectData.getDataContractor() + " г.";
    }

    public static String generateContractor2(PassportObjectData passportObjectData) {
        return passportObjectData.getPostContractor2() + " " +
                passportObjectData.getFioContractor2() + ", " +
                passportObjectData.getIdContractor2() + ", " +
                passportObjectData.getNameContractor2() + " № " +
                passportObjectData.getNumberContractor2() + " от " +
                passportObjectData.getDataContractor2() + " г.";
    }

    public static String generateProjector(PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(passportObjectData.getPostProjector()).append(" ")
                .append(passportObjectData.getFioProjector()).append(", ");
        if (!passportObjectData.getIdProjector().isEmpty()) {
            stringBuilder.append(passportObjectData.getIdProjector()).append(", ");
        }
        stringBuilder.append(passportObjectData.getNameProjector()).append(" № ")
                .append(passportObjectData.getNumberProjector()).append(" от ")
                .append(passportObjectData.getDataProjector()).append(" г., ")
                .append(passportObjectData.getNameOrganizationProjector()).append(", ОГРН ")
                .append(passportObjectData.getOgrnOrganizationProjector()).append(", ИНН ")
                .append(passportObjectData.getInnOrganizationProjector()).append(", ")
                .append(passportObjectData.getAdrOrganizationProjector());
        return stringBuilder.toString();
    }

    public static String generateProjectCode(PassportObjectData passportObjectData, int index) {
        return "US-" + passportObjectData.getProjectCode() + "-" + (index < 10 ? "0" + index : index);
    }

    public static String generateNameOrganization(PassportObjectData passportObjectData) {
        return passportObjectData.getNameContractor();
    }

    public static String generateNameOfHiddenWork(ProjectDocumentationData projectDocumentationData, String work1, ExecutiveSchemesData executiveSchemesData) {
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
                    .append(projectDocumentationData.getNetwork());
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateDrawings(Set<DrawingsData> drawings, PassportObjectData passportObjectData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!drawings.isEmpty()) {
            stringBuilder.append("чертеж ");
            for (DrawingsData drawing : drawings) {
                if (drawing.getProjDocToDrawings().getProjectSection().contains("Z22098-НВК")) {
                    stringBuilder.append(drawing.getProjDocToDrawings().getProjectSection()).append(drawing.getDrawing()).append(", ");
                } else {
                    stringBuilder.append(drawing.getProjDocToDrawings().getProjectSection()).append(" ").append(drawing.getDrawing()).append(", ");
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

    public static String generateExecutiveSchemes(ExecutiveSchemesData executiveSchemesData, ProjectDocumentationData projectDocumentationData, PassportObjectData passportObjectData, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        if (executiveSchemesData != null) {
            String code = generateExecutiveSchemesCode(passportObjectData, index);
            stringBuilder.append("Исполнительная схема № ").append(code).append(" ")
                    .append(executiveSchemesData.getNameScheme()).append(" в районе ")
                    .append(executiveSchemesData.getSection()).append(", сеть ")
                    .append(projectDocumentationData.getNetwork());
        } else {
            stringBuilder.append("---");
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

    public static String generateEngineeringSupportNetworks(String work2, ExecutiveSchemesData executiveSchemesData, ProjectDocumentationData projectDocumentationData) {
        StringBuilder stringBuilder = new StringBuilder();
        if (executiveSchemesData != null) {
            stringBuilder.append(work2).append(" на участке в районе ").append(executiveSchemesData.getSection()).append(", сеть ")
                    .append(projectDocumentationData.getNetwork());
        } else {
            stringBuilder.append("---");
        }
        return stringBuilder.toString();
    }

    public static String generateFioCustomer(PassportObjectData passportObjectData) {
        return passportObjectData.getFioCustomer();
    }

    public static String generateFioFirstContractor(PassportObjectData passportObjectData) {
        return passportObjectData.getFioContractor();
    }

    public static String generateFioSecondContractor(PassportObjectData passportObjectData) {
        return passportObjectData.getFioContractor2();
    }

    public static String generateFioSecondProjector(PassportObjectData passportObjectData) {
        return passportObjectData.getFioProjector();
    }

    public static String generateDescription(AosrData aosrData) {
        StringBuilder stringBuilder = new StringBuilder();
        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
        ProjectDocumentationData projectDocumentationData = aosrData.getProjectDocumentationData();
        stringBuilder.append(aosrData.getTypeOfWork()).append(" на участке в районе ").append(executiveSchemesData.getSection())
                .append(" в отметках ").append(executiveSchemesData.getStartingPoint()).append(" / ").append(executiveSchemesData.getEndingPoint())
                .append(", \n").append("V = ");
        switch (aosrData.getTypeOfWork()) {
            case "Механизированная разработка грунта и шурфовка на наличие существующих сетей":
                stringBuilder.append(executiveSchemesData.getShoofing()).append(" м³");
                break;
            case "Механизированная разработка грунта":
                stringBuilder.append(executiveSchemesData.getSoilDevelopment()).append(" м³");
                break;
            case "Устройство песчаного основания под трубопровод", "Устройство песчаного основания под колодец":
                stringBuilder.append(executiveSchemesData.getSandyBase()).append(" м³");
                break;
            case "Обратная засыпка грунтом мест шурфовок":
                stringBuilder.append(executiveSchemesData.getBackfillingShoofing()).append(" м³");
                break;
            case "Обратная засыпка грунтом":
                stringBuilder.append(executiveSchemesData.getSand()).append(" м³");
                break;
            case "Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом":
                stringBuilder.append(executiveSchemesData.getSand()).append(" м³ (песок)\n").append("V = ")
                        .append(executiveSchemesData.getPriming()).append(" м³ (грунт)");
                break;
            default:
                break;
        }
        stringBuilder.append("\n").append("сеть ").append(projectDocumentationData.getNetwork());
        return stringBuilder.toString();
    }

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

    public static String generateFioPersonsString(String date, PassportObjectData passObj) {
        StringBuilder stringBuilder = new StringBuilder();
        if (passObj != null) {
            stringBuilder.append(date).append("\n");
            if (passObj.getFioContractor() != null) {
                stringBuilder.append(passObj.getPostContractor()).append(" ").append(passObj.getFioContractor()).append("\n");
            }
            if (passObj.getFioContractor2() != null) {
                stringBuilder.append(passObj.getPostContractor2()).append(" ").append(passObj.getFioContractor2()).append("\n");
            }
            if (passObj.getFioCustomer() != null) {
                stringBuilder.append(passObj.getPostCustomer()).append(" ").append(passObj.getFioCustomer()).append("\n");
            }
            if (passObj.getFioProjector() != null) {
                stringBuilder.append(passObj.getPostProjector()).append(" ").append(passObj.getFioProjector()).append("\n");
            }
            if (passObj.getFioAnotherPerson() != null) {
                stringBuilder.append(passObj.getPostAnotherPerson()).append(" ").append(passObj.getFioAnotherPerson());
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
