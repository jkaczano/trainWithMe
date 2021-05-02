package com.example.trainWithMe.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
public class TrainingUnit {

    public TrainingUnit() {

    }

    public Long getUnitID() {
        return unitID;
    }

    public void setUnitID(Long unitID) {
        this.unitID = unitID;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long unitID;
    @NotNull(message = "Choose user")
    private Long userID;
    @NotNull(message = "Enter description")
    @Size(max = 100, message = "Description is too long")
    private String description;
    @NotNull(message = "Enter a topic")
    @Size(max = 25, message = "Topic is too long")
    private String topic;
    @NotNull(message = "Choose a date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    public TrainingUnit(Long unitID, Long userID, String description, String topic, LocalDate date) {
        this.unitID = unitID;
        this.userID = userID;
        this.description = description;
        this.topic = topic;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

}
