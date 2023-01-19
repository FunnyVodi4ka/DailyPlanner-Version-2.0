package com.example.dailyplanner.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Создаём модель "Задача", подключаем интерфейс для передачи activity
@Entity
public class Task implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "taskText")
    public String taskText;

    @ColumnInfo(name = "timestamp")
    public long timestamp;

    @ColumnInfo(name = "done")
    public boolean done;

    public Task() {
    }

    //Стандартная реализация функции Creator()
    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    //Стандартная реализация функции equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task note = (Task) o;

        if (id != note.id) return false;
        if (timestamp != note.timestamp) return false;
        if (done != note.done) return false;
        return taskText != null ? taskText.equals(note.taskText) : note.taskText == null;
    }

    //Стандартная реализация функции hashCode()
    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (taskText != null ? taskText.hashCode() : 0);
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        result = 31 * result + (done ? 1 : 0);
        return result;
    }

    //Стандартная реализация функции Task() для данных о модели
    protected Task(Parcel in) {
        id = in.readInt();
        taskText = in.readString();
        timestamp = in.readLong();
        done = in.readByte() != 0;
    }

    //Стандартная реализация функции writeToParcel()
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(taskText);
        dest.writeLong(timestamp);
        dest.writeByte((byte) (done ? 1 : 0));
    }

    //Стандартная реализация функции describeContents()
    @Override
    public int describeContents() {
        return 0;
    }
}
