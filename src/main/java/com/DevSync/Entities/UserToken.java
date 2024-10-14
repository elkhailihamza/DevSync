package com.DevSync.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_tokens")
public class UserToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "daily_update_tokens", nullable = false)
    private int dailyUpdateTokens = 2;

    @Column(name = "monthly_deletion_tokens", nullable = false)
    private int monthlyDeletionTokens = 1;

    @Column(name = "last_reset_date", nullable = false)
    private LocalDate lastResetDate = LocalDate.now();

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Utilisateur user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDailyUpdateTokens() {
        return dailyUpdateTokens;
    }

    public void setDailyUpdateTokens(int dailyUpdateTokens) {
        this.dailyUpdateTokens = dailyUpdateTokens;
    }

    public int getMonthlyDeletionTokens() {
        return monthlyDeletionTokens;
    }

    public void setMonthlyDeletionTokens(int monthlyDeletionTokens) {
        this.monthlyDeletionTokens = monthlyDeletionTokens;
    }

    public LocalDate getLastResetDate() {
        return lastResetDate;
    }

    public void setLastResetDate(LocalDate lastResetDate) {
        this.lastResetDate = lastResetDate;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
}
