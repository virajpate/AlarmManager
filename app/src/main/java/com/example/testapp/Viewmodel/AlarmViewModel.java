package com.example.testapp.Viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.testapp.Dao.EventDao;
import com.example.testapp.Database.Databaseclass;
import com.example.testapp.model.EntityClass;

import java.util.List;

public class AlarmViewModel extends AndroidViewModel {



    private EventDao eventDao;
    private Databaseclass eventdb;
    private LiveData<List<EntityClass>> mAlllist;



    public AlarmViewModel(@NonNull Application application) {
        super(application);

        eventdb=Databaseclass.getDatabase(application);
        eventDao=eventdb.eventDao();
        mAlllist=eventDao.getAllData();
    }

    public void insert(EntityClass entityClass){
        new InsertAsynktask(eventDao).execute(entityClass);
    }

    public LiveData<List<EntityClass>> getAllList(){
        return mAlllist;
    }

    public void update(EntityClass entityClass){
        new UpdateAsynkTask(eventDao).execute(entityClass);
    }

    public void delet(EntityClass entityClass){
        new DeleteAsynkTask(eventDao).execute(entityClass);
    }

    private class InsertAsynktask extends AsyncTask<EntityClass,Void,Void> {

        EventDao meventDao;
        public InsertAsynktask(EventDao eventDao) {
            this.meventDao=eventDao;
        }

        @Override
        protected Void doInBackground(EntityClass... entityClasses) {
            meventDao.insertall(entityClasses[0]);
            return null;
        }
    }

    private class UpdateAsynkTask extends AsyncTask<EntityClass,Void,Void>{
        EventDao eventDao;

        public UpdateAsynkTask(EventDao eventDa) {
            this.eventDao=eventDa;
        }


        @Override
        protected Void doInBackground(EntityClass... entityClasses) {
            eventDao.update(entityClasses[0]);
            return null;
        }
    }

    private class DeleteAsynkTask extends AsyncTask<EntityClass,Void,Void>{
        EventDao eventDao;

        public DeleteAsynkTask(EventDao eventDao) {
            this.eventDao=eventDao;
        }

        @Override
        protected Void doInBackground(EntityClass... entityClasses) {
            eventDao.delete(entityClasses[0]);
            return null;
        }
    }
}
