package com.lauszus.facerecognitionapp;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CalendarView;

public class calendarview extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendarview);

        final String email = getIntent().getStringExtra("email");

        CalendarView Calendarview = findViewById(R.id.calendarView);
        Calendarview.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;
                Intent intent = new Intent(calendarview.this,subject_activityclass.class);
                intent.putExtra("date",date);
                intent.putExtra("email",email);
                startActivity(intent);
             }
        });
    }
}
