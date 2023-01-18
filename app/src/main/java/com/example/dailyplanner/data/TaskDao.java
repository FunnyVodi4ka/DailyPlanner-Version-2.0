package com.example.dailyplanner.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dailyplanner.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllLiveData();

    @Query("SELECT * FROM Task WHERE id IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Task WHERE id = :id LIMIT 1")
    Task findById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    @Update
    void update(Task task);

    @Delete
    void delete(Task task);
}
