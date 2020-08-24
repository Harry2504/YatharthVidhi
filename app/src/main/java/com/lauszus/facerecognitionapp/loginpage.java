package com.lauszus.facerecognitionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class loginpage extends AppCompatActivity {
    private EditText edtName, edtPassword;
    public String username, password, type, email, mobile, name;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase db;
    Cursor cursor;
    private boolean isGenuine = false;
    private boolean usergenuine = false;
    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);
        edtName = findViewById(R.id.edtName_login);
        edtPassword = findViewById(R.id.edtpassword_login);
    }

    public void Register(View view) {
        Toast.makeText(this, "Let's Register", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(loginpage.this, RegisterPage.class));
    }

    public void Login(View view) {
        username = edtName.getText().toString();
        password = edtPassword.getText().toString();

        myDatabaseHelper = new MyDatabaseHelper(this);
        db = myDatabaseHelper.getReadableDatabase();
        String[] columns = {MyDatabaseHelper.USER_username, MyDatabaseHelper.USER_PASSWD, MyDatabaseHelper.USER_firstname, MyDatabaseHelper.USER_lastname, MyDatabaseHelper.USER_Email, MyDatabaseHelper.USER_Mobile};
        cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String enteredUsername = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.USER_Email));
            String enteredPassword = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.USER_PASSWD));


            if((username.equals(enteredUsername))){
                usergenuine =true;
            if ((username.equals(enteredUsername)) && (password.equals(enteredPassword))) {
                isGenuine = true;
                name = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.USER_firstname)) + " " + cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.USER_lastname));
                email = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.USER_username));
                mobile = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.USER_Mobile));

                //saving data in shared preferences
                sp = getSharedPreferences("userPersonalInfo", MODE_PRIVATE);
                spEditor = sp.edit();
                spEditor.putString("User_UserName", username);
                spEditor.putString("User_Password", password);
                spEditor.putString("User_Name", name);
                spEditor.putString("User_Email", email);
                spEditor.putString("User_Mobile", mobile);
                spEditor.commit();
                break;
            }
        }
        }
        if (isGenuine) {
            Toast.makeText(this, "User genuine", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(loginpage.this,appbase.class);;
            intent.putExtra("email",email);
            startActivity(intent);
           }
        else{
            if(usergenuine){
                Toast.makeText(this, "Password Incorrect", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Please Register", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void ForgotPassword(View view) {
        startActivity(new Intent(loginpage.this, ForgotPasswordPage.class));
    }
}