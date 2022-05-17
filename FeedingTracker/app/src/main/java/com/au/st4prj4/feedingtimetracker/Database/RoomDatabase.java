package com.au.st4prj4.feedingtimetracker.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.TypeConverters;

import com.au.st4prj4.feedingtimetracker.models.Account;
import com.au.st4prj4.feedingtimetracker.models.Feeding;
import com.au.st4prj4.feedingtimetracker.models.TypeConverterForArray;

@Database(entities = {Account.class, Feeding.class},version = 1,exportSchema = false)
@TypeConverters({TypeConverterForArray.class})
public abstract class RoomDatabase extends androidx.room.RoomDatabase {
    public abstract FeedingsDAO feedingsDao();
    private static RoomDatabase database;
    private static String DATABASE_NAME="FeedingsLocalData";

    public synchronized static RoomDatabase getInstance(Context context){
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
}
