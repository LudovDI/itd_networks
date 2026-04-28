//package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;
//
//import jakarta.persistence.*;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//public class GeneralWorksLogData {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @OneToMany(mappedBy = "generalWorksLogData", cascade = CascadeType.ALL)
//    private Set<AosrData> aosrs = new HashSet<>();
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Set<AosrData> getAosrs() {
//        return aosrs;
//    }
//
//    public void setAosrs(Set<AosrData> aosrs) {
//        this.aosrs = aosrs;
//    }
//
//    public GeneralWorksLogData() {
//    }
//}
