package com.example.testapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.testapp.R;
import com.example.testapp.Viewmodel.EditAlarmViewModel;
import com.example.testapp.model.EntityClass;

import java.util.Calendar;

public class EditAlarmActivity extends AppCompatActivity {
    TextView tv_time,tv_date,tv_message;
    Button btn_time,btn_date;
    ImageView btn_back,btn_submit;
    String timetonotify;
    private Bundle bundle;
    private String id;
    private LiveData<EntityClass> entityclass;
    EditAlarmViewModel viewModel;
    public static final String NOTEID ="id";
    public static final String UPDATE_Ararm="alarm_text";
    public static final String UPDATE_date="alarm_date";
    public static final String UPDATE_time="alarm_time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alarm);

        Initilization();

        bundle=getIntent().getExtras();

        if (bundle !=null){
            id=bundle.getString("id");
        }

        viewModel= ViewModelProviders.of(this).get(EditAlarmViewModel.class);

         entityclass = viewModel.getAlarm(id);

         entityclass.observe(this, new Observer<EntityClass>() {
             @Override
             public void onChanged(EntityClass entityClas) {
                 tv_time.setText(entityClas.getTime());
                 tv_date.setText(entityClas.getDate());
                 tv_message.setText(entityClas.getMessage());
             }
         });

         btnmethod();
    }



    private void Initilization() {
        tv_time=findViewById(R.id.tv_time);
        tv_date=findViewById(R.id.tv_date);
        tv_message=findViewById(R.id.tv_messageuser);
        btn_back=findViewById(R.id.btn_back);
        btn_submit=findViewById(R.id.btn_submit);
        btn_time=findViewById(R.id.btn_time);
        btn_date=findViewById(R.id.btn_date);

    }

    private void btnmethod() {

        //btn bac
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //btn time
        btn_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTimeMethod();
            }
        });

        //btn date
        btn_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDateMethod();
            }
        });


    }
    private void selectTimeMethod() {

        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR_OF_DAY);
        int minute =calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timetonotify= hourOfDay + ":"+minute;
                tv_time.setText(timetonotify);
                //todo: format time
            }
        },hour,minute,false);
        timePickerDialog.show();
    }
    private void selectDateMethod() {

        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog= new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                String date= dayOfMonth + "-" +month+ 1+ "-" +year;
                tv_date.setText(date);

            }
        },year,month,day);

        datePickerDialog.show();
    }

    public void update(View view) {

        String updatemessage=tv_message.getText().toString();
        String updatedate=tv_date.getText().toString();
        String updatetime=tv_time.getText().toString();

        Intent resultIntent= new Intent();
        resultIntent.putExtra(NOTEID,id);
        resultIntent.putExtra(UPDATE_Ararm,updatemessage);
        resultIntent.putExtra(UPDATE_date,updatedate);
        resultIntent.putExtra(UPDATE_time,updatetime);
        setResult(RESULT_OK,resultIntent);
        finish();

    }
}
