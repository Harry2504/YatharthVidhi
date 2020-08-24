package com.lauszus.facerecognitionapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    Context context;

    //define the schema
    public static final String DATABASE_NAME = "user_info";
    public static final String TABLE_NAME = "register";
    //Columns
    public static final String USER_id = "id";
    public static final String USER_firstname = "firstname";
    public static final String USER_lastname = "lastname";
    public static final String USER_PASSWD = "passwd";
    public static final String USER_username = "username";
    public static final String USER_Mobile = "mobile";
    public static final String USER_Email = "email";

    //Queries
    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( " +
            USER_id + " integer, " +
            USER_firstname + " text, " +
            USER_lastname + " text, " +
            USER_PASSWD + " text, " +
            USER_username + " text, " +
            USER_Email + " text, " +
            USER_Mobile + " text ) ";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
         this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        Toast.makeText(context, "Both tables created.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }
}