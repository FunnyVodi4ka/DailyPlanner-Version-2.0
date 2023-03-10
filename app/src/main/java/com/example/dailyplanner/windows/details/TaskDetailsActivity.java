package com.example.dailyplanner.windows.details;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.dailyplanner.App;
import com.example.dailyplanner.R;
import com.example.dailyplanner.model.Task;

public class TaskDetailsActivity extends AppCompatActivity {
    public static final String EXTRA_TASK = "TaskDetailsActivity.EXTRA_TASK";

    private Task task;
    private EditText editText;

    //Запуск activity содержимого задачи
    public static void start(Activity caller, Task task) {
        Intent intent = new Intent(caller, TaskDetailsActivity.class);
        if (task != null) {
            intent.putExtra(EXTRA_TASK, task);
        }
        caller.startActivity(intent);
    }

    //Выполнение при создании
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        setTitle(getString(R.string.task_details_title));

        editText = findViewById(R.id.task_text);

        if (getIntent().hasExtra(EXTRA_TASK)) {
            task = getIntent().getParcelableExtra(EXTRA_TASK);
            editText.setText(task.taskText);
        } else {
            task = new Task();
        }
    }

    //Извлечение меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Обработка для событий изменения задачи
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            //Отмена
            case android.R.id.home:
                finish();
                break;
                //Сохранение заметки
            case R.id.action_save:
                if (editText.getText().length() > 0) {
                    task.taskText = editText.getText().toString();
                    task.done = false;
                    task.timestamp = System.currentTimeMillis();
                    if (getIntent().hasExtra(EXTRA_TASK)) {
                        App.getInstance().getTaskDao().update(task);
                    } else {
                        App.getInstance().getTaskDao().insert(task);
                    }
                    finish();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}