package com.example.studentdbapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String DbName="college.db";
    static String TableName="students";
    static String col1="id";
    static String col2="name";
    static String col3="adno";
    static String col4="rollno";
    static String col5="college";
    public DatabaseHelper( Context context) {
        super(context, DbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="create table "+TableName+"("+col1+" integer primary key autoincrement,"+
                col2+" text,"+
                col3+" text,"+
                col4+" text,"+
                col5+" text)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean insertData(String name,String adno,String rollno,String college) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put(col2, name);
        content.put(col3, adno);
        content.put(col4, rollno);
        content.put(col5, college);
        long status = db.insert(TableName, null, content);
        if (status == -1) {
            return false;
        }
        else {
            return  true;

        }
    }
    public Cursor searchData(String AdNo){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="select * from "+TableName+" where "+col3+"='"+AdNo+"'";
        Cursor c=db.rawQuery(query,null);
        return c;
    }

    }