package com.DevSync.Entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="utilisateurs",
    uniqueConstraints={@UniqueConstraint(columnNames={"id"})})
public class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    @Column(name = "user_pass", nullable = false)
    private String user_pass;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "manager", nullable = false)
    private boolean manager;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Tasks> createdTasks;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Tasks> assignedTasks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }

    public List<Tasks> getCreatedTasks() {
        return createdTasks;
    }

    public void setCreatedTasks(List<Tasks> createdTasks) {
        this.createdTasks = createdTasks;
    }

    public List<Tasks> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(List<Tasks> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }
}
