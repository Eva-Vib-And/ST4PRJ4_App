package com.au.st4prj4.feedingtimetracker.models;

import android.app.Application;
import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Database;

import com.au.st4prj4.feedingtimetracker.Database.FeedingsDAO;
import com.au.st4prj4.feedingtimetracker.Database.RoomDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Repository {
    private RoomDatabase db;
    private FeedingsDAO feedingsDAO;
    private ExecutorService executor;

    //private LiveData<List<Movie>> movies;

    private static Repository instance;
    Context context;

    public Repository(Application app){
        db = RoomDatabase.getInstance(app.getApplicationContext());
                //getDatabase(app.getApplicationContext());
        context = app.getApplicationContext();
        feedingsDAO = db.feedingsDao();
        FeedingList feedings = (FeedingList) db.feedingsDao().loadFeedinglist();
        executor = Executors.newSingleThreadExecutor();
    }

    //Create new instance if null otherwise return
    public static Repository getInstance(Application application){
        if(instance == null){
            instance = new Repository(application);
        }
        return instance;
    }
    public void deleteMovie(FeedingList feedingList){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.feedingsDao().deleteFeedingList(feedingList);
            }
        });
    }

    public ArrayList<FeedingList> getFeedingsNonLive(){

        Future<List<FeedingList>> m = executor.submit(new Callable<List<FeedingList>>() {
            @Override
            public List<FeedingList> call() {
                return (List<FeedingList>) db.feedingsDao().loadFeedinglist();
            }
        });
        try {
            return (ArrayList<FeedingList>) m.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
    //Async
   /* private static class InsertAsyncTask extends AsyncTask<Movie, Void, Void> {

        private MovieDAO movieDAO;
        private InsertAsyncTask(MovieDAO movieDAO) {
            this.movieDAO = movieDAO;
        }
        @Override
        protected Void doInBackground(Movie... movies) {
            movieDAO.insertMovie(movies[0]);
            return null;
        }
    }

    public Movie getMovieAsync(final int index){
        Future<Movie> m = executor.submit((Callable<Movie>) () -> {
            return db.movieDAO().getMoviesNonLive().get(index);
        });

        try {
            return m.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateMovieAsync(final Movie movie){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                db.movieDAO().updateMovie(movie);
            }
        });
    }*/
}
