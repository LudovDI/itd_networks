package com.automation_of_ITD_formation.Automation.of.ITD.formation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class UserData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username, password, fullname;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "userData", cascade = CascadeType.ALL)
    private List<ItdData> itdData;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PasswordResetTokenData passwordResetTokenData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public List<ItdData> getItdData() {
        return itdData;
    }

    public void setItdData(List<ItdData> itdData) {
        this.itdData = itdData;
    }

    public PasswordResetTokenData getPasswordResetTokenData() {
        return passwordResetTokenData;
    }

    public void setPasswordResetTokenData(PasswordResetTokenData passwordResetTokenData) {
        this.passwordResetTokenData = passwordResetTokenData;
    }

    public UserData() {
    }
}
