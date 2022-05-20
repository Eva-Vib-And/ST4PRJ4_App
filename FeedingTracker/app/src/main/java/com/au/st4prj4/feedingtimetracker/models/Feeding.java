package com.au.st4prj4.feedingtimetracker.models;

import java.time.LocalDateTime;

public class Feeding {
    String date;
    double milkInMl;

    public String getDate() {
        return date;
    }

    public Feeding() {
    }

    public Feeding( double milkInMl,String date) {
        this.date = date;
        this.milkInMl = milkInMl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMilkInMl() {
        return milkInMl;
    }

    public void setMilkInMl(double milkInMl) {
        this.milkInMl = milkInMl;
    }
}
