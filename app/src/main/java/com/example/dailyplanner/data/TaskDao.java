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

//Интерфейс модели "Задача"
@Dao
public interface TaskDao {

    //Получение всех данных
    @Query("SELECT * FROM Task")
    List<Task> getAll();

    //Получение всех данных в реальном времени
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllLiveData();

    //Поиск данных по идентификатору
    @Query("SELECT * FROM Task WHERE id IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    //Поиск конректной записи по идентификатору
    @Query("SELECT * FROM Task WHERE id = :id LIMIT 1")
    Task findById(int id);

    //Добавление данных в БД
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Task task);

    //Обновление данных в БД
    @Update
    void update(Task task);

    //Удаление данных в БД
    @Delete
    void delete(Task task);
}
