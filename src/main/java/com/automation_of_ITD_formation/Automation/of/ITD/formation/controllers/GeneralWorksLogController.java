package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.AosrRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.GeneralWorksLogRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateStringUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils.generateGeneralLogDocxFile;
import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils.generateGeneralLogXlsxFile;
import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.ReplacePlaceholderUtils.replacePlaceholder;
import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.UpdateSheetUtils.updateSheet;

@Controller
public class GeneralWorksLogController {
    @Autowired
    private final AosrRepository aosrRepository;

    @Autowired
    private GeneralWorksLogRepository generalWorksLogRepository;

    private final Map<String, Integer> monthMap = new HashMap<>();

    public GeneralWorksLogController(AosrRepository aosrRepository) {
        this.aosrRepository = aosrRepository;
        initializeMonthMap();
    }

    private void initializeMonthMap() {
        monthMap.put("января", 1);
        monthMap.put("февраля", 2);
        monthMap.put("марта", 3);
        monthMap.put("апреля", 4);
        monthMap.put("мая", 5);
        monthMap.put("июня", 6);
        monthMap.put("июля", 7);
        monthMap.put("августа", 8);
        monthMap.put("сентября", 9);
        monthMap.put("октября", 10);
        monthMap.put("ноября", 11);
        monthMap.put("декабря", 12);
    }

    @GetMapping("/general-works-log")
    public String generalWorksLog(Model model) {
        return "generalWorksLog";
    }

    @PostMapping("/general-works-log")
    @Transactional
    public void postGeneralWorksLog(HttpServletResponse response) {
        Iterable<GeneralWorksLogData> generalWorksLogList = generalWorksLogRepository.findAll();
        Iterator<GeneralWorksLogData> iterator = generalWorksLogList.iterator();
        GeneralWorksLogData generalWorksLogData;
        if (iterator.hasNext()) {
            generalWorksLogData = iterator.next();
        } else {
            generalWorksLogData = new GeneralWorksLogData();
        }
        Iterable<AosrData> aosrList = aosrRepository.findAll();
        List<AosrData> aosrDataList = new ArrayList<>();
        aosrList.forEach(aosrData -> {
                aosrData.setGeneralWorksLogData(generalWorksLogData);
                aosrDataList.add(aosrData);
                aosrRepository.save(aosrData);
        });
        aosrDataList.sort(Comparator.comparing(AosrData::getStartDate));
        generalWorksLogData.setAosrs(new HashSet<>(aosrDataList));
        generalWorksLogRepository.save(generalWorksLogData);
        try {
            AosrData firstAosrData = aosrDataList.getFirst();
            AosrData endAosrData = aosrDataList.getLast();
            PassportObjectData passObj = firstAosrData.getPassportObjectData();

            File tempDocxFile = generateGeneralLogDocxFile(passObj);

            File tempXlsxFile = generateGeneralLogXlsxFile(firstAosrData, endAosrData, aosrDataList);

            File tempZipFile = File.createTempFile("GeneralWorksLog", ".zip");
            try (ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(tempZipFile))) {
                zipOut.putNextEntry(new ZipEntry("GeneralWorksLog.docx"));
                try (FileInputStream fis = new FileInputStream(tempDocxFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zipOut.write(buffer, 0, length);
                    }
                }
                zipOut.closeEntry();

                zipOut.putNextEntry(new ZipEntry("GeneralWorksLog.xlsx"));
                try (FileInputStream fis = new FileInputStream(tempXlsxFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zipOut.write(buffer, 0, length);
                    }
                }
                zipOut.closeEntry();
            }

            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=GeneralWorksLog.zip");
            response.setHeader("Content-Length", String.valueOf(tempZipFile.length()));

            try (InputStream in = new FileInputStream(tempZipFile)) {
                FileCopyUtils.copy(in, response.getOutputStream());
            }
            response.flushBuffer();

            tempDocxFile.delete();
            tempXlsxFile.delete();
            tempZipFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}