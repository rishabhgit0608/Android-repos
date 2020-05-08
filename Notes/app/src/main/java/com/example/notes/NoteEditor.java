package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class NoteEditor extends AppCompatActivity {
    EditText text ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);
        text = findViewById(R.id.editText);
        Intent intent = getIntent();
        int noteID = intent.getIntExtra("noteID", -1);
        if(noteID != -1){
            text.setText(MainActivity.arr.get(noteID));
        }
        else{
            MainActivity.arr.add("");
            noteID= MainActivity.arr.size()-1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }
        final int finalNoteID = noteID;
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.arr.set(finalNoteID, String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notes", Context.MODE_PRIVATE);



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
