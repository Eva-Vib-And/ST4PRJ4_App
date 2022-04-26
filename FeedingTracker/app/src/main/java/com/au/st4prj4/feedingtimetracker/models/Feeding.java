package com.au.st4prj4.feedingtimetracker.models;

import java.time.LocalDateTime;

public class Feeding {
    String FeedingType;
    LocalDateTime datetime;

    public String getFeedingType() {
        return FeedingType;
    }

    public void setFeedingType(String feedingType) {
        FeedingType = feedingType;
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
}
