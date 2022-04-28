package com.makichanov.relax_app.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HoroscopeData {

    private String name;

    @JsonProperty("date_range")
    private String dateRange;

    @JsonProperty("current_date")
    private String currentDate;

    private String description;

    private String compatibility;

    private String mood;

    private String color;

    @JsonProperty("lucky_number")
    private String luckyNumber;

    @JsonProperty("lucky_time")
    private String luckyTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompatibility() {
        return compatibility;
    }

    public void setCompatibility(String compatibility) {
        this.compatibility = compatibility;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLuckyNumber() {
        return luckyNumber;
    }

    public void setLuckyNumber(String luckyNumber) {
        this.luckyNumber = luckyNumber;
    }

    public String getLuckyTime() {
        return luckyTime;
    }

    public void setLuckyTime(String luckyTime) {
        this.luckyTime = luckyTime;
    }

}
