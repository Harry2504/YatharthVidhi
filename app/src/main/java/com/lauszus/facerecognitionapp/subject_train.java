package com.lauszus.facerecognitionapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class subject_train extends AppCompatActivity {
    private ListView lv;
    private SingleRow singlerow;
    private ArrayList<SingleRow> singleRowArrayList;
    private MyDatabaseHelper1 myDatabaseHelper1;
    private SQLiteDatabase db;
    Cursor cursor;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_train);

        final String email = getIntent().getStringExtra("email");
        lv = findViewById(R.id.subject_train);

        myDatabaseHelper1 = new MyDatabaseHelper1(this);

        db = myDatabaseHelper1.getReadableDatabase();
        String[] columns = {MyDatabaseHelper1.coursename, MyDatabaseHelper1.timing, MyDatabaseHelper1.email};
        cursor = db.query(MyDatabaseHelper1.COURSE_TABLE, columns, null, null, null, null, null);

        String coursename,timing,edtemail;
        singleRowArrayList = new ArrayList<>();

        while (cursor.moveToNext()) {

            edtemail = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper1.email));
            if(edtemail.equals(email)) {
                coursename = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper1.coursename));
                timing = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper1.timing));
                singlerow = new SingleRow(coursename,timing);
                singleRowArrayList.add(singlerow);
            }
       }

      /*  singleRowArrayList = new ArrayList<>();

       String courses[]={"Chemical"};
       String timing [] = {"10:00-10:50"};

       for(int i=0;i<courses.length;i++){
           singlerow = new SingleRow(courses[i],timing[i]);
           singleRowArrayList.add(singlerow);
       }
*/
        boolean now = (singleRowArrayList.size()>0);

        if(now) {
            myAdapter adapter = new myAdapter(this, singleRowArrayList);
            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(subject_train.this, "Lets go to", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(subject_train.this,FaceRecognitionAppActivity.class);;
                    startActivity(intent);
                }
            });
        }
        else{
            Toast.makeText(this, "nothing to show", Toast.LENGTH_SHORT).show();
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subject_train.this,add_subject.class);;
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
    }

}
