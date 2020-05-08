package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.content.Context.*;

public class SecondActivity extends AppCompatActivity {
    EditText multi ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        multi = findViewById(R.id.editText2);
        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);
        try {
            sharedPreferences.edit().putString(multi.getText().toString(),"");
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
