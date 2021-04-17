package com.example.trainWithMe.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userID;

    private float bodyMass;
    private int height;
    private int pullUps;
    private int pushUps;
    private int squats;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    public UserInfo(Long id, Long userID, float bodyMass, int height, int pullUps, int pushUps, int squats, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
