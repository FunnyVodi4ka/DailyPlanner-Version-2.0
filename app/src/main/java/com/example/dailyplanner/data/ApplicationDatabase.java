package com.example.dailyplanner.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.dailyplanner.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public  abstract  class ApplicationDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
