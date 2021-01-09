package com.example.testapp.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.testapp.Dao.EventDao;
import com.example.testapp.model.EntityClass;

@Database(entities = {EntityClass.class},version = 1)
public abstract class Databaseclass extends RoomDatabase {

    private static Databaseclass databaseclassInstance;
    public abstract EventDao eventDao();

    public static Databaseclass getDatabase(final Context context){
        if (databaseclassInstance == null){
            synchronized (Databaseclass.class){
                if (databaseclassInstance == null){
                    databaseclassInstance = Room.databaseBuilder(context.getApplicationContext(),Databaseclass.class,"event_database").build();
                }
            }
        }
        return databaseclassInstance;
    }


}
