package com.au.st4prj4.feedingtimetracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class FeedingList {
    @Embedded
    public Account user;

    String _date;
    @Relation(parentColumn = "id", entityColumn = "userId", entity = Feeding.class)
    List<Feeding> totaltFeedingsToday;

    public String getDateTime() {
        return _date;
    }

    public void setDate(String _date) {
        this._date = _date;
    }

    public List<Feeding> getTotaltFeedingsToday() {
        return totaltFeedingsToday;
    }

    public void setTotaltFeedingsToday(ArrayList<Feeding> totaltFeedingsToday) {
        this.totaltFeedingsToday = totaltFeedingsToday;
    }
}
