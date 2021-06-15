package com.example.crudtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user (firstName TEXT primary key,lastName TEXT,adresse TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
    }

    public Boolean insertUser(String firstName,String lastName,String adresse){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("firstName",firstName);
        contentValues.put("lastName",lastName);
        contentValues.put("adresse",adresse);
        Long result=db.insert("user",null,contentValues);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean updateUser(String firstName,String lastName,String adresse) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("lastName", lastName);
        contentValues.put("adresse", adresse);
        Cursor cursor = db.rawQuery("SELECT * FROM user where firstName=?", new String[]{firstName});
        if (cursor.getCount() > 0) {

            int result = db.update("user", contentValues, "firstName=?", new String[]{firstName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }

    }

    public Boolean deleteUser(String firstName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user where firstName=?", new String[]{firstName});
        if (cursor.getCount() > 0) {

            int result = db.delete("user","firstName=?", new String[]{firstName});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        }else {
            return false;
        }

    }
    public Cursor getAllUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);
       return cursor;
    }
}
