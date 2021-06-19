package com.aplus.registrationpage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    public DBHandler(@Nullable Context context) {
        super(context, "Register.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username Text primary Key,password Text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop Table if exists users");
    }


    public Boolean insertData(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);

        long result=db.insert("users",null,contentValues);


        if(result==-1){
            return false;
        }
        else
            return true;



    }

    public Boolean checkusername(String username){
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from users where username =?",new String[] {username});

        if(cursor.getCount()>0){
            return true;
        }

        else
            return false;
    }

    public Boolean checkUsernamePassword(String username,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username =? and password =?",new String[] {username,password});

        if(cursor.getCount()>0){
            return true;
        }
        else
            return false;

    }











}

