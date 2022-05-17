package com.au.st4prj4.feedingtimetracker.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;
@Entity(tableName = "feeding")
public class Feeding {
    @PrimaryKey(autoGenerate = true)
    public int id;     // item id
    public int userId; // User id
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
