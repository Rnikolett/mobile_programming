package com.example.mobile_application_wsi08i;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Bookdetails.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table Bookdetails(isbn TEXT primary key, cim TEXT NOT NULL, iro TEXT NOT NULL)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists Bookdetails");
    }
    public Boolean insertbookdata(String isbn, String cim, String iro)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("isbn", isbn);
        contentValues.put("cim", cim);
        contentValues.put("iro", iro);
        long result=DB.insert("Bookdetails",null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean deletedata (String isbn)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Bookdetails where isbn = ?", new String[]{isbn});
        if (cursor.getCount() > 0) {
            long result = DB.delete("Bookdetails", "isbn=?", new String[]{isbn});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Bookdetails", null);
        return cursor;
    }

}
