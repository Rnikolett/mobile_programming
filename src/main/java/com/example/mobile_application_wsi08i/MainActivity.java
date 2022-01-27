package com.example.mobile_application_wsi08i;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    DBUserHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);
        DB = new DBUserHelper(this);


        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginbtn);
        MaterialButton regbtn = (MaterialButton) findViewById(R.id.register);

        //admin and admin

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                while (res.moveToNext()) {
                    if (username.getText().toString().equals(res.getString(0))  && password.getText().toString().equals(res.getString(1))) {
                        openRecordSystem();
                        Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getdata();
                String user = username.getText().toString();
                String psw = password.getText().toString();

                    if (user.length() != 0){
                        if(psw.length() != 0){
                            DB.insertuserdata(user, psw);
                            Toast.makeText(MainActivity.this, "User inserted, you can login now", Toast.LENGTH_SHORT).show();
                        }
                    }else
                        Toast.makeText(MainActivity.this, "User cannot be made", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void openRecordSystem() {
        Intent intent = new Intent(this, record_system.class);
        startActivity(intent);
    }
}