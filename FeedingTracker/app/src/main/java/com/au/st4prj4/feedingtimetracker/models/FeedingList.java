package com.au.st4prj4.feedingtimetracker.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FeedingList {
    LocalDate dateTime;
    ArrayList<Feeding> totaltFeedingsToday;

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDate(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    public ArrayList<Feeding> getTotaltFeedingsToday() {
        return totaltFeedingsToday;
    }

    public void setTotaltFeedingsToday(ArrayList<Feeding> totaltFeedingsToday) {
        this.totaltFeedingsToday = totaltFeedingsToday;
    }
}
