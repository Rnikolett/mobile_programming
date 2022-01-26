package com.example.mobile_application_wsi08i;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class record_system extends AppCompatActivity {
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_system);

        TextView ISBN = (TextView) findViewById(R.id.azon);
        TextView CIM = (TextView) findViewById(R.id.cim);
        TextView IRO = (TextView) findViewById(R.id.iro);
        DB = new DBHelper(this);

        MaterialButton hozzaad = (MaterialButton) findViewById(R.id.add);
        MaterialButton View = (MaterialButton) findViewById(R.id.view);
        MaterialButton Del = (MaterialButton) findViewById(R.id.delete);
        MaterialButton Help = (MaterialButton) findViewById(R.id.help);

        hozzaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                String isbn = ISBN.getText().toString();
                String cim = CIM.getText().toString();
                String iro = IRO.getText().toString();

                        if(isbn.length() ==6) {
                            if(cim.length() !=0){
                                if(iro.length() != 0){
                                    DB.insertbookdata(isbn, cim, iro);
                                    Toast.makeText(record_system.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                                }else
                                    Toast.makeText(record_system.this, "Író Field is Required", Toast.LENGTH_SHORT).show();
                            }else
                                Toast.makeText(record_system.this, "Könyv Címe Field is Required", Toast.LENGTH_SHORT).show();

                        }else if(isbn.length() < 6)
                                Toast.makeText(record_system.this, "ID is less than 6 (" + isbn.length()+"), no New Entry", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(record_system.this, "ID is more than 6 (" + isbn.length()+"), no New Entry", Toast.LENGTH_SHORT).show();
            }
        });

        View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(record_system.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("ID :"+res.getString(0)+"\n");
                    buffer.append("Cím :"+res.getString(1)+"\n");
                    buffer.append("Íro:"+res.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(record_system.this);
                builder.setCancelable(true);
                builder.setTitle("Books available");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });

        Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = ISBN.getText().toString();
                Boolean checkudeletedata = DB.deletedata(isbn);
                if(checkudeletedata==true)
                    Toast.makeText(record_system.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(record_system.this, "There is no such Entry", Toast.LENGTH_SHORT).show();
            }        });

        Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "Az azonosító számnak 6 számjegyből kell állnia.\nA törléshez adja meg a megfelelő Könyv címét";
                AlertDialog.Builder builder = new AlertDialog.Builder(record_system.this);
                builder.setCancelable(true);
                builder.setTitle("Help");
                builder.setMessage(message);
                builder.show();
            }        });

    }

}