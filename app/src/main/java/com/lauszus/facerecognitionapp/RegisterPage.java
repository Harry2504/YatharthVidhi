package com.lauszus.facerecognitionapp;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegisterPage extends AppCompatActivity {
    private EditText edtfirst,edtlast,edtpasswd,edtpasswordagain,edtusername,edtmobile,edtemail;
    private String firstname,lastname,passwd,passwordAgain,type,username,mobile,email;
    private SQLiteDatabase db;
    private MyDatabaseHelper myDatabaseHelper;
    private ContentValues cv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

    }

    public void Register_password(View view) {
        edtfirst = findViewById(R.id.edtFirstName_Register);
        edtlast = findViewById(R.id.edtLastName_register);
        edtpasswd = findViewById(R.id.edtPassword_register);
        edtpasswordagain = findViewById(R.id.edtPasswordAgain_register);
        edtusername = findViewById(R.id.edtUsername_register);
        edtmobile = findViewById(R.id.edtMobileNumber_register);
        edtemail=findViewById(R.id.edtEmail_register);


        firstname = edtfirst.getText().toString();
        lastname = edtlast.getText().toString();
        passwd = edtpasswd.getText().toString();
        passwordAgain = edtpasswordagain.getText().toString();
        username=edtusername.getText().toString();
        mobile=edtmobile.getText().toString();
        email=edtemail.getText().toString();

        if(passwd.equals(passwordAgain)) {
            myDatabaseHelper = new MyDatabaseHelper(this);
            db = myDatabaseHelper.getWritableDatabase();

            //content values
            cv = new ContentValues();
            cv.put(MyDatabaseHelper.USER_firstname, firstname);
            cv.put(MyDatabaseHelper.USER_lastname, lastname);
            cv.put(MyDatabaseHelper.USER_PASSWD, passwd);
            cv.put(MyDatabaseHelper.USER_username, username);
            cv.put(MyDatabaseHelper.USER_Mobile, mobile);
            cv.put(MyDatabaseHelper.USER_Email, email);

            long id = db.insert(MyDatabaseHelper.TABLE_NAME,null,cv);

            if (id==-1){
                Toast.makeText(this, "Registration not successful", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this, "Registered Successfully"+ type, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterPage.this,loginpage.class));
            }
        }
        else{
            Toast.makeText(this, "Password not same in both.", Toast.LENGTH_SHORT).show();
        }
    }
}
