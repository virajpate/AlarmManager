package com.example.testapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.testapp.Adapter.AlarmAdapter;
import com.example.testapp.Broadcast.AlarmBroadcast;
import com.example.testapp.Database.Databaseclass;
import com.example.testapp.R;
import com.example.testapp.Viewmodel.AlarmViewModel;
import com.example.testapp.model.EntityClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements AlarmAdapter.OnDeleteClickListener {

    FloatingActionButton insertbtn;
    RecyclerView recyclerView;
    Databaseclass databaseclass;
    private static final int New_Alarm_REQ_CODE=1;
    public static final int Update_Alarm_REQ_CODE=2;
    private AlarmViewModel viewModel;
    AlarmAdapter adapter;
    private List<EntityClass> entityClassList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertbtn=findViewById(R.id.btn_insert);
        recyclerView=findViewById(R.id.recyclerviewAlarm);

        adapter=new AlarmAdapter(this,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // btn on click listner
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),CreateAlarmActivity.class);
                startActivityForResult(intent,New_Alarm_REQ_CODE);
            }
        });


        viewModel= ViewModelProviders.of(this).get(AlarmViewModel.class);

        viewModel.getAllList().observe(this, new Observer<List<EntityClass>>() {
            @Override
            public void onChanged(List<EntityClass> entityClasses) {

                adapter.setAlarm(entityClasses);
            }
        });



    }




//    private void setAdapter(){
//        List<EntityClass> entityClassList=databaseclass.eventDao().getAllData();
//        adapter=new AlarmAdapter(getApplicationContext(),entityClassList);
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == New_Alarm_REQ_CODE && resultCode ==RESULT_OK){

            final String alarmid= UUID.randomUUID().toString();
            EntityClass entityClass=new EntityClass(alarmid,data.getStringExtra(CreateAlarmActivity.MESSAGEADED)
                                                           ,data.getStringExtra(CreateAlarmActivity.DATEADED)
                                                           ,data.getStringExtra(CreateAlarmActivity.TIMEADED));

            viewModel.insert(entityClass);

            String message=data.getStringExtra(CreateAlarmActivity.MESSAGEADED);
            String date=data.getStringExtra(CreateAlarmActivity.DATEADED);
            String time=data.getStringExtra(CreateAlarmActivity.TIMEADED);




            ShowMessage("Save");
        }
        else if(requestCode == Update_Alarm_REQ_CODE && resultCode ==RESULT_OK){

                  EntityClass entityClass=new EntityClass(data.getStringExtra(EditAlarmActivity.NOTEID),
                                                           data.getStringExtra(EditAlarmActivity.UPDATE_Ararm),
                                                           data.getStringExtra(EditAlarmActivity.UPDATE_date),
                                                           data.getStringExtra(EditAlarmActivity.UPDATE_time));
                  viewModel.update(entityClass);

                  ShowMessage("Alarm Updated..");

        }
        else {
            ShowMessage("Not Save");
        }
    }

    private void ShowMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnDeleteClickListener(EntityClass entityClass) {
        viewModel.delet(entityClass);
    }



}
