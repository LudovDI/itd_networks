package com.automation_of_ITD_formation.Automation.of.ITD.formation.controllers;

import com.automation_of_ITD_formation.Automation.of.ITD.formation.model.*;
import com.automation_of_ITD_formation.Automation.of.ITD.formation.repository.*;
//import com.automation_of_ITD_formation.Automation.of.ITD.formation.utils.GenerateFileUtils;
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
    private final AnotherPersonResponsibleRepository anotherPersonResponsibleRepository;

    @Autowired
    private final CustomerResponsibleRepository customerResponsibleRepository;

    @Autowired
    private final DesignerResponsibleRepository designerResponsibleRepository;

    @Autowired
    private final SubcustomerResponsibleRepository subcustomerResponsibleRepository;

    @Autowired
    private final SubcustomerResponsible2Repository subcustomerResponsible2Repository;

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

    public AosrController(PassportObjectRepository passportObjectRepository, ProjectDocumentationRepository projectDocumentationRepository,
                          MaterialsUsedRepository materialsUsedRepository, ExecutiveSchemesRepository executiveSchemesRepository,
                          ActsViCRepository actsViCRepository, SealingProtocolsRepository sealingProtocolsRepository,
                          ProtocolsGnbRepository protocolsGnbRepository, AnotherPersonResponsibleRepository anotherPersonResponsibleRepository,
                          CustomerResponsibleRepository customerResponsibleRepository, DesignerResponsibleRepository designerResponsibleRepository,
                          SubcustomerResponsibleRepository subcustomerResponsibleRepository, SubcustomerResponsible2Repository subcustomerResponsible2Repository) {
        this.passportObjectRepository = passportObjectRepository;
        this.projectDocumentationRepository = projectDocumentationRepository;
        this.materialsUsedRepository = materialsUsedRepository;
        this.executiveSchemesRepository = executiveSchemesRepository;
        this.actsViCRepository = actsViCRepository;
        this.sealingProtocolsRepository = sealingProtocolsRepository;
        this.protocolsGnbRepository = protocolsGnbRepository;
        this.anotherPersonResponsibleRepository = anotherPersonResponsibleRepository;
        this.customerResponsibleRepository = customerResponsibleRepository;
        this.designerResponsibleRepository = designerResponsibleRepository;
        this.subcustomerResponsibleRepository = subcustomerResponsibleRepository;
        this.subcustomerResponsible2Repository = subcustomerResponsible2Repository;
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
        spMap.put(Arrays.asList("Монтаж стремянок", "Монтаж опор под пожарные гидранты", "Демонтаж"), "СП 399.1325800.2018 Системы водоснабжения и канализации наружные из полимерных материалов; СП 129.13330.2019 Наружные сети и сооружения водоснабжения и канализации;");
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
        Iterable<ExecutiveSchemesData> executiveSchemesList = executiveSchemesRepository.findAll();
        Iterable<MaterialsUsedData> materialsUsedList = materialsUsedRepository.findAll();
        Iterable<ActsViCData> actsViCList = actsViCRepository.findAll();
        Iterable<SealingProtocolsData> sealingProtocolsList = sealingProtocolsRepository.findAll();
        Iterable<ProtocolsGnbData> protocolsGnbList = protocolsGnbRepository.findAll();
        Iterable<DrawingsData> drawingsList = drawingsRepository.findAll();
        Iterable<AnotherPersonResponsibleData> anotherPersonResponsibleList = anotherPersonResponsibleRepository.findAll();
        Iterable<CustomerResponsibleData> customerResponsibleList = customerResponsibleRepository.findAll();
        Iterable<DesignerResponsibleData> designerResponsibleList = designerResponsibleRepository.findAll();
        Iterable<SubcustomerResponsibleData> subcustomerResponsibleList = subcustomerResponsibleRepository.findAll();
        Iterable<SubcustomerResponsible2Data> subcustomerResponsible2List = subcustomerResponsible2Repository.findAll();
        model.addAttribute("drawingsList", drawingsList);
        model.addAttribute("actsViCList", actsViCList);
        model.addAttribute("sealingProtocolsList", sealingProtocolsList);
        model.addAttribute("protocolsGnbList", protocolsGnbList);
        model.addAttribute("materialsUsedList", materialsUsedList);
        model.addAttribute("executiveSchemesList", executiveSchemesList);
        model.addAttribute("projectDocumentationList", projectDocumentationList);
        model.addAttribute("anotherPersonResponsibleList", anotherPersonResponsibleList);
        model.addAttribute("customerResponsibleList", customerResponsibleList);
        model.addAttribute("designerResponsibleList", designerResponsibleList);
        model.addAttribute("subcustomerResponsibleList", subcustomerResponsibleList);
        model.addAttribute("subcustomerResponsible2List", subcustomerResponsible2List);
        model.addAttribute("typeOfWorkSet", typeOfWorkSet);
    }

    @GetMapping("/aosr-table")
    public String aosrTable(Model model) {
        Iterable<AosrData> aosrList = aosrRepository.findAll();
        model.addAttribute("aosrList", aosrList);
        return "aosrTable";
    }

    @GetMapping("/aosr-add")
    public String aosrAdd(Model model) {
        populateModel(model);
        model.addAttribute("aosrFormData", new ArrayList<String>());
        return "aosrAdd";
    }

    @GetMapping("/aosr-search-materials")
    public String searchMaterials(@RequestParam("query") String query,
                                  @RequestParam(name = "selectedMaterials", required = false) List<Long> selectedMaterialsIds,
                                  @RequestParam(name = "sourcePage", required = false, defaultValue = "aosrAdd") String sourcePage,
                                  @RequestParam(name = "id", required = false) Long id,
                                  @RequestParam Map<String, String> formData,
                                  Model model) {
        List<String> aosrFormData = (List<String>) model.asMap().getOrDefault("aosrFormData", new ArrayList<String>());

        formData.forEach((key, value) -> {
            if (key.startsWith("projectSectionHidden") && !aosrFormData.contains(value)) {
                aosrFormData.add(value);
            }
        });

        if (id != null) {
            Optional<AosrData> aosrDataOptional = aosrRepository.findById(id);
            if (aosrDataOptional.isPresent()) {
                AosrData aosrData = aosrDataOptional.get();
                model.addAttribute("aosrData", aosrData);
                Set<Long> drawingIds = aosrData.getAosrToDrawings().stream().map(DrawingsData::getId).collect(Collectors.toSet());
                model.addAttribute("drawingIds", drawingIds);

            }
        }
        populateModel(model);

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
        model.addAttribute("aosrFormData", aosrFormData);
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
                              @RequestParam("customerResponsibleSelect") Long customerResId,
                              @RequestParam("subcustomerResponsibleSelect") Long subcustomerResId,
                              @RequestParam("subcustomerResponsible2Select") Long subcustomerRes2Id,
                              @RequestParam("designerResponsibleSelect") Long designerResId,
                              @RequestParam("anotherPersonResponsibleSelect") Long anotherPersonResId,
                              @RequestParam(value = "selectedMaterials", required = false) List<Long> materialIds,
                              @RequestParam Map<String, String> formData) {
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

        Set<DrawingsData> drawingsSet = new HashSet<>();
        Set<ActsViCData> actsViCSet = new HashSet<>();
        Set<ProjectDocumentationData> projectDocumentationSet = new HashSet<>(aosrData.getAosrToProjDocs());

        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                String[] parts = key.split("-");
                if (parts.length >= 2) {
                    Long drawingId = Long.parseLong(parts[0].replace("drawingCheckbox", ""));
                    DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                    drawingsSet.add(drawing);

                    ProjectDocumentationData projDoc = drawing.getProjDocToDrawings();
                    projectDocumentationSet.add(projDoc);
                }
            }
            if (key.startsWith("actViCCheckbox")) {
                Long actVicId = Long.parseLong(key.replace("actViCCheckbox", ""));
                ActsViCData actsViCData = actsViCRepository.findById(actVicId).orElseThrow();
                actsViCData.setActsToAosrData(aosrData);
                actsViCSet.add(actsViCData);
            }
        });

        aosrData.setAosrToDrawings(drawingsSet);
        aosrData.setActsViCData(actsViCSet);
        aosrData.setAosrToProjDocs(projectDocumentationSet);

        if (execSchemId != null) {
            ExecutiveSchemesData executiveSchemesData = executiveSchemesRepository.findById(execSchemId).orElseThrow();
            aosrData.setExecutiveSchemesData(executiveSchemesData);
        }
        if (protocolGnbId != null && protocolGnbId != -1) {
            ProtocolsGnbData protocolsGnbData = protocolsGnbRepository.findById(protocolGnbId).orElseThrow();
            protocolsGnbData.setGnbToAosrData(aosrData);
        }
        if (sealingProtocolId != null && sealingProtocolId != -1) {
            SealingProtocolsData sealingProtocolsData = sealingProtocolsRepository.findById(sealingProtocolId).orElseThrow();
            sealingProtocolsData.setSealingToAosrData(aosrData);
        }
        if (customerResId != null && customerResId != -1) {
            CustomerResponsibleData customerResponsibleData = customerResponsibleRepository.findById(customerResId).orElseThrow();
            aosrData.setCustomerResponsibleData(customerResponsibleData);
        }
        if (subcustomerResId != null && subcustomerResId != -1) {
            SubcustomerResponsibleData subcustomerResponsibleData = subcustomerResponsibleRepository.findById(subcustomerResId).orElseThrow();
            aosrData.setSubcustomerResponsibleData(subcustomerResponsibleData);
        }
        if (subcustomerRes2Id != null && subcustomerRes2Id != -1) {
            SubcustomerResponsible2Data subcustomerResponsible2Data = subcustomerResponsible2Repository.findById(subcustomerRes2Id).orElseThrow();
            aosrData.setSubcustomerResponsible2Data(subcustomerResponsible2Data);
        }
        if (designerResId != null && designerResId != -1) {
            DesignerResponsibleData designerResponsibleData = designerResponsibleRepository.findById(designerResId).orElseThrow();
            aosrData.setDesignerResponsibleData(designerResponsibleData);
        }
        if (anotherPersonResId != null && anotherPersonResId != -1) {
            AnotherPersonResponsibleData anotherPersonResponsibleData = anotherPersonResponsibleRepository.findById(anotherPersonResId).orElseThrow();
            aosrData.setAnotherPersonResponsibleData(anotherPersonResponsibleData);
        }

        Iterable<PassportObjectData> passportObjectList = passportObjectRepository.findAll();
        passportObjectList.forEach(aosrData::setPassportObjectData);

        aosrRepository.save(aosrData);
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
            Set<Long> actsVicIds = aosrData.getActsViCData().stream().map(ActsViCData::getId).collect(Collectors.toSet());
            List<String> projectSectionList = new ArrayList<>();
            for (DrawingsData drawing : aosrData.getAosrToDrawings()) {
                if (!projectSectionList.contains(drawing.getProjDocToDrawings().getProjectSection())) {
                    projectSectionList.add(drawing.getProjDocToDrawings().getProjectSection());
                }
            }
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
            model.addAttribute("projectSectionList", projectSectionList);
            model.addAttribute("drawingIds", drawingIds);
            model.addAttribute("actsVicIds", actsVicIds);
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
                                 @RequestParam("protocolGnbSelect") Long protocolGnbId,
                                 @RequestParam("sealingProtocolSelect") Long sealingProtocolId,
                                 @RequestParam("customerResponsibleSelect") Long customerResId,
                                 @RequestParam("subcustomerResponsibleSelect") Long subcustomerResId,
                                 @RequestParam("subcustomerResponsible2Select") Long subcustomerRes2Id,
                                 @RequestParam("designerResponsibleSelect") Long designerResId,
                                 @RequestParam("anotherPersonResponsibleSelect") Long anotherPersonResId,
                                 @RequestParam(value = "selectedMaterials", required = false) List<Long> materialIds,
                                 @RequestParam Map<String, String> formData) {
        AosrData aosrData = aosrRepository.findById(id).orElseThrow();

        for (DrawingsData drawing : aosrData.getAosrToDrawings()) {
            drawing.getDrawingsToAosr().remove(aosrData);
            drawingsRepository.save(drawing);
        }
        aosrData.getAosrToDrawings().clear();

        for (ProjectDocumentationData projDoc : aosrData.getAosrToProjDocs()) {
            projDoc.setAosrs(null);
            projectDocumentationRepository.save(projDoc);
        }
        aosrData.getAosrToProjDocs().clear();

        for (ActsViCData act : aosrData.getActsViCData()) {
            act.setActsToAosrData(null);
            actsViCRepository.save(act);
        }
        aosrData.getActsViCData().clear();

        formData.forEach((key, value) -> {
            if (key.startsWith("drawingCheckbox")) {
                String[] parts = key.split("-");
                if (parts.length >= 2) {
                    Long drawingId = Long.parseLong(parts[0].replace("drawingCheckbox", ""));
                    DrawingsData drawing = drawingsRepository.findById(drawingId).orElseThrow();
                    drawing.getDrawingsToAosr().add(aosrData);
                    aosrData.getAosrToDrawings().add(drawing);
                    drawingsRepository.save(drawing);
                }
            }
            if (key.startsWith("projectSectionSelect")) {
                ProjectDocumentationData projDoc = projectDocumentationRepository.findByProjectSection(value)
                        .orElseThrow(() -> new NoSuchElementException("ProjectDocumentationData with section " + value + " not found"));
                if (projDoc.getAosrs() == null) {
                    projDoc.setAosrs(new HashSet<>());
                }
                projDoc.getAosrs().add(aosrData);
                aosrData.getAosrToProjDocs().add(projDoc);
                projectDocumentationRepository.save(projDoc);
            }
            if (key.startsWith("actViCCheckbox")) {
                Long actVicId = Long.parseLong(key.replace("actViCCheckbox", ""));
                ActsViCData actsViCData = actsViCRepository.findById(actVicId).orElseThrow();
                actsViCData.setActsToAosrData(aosrData);
                aosrData.getActsViCData().add(actsViCData);
                actsViCRepository.save(actsViCData);
            }
        });

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
            ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
            if (protocolsGnbData != null) {
                protocolsGnbData.setGnbToAosrData(null);
                aosrData.setProtocolsGnbData(null);
                protocolsGnbRepository.save(protocolsGnbData);
            }

            if (protocolGnbId != -1) {
                ProtocolsGnbData newProtocol = protocolsGnbRepository.findById(protocolGnbId).orElseThrow();
                newProtocol.setGnbToAosrData(aosrData);
                aosrData.setProtocolsGnbData(newProtocol);
                protocolsGnbRepository.save(newProtocol);
            }
        }

        if (sealingProtocolId != null) {
            SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
            if (sealingProtocolsData != null) {
                sealingProtocolsData.setSealingToAosrData(null);
                aosrData.setSealingProtocolsData(null);
                sealingProtocolsRepository.save(sealingProtocolsData);
            }

            if (sealingProtocolId != -1) {
                SealingProtocolsData newProtocol = sealingProtocolsRepository.findById(sealingProtocolId).orElseThrow();
                aosrData.setSealingProtocolsData(newProtocol);
                newProtocol.setSealingToAosrData(aosrData);
                sealingProtocolsRepository.save(newProtocol);
            }
        }

        if (customerResId != null) {
            CustomerResponsibleData customerResponsibleData = aosrData.getCustomerResponsibleData();
            if (customerResponsibleData != null) {
                customerResponsibleData.setAosrData(null);
                aosrData.setCustomerResponsibleData(null);
                customerResponsibleRepository.save(customerResponsibleData);
            }

            if (customerResId != -1) {
                CustomerResponsibleData newCustomer = customerResponsibleRepository.findById(customerResId).orElseThrow();
                aosrData.setCustomerResponsibleData(newCustomer);
                if (newCustomer.getAosrData() == null) {
                    newCustomer.setAosrData(new ArrayList<>());
                }
                newCustomer.getAosrData().add(aosrData);
                customerResponsibleRepository.save(newCustomer);
            }
        }

        if (subcustomerResId != null) {
            SubcustomerResponsibleData subcustomerResponsibleData = aosrData.getSubcustomerResponsibleData();
            if (subcustomerResponsibleData != null) {
                subcustomerResponsibleData.setAosrData(null);
                aosrData.setSubcustomerResponsibleData(null);
                subcustomerResponsibleRepository.save(subcustomerResponsibleData);
            }

            if (subcustomerResId != -1) {
                SubcustomerResponsibleData newSubcustomer = subcustomerResponsibleRepository.findById(subcustomerResId).orElseThrow();
                aosrData.setSubcustomerResponsibleData(newSubcustomer);
                if (newSubcustomer.getAosrData() == null) {
                    newSubcustomer.setAosrData(new ArrayList<>());
                }
                newSubcustomer.getAosrData().add(aosrData);
                subcustomerResponsibleRepository.save(newSubcustomer);
            }
        }

        if (subcustomerRes2Id != null) {
            SubcustomerResponsible2Data subcustomerResponsible2Data = aosrData.getSubcustomerResponsible2Data();
            if (subcustomerResponsible2Data != null) {
                subcustomerResponsible2Data.setAosrData(null);
                aosrData.setSubcustomerResponsible2Data(null);
                subcustomerResponsible2Repository.save(subcustomerResponsible2Data);
            }

            if (subcustomerRes2Id != -1) {
                SubcustomerResponsible2Data newSubcustomer2 = subcustomerResponsible2Repository.findById(subcustomerRes2Id).orElseThrow();
                aosrData.setSubcustomerResponsible2Data(newSubcustomer2);
                if (newSubcustomer2.getAosrData() == null) {
                    newSubcustomer2.setAosrData(new ArrayList<>());
                }
                newSubcustomer2.getAosrData().add(aosrData);
                subcustomerResponsible2Repository.save(newSubcustomer2);
            }
        }

        if (designerResId != null) {
            DesignerResponsibleData designerResponsibleData = aosrData.getDesignerResponsibleData();
            if (designerResponsibleData != null) {
                designerResponsibleData.setAosrData(null);
                aosrData.setDesignerResponsibleData(null);
                designerResponsibleRepository.save(designerResponsibleData);
            }

            if (designerResId != -1) {
                DesignerResponsibleData newDesigner = designerResponsibleRepository.findById(designerResId).orElseThrow();
                aosrData.setDesignerResponsibleData(newDesigner);
                if (newDesigner.getAosrData() == null) {
                    newDesigner.setAosrData(new ArrayList<>());
                }
                newDesigner.getAosrData().add(aosrData);
                designerResponsibleRepository.save(newDesigner);
            }
        }

        if (anotherPersonResId != null) {
            AnotherPersonResponsibleData anotherPersonResponsibleData = aosrData.getAnotherPersonResponsibleData();
            if (anotherPersonResponsibleData != null) {
                anotherPersonResponsibleData.setAosrData(null);
                aosrData.setAnotherPersonResponsibleData(null);
                anotherPersonResponsibleRepository.save(anotherPersonResponsibleData);
            }

            if (anotherPersonResId != -1) {
                AnotherPersonResponsibleData newAnotherPerson = anotherPersonResponsibleRepository.findById(anotherPersonResId).orElseThrow();
                aosrData.setAnotherPersonResponsibleData(newAnotherPerson);
                if (newAnotherPerson.getAosrData() == null) {
                    newAnotherPerson.setAosrData(new ArrayList<>());
                }
                newAnotherPerson.getAosrData().add(aosrData);
                anotherPersonResponsibleRepository.save(newAnotherPerson);
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

    @PostMapping("/aosr-table/{id}/aosr-remove")
    @Transactional
    public String postAosrDelete(@PathVariable(value = "id") long id, Model model) {
        AosrData aosrData = aosrRepository.findById(id).orElseThrow();

        Set<DrawingsData> drawings = aosrData.getAosrToDrawings();
        Set<MaterialsUsedData> materialsUsedSet = aosrData.getAosrToMaterials();
        Set<ActsViCData> actsViCSet = aosrData.getActsViCData();
        Set<ProjectDocumentationData> projDocs = aosrData.getAosrToProjDocs();

        if (!drawings.isEmpty()) {
            drawings.forEach(drawing -> drawing.getDrawingsToAosr().remove(aosrData));
            aosrData.getAosrToDrawings().clear();
        }

        if (!materialsUsedSet.isEmpty()) {
            materialsUsedSet.forEach(material -> material.getMaterialsToAosr().remove(aosrData));
            aosrData.getAosrToMaterials().clear();
        }

        if (!actsViCSet.isEmpty()) {
            actsViCSet.forEach(actViC -> {
                actViC.setActsToAosrData(null);
                actsViCRepository.save(actViC);
            });
            aosrData.getActsViCData().clear();
        }

        if (!projDocs.isEmpty()) {
            projDocs.forEach(projDoc -> projDoc.getAosrs().remove(aosrData));
            aosrData.getAosrToProjDocs().clear();
        }

        ExecutiveSchemesData executiveSchemesData = aosrData.getExecutiveSchemesData();
        if (executiveSchemesData != null) {
            executiveSchemesData.getAosrs().remove(aosrData);
            aosrData.setExecutiveSchemesData(null);
        }

        PassportObjectData passportObjectData = aosrData.getPassportObjectData();
        if (passportObjectData != null) {
            passportObjectData.getAosrs().remove(aosrData);
            aosrData.setPassportObjectData(null);
        }

        ProtocolsGnbData protocolsGnbData = aosrData.getProtocolsGnbData();
        if (protocolsGnbData != null) {
            protocolsGnbData.setGnbToAosrData(null);
            aosrData.setProtocolsGnbData(null);
        }

        SealingProtocolsData sealingProtocolsData = aosrData.getSealingProtocolsData();
        if (sealingProtocolsData != null) {
            sealingProtocolsData.setSealingToAosrData(null);
            aosrData.setSealingProtocolsData(null);
        }

        aosrRepository.delete(aosrData);

        return "redirect:/aosr-table";
    }

    @PostMapping("/aosr-table")
    public void postAosrTable(HttpServletResponse response) {
        List<File> tempFiles = new ArrayList<>();
        Iterable<AosrData> aosrList = aosrRepository.findAll();
        try {
            int indexAosr = 1;
            for (AosrData aosrData : aosrList) {
//                File tempFile = GenerateFileUtils.generateAosrFile(aosrData, indexAosr, spMap, monthMap);
//                tempFiles.add(tempFile);
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