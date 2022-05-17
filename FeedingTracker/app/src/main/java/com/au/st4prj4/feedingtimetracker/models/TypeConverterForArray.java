package com.au.st4prj4.feedingtimetracker.models;

import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class TypeConverterForArray {
    @androidx.room.TypeConverter
    public static String fromArrayList(ArrayList<FeedingList> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    @androidx.room.TypeConverter
    public static ArrayList<FeedingList> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }
}
