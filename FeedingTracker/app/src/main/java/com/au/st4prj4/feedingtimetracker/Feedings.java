package com.au.st4prj4.feedingtimetracker;

import java.time.LocalDate;

public class Feedings {
    double Milk;
    String FeedingType;
    LocalDate date;

    public Feedings() {
    }

    public Feedings(double milk, String feedingType, LocalDate date) {
        Milk = milk;
        FeedingType = feedingType;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getMilk() {
        return Milk;
    }

    public void setMilk(double milk) {
        Milk = milk;
    }

    public String getFeedingType() {
        return FeedingType;
    }

    public void setFeedingType(String feedingType) {
        FeedingType = feedingType;
    }
}
