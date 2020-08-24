package com.lauszus.facerecognitionapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class subject_activityclass extends AppCompatActivity {
    private ListView lv;
    private SingleRow singlerow;
    private ArrayList<SingleRow> singleRowArrayList;
    private MyDatabaseHelper1 myDatabaseHelper1;
    private SQLiteDatabase db;
    Cursor cursor;


    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_subject_activityclass);
            //finding the id of list to display the data coming through the link of adapter
            lv = findViewById(R.id.subject_id);
            TextView date = findViewById(R.id.date);

            String Date = getIntent().getStringExtra("date");
        String email = getIntent().getStringExtra("email");

        date.setText(Date);


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
                        Toast.makeText(subject_activityclass.this, "Marking Attendance", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(subject_activityclass.this,FaceRecognitionAppActivity.class);;
                        startActivity(intent);
                    }
                });
            }
            else{
                Toast.makeText(this, "nothing to show", Toast.LENGTH_SHORT).show();
            }


        }

    }

