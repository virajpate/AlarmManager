package com.example.testapp.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "mytable")
public class EntityClass {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "message")
    String message;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "time")
    String time;

//    public EntityClass(String message, String date, String time) {
//        this.message = message;
//        this.date = date;
//        this.time = time;
//    }

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
}

