package com.example.testapp.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testapp.model.EntityClass;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertall(EntityClass entityClass);

    @Query("SELECT * FROM mytable")
    List<EntityClass> getAllData();
}
