package com.example.notewriter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class NotesWriterMainActivity extends AppCompatActivity {

    EditText editText;
    int notedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_writer_main);

        editText = findViewById(R.id.editText);

        Intent intent = getIntent();
        notedId = intent.getIntExtra("notes", -1);

        if (notedId != -1) {
            editText.setText(MainActivity.addnotes.get(notedId));
        } else {
            MainActivity.addnotes.add("");
            notedId = MainActivity.addnotes.size() -1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.addnotes.set(notedId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetInvalidated();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notewriter", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.addnotes);
                sharedPreferences.edit().putStringSet("arrays", set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}