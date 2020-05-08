package com.example.workingwdatabases;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try{
//            SQLiteDatabase sql = this.openOrCreateDatabase("Users", MODE_PRIVATE,null);
//
//            sql.execSQL("CREATE TABLE IF NOT EXISTS users (name VARCHAR ,   age INT(3))");
//
//            sql.execSQL("INSERT INTO users(name,age) VALUES('Rishabh','34')");
//
//            sql.execSQL("INSERT INTO users(name,age) VALUES('Yash','16')");
//
//            Cursor c = sql.rawQuery("SELECT*FROM users",null);
//
//            int nameIndex = c.getColumnIndex("name");
//
//            int ageIndex = c.getColumnIndex("age");
//
//            c.moveToFirst();
//
//            while(c!=null){
//                Log.i("Name:",c.getString(nameIndex));
//                Log.i("Age:",c.getString(ageIndex));
//                c.moveToNext();
//            }
            SQLiteDatabase sql  = this.openOrCreateDatabase("Events",MODE_PRIVATE,null);
            sql.execSQL("CREATE TABLE IF NOT EXISTS events(name VARCHAR, year INT(4))");
            sql.execSQL("INSERT INTO events(name,year) VALUES('WorldWar1','1916')");
            sql.execSQL("INSERT INTO events(name,year) VALUES('WorldWar2','1945')");
            Cursor c = sql.rawQuery("SELECT*FROM events",null);
            int eventIndex = c.getColumnIndex("name");
            int yearIndex = c.getColumnIndex("year");
            c.moveToFirst();
            while(c!=null){
                Log.i("Events",c.getString(eventIndex));
                Log.i("Year" , c.getString(yearIndex));
                c.moveToNext();
            }
        }
            catch(Exception e){
            e.printStackTrace();
            }
    }
}
