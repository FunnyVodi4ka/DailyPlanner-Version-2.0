package com.example.dailyplanner.data;

import androidx.room.Database;

import com.example.dailyplanner.model.Task;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public class ApplicationDatabase {
}
