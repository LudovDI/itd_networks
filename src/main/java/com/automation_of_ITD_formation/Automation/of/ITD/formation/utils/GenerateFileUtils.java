package com.automation_of_ITD_formation.Automation.of.ITD.formation.utils;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ReplacePlaceholderUtils.replacePlaceholder;
import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ReplacePlaceholderUtils.replacePlaceholderInTable;

public class GenerateFileUtils {

    public static File generateAosrFile(AosrData aosrData, int indexAosr, Map<List<String>, String> spMap, Map<String, Integer> monthMap) throws IOException {
        FileInputStream inputStream = new FileInputStream("E:\\javaProjects\\itd_networks\\src\\main\\resources\\files\\АОСР.docx");
        XWPFDocument document = new XWPFDocument(inputStream);
        inputStream.close();

        PassportObjectData passportObjectData = aosrData.getPassportObjectData();
        CustomerResponsibleData customerResponsibleData = aosrData.getCustomerResponsibleData();
        DesignerResponsibleData designerResponsibleData = aosrData.getDesignerResponsibleData();
        SubcustomerResponsibleData subcustomerResponsibleData = aosrData.getSubcustomerResponsibleData();
        SubcustomerResponsible2Data subcustomerResponsible2Data = aosrData.getSubcustomerResponsible2Data();
        AnotherPersonResponsibleData anotherPersonResponsibleData = aosrData.getAnotherPersonResponsibleData();

        if (customerResponsibleData == null) {
            customerResponsibleData = new CustomerResponsibleData();
        }
        if (subcustomerResponsibleData == null) {
            subcustomerResponsibleData = new SubcustomerResponsibleData();
        }
        if (subcustomerResponsible2Data == null) {
            subcustomerResponsible2Data = new SubcustomerResponsible2Data();
        }
        if (designerResponsibleData == null) {
            designerResponsibleData = new DesignerResponsibleData();
        }
        if (anotherPersonResponsibleData == null) {
            anotherPersonResponsibleData = new AnotherPersonResponsibleData();
        }

        Set<ProjectDocumentationData> projectDocumentationSet = aosrData.getAosrToProjDocs();
        String nameNetwork = projectDocumentationSet.stream().toList().getFirst().getNetwork();

        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
        String work1 = aosrData.getTypeOfWork();
        String work2 = aosrData.getPermittedFollowingWork();
        Set<DrawingsData> drawingsDataSet = aosrData.getAosrToDrawings();
        Set<MaterialsUsedData> materialsUsedDataSet = aosrData.getAosrToMaterials();

        String capitalConstructionProject = GenerateStringUtils.generateCapitalConstructionProject(passportObjectData);
        replacePlaceholderInTable(document, "CapitalConstructionProject", capitalConstructionProject, 110, 110);
        String customer = GenerateStringUtils.generateCustomer(passportObjectData);
        replacePlaceholderInTable(document, "InfoCustomer", customer, 100, 100);
        String contractor = GenerateStringUtils.generateContractor(passportObjectData);
        replacePlaceholderInTable(document, "InfoContractor", contractor, 100, 100);
        String designer = GenerateStringUtils.generateDesigner(passportObjectData);
        replacePlaceholderInTable(document, "InfoDesigner", designer, 100, 100);
        String projectCode = GenerateStringUtils.generateProjectCode(passportObjectData, indexAosr);
        replacePlaceholderInTable(document, "ProjectCode", projectCode, 30, 30);
        String customerRes = GenerateStringUtils.generateCustomerResponsible(customerResponsibleData);
        replacePlaceholderInTable(document, "InfoResCustomer", customerRes, 100, 100);
        String subcustomer = GenerateStringUtils.generateSubcustomerResponsible(subcustomerResponsibleData);
        replacePlaceholderInTable(document, "InfoFirstResSubcustomer", subcustomer, 120, 120);
        String subcustomer2 = GenerateStringUtils.generateSubcustomer2Responsible(subcustomerResponsible2Data);
        replacePlaceholderInTable(document, "InfoSecondResSubcustomer", subcustomer2, 120, 120);
        String designerRes = GenerateStringUtils.generateDesignerResponsible(designerResponsibleData);
        replacePlaceholderInTable(document, "InfoResDesigner", designerRes, 100, 100);
        String anotherPerson = GenerateStringUtils.generateAnotherPersonResponsible(anotherPersonResponsibleData);
        replacePlaceholderInTable(document, "InfoResAnotherPerson", anotherPerson, 100, 100);
        String nameOrganization = GenerateStringUtils.generateNameOrganization(passportObjectData);
        replacePlaceholderInTable(document, "NameOrganization", nameOrganization, 80, 80);
        String nameOfHiddenWork = GenerateStringUtils.generateNameOfHiddenWork(nameNetwork, work1, executiveSchemesData);
        replacePlaceholderInTable(document, "NameOfHiddenWork", nameOfHiddenWork, 60, 120);
        String drawings = GenerateStringUtils.generateDrawings(projectDocumentationSet, drawingsDataSet, passportObjectData);
        replacePlaceholderInTable(document, "Drawings", drawings, 70, 135);
        String materials = GenerateStringUtils.generateMaterials(materialsUsedDataSet);
        replacePlaceholderInTable(document, "Materials", materials, 80, 115);
        String executiveSchemes = GenerateStringUtils.generateExecSchemesActsProtocols(aosrData, executiveSchemesData, nameNetwork, passportObjectData, indexAosr);
        replacePlaceholderInTable(document, "ExecutiveSchemes", executiveSchemes, 120, 120);
        String spAndProjectDocumentations = GenerateStringUtils.generateSpAndProjectDocumentations(work1, drawingsDataSet, spMap);
        replacePlaceholderInTable(document, "ProjectDocumentations", spAndProjectDocumentations, 80, 120);
        String engineeringSupportNetworks = GenerateStringUtils.generateEngineeringSupportNetworks(work2, executiveSchemesData, nameNetwork);
        replacePlaceholderInTable(document, "EngineeringSupportNetworks", engineeringSupportNetworks, 70, 120);
        String fioCustomer = GenerateStringUtils.generateFioCustomerRes(customerResponsibleData);
        replacePlaceholderInTable(document, "FioCustomer", fioCustomer, 30, 30);
        String fioFirstSubcustomer = GenerateStringUtils.generateFioSubcustomerRes(subcustomerResponsibleData);
        replacePlaceholderInTable(document, "FioFirstSubcustomer", fioFirstSubcustomer, 30, 30);
        String fioSecondSubcustomer = GenerateStringUtils.generateFioSubcustomer2Res(subcustomerResponsible2Data);
        replacePlaceholderInTable(document, "FioSecondSubcustomer", fioSecondSubcustomer, 30, 30);
        String fioSecondDesigner = GenerateStringUtils.generateFioDesignerRes(designerResponsibleData);
        replacePlaceholderInTable(document, "FioDesigner", fioSecondDesigner, 30, 30);
        String fioAnotherPerson = GenerateStringUtils.generateFioAnotherPersonRes(anotherPersonResponsibleData);
        replacePlaceholderInTable(document, "FioAnotherPerson", fioAnotherPerson, 30, 30);

        LocalDate dateStart = aosrData.getStartDate();
        LocalDate dateEnd = aosrData.getEndDate();

        String dayStart = Integer.toString(dateStart.getDayOfMonth());
        String yearStart = Integer.toString(dateStart.getYear()).substring(2, 4);
        String dayEnd = Integer.toString(dateEnd.getDayOfMonth());
        String yearEnd = Integer.toString(dateEnd.getYear()).substring(2, 4);
        StringBuilder monthStart = new StringBuilder();
        StringBuilder monthEnd = new StringBuilder();
        monthMap.forEach((key, value) -> {
            if (value.equals(dateStart.getMonthValue()) && value.equals(dateEnd.getMonthValue())) {
                monthStart.append(key);
                monthEnd.append(key);
            } else if (value.equals(dateStart.getMonthValue())) {
                monthStart.append(key);
            } else if (value.equals(dateEnd.getMonthValue())) {
                monthEnd.append(key);
            }
        });

        replacePlaceholderInTable(document, "daySt", dayStart, 10, 10);
        replacePlaceholderInTable(document, "monthStart", monthStart.toString(), 10, 10);
        replacePlaceholderInTable(document, "yearSt", yearStart, 10, 10);
        replacePlaceholderInTable(document, "dayE", dayEnd, 10, 10);
        replacePlaceholderInTable(document, "monthEnd", monthEnd.toString(), 10, 10);
        replacePlaceholderInTable(document, "yearE", yearEnd, 10, 10);

        File tempFile = File.createTempFile("Aosr_" + indexAosr, ".docx");
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            document.write(outputStream);
        }

