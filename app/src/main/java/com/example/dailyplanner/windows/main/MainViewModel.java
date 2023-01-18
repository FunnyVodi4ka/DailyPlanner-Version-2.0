package com.example.dailyplanner.windows.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dailyplanner.App;
import com.example.dailyplanner.model.Task;

import java.util.List;

public class MainViewModel extends ViewModel {
    private LiveData<List<Task>> noteLiveData = App.getInstance().getTaskDao().getAllLiveData();

    public LiveData<List<Task>> getNoteLiveData() {
        return noteLiveData;
    }
}
