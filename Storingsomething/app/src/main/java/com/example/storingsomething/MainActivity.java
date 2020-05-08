 package com.example.storingsomething;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.storingsomething", Context.MODE_PRIVATE);
        ArrayList<String> arr = new ArrayList<>();
        arr.add("Monica");
        arr.add("Chandler");
        try {

            sharedPreferences.edit().putString("arr",ObjectSerializer.serialize(arr)).apply();

        } catch (IOException e) {

            e.printStackTrace();

        }

        ArrayList<String> newarr = new ArrayList<>();
        try {

            newarr = (ArrayList)ObjectSerializer.deserialize(sharedPreferences.getString("arr",ObjectSerializer.serialize(new ArrayList<String>())));

        } catch (IOException e) {

            e.printStackTrace();

        }
        Log.i("new arr", arr.toString());
//        sharedPreferences.edit().putString("username","hordan").apply();
//
//        String username = sharedPreferences.getString("username", "");
//
//        Log.i("username",username);

    }
}
