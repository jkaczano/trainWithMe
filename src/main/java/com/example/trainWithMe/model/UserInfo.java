package com.example.trainWithMe.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userID;

    @NotNull(message = "Enter your body mass")
    @Min(value = 30, message = "Too small mass")
    @Max(value = 500, message = "Too big mass")
    private float bodyMass;
    @Min(value = 120, message = "You are too short")
    @Max(value = 300, message = "You are too tall")
    @NotNull(message = "Enter your height")
    private int height;
    @Min(value = 1, message = "Provide a number")
    @NotNull(message = "How many pull ups can you do?")
    private int pullUps;
    @Min(value = 1, message = "Provide a number")
    @NotNull(message = "How many push ups can you do?")
    private int pushUps;
    @Min(value = 1, message = "Provide a number")
    @NotNull(message = "How many squats can you do?")
    private int squats;
    @NotNull(message = "Enter a date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public UserInfo(Long id, Long userID, float bodyMass, int height, int pullUps, int pushUps, int squats, LocalDate date) {
        this.id = id;
        this.userID = userID;
        this.bodyMass = bodyMass;
        this.height = height;
        this.pullUps = pullUps;
        this.pushUps = pushUps;
        this.squats = squats;
        this.date = date;
    }

    public UserInfo(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public float getBodyMass() {
        return bodyMass;
    }

    public void setBodyMass(float bodyMass) {
        this.bodyMass = bodyMass;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPullUps() {
        return pullUps;
    }

    public void setPullUps(int pullUps) {
        this.pullUps = pullUps;
    }

    public int getPushUps() {
        return pushUps;
    }

    public void setPushUps(int pushUps) {
        this.pushUps = pushUps;
    }

    public int getSquats() {
        return squats;
    }

    public void setSquats(int squats) {
        this.squats = squats;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
