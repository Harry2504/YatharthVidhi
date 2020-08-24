package com.lauszus.facerecognitionapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class add_subject extends AppCompatActivity {
    private SQLiteDatabase db;
    private MyDatabaseHelper1 myDatabaseHelper1;
    private ContentValues cv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_add);

        final EditText edtcoursename;
        final EditText edttiming;
        Button save;

        edtcoursename = findViewById(R.id.edtcourse);
        edttiming = findViewById(R.id.edttime);
        save = findViewById(R.id.save_subject);

         save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        final String email = getIntent().getStringExtra("email");
        String coursename = edtcoursename.getText().toString();
         String timing = edttiming.getText().toString();

                myDatabaseHelper1 = new MyDatabaseHelper1(getBaseContext());
                db = myDatabaseHelper1.getWritableDatabase();

                //content values
                cv = new ContentValues();
                cv.put(MyDatabaseHelper1.coursename, coursename);
                cv.put(MyDatabaseHelper1.timing, timing);
                cv.put(MyDatabaseHelper1.email, email);

                long id = db.insert(MyDatabaseHelper1.COURSE_TABLE,null,cv);

                if (id==-1){
                    Toast.makeText(add_subject.this, "Subject not added.Try again", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(add_subject.this, "Subject Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(add_subject.this,subject_train.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                }
            }
        });
    }
}
