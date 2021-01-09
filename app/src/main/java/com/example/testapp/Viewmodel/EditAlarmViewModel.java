package com.example.testapp.Viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.Dao.EventDao;
import com.example.testapp.Database.Databaseclass;
import com.example.testapp.model.EntityClass;

public class EditAlarmViewModel extends AndroidViewModel {

    private EventDao eventDao;
    private Databaseclass db;

    public EditAlarmViewModel(@NonNull Application application) {
        super(application);

        db=Databaseclass.getDatabase(application);
        eventDao=db.eventDao();
    }

    public LiveData<EntityClass> getAlarm(String id){
        return eventDao.getAlarm(id);
    }
}
