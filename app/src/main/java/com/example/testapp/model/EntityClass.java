package com.example.testapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mytable")
public class EntityClass {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "message")
    @NonNull
    String message;

    @ColumnInfo(name = "date")
    @NonNull
    String date;

    @ColumnInfo(name = "time")
    @NonNull
    String time;

    public EntityClass(String id, @NonNull String message, @NonNull String date, @NonNull String time) {
        this.id = id;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }
}

