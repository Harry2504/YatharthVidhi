package com.lauszus.facerecognitionapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.Toast;

public class MyDatabaseHelper1 extends SQLiteOpenHelper {
    Context context;

    //define the schema
    public static final String COURSE_TABLE = "course_table";

    //Columns
    public static final String coursename = "Coursename";
    public static final String timing = "timing";
    public static final String email = "email";

    //Queries
    public static final String CREATE_TABLE = "CREATE TABLE " + COURSE_TABLE + " ( " +
            coursename + " text, " +
            timing + " text, " +
            email + " text ) ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + COURSE_TABLE;

    public MyDatabaseHelper1(Context context) {
        super(context, COURSE_TABLE, null, 3);
        this.context = context;
            }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(context, "table created.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}