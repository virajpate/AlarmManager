package com.example.testapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.testapp.Broadcast.AlarmBroadcast;
import com.example.testapp.Broadcast.AlertReceiver;
import com.example.testapp.Broadcast.NotificationHelper;
import com.example.testapp.Database.Databaseclass;
import com.example.testapp.R;
import com.example.testapp.Viewmodel.AlarmViewModel;
import com.example.testapp.model.EntityClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CreateAlarmActivity extends AppCompatActivity {

    TextView tv_time,tv_date,tv_message;
    Button btn_time,btn_date;
    ImageView btn_back,btn_submit;
    String timetonotify;
    Databaseclass databaseclass;
    public static final String TIMEADED="Alarm Added";
    public static final String DATEADED="Date Added";
    public static final String MESSAGEADED="Message Added";
    private static final String TAG = "CreateAlarmActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createalarm);

        Initilization();
        databaseclass=Databaseclass.getDatabase(getApplicationContext());


        //btn click listner methods
        btnclicklistner();


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

    private void btnclicklistner() {
        //back btn
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //btn submit
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitMethod();
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
                tv_time.setText(FormatTime(hourOfDay,minute));
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

    private void submitMethod() {
        String time=tv_time.getText().toString().trim();
        String date=tv_date.getText().toString().trim();
        String message=tv_message.getText().toString().trim();
        Intent resultintent=new Intent();

        if (time.isEmpty()){
            ShowMessage("Plese fill time");

        }
        else if (date.isEmpty()){
            ShowMessage("Plese fill date");

        }else if (message.isEmpty()){
            ShowMessage("Plese fill message");
        }
        else {

//            EntityClass entityClass=new EntityClass();
//            entityClass.setTime(time);
//            entityClass.setDate(date);
//            entityClass.setMessage(message);
//            databaseclass.eventDao().insertall(entityClass);

            resultintent.putExtra(TIMEADED,time);
            resultintent.putExtra(DATEADED,date);
            resultintent.putExtra(MESSAGEADED,message);
            setResult(RESULT_OK,resultintent);
            setAlarm(message,date,time);

        }


    }


    private void ShowMessage(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    public String FormatTime(int hour, int minute) {

        String time;
        time = "";
        String formattedMinute;

        if (minute / 10 == 0) {
            formattedMinute = "0" + minute;
        } else {
            formattedMinute = "" + minute;
        }


        if (hour == 0) {
            time = "12" + ":" + formattedMinute + " AM";
        } else if (hour < 12) {
            time = hour + ":" + formattedMinute + " AM";
        } else if (hour == 12) {
            time = "12" + ":" + formattedMinute + " PM";
        } else {
            int temp = hour - 12;
            time = temp + ":" + formattedMinute + " PM";
        }


        return time;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (requestCode == RESULT_OK && data != null){

            }
        }
    }

    private void setAlarm(String text,String date,String time){
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent=new Intent(getApplicationContext(), AlertReceiver.class);
        Intent intent1=new Intent(getApplicationContext(), NotificationHelper.class);


        intent.putExtra("event",text);
        intent1.putExtra("event",text);
        Log.i(TAG,text);
        intent.putExtra("time",time);
        intent1.putExtra("time",time);
        Log.i(TAG,time);
        intent.putExtra("date",date);
        intent1.putExtra("date",date);
        Log.i(TAG,date);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),1,intent,0);

        String datetime= date+ " "+timetonotify;
        DateFormat format=new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1=format.parse(datetime);
            Log.i(TAG, String.valueOf(date1));
            alarmManager.set(AlarmManager.RTC_WAKEUP,date1.getTime(),pendingIntent);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        finish();

    }
}
