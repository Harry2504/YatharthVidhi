package com.lauszus.facerecognitionapp;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class appbase extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appbase);

        final String email = getIntent().getStringExtra("email");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Yatharth Vidhi");
        getSupportActionBar().setIcon(getDrawable(R.mipmap.ic_launcher));
        ImageView attendance = findViewById(R.id.attendance);
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(appbase.this, "Let's mark attendance", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(appbase.this,calendarview.class);
                intent.putExtra("email",email);
                startActivity(intent);
       }
        });
        ImageView train = findViewById(R.id.training);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(appbase.this, "Let's train data", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(appbase.this,subject_train.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });

    }

}
