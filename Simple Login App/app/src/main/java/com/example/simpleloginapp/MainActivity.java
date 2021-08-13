package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode.ThreadPolicy.Builder;
import android.os.StrictMode;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    Button btnReg,btnLogin;
    EditText _txtUsername, _txtPassword, _txtEmail, _txtPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openHelper=new DatabaseHelper(this);
        btnReg=(Button)findViewById(R.id.btnReg);
        _txtUsername=(EditText)findViewById(R.id.txtUsername);
        _txtPassword=(EditText)findViewById(R.id.txtPassword);
        _txtEmail=(EditText)findViewById(R.id.txtEmail);
        _txtPhone=(EditText)findViewById(R.id.txtPhone);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnReg.setOnClickListener((v)->{
            db=openHelper.getWritableDatabase();
            String username=_txtUsername.getText().toString();
            String password=_txtPassword.getText().toString();
            String email=_txtEmail.getText().toString();
            String phone=_txtPhone.getText().toString();
            InsertData(username,password,email,phone);
            Toast.makeText(getApplicationContext(),"register successfully",Toast.LENGTH_LONG).show();
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, login.class);
                startActivity(intent);
            }
        });
    }
    public void InsertData(String username, String password, String email, String phone){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COL_2,username);
        contentValues.put(DatabaseHelper.COL_3,password);
        contentValues.put(DatabaseHelper.COL_4,email);
        contentValues.put(DatabaseHelper.COL_5,phone);
        long id = db.insert(DatabaseHelper.TABLE_NAME, null, contentValues);

    }
}