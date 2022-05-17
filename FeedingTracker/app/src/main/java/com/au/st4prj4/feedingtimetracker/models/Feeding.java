package com.au.st4prj4.feedingtimetracker.models;

import java.time.LocalDateTime;

public class Feeding {
    String FeedingType;
    double milkInMl;

    public String getFeedingType() {
        return FeedingType;
    }

    public void setFeedingType(String feedingType) {
        FeedingType = feedingType;
    }

    public double getMilkInMl() {
        return milkInMl;
    }

    public void setMilkInMl(double milkInMl) {
        this.milkInMl = milkInMl;
    }
}
