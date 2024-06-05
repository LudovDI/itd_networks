package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.AosrData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.ExcavationLogData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.PassportObjectData;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.AosrRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ExcavationLogRepository;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.GeneralWorksLogRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.util.*;

import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils.generateRegistryFile;

@Controller
public class RegistryController {
    @Autowired
    private final AosrRepository aosrRepository;

    @Autowired
    private GeneralWorksLogRepository generalWorksLogRepository;

    @Autowired
    private ExcavationLogRepository excavationLogRepository;

    private final Map<String, Integer> monthMap = new HashMap<>();

    public RegistryController(AosrRepository aosrRepository) {
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

    @GetMapping("/registry")
    public String registryLog(Model model) {
        return "registry";
    }

    @PostMapping("/registry")
    @Transactional
    public void postRegistry(HttpServletResponse response) {
        Iterable<ExcavationLogData> excavationLogDataIterable = excavationLogRepository.findAll();
        Iterator<ExcavationLogData> iterator = excavationLogDataIterable.iterator();
        ExcavationLogData excavationLogData = null;
        if (iterator.hasNext()) {
            excavationLogData = iterator.next();
        }
        Iterable<AosrData> aosrList = aosrRepository.findAll();
        Set<AosrData> aosrExcLogSet = excavationLogData.getAosrs();
        List<AosrData> aosrToExcavLogList = new ArrayList<>(aosrExcLogSet);
        List<AosrData> aosrDataList = new ArrayList<>();
        aosrList.forEach(aosrDataList::add);
        aosrDataList.sort(Comparator.comparing(AosrData::getStartDate));
        aosrToExcavLogList.sort(Comparator.comparing(AosrData::getStartDate));
        try {
            AosrData firstAosrData = aosrDataList.getFirst();
            PassportObjectData passObj = firstAosrData.getPassportObjectData();

            File tempXlsxFile = generateRegistryFile(passObj, aosrDataList, aosrToExcavLogList);

            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=Registry.xlsx");
            response.setHeader("Content-Length", String.valueOf(tempXlsxFile.length()));

            InputStream in = new FileInputStream(tempXlsxFile);
            FileCopyUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
            tempXlsxFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}