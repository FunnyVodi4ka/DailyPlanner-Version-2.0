package com.example.dailyplanner;

import android.app.Application;

import androidx.room.Room;

import com.example.dailyplanner.data.ApplicationDatabase;
import com.example.dailyplanner.data.TaskDao;

public class App extends Application {

    private ApplicationDatabase database;
    private TaskDao noteDao;

    private static App instance;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        instance = this;

        database = Room.databaseBuilder(getApplicationContext(),
                        ApplicationDatabase.class, "app-db-name")
                .allowMainThreadQueries()
                .build();

        noteDao = database.taskDao();
    }

    public ApplicationDatabase getDatabase() {
        return database;
    }

    public void setDatabase(ApplicationDatabase database) {
        this.database = database;
    }

    public TaskDao getTaskDao() {
        return noteDao;
    }

    public void setTaskDao(TaskDao noteDao) {
        this.noteDao = noteDao;
    }
}
