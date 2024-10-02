package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.Collator;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Controller
public class AosrController {
    @Autowired
    private AosrRepository aosrRepository;

    @Autowired
    private final PassportObjectRepository passportObjectRepository;

    @Autowired
    private final ProjectDocumentationRepository projectDocumentationRepository;

    @Autowired
    private DrawingsRepository drawingsRepository;

    @Autowired
    private final MaterialsUsedRepository materialsUsedRepository;

    @Autowired
    private final ExecutiveSchemesRepository executiveSchemesRepository;

    @Autowired
    private final ActsViCRepository actsViCRepository;

    @Autowired
    private final SealingProtocolsRepository sealingProtocolsRepository;

    @Autowired
    private final ProtocolsGnbRepository protocolsGnbRepository;

    private final Map<List<String>, String> spMap = new HashMap<>();
    private final Map<String, Integer> monthMap = new HashMap<>();
    Collator collator = Collator.getInstance(new Locale("ru", "RU"));
    private final Set<String> typeOfWorkSet = new TreeSet<>(collator);

    public AosrController(PassportObjectRepository passportObjectRepository,
                          ProjectDocumentationRepository projectDocumentationRepository,
                          MaterialsUsedRepository materialsUsedRepository,
                          ExecutiveSchemesRepository executiveSchemesRepository,
                          ActsViCRepository actsViCRepository,
                          SealingProtocolsRepository sealingProtocolsRepository,
                          ProtocolsGnbRepository protocolsGnbRepository) {
        this.passportObjectRepository = passportObjectRepository;
        this.projectDocumentationRepository = projectDocumentationRepository;
        this.materialsUsedRepository = materialsUsedRepository;
        this.executiveSchemesRepository = executiveSchemesRepository;
        this.actsViCRepository = actsViCRepository;
        this.sealingProtocolsRepository = sealingProtocolsRepository;
        this.protocolsGnbRepository = protocolsGnbRepository;
        initializeMapSp();
        initializeMonthMap();
        initializeTypeOfWorkSet();
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

    private void initializeMapSp() {
        spMap.put(Arrays.asList("Механизированная разработка грунта и шурфовка на наличие существующих сетей", "Механизированная разработка грунта"), "СП 45.13330.2017 Земляные сооружения, основания и фундаменты, раздел 6;");
        spMap.put(Arrays.asList("Устройство песчаного основания под трубопровод", "Устройство песчаного основания под колодец", "Обратная засыпка грунтом мест шурфовок", "Обратная засыпка грунтом", "Устройство над трубопроводом защитного слоя из песка и обратная засыпка грунтом"), "СП 45.13330.2017 Земляные сооружения, основания и фундаменты, раздел 7;");
        spMap.put(Arrays.asList("Сварка полиэтиленового трубопровода в плеть", "Сварка полиэтиленового трубопровода и фасонных частей", "Сварка трубопровода с помощью электромуфты", "Сварка и укладка на песчаное основание полиэтиленового трубопровода с фасонными частями"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7;");
        spMap.put(Arrays.asList("Сварка стального трубопровода в плеть", "Сварка стального трубопровода и фасонных частей", "Сварка и укладка на песчаное основание стального трубопровода с фасонными частями"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
        spMap.put(Arrays.asList("Сварка полиэтиленового трубопровода и фасонных частей, монтаж запорной арматуры в колодце"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
        spMap.put(Arrays.asList("Сварка полиэтиленового трубопровода и фасонных частей, монтаж пожарного гидранта в колодце"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
        spMap.put(Arrays.asList("Сварка стального трубопровода и фасонных частей, монтаж запорной арматуры в колодце"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
        spMap.put(Arrays.asList("Сварка стального трубопровода и фасонных частей, монтаж пожарного гидранта в колодце"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
        spMap.put(Arrays.asList("Устройство врезки в существующий полиэтиленовый трубопровод"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 40-102-2000 Проектирование и монтаж трубопроводов систем водоснабжения и канализации из полимерных материалов, раздел 7;");
        spMap.put(Arrays.asList("Устройство врезки в существующий стальной трубопровод"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел 6;");
        spMap.put(Arrays.asList("Монтаж колонок управления задвижками"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации;");
        spMap.put(Arrays.asList("Бестраншейная прокладка методом ГНБ полиэтиленового трубопровода", "Бестраншейная прокладка полиэтиленового трубопровода с протаскиванием в футляр методом ГНБ"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 341.1325800.2017 Подземные инженерные коммуникации. Прокладка горизонтальным направленным бурением, раздел 8;");
        spMap.put(Arrays.asList("Бестраншейная прокладка методом ГНБ стального трубопровода", "Бестраншейная прокладка стального трубопровода с протаскиванием в футляр методом ГНБ"), "СП 341.1325800.2017 Подземные инженерные коммуникации. Прокладка горизонтальным направленным бурением, раздел 8;");
        spMap.put(Arrays.asList("Заделка концов футляра"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 6; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел ;");
        spMap.put(Arrays.asList("Монтаж днища и рабочей части сборного железобетонного колодца", "Монтаж плиты перекрытия и горловины сборного железобетонного колодца"), "СП 70.13330.2012 Несущие и ограждающие конструкции, раздел 6;");
        spMap.put(Arrays.asList("Монтаж стремянок", "Монтаж опор под пожарные гидранты", "Демонтаж "), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации;");
        spMap.put(Arrays.asList("Устройство оклеечной гидроизоляции стеклоизолом в два слоя по слою праймера битумного наружной поверхности колодца, а также мест стыковок железобетонных элементов колодца"), "СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 9;");
        spMap.put(Arrays.asList("Герметизация мест прохода полиэтиленового трубопровода через стенки колодца"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов, раздел 4;");
        spMap.put(Arrays.asList("Герметизация мест прохода стального через стенки колодца"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации, раздел ;");
        spMap.put(Arrays.asList("Подготовка под изоляцию (очистка, обеспыливание, обезжиривание) металлических поверхностей"), "СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 5;");
        spMap.put(Arrays.asList("Огрунтовка за один раз металлических поверхностей", "Окраска в два слоя металлических огрунтованных поверхностей"), "СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 6;");
        spMap.put(Arrays.asList("Нанесение антикоррозийной изоляции лентами поливинилхлоридными липкими в три слоя по слою праймера битумного на стальной трубопровод"), "СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации; СП 72.13330.2016 Защита строительных конструкций и сооружений от коррозии, раздел 9;");
    }

    private void initializeTypeOfWorkSet() {
        spMap.keySet().forEach(typeOfWorkSet::addAll);
    }

    private void populateModel(Model model) {
        Iterable<ProjectDocumentationData> projectDocumentationList = projectDocumentationRepository.findAll();
        Iterator<ProjectDocumentationData> iterator = projectDocumentationList.iterator();
        ProjectDocumentationData projDoc = iterator.next();
        Iterable<ExecutiveSchemesData> executiveSchemesList = executiveSchemesRepository.findAll();
        Iterable<MaterialsUsedData> materialsUsedList = materialsUsedRepository.findAll();
        Iterable<ActsViCData> actsViCList = actsViCRepository.findAll();
        Iterable<SealingProtocolsData> sealingProtocolsList = sealingProtocolsRepository.findAll();
        Iterable<ProtocolsGnbData> protocolsGnbList = protocolsGnbRepository.findAll();
        model.addAttribute("actsViCList", actsViCList);
        model.addAttribute("sealingProtocolsList", sealingProtocolsList);
        model.addAttribute("protocolsGnbList", protocolsGnbList);
        model.addAttribute("materialsUsedList", materialsUsedList);
        model.addAttribute("executiveSchemesList", executiveSchemesList);
        model.addAttribute("projDoc", projDoc);
        model.addAttribute("typeOfWorkSet", typeOfWorkSet);
    }

    @GetMapping("/aosr-table")
    public String aosrTable(Model model) {
        Iterable<AosrData> aosrList = aosrRepository.findAll();
        model.addAttribute("aosrList", aosrList);
        Map<Long, Set<String>> projectSectionMap = new HashMap<>();
        for (AosrData aosr : aosrList) {
            Set<String> mySet = new HashSet<>();
            for (DrawingsData drawing : aosr.getAosrToDrawings()) {
                mySet.add(drawing.getProjDocToDrawings().getProjectSection());
            }
            projectSectionMap.put(aosr.getId(), mySet);
        }
        model.addAttribute("projectSectionMap", projectSectionMap);
        return "aosrTable";
    }

    @GetMapping("/aosr-add")
    public String aosrAdd(Model model) {
        populateModel(model);
        return "aosrAdd";
    }

    @GetMapping("/search-materials")
    public String searchMaterials(@RequestParam("query") String query,
                                  @RequestParam(name = "selectedMaterials", required = false) List<Long> selectedMaterialsIds,
                                  @RequestParam(name = "sourcePage", required = false, defaultValue = "aosrAdd") String sourcePage,
                                  Model model) {

        List<MaterialsUsedData> foundMaterials = materialsUsedRepository.findByNameMaterialContainingIgnoreCase(query);
        Iterable<MaterialsUsedData> selectedMaterials = new ArrayList<>();
        if (selectedMaterialsIds != null) {
            selectedMaterials = materialsUsedRepository.findAllById(selectedMaterialsIds);
            foundMaterials = foundMaterials.stream()
                    .filter(material -> !selectedMaterialsIds.contains(material.getId()))
                    .collect(Collectors.toList());
        }
        model.addAttribute("foundMaterialsList", foundMaterials);
        model.addAttribute("selectedMaterialsList", selectedMaterials);
        populateModel(model);
        return sourcePage;
    }

    @PostMapping("/aosr-table/{id}/update-status")
    public String updateStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        AosrData aosr = aosrRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid act Id:" + id));
        aosr.setStatus(status);
        aosrRepository.save(aosr);
        return "redirect:/aosr-table";
    }

    @PostMapping("/aosr-add")
    public String postAosrAdd(@RequestParam("work1") String work1,
                              @RequestParam("work2") String work2,
                              @RequestParam("dayStart") String dayStart,
                              @RequestParam("monthStart") String monthStart,
                              @RequestParam("yearStart") String yearStart,
                              @RequestParam("dayEnd") String dayEnd,
                              @RequestParam("monthEnd") String monthEnd,
                              @RequestParam("yearEnd") String yearEnd,
                              @RequestParam("execSchemSelect") Long execSchemId,
                              @RequestParam("protocolGnbSelect") Long protocolGnbId,
                              @RequestParam("sealingProtocolSelect") Long sealingProtocolId,
                              @RequestParam(value = "actViCCheckbox", required = false) List<Long> actViCIds,
                              @RequestParam(value = "drawingCheckbox", required = false) List<Long> drawingIds,
                              @RequestParam(value = "selectedMaterials", required = false) List<Long> materialIds) {
        yearStart = "20" + yearStart;
        yearEnd = "20" + yearEnd;

        int monthStartNum = monthMap.get(monthStart.toLowerCase());
        int monthEndNum = monthMap.get(monthEnd.toLowerCase());

        LocalDate startDate = LocalDate.of(Integer.parseInt(yearStart), monthStartNum, Integer.parseInt(dayStart));
        LocalDate endDate = LocalDate.of(Integer.parseInt(yearEnd), monthEndNum, Integer.parseInt(dayEnd));

        AosrData aosrData = new AosrData(work1, work2, startDate, endDate);

        if (materialIds != null) {
            Set<MaterialsUsedData> materials = new HashSet<>();
            for (Long materialId : materialIds) {
                MaterialsUsedData material = materialsUsedRepository.findById(materialId).orElseThrow();
                materials.add(material);
            }
            aosrData.setAosrToMaterials(materials);
        }
        if (drawingIds != null) {
            Set<DrawingsData> drawings = new HashSet<>();
            for (Long drawingId : drawingIds) {
                DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                drawings.add(drawing);
            }
            aosrData.setAosrToDrawings(drawings);
            if (drawings.stream().findFirst().isPresent()) {
                DrawingsData drawingsData = drawings.stream().findFirst().get();
                aosrData.setProjectDocumentationData(drawingsData.getProjDocToDrawings());
            }
        }
        if (execSchemId != null) {
            ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();
            aosrData.setExecutiveSchemesData(executiveSchemesData);
        }
        if (protocolGnbId != null && protocolGnbId != -1) {
            ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(protocolGnbId).orElseThrow();
            aosrData.setProtocolsGnbData(protocolsGnbData);
        }
        if (actViCIds != null) {
            Set<ActsViCData> actsViCSet = new HashSet<>();
            for (Long actVicId : actViCIds) {
                ActsViCData actsViCData = actsViCRepository.findById(actVicId).orElseThrow();
                actsViCSet.add(actsViCData);
            }
            aosrData.setActsViCData(actsViCSet);
        }
        if (sealingProtocolId != null && sealingProtocolId != -1) {
            SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(sealingProtocolId).orElseThrow();
            aosrData.setSealingProtocolsData(sealingProtocolsData);
        }

        Iterable<PassportObjectData> passportObjectList = passportObjectRepository.findAll();
        passportObjectList.forEach(aosrData::setPassportObjectData);

        aosrRepository.save(aosrData);
        return "redirect:/aosr-table";
    }

    @PostMapping("/aosr-table/{id}/aosr-remove")
    @Transactional
    public String postAosrDelete(@PathVariable(value = "id") long id, Model model) {
        AosrData aosrData = aosrRepository.findById(id).orElseThrow();

        Set<DrawingsData> drawings = aosrData.getAosrToDrawings();
        Set<MaterialsUsedData> materialsUsedSet = aosrData.getAosrToMaterials();
        Set<ActsViCData> actsViCSet = aosrData.getActsViCData();

        aosrRepository.delete(aosrData);

        if (!drawings.isEmpty()) {
            drawings.forEach(drawing -> {
                Set<AosrData> aosrs = drawing.getDrawingsToAosr();
                aosrs.remove(aosrData);
                drawing.setDrawingsToAosr(aosrs);
                drawingsRepository.save(drawing);
            });
        }

        if (!materialsUsedSet.isEmpty()) {
            materialsUsedSet.forEach(material -> {
                Set<AosrData> aosrs = material.getMaterialsToAosr();
                aosrs.remove(aosrData);
                material.setMaterialsToAosr(aosrs);
                materialsUsedRepository.save(material);
            });
        }

        if (!actsViCSet.isEmpty()) {
            actsViCSet.forEach(actViC -> {
                actViC.setDataActViC(null);
                actsViCRepository.save(actViC);
            });
        }

        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
        if (executiveSchemesData != null) {
            Set<AosrData> aosrs = executiveSchemesData.getAosrs();
            aosrs.remove(aosrData);
            executiveSchemesData.setExecSchemesToAosr(aosrs);
            executiveSchemesRepository.save(executiveSchemesData);
        }

        ProjectDocumentationData projectDocumentationData = aosrData.getProjectDocumentationData();
        if (projectDocumentationData != null) {
            Set<AosrData> aosrs = projectDocumentationData.getAosrs();
            aosrs.remove(aosrData);
            projectDocumentationData.setAosrs(aosrs);
            projectDocumentationRepository.save(projectDocumentationData);
        }
        PassportObjectData passportObjectData = aosrData.getPassportObjectData();
        if (passportObjectData != null) {
            Set<AosrData> aosrs = passportObjectData.getAosrs();
            aosrs.remove(aosrData);
            passportObjectData.setAosrs(aosrs);
            passportObjectRepository.save(passportObjectData);
        }
        ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
        if (protocolsGnbData != null) {
            protocolsGnbData.setGnbToAosrData(null);
            protocolsGnbRepository.save(protocolsGnbData);
        }
        SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
        if (sealingProtocolsData != null) {
            sealingProtocolsData.setSealingToAosrData(null);
            sealingProtocolsRepository.save(sealingProtocolsData);
        }

        return "redirect:/aosr-table";
    }

    @GetMapping("/aosr-table/{id}/aosr-edit")
    public String aosrEdit(@PathVariable(value = "id") long id, Model model) {
        if (!aosrRepository.existsById(id)) {
            return "redirect:/aosr-table";
        }
        Optional<AosrData> aosrDataOptional = aosrRepository.findById(id);
        if (aosrDataOptional.isPresent()) {
            AosrData aosrData = aosrDataOptional.get();
            List<Long> selectedMaterialsIds = new ArrayList<>();
            aosrData.getAosrToMaterials().forEach(materialsUsedData -> selectedMaterialsIds.add(materialsUsedData.getId()));
            Iterable<MaterialsUsedData> selectedMaterials = materialsUsedRepository.findAllById(selectedMaterialsIds);
            Set<Long> drawingIds = aosrData.getAosrToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
            List<String> myList = new ArrayList<>();
            for (DrawingsData drawing : aosrData.getAosrToDrawings()) {
                if (!myList.contains(drawing.getProjDocToDrawings().getProjectSection())) {
                    myList.add(drawing.getProjDocToDrawings().getProjectSection());
                }
            }
            String countProjects = (myList.size() == 2) ? "2" : "1";
            String projectSection = myList.getFirst();
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
            model.addAttribute("dayStart", dayStart);
            model.addAttribute("yearStart", yearStart);
            model.addAttribute("dayEnd", dayEnd);
            model.addAttribute("yearEnd", yearEnd);
            model.addAttribute("monthStart", monthStart);
            model.addAttribute("monthEnd", monthEnd);
            model.addAttribute("countProjects", countProjects);
            model.addAttribute("projectSection", projectSection);
            model.addAttribute("drawingIds", drawingIds);
            model.addAttribute("aosrData", aosrData);
            model.addAttribute("selectedMaterialsList", selectedMaterials);
        }
        populateModel(model);
        return "aosrEdit";
    }

    @PostMapping("/aosr-table/{id}/aosr-edit")
    @Transactional
    public String postAosrUpdate(@PathVariable(value = "id") long id,
                                 @RequestParam("work1") String work1,
                                 @RequestParam("work2") String work2,
                                 @RequestParam("dayStart") String dayStart,
                                 @RequestParam("monthStart") String monthStart,
                                 @RequestParam("yearStart") String yearStart,
                                 @RequestParam("dayEnd") String dayEnd,
                                 @RequestParam("monthEnd") String monthEnd,
                                 @RequestParam("yearEnd") String yearEnd,
                                 @RequestParam("execSchemSelect") Long execSchemId,
                                 @RequestParam("countProjectsSelect") String countProjectsSelect,
                                 @RequestParam("projectSectionSelect") String projectSectionSelect,
                                 @RequestParam("protocolGnbSelect") Long protocolGnbId,
                                 @RequestParam("sealingProtocolSelect") Long sealingProtocolId,
                                 @RequestParam(value = "actViCCheckbox", required = false) List<Long> actViCIds,
                                 @RequestParam(value = "drawingCheckbox", required = false) List<Long> drawingIds,
                                 @RequestParam(value = "selectedMaterials", required = false) List<Long> materialIds) {
        AosrData aosrData = aosrRepository.findById(id).orElseThrow();

        for (DrawingsData drawing : aosrData.getAosrToDrawings()) {
            drawing.getDrawingsToAosr().remove(aosrData);
            drawingsRepository.save(drawing);
        }
        aosrData.getAosrToDrawings().clear();

        if (drawingIds != null) {
            if (countProjectsSelect.equals("2")) {
                for (Long drawingId : drawingIds) {
                    DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                    drawing.getDrawingsToAosr().add(aosrData);
                    aosrData.getAosrToDrawings().add(drawing);
                    drawingsRepository.save(drawing);
                }
            } else {
                for (Long drawingId : drawingIds) {
                    DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                    if (drawing.getProjDocToDrawings().getProjectSection().equals(projectSectionSelect)) {
                        drawing.getDrawingsToAosr().add(aosrData);
                        aosrData.getAosrToDrawings().add(drawing);
                        drawingsRepository.save(drawing);
                    }
                }
            }
        }

        for (MaterialsUsedData material : aosrData.getAosrToMaterials()) {
            material.getMaterialsToAosr().remove(aosrData);
            materialsUsedRepository.save(material);
        }
        aosrData.getAosrToMaterials().clear();

        if (materialIds != null) {
            for (Long materialId : materialIds) {
                if (materialId != -1) {
                    MaterialsUsedData material = materialsUsedRepository.findById(materialId).orElseThrow();
                    material.getMaterialsToAosr().add(aosrData);
                    aosrData.getAosrToMaterials().add(material);
                    materialsUsedRepository.save(material);
                }
            }
        }
        if (protocolGnbId != null) {
            if (protocolGnbId != -1) {
                ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(protocolGnbId).orElseThrow();
                aosrData.setProtocolsGnbData(protocolsGnbData);
                protocolsGnbData.setGnbToAosrData(aosrData);
                protocolsGnbRepository.save(protocolsGnbData);
            } else {
                ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
                if (protocolsGnbData != null) {
                    protocolsGnbData.setGnbToAosrData(null);
                    aosrData.setProtocolsGnbData(null);
                    protocolsGnbRepository.save(protocolsGnbData);
                }
            }
        }

        for (ActsViCData actsViCData : aosrData.getActsViCData()) {
            actsViCData.setActsToAosrData(null);
            actsViCRepository.save(actsViCData);
        }
        aosrData.getAosrToDrawings().clear();

        if (actViCIds != null) {
            for (Long actVicId : actViCIds) {
                ActsViCData actsViCData = actsViCRepository.findById(actVicId).orElseThrow();
                actsViCData.setActsToAosrData(aosrData);
                aosrData.getActsViCData().add(actsViCData);
                actsViCRepository.save(actsViCData);
            }
        }

        if (sealingProtocolId != null) {
            if (sealingProtocolId != -1) {
                SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(sealingProtocolId).orElseThrow();
                aosrData.setSealingProtocolsData(sealingProtocolsData);
                sealingProtocolsData.setSealingToAosrData(aosrData);
                sealingProtocolsRepository.save(sealingProtocolsData);
            } else {
                SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
                if (sealingProtocolsData != null) {
                    sealingProtocolsData.setSealingToAosrData(null);
                    aosrData.setSealingProtocolsData(null);
                    sealingProtocolsRepository.save(sealingProtocolsData);
                }
            }
        }

        ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();
        aosrData.setExecutiveSchemesData(executiveSchemesData);
        aosrData.setTypeOfWork(work1);
        aosrData.setPermittedFollowingWork(work2);

        yearStart = "20" + yearStart;
        yearEnd = "20" + yearEnd;

        int monthStartNum = monthMap.get(monthStart.toLowerCase());
        int monthEndNum = monthMap.get(monthEnd.toLowerCase());

        LocalDate startDate = LocalDate.of(Integer.parseInt(yearStart), monthStartNum, Integer.parseInt(dayStart));
        LocalDate endDate = LocalDate.of(Integer.parseInt(yearEnd), monthEndNum, Integer.parseInt(dayEnd));

        aosrData.setStartDate(startDate);
        aosrData.setEndDate(endDate);

        aosrRepository.save(aosrData);

        return "redirect:/aosr-table";
    }

    @PostMapping("/aosr-table")
    public void postAosrTable(HttpServletResponse response) {
        List<File> tempFiles = new ArrayList<>();
        Iterable<AosrData> aosrList = aosrRepository.findAll();
        try {
            int indexAosr = 1;
            for (AosrData aosrData : aosrList) {
                File tempFile = GenerateFileUtils.generateAosrFile(aosrData, indexAosr, spMap, monthMap);
                tempFiles.add(tempFile);
                indexAosr++;
            }

            File zipFile = File.createTempFile("AosrDocs", ".zip");
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
                byte[] buffer = new byte[1024];
                for (File file : tempFiles) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zos.putNextEntry(zipEntry);
                        int len;
                        while ((len = fis.read(buffer)) > 0) {
                            zos.write(buffer, 0, len);
                        }
                        zos.closeEntry();
                    }
                }
            }

            response.setContentType("application/zip");
            response.setHeader("Content-Disposition", "attachment; filename=AosrDocs.zip");
            response.setHeader("Content-Length", String.valueOf(zipFile.length()));

            try (InputStream in = new FileInputStream(zipFile)) {
                FileCopyUtils.copy(in, response.getOutputStream());
            }

            response.flushBuffer();

            for (File file : tempFiles) {
                file.delete();
            }
            zipFile.delete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}