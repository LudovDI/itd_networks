//package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;
//
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.AosrRepository;
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.ExcavationLogRepository;
////import com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils;
//import jakarta.servlet.http.HttpServletResponse;
//import org.apache.poi.xwpf.usermodel.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.util.*;
//
//@Controller
//public class ExcavationLogController {
//    @Autowired
//    private final AosrRepository aosrRepository;
//
//    @Autowired
//    private ExcavationLogRepository excavationLogRepository;
//
//    private final Map<String, Integer> monthMap = new HashMap<>();
//
//    public ExcavationLogController(AosrRepository aosrRepository) {
//        this.aosrRepository = aosrRepository;
//        initializeMonthMap();
//    }
//
//    private void initializeMonthMap() {
//        monthMap.put("января", 1);
//        monthMap.put("февраля", 2);
//        monthMap.put("марта", 3);
//        monthMap.put("апреля", 4);
//        monthMap.put("мая", 5);
//        monthMap.put("июня", 6);
//        monthMap.put("июля", 7);
//        monthMap.put("августа", 8);
//        monthMap.put("сентября", 9);
//        monthMap.put("октября", 10);
//        monthMap.put("ноября", 11);
//        monthMap.put("декабря", 12);
//    }
//
//    @GetMapping("/excavation-log")
//    public String excavationLog(Model model) {
//        Iterable<AosrData> aosrList = aosrRepository.findAll();
//        List<AosrData> aosrToLogList = new ArrayList<>();
//        aosrList.forEach(aosrData -> {
//            if (aosrData.getTypeOfWork().equals("Механизированная разработка грунта и шурфовка на наличие существующих сетей") || aosrData.getTypeOfWork().equals("Механизированная разработка грунта") || aosrData.getTypeOfWork().equals("Устройство песчаного основания под трубопровод") || aosrData.getTypeOfWork().equals("Устройство песчаного основания под колодец") || aosrData.getTypeOfWork().equals("Обратная засыпка грунтом мест шурфовок") || aosrData.getTypeOfWork().equals("Обратная засыпка грунтом") || aosrData.getTypeOfWork().equals("Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом")) {
//                aosrToLogList.add(aosrData);
//            }
//        });
//        aosrToLogList.sort(Comparator.comparing(AosrData::getStartDate));
//
//        model.addAttribute("aosrToLogList", aosrToLogList);
//        Set<String> namePersons = new HashSet<>();
//        Iterator<AosrData> iterator = aosrList.iterator();
//        if (iterator.hasNext()) {
//            AosrData firstElement = iterator.next();
//            PassportObjectData passportObjectData = firstElement.getPassportObjectData();
////            if (passportObjectData.getFioContractor() != null) {
////                namePersons.add(passportObjectData.getFioContractor());
////            }
////            if (passportObjectData.getFioContractor2() != null) {
////                namePersons.add(passportObjectData.getFioContractor2());
////            }
////            if (passportObjectData.getFioCustomer() != null) {
////                namePersons.add(passportObjectData.getFioCustomer());
////            }
////            if (passportObjectData.getFioProjector() != null) {
////                namePersons.add(passportObjectData.getFioProjector());
////            }
////            if (passportObjectData.getFioAnotherPerson() != null) {
////                namePersons.add(passportObjectData.getFioAnotherPerson());
////            }
//        }
//
//        model.addAttribute("namePersons", namePersons);
//        return "excavationLog";
//    }
//
//    @PostMapping("/excavation-log")
//    @Transactional
//    public void postExcavationLog(@RequestParam("NamePerformanceManager") String namePerformanceManager,
//                                  HttpServletResponse response) {
//        Iterable<ExcavationLogData> excavationLogList = excavationLogRepository.findAll();
//        Iterator<ExcavationLogData> iterator = excavationLogList.iterator();
//        ExcavationLogData excavationLogData;
//        if (iterator.hasNext()) {
//            excavationLogData = iterator.next();
//            excavationLogData.setNamePerformanceManager(namePerformanceManager);
//        } else {
//            excavationLogData = new ExcavationLogData(namePerformanceManager);
//        }
//        Iterable<AosrData> aosrList = aosrRepository.findAll();
//        List<AosrData> aosrToLogList = new ArrayList<>();
//        aosrList.forEach(aosrData -> {
//            if (aosrData.getTypeOfWork().equals("Механизированная разработка грунта и шурфовка на наличие существующих сетей") || aosrData.getTypeOfWork().equals("Механизированная разработка грунта") || aosrData.getTypeOfWork().equals("Устройство песчаного основания под трубопровод") || aosrData.getTypeOfWork().equals("Устройство песчаного основания под колодец") || aosrData.getTypeOfWork().equals("Обратная засыпка грунтом мест шурфовок") || aosrData.getTypeOfWork().equals("Обратная засыпка грунтом") || aosrData.getTypeOfWork().equals("Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом")) {
//                aosrToLogList.add(aosrData);
//                aosrData.setExcavationLogData(excavationLogData);
//                aosrRepository.save(aosrData);
//            }
//        });
//        aosrToLogList.sort(Comparator.comparing(AosrData::getStartDate));
//        excavationLogData.setAosrs(new HashSet<>(aosrToLogList));
//        excavationLogRepository.save(excavationLogData);
//        try {
////            File excavationLogFile = GenerateFileUtils.generateExcavationLogFile(monthMap, namePerformanceManager, aosrToLogList);
////
////            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
////            response.setHeader("Content-Disposition", "attachment; filename=ExcavationLog.docx");
////            response.setHeader("Content-Length", String.valueOf(excavationLogFile.length()));
////
////            InputStream in = new FileInputStream(excavationLogFile);
////            FileCopyUtils.copy(in, response.getOutputStream());
////            response.flushBuffer();
////            excavationLogFile.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}