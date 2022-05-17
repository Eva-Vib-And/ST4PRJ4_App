package com.au.st4prj4.feedingtimetracker.Database;

import androidx.room.Delete;
import androidx.room.OnConflictStrategy;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.au.st4prj4.feedingtimetracker.models.FeedingList;

import java.util.List;

@Dao
public interface FeedingsDAO {

    /*@Query("SELECT * FROM feedings")
    List<FeedingList> getFeedingsListNonLive();*/

   /* @Query("SELECT * FROM feedings")
    FeedingList getFeedingsNonLive();*/

    @Query("SELECT * FROM account")
    public List<FeedingList> loadFeedinglist();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<FeedingList> feedings);

    @Delete
    void deleteFeedingList(FeedingList feedinglist);

    @Update
    void updateFeedingList(FeedingList feedingList);

   /* @Delete
    void deleteFeeding(Feeding feeding);

    @Update
    void updateFeeding(Feeding feeding);
*/

}
