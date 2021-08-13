package com.example.simpleloginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button btnLogin,btnBack;
    EditText Login_Username, Login_Password;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openHelper = new DatabaseHelper(this);
        db=openHelper.getReadableDatabase();
        btnLogin=(Button)findViewById(R.id.btnLogin_Login);
        btnBack=(Button)findViewById(R.id.btnBack);
        Login_Username=(EditText)findViewById(R.id.txtUsername_Login);
        Login_Password=(EditText)findViewById(R.id.txt_Password_Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Login_Username.getText().toString();
                String password = Login_Password.getText().toString();
                cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_NAME+ " WHERE " + DatabaseHelper.COL_2 + "=? "  + " AND " + DatabaseHelper.COL_3 + "=?",new String[]{username,password});
                if(cursor!=null){
                    if(cursor.getCount()>0){
                        Intent intent = new Intent(login.this, Congrats.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"login successfully as" + username,Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}