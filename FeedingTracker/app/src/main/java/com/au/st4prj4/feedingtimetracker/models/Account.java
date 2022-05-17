package com.au.st4prj4.feedingtimetracker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Account implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int id=0;

    @ColumnInfo(name = "user")
    String user ="";

    @ColumnInfo(name = "child")
    String child ="";

}
