package com.example.testapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.testapp.R;

public class NotificationMessage extends AppCompatActivity {

    private TextView messagetv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_message);
        findViewById(R.id.tv_message);
        Bundle bundle=getIntent().getExtras();
        messagetv.setText(bundle.getString("message"));

    }
}
