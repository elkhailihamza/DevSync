package com.DevSync.Entities;

public class Utilisateurs {
    private final long id;
    private String user_name;
    private String user_pass;
    private String nom;
    private String prenom;
    private String email;
    private boolean manager;

    public Utilisateurs(long id, String user_name, String user_pass, String nom, String prenom, String email, boolean manager) {
        this.id = id;
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.manager = manager;
    }

    public long getId() {
        return id;
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
}
