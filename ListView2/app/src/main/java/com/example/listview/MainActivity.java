package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mylist ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mylist = (ListView)findViewById(R.id.mylist);
        final ArrayList<String> myFamily = new ArrayList<String>();
        myFamily.add("Rishabh");
        myFamily.add("yash");
        myFamily.add("Ritu");
        myFamily.add("Shashi kant");
        ArrayAdapter<String> arrayAdapter =new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,myFamily);

        mylist.setAdapter(arrayAdapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,myFamily.get(position),Toast.LENGTH_SHORT).show();
                Intent newpage = new Intent(MainActivity.this ,otherscreen.class);
                startActivity(newpage);
            }
        });
    }
}
