package com.example.testapp.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.testapp.model.EntityClass;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertall(EntityClass entityClass);

    @Query("SELECT * FROM mytable")
    LiveData<List<EntityClass>> getAllData();

    @Query("SELECT * FROM mytable WHERE id=:id")
    LiveData<EntityClass> getAlarm(String id);

    @Update
    void update(EntityClass entityClass);

    @Delete
    int delete(EntityClass entityClass);
}
