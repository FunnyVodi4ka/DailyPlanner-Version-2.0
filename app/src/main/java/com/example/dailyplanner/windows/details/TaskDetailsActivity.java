package com.example.dailyplanner.windows.details;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.dailyplanner.R;
import com.example.dailyplanner.model.Task;

public class TaskDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_TASK = "TaskDetailsActivity.EXTRA_TASK";

    Task task;
    private EditText editText;

    public static void start(Activity caller, Task task) {
        Intent intent = new Intent(caller, TaskDetailsActivity.class);
        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
        }
        caller.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);
    }
}