        return tempFile;
    }

//    public static File generateExcavationLogFile(Map<String, Integer> monthMap, String namePerformanceManager, List<AosrData> aosrToLogList) throws IOException {
//        FileInputStream inputStream = new FileInputStream("E:\\javaProjects\\Automation-of-ITD-formation\\src\\main\\resources\\files\\ЖЗР.docx");
//        XWPFDocument document = new XWPFDocument(inputStream);
//        inputStream.close();
//        AosrData firstAosrData = aosrToLogList.getFirst();
//        AosrData endAosrData = aosrToLogList.getLast();
//
//        PassportObjectData passObj = firstAosrData.getPassportObjectData();
//        Map<String, String> personMap = getPersonMap(passObj);
//
//        LocalDate dateStart = firstAosrData.getStartDate();
//        LocalDate dateEnd = endAosrData.getEndDate();
//        String dayStart = Integer.toString(dateStart.getDayOfMonth());
//        String yearStart = Integer.toString(dateStart.getYear()).substring(2, 4);
//        String dayEnd = Integer.toString(dateEnd.getDayOfMonth());
//        String yearEnd = Integer.toString(dateEnd.getYear()).substring(2, 4);
//        StringBuilder monthStart = new StringBuilder();
//        StringBuilder monthEnd = new StringBuilder();
//        monthMap.forEach((key, value) -> {
//            if (value.equals(dateStart.getMonthValue()) && value.equals(dateEnd.getMonthValue())) {
//                monthStart.append(key);
//                monthEnd.append(key);
//            } else if (value.equals(dateStart.getMonthValue())) {
//                monthStart.append(key);
//            } else if (value.equals(dateEnd.getMonthValue())) {
//                monthEnd.append(key);
//            }
//        });
//
//        String nameCustomer = passObj.getNameCustomer();
//        replacePlaceholder(document, "NameCustomer", nameCustomer);
//        String nameBuilder = passObj.getNameContractor();
//        replacePlaceholder(document, "NameBuilder", nameBuilder);
//        String nameObject = passObj.getNameObject();
//        replacePlaceholder(document, "NameObject", nameObject);
//        String numberLog = "УС-" + passObj.getProjectCode() + "-ЗР-01";
//        replacePlaceholder(document, "NumberLog", numberLog);
//        String performanceManagerStart = personMap.get(namePerformanceManager);
//        replacePlaceholder(document, "PerformanceManager", performanceManagerStart);
//        String dayMonthStart = dayStart + " " + monthStart;
//        replacePlaceholder(document, "DayMonthStart", dayMonthStart);
//        replacePlaceholder(document, "YearStart", yearStart);
//        String dayMonthEnd = dayEnd + " " + monthEnd;
//        replacePlaceholder(document, "DayMonthEnd", dayMonthEnd);
//        replacePlaceholder(document, "YearEnd", yearEnd);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        String dateEndString = dateEnd.format(formatter);
//        replacePlaceholder(document, "DateEnd", dateEndString);
//        int indexAosr = 1;
//        ReplacePlaceholderUtils.duplicateRowWithPlaceholders(document, aosrToLogList);
//        for (AosrData aosrData : aosrToLogList) {
//            replacePlaceholder(document, "Num", Integer.toString(indexAosr));
//            DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.\nyyyy");
//            String dateStartString = aosrData.getStartDate().format(formatterDate);
//            replacePlaceholder(document, "Date", dateStartString);
//            String description = GenerateStringUtils.generateDescription(aosrData);
//            replacePlaceholder(document, "Description", description);
//            String boundaries = GenerateStringUtils.generateBoundaries(aosrData);
//            replacePlaceholder(document, "Boundaries", boundaries);
//            String soilType = GenerateStringUtils.generateSoilType(aosrData);
//            replacePlaceholder(document, "SoilType", soilType);
//            replacePlaceholder(document, "NoComments", "Замечаний нет");
//            replacePlaceholder(document, "Mark", "-");
//            String fio = namePerformanceManager.replace(" ", "\n");
//            replacePlaceholder(document, "Signature", fio);
//            replacePlaceholder(document, "Notes", "-");
//            indexAosr++;
//        }
//        File tempExcavLogFile = File.createTempFile("ExcavationLog", ".docx");
//        FileOutputStream outputStream = new FileOutputStream(tempExcavLogFile);
//        document.write(outputStream);
//        outputStream.close();
//
//        return tempExcavLogFile;
//    }
//
//    public static File generateGeneralLogDocxFile(PassportObjectData passObj) throws IOException {
//        FileInputStream docInputStream = new FileInputStream("E:\\javaProjects\\Automation-of-ITD-formation\\src\\main\\resources\\files\\ОЖР.docx");
//        XWPFDocument docxDocument = new XWPFDocument(docInputStream);
//        docInputStream.close();
//
//        replacePlaceholder(docxDocument, "NumLog", "1", 10, 10);
//        String nameObject = passObj.getNameObject();
//        replacePlaceholder(docxDocument, "NameObject", nameObject, 60, 70);
//        String addressObject = passObj.getAddressObject();
//        replacePlaceholder(docxDocument, "AddressObject", addressObject, 55, 70);
//        String nameOrganization = passObj.getNameContractor();
//        replacePlaceholder(docxDocument, "NameOrganization", nameOrganization, 50, 50);
//
//        File tempDocxFile = File.createTempFile("GeneralWorksLog", ".docx");
//        FileOutputStream docxOutputStream = new FileOutputStream(tempDocxFile);
//        docxDocument.write(docxOutputStream);
//        docxOutputStream.close();
//
//        return tempDocxFile;
//    }
//
//    public static File generateGeneralLogXlsxFile(AosrData firstAosrData, AosrData endAosrData, List<AosrData> aosrDataList) throws IOException {
//        FileInputStream excelInputStream = new FileInputStream("E:\\javaProjects\\Automation-of-ITD-formation\\src\\main\\resources\\files\\ОЖР.xlsx");
//        XSSFWorkbook excelDocument = new XSSFWorkbook(excelInputStream);
//        excelInputStream.close();
//
//        List<List<String>> values = new ArrayList<>(List.of(
//                List.of("1", "ООО «Уватстрой»", "Соколов А.А., директор", "", ""),
//                List.of("2", "ООО «Уватстрой»", "Палько Е.П., начальник строительного участка", "", ""),
//                List.of("3", "ООО «Уватстрой»", "Максимаш И.А., производитель работ", "", "")
//        ));
//
//        updateSheet(excelDocument, "1 раздел", 4, values);
//        values.clear();
//
//        LocalDate startDate = firstAosrData.getStartDate();
//        LocalDate endDate = endAosrData.getEndDate();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//
//        LocalDate currentDate = startDate;
//        int index = 1;
//        while (!currentDate.isAfter(endDate)) {
//            boolean addedRow = false;
//            for(AosrData aosrData : aosrDataList) {
//                if (aosrData.getEndDate().equals(currentDate)) {
//                    StringBuilder str1 = new StringBuilder(aosrData.getTypeOfWork() + " на участке в районе " +
//                            aosrData.getExecutiveSchemesData().getSection() + ", сеть " +
//                            aosrData.getProjectDocumentationData().getNetwork());
//                    if (!aosrData.getAosrToMaterials().isEmpty()) {
//                        str1.append("\nПрименяемые материалы: ");
//                        for (MaterialsUsedData material : aosrData.getAosrToMaterials()) {
//                            str1.append(material.getNameMaterial()).append(", ");
//                        }
//                        str1.delete(str1.length() - 3, str1.length() - 1);
//                    }
//                    String str2 = aosrData.getPassportObjectData().getPostContractor() + " " + aosrData.getPassportObjectData().getFioContractor();
//                    values.add(List.of(Integer.toString(index), currentDate.format(formatter), "", str1.toString(), str2));
//                    addedRow = true;
//                    index++;
//                    if (aosrData.getTypeOfWork().contains("Сварка")) {
//                        String str3 = "Визуально-измерительный контроль сварных соединений на участках " +
//                                aosrData.getExecutiveSchemesData().getSection() + ", сеть " +
//                                aosrData.getProjectDocumentationData().getNetwork();
//                        values.add(List.of(Integer.toString(index), currentDate.format(formatter), "", str3, str2));
//                        index++;
//                    }
//                    if (aosrData.getTypeOfWork().equals("Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом")) {
//                        String str4 = "Определение степени уплотнения оснований методом режущих колец на участке" +
//                                aosrData.getExecutiveSchemesData().getSection() + ", сеть " + aosrData.getProjectDocumentationData().getNetwork();
//                        values.add(List.of(Integer.toString(index), currentDate.format(formatter), "", str4, str2));
//                    }
//                }
//            }
//            if (!addedRow) {
//                String str = firstAosrData.getPassportObjectData().getPostContractor() + " " + firstAosrData.getPassportObjectData().getFioContractor();
//                values.add(List.of(Integer.toString(index), currentDate.format(formatter), "---", "Работы не производились", str));
//            }
//            currentDate = currentDate.plusDays(1);
//        }
//
//        updateSheet(excelDocument, "3 раздел", 4, values);
//        values.clear();
//        index = 1;
//        for (AosrData aosrData : aosrDataList) {
//            String str1 = GenerateStringUtils.generateAosrForGenLogString(aosrData.getProjectDocumentationData(), aosrData.getTypeOfWork(),
//                    aosrData.getExecutiveSchemesData(), aosrData.getPassportObjectData(), index);
//            String str2 = GenerateStringUtils.generateFioPersonsString(aosrData.getEndDate().format(formatter), aosrData.getPassportObjectData());
//            values.add(List.of(Integer.toString(index), str1, str2, "", ""));
//            index++;
//        }
//
//        updateSheet(excelDocument, "6 раздел", 4, values);
//
//        File tempXlsxFile = File.createTempFile("GeneralWorksLog", ".xlsx");
//        FileOutputStream xlsxOutputStream = new FileOutputStream(tempXlsxFile);
//        excelDocument.write(xlsxOutputStream);
//        xlsxOutputStream.close();
//
//        return tempXlsxFile;
//    }
//
//    public static File generateRegistryFile(PassportObjectData passObj, List<AosrData> aosrDataList, List<AosrData> aosrToExcavLogList) throws IOException {
//        FileInputStream excelInputStream = new FileInputStream("E:\\javaProjects\\Automation-of-ITD-formation\\src\\main\\resources\\files\\Реестр.xlsx");
//        XSSFWorkbook excelDocument = new XSSFWorkbook(excelInputStream);
//        excelInputStream.close();
//
//        setTextInCell(excelDocument, "РЕЕСТР - папка №1", 1, 1, passObj.getNameCustomer());
//        setTextInCell(excelDocument, "РЕЕСТР - папка №1", 2, 1, passObj.getNameCustomer());
//        setTextInCell(excelDocument, "РЕЕСТР - папка №1", 3, 1, passObj.getNameContractor());
//        setTextInCell(excelDocument, "РЕЕСТР - папка №1", 0, 4, passObj.getNameObject());
//        setTextInCell(excelDocument, "РЕЕСТР - папка №1", 1, 4, passObj.getProjectCode());
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//
//        List<List<String>> values = new ArrayList<>(List.of());
//
//        updateSheet(excelDocument, "3 раздел", 4, values);
//        values.clear();
//        int index = 1;
//        int indexAosr = 1;
//        for (AosrData aosrData : aosrDataList) {
//            String str1 = GenerateStringUtils.generateAosrForRegistryString(aosrData.getProjectDocumentationData(), aosrData.getTypeOfWork(),
//                    aosrData.getExecutiveSchemesData());
//            String str2 = GenerateStringUtils.generateProjectCode(aosrData.getPassportObjectData(), indexAosr);
//            values.add(List.of(Integer.toString(index), str1, str2, aosrData.getEndDate().format(formatter), passObj.getNameContractor(), "1"));
//            index++;
//            String str3 = GenerateStringUtils.generateExecutiveSchemes(aosrData.getExecutiveSchemesData(), aosrData.getProjectDocumentationData(), aosrData.getPassportObjectData(), indexAosr);
//            String str4 = GenerateStringUtils.generateExecutiveSchemesCode(aosrData.getPassportObjectData(), indexAosr);
//            values.add(List.of(Integer.toString(index), str3, str4, aosrData.getEndDate().format(formatter), passObj.getNameContractor(), "1"));
//            index++;
//            if (!aosrData.getActsViCData().isEmpty()) {
//                for (ActsViCData actsViCData : aosrData.getActsViCData()) {
//                    values.add(List.of(Integer.toString(index), "Акт визуально-измерительного контроля сварных соединений",
//                            actsViCData.getNumberActViC(), actsViCData.getDataActViC(), actsViCData.getNameLaboratory(), "1"));
//                    index++;
//                }
//            }
//            if (aosrData.getProtocolsGnbData() != null) {
//                ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
//                values.add(List.of(Integer.toString(index), "Протокол работ по бурению", protocolsGnbData.getNumberProtocolGnb(),
//                        protocolsGnbData.getDataProtocolGnb(), aosrData.getPassportObjectData().getNameContractor(), "1"));
//                index++;
//            }
//            if (aosrData.getSealingProtocolsData() != null) {
//                SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
//                values.add(List.of(Integer.toString(index), "Протокол определения степени уплотнения оснований методом режущих колец",
//                        sealingProtocolsData.getNumberSealingProtocols(), sealingProtocolsData.getDataSealingProtocols(),
//                        sealingProtocolsData.getNameLaboratory(), "1"));
//                index++;
//            }
//            indexAosr++;
//        }
//        String str1 = "УС-" + passObj.getProjectCode() + "-ЗР-01";
//        values.add(List.of(Integer.toString(index), "Журнал производства земляных работ", str1, aosrToExcavLogList.getLast().getEndDate().format(formatter),
//                passObj.getNameContractor(), "3"));
//        index++;
//        values.add(List.of(Integer.toString(index), "Общий журнал работ", "1", aosrDataList.getLast().getEndDate().format(formatter),
//                passObj.getNameContractor(), "14"));
//
//        updateSheet(excelDocument, "РЕЕСТР - папка №1", 8, values);
//
//        File tempXlsxFile = File.createTempFile("Registry", ".xlsx");
//        FileOutputStream xlsxOutputStream = new FileOutputStream(tempXlsxFile);
//        excelDocument.write(xlsxOutputStream);
//        xlsxOutputStream.close();
//
//        return tempXlsxFile;
//    }

    public static void addFileToZip(ZipOutputStream zos, File file, String zipEntryName, byte[] buffer) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            ZipEntry zipEntry = new ZipEntry(zipEntryName);
            zos.putNextEntry(zipEntry);
            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }
            zos.closeEntry();
        }
    }

//    private static Map<String, String> getPersonMap(PassportObjectData passObj) {
//        Map<String, String> personMap = new HashMap<>();
//        personMap.put(passObj.getFioContractor(), passObj.getPostContractor() + " " + passObj.getFioContractor());
//        personMap.put(passObj.getFioContractor2(), passObj.getPostContractor2() + " " + passObj.getFioContractor2());
//        personMap.put(passObj.getFioCustomer(), passObj.getPostCustomer() + " " + passObj.getNameCustomer() + " " + passObj.getFioCustomer());
//        personMap.put(passObj.getFioProjector(), passObj.getPostProjector() + " " + passObj.getNameProjector() + " " + passObj.getFioProjector());
//        personMap.put(passObj.getFioAnotherPerson(), passObj.getPostAnotherPerson() + " " + passObj.getNameAnotherPerson() + " " + passObj.getFioAnotherPerson());
//        return personMap;
//    }
}
