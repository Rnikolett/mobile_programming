package com.example.mobile_application_wsi08i;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBUserHelper extends SQLiteOpenHelper {
    public DBUserHelper(Context context) {
        super(context, "User.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table User(user TEXT primary key, psw TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists User");
    }
    public Boolean insertuserdata(String user, String psw)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("user", user);
        contentValues.put("psw", psw);
        long result=DB.insert("User",null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from User", null);
        return cursor;
    }
}
