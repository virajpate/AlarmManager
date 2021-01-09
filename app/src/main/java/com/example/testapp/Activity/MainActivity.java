package com.example.testapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.testapp.Adapter.AlarmAdapter;
import com.example.testapp.Database.Databaseclass;
import com.example.testapp.R;
import com.example.testapp.model.EntityClass;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton insertbtn;
    RecyclerView recyclerView;
    AlarmAdapter adapter;
    Databaseclass databaseclass;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialization();

        databaseclass =Databaseclass.getDatabase(getApplicationContext());



        // btn on click listner
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),CreateAlarmActivity.class);
                startActivity(intent);
            }
        });
    }

    private void Initialization() {
        insertbtn=findViewById(R.id.btn_insert);
        recyclerView=findViewById(R.id.recyclerviewAlarm);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setAdapter();
    }

    private void setAdapter(){
        List<EntityClass> entityClassList=databaseclass.eventDao().getAllData();
        adapter=new AlarmAdapter(getApplicationContext(),entityClassList);
        recyclerView.setAdapter(adapter);
    }
}
