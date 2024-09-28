package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class UserDocumentsData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserData userData;

    @ElementCollection
    @CollectionTable(name = "itd_documents", joinColumns = @JoinColumn(name = "user_documents_id"))
    @Column(name = "documents")
    private Set<String> documents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public Set<String> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<String> documents) {
        this.documents = documents;
    }

    public UserDocumentsData() {
    }
}
