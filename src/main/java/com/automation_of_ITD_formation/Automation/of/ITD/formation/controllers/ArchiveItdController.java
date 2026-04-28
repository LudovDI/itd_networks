//package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;
//
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.util.FileCopyUtils;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.io.*;
//import java.text.Collator;
//import java.util.*;
//import java.util.zip.ZipOutputStream;
//
//import static com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils.*;
//
//@Controller
//public class ArchiveItdController {
//    @Autowired
//    private GeneralWorksLogRepository generalWorksLogRepository;
//
//    @Autowired
//    private ExcavationLogRepository excavationLogRepository;
//
//    @Autowired
//    private UserDocumentsRepository userDocumentsRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private final AosrRepository aosrRepository;
//
//    private final Map<List<String>, String> spMap = new HashMap<>();
//    private final Map<String, Integer> monthMap = new HashMap<>();
//    Collator collator = Collator.getInstance(new Locale("ru", "RU"));
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
//    private void initializeMapSp() {
//        spMap.put(Arrays.asList("Механизированная разработка грунта и шурфовка на наличие существующих сетей", "Механизированная разработка грунта"), "СП 45.13330.2017 Земляные сооружения, основания и фундаменты, раздел 6;");
//        spMap.put(Arrays.asList("Устройство песчаного основания под трубопровод", "Устройство песчаного основания под колодец", "Обратная засыпка грунтом мест шурфовок", "Обратная засыпка грунтом", "Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом"), "СП 45.13330.2017 Земляные сооружения, основания и фундаменты, раздел 7;");
//        spMap.put(Arrays.asList("Сварка полиэтиленового трубопровода в плеть", "Сварка полиэтиленового трубопровода и фасонных частей", "Сварка трубопровода с помощью электромуфты", "Сварка и укладка на песчаное основание полиэтиленового трубопровода с фасонными частями"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7;");
//        spMap.put(Arrays.asList("Сварка стального трубопровода в плеть", "Сварка стального трубопровода и фасонных частей", "Сварка и укладка на песчаное основание стального трубопровода с фасонными частями"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
//        spMap.put(Arrays.asList("Сварка полиэтиленового трубопровода и фасонных частей, монтаж запорной арматуры в колодце"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
//        spMap.put(Arrays.asList("Сварка полиэтиленового трубопровода и фасонных частей, монтаж пожарного гидранта в колодце"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
//        spMap.put(Arrays.asList("Сварка стального трубопровода и фасонных частей, монтаж запорной арматуры в колодце"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
//        spMap.put(Arrays.asList("Сварка стального трубопровода и фасонных частей, монтаж пожарного гидранта в колодце"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
//        spMap.put(Arrays.asList("Устройство врезки в существующий полиэтиленовый трубопровод"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7;");
//        spMap.put(Arrays.asList("Устройство врезки в существующий стальной трубопровод"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
//        spMap.put(Arrays.asList("Монтаж колонок управления задвижками"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации;");
//        spMap.put(Arrays.asList("Бестраншейная прокладка методом ГНБ полиэтиленового трубопровода", "Бестраншейная прокладка полиэтиленового трубопровода с протаскиванием в футляр методом ГНБ"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 341.1325800.2017 Подземные инженерные коммуникации. Прокладка горизонтальным направленным бурением, раздел 8;");
//        spMap.put(Arrays.asList("Бестраншейная прокладка методом ГНБ стального трубопровода", "Бестраншейная прокладка стального трубопровода с протаскиванием в футляр методом ГНБ"), "СП 341.1325800.2017 Подземные инженерные коммуникации. Прокладка горизонтальным направленным бурением, раздел 8;");
//        spMap.put(Arrays.asList("Заделка концов футляра"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел ;");
//        spMap.put(Arrays.asList("Монтаж днища и рабочей части сборного железобетонного колодца", "Монтаж плиты перекрытия и горловины сборного железобетонного колодца"), "СП 70.13330.2012 Несущие и ограждающие конструкции, раздел 6;");
//        spMap.put(Arrays.asList("Монтаж стремянок", "Монтаж опор под пожарные гидранты", "Демонтаж "), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации;");
//        spMap.put(Arrays.asList("Устройство оклеечной гидроизоляции стеклоизолом в два слоя по слою праймера битумного наружной поверхности колодца, а также мест стыковок железобетонных элементов колодца"), "СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 9;");
//        spMap.put(Arrays.asList("Герметизация мест прохода полиэтиленового трубопровода через стенки колодца"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 4;");
//        spMap.put(Arrays.asList("Герметизация мест прохода стального через стенки колодца"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел ;");
//        spMap.put(Arrays.asList("Подготовка под изоляцию (очистка, обеспыливание, обезжиривание) металлических поверхностей"), "СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 5;");
//        spMap.put(Arrays.asList("Огрунтовка за один раз металлических поверхностей", "Окраска в два слоя металлических огрунтованных поверхностей"), "СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 6;");
//        spMap.put(Arrays.asList("Нанесение антикоррозийной изоляции лентами поливинилхлоридными липкими в три слоя по слою праймера битумного на стальной трубопровод"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации; СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 9;");
//    }
//
//    public ArchiveItdController(AosrRepository aosrRepository) {
//        this.aosrRepository = aosrRepository;
//        initializeMapSp();
//        initializeMonthMap();
//    }
//
//    @GetMapping("/archive-itd")
//    public String archiveItd(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//        if (userDetails == null) {
//            return "redirect:/login";
//        }
//        Optional<UserData> user = userRepository.findByUsername(userDetails.getUsername());
//        Optional<UserDocumentsData> optionalDocuments = userDocumentsRepository.findByUserData(user.orElseThrow());
//        if (optionalDocuments.isPresent()) {
//            UserDocumentsData userDocumentsData = optionalDocuments.get();
//            Set<String> documents = userDocumentsData.getDocuments();
//            model.addAttribute("documents", documents);
//        }
//
//        return "archiveItd";
//    }
//
//    @PostMapping("/archive-itd")
//    public void postArchiveItd(HttpServletResponse response) {
//        List<File> tempFiles = new ArrayList<>();
//        Iterable<AosrData> aosrList = aosrRepository.findAll();
//        Iterable<ExcavationLogData> excavationLogList = excavationLogRepository.findAll();
//        Iterator<ExcavationLogData> iterator = excavationLogList.iterator();
//        ExcavationLogData excavationLogData = iterator.next();
//        List<AosrData> aosrToLogList = new ArrayList<>();
//        List<AosrData> aosrAllList = new ArrayList<>();
//        aosrList.forEach(aosrData -> {
//            if (aosrData.getTypeOfWork().equals("Механизированная разработка грунта и шурфовка на наличие существующих сетей") || aosrData.getTypeOfWork().equals("Механизированная разработка грунта") || aosrData.getTypeOfWork().equals("Устройство песчаного основания под трубопровод") || aosrData.getTypeOfWork().equals("Устройство песчаного основания под колодец") || aosrData.getTypeOfWork().equals("Обратная засыпка грунтом мест шурфовок") || aosrData.getTypeOfWork().equals("Обратная засыпка грунтом") || aosrData.getTypeOfWork().equals("Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом")) {
//                aosrToLogList.add(aosrData);
//                aosrData.setExcavationLogData(excavationLogData);
//            }
//            aosrAllList.add(aosrData);
//        });
//        aosrAllList.sort(Comparator.comparing(AosrData::getStartDate));
//        aosrToLogList.sort(Comparator.comparing(AosrData::getStartDate));
//        try {
//            int indexAosr = 1;
//            for (AosrData aosrData : aosrList) {
//                File tempFile = GenerateFileUtils.generateAosrFile(aosrData, indexAosr, spMap, monthMap);
//                tempFiles.add(tempFile);
//                indexAosr++;
//            }
//
//            File excavationLogFile = GenerateFileUtils.generateExcavationLogFile(monthMap, excavationLogData.getNamePerformanceManager(), aosrToLogList);
//
//            AosrData firstAosr = aosrAllList.getFirst();
//            AosrData endAosr = aosrAllList.getLast();
//
//            File generalLogDocxFile = generateGeneralLogDocxFile(firstAosr.getPassportObjectData());
//
//            File generalLogXlsxFile = generateGeneralLogXlsxFile(firstAosr, endAosr, aosrAllList);
//
//            File regitryFile = generateRegistryFile(firstAosr.getPassportObjectData(), aosrAllList, aosrToLogList);
//
//            File zipFile = File.createTempFile("archiveItd", ".zip");
//            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
//                byte[] buffer = new byte[1024];
//
//                for (File file : tempFiles) {
//                    addFileToZip(zos, file, "АОСР/" + file.getName(), buffer);
//                }
//
//                addFileToZip(zos, excavationLogFile, "ЖЗР.docx", buffer);
//                addFileToZip(zos, generalLogDocxFile, "ОЖР.docx", buffer);
//                addFileToZip(zos, generalLogXlsxFile, "ОЖР.xlsx", buffer);
//                addFileToZip(zos, regitryFile, "Реестр.xlsx", buffer);
//            }
//
//            response.setContentType("application/zip");
//            response.setHeader("Content-Disposition", "attachment; filename=archiveItd.zip");
//            response.setHeader("Content-Length", String.valueOf(zipFile.length()));
//
//            try (InputStream in = new FileInputStream(zipFile)) {
//                FileCopyUtils.copy(in, response.getOutputStream());
//            }
//
//            response.flushBuffer();
//
//            for (File file : tempFiles) {
//                file.delete();
//            }
//            excavationLogFile.delete();
//            generalLogDocxFile.delete();
//            generalLogXlsxFile.delete();
//            regitryFile.delete();
//            zipFile.delete();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}