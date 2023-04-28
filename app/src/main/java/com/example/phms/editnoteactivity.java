package com.example.phms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editnoteactivity extends AppCompatActivity {

    Intent data;
    EditText medittitleofnote, meditcontentofnote;
    FloatingActionButton msavededitnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnoteactivity);

        medittitleofnote = findViewById(R.id.edittitleOfNote);
        meditcontentofnote = findViewById(R.id.editContentOfNote);
        msavededitnote = findViewById(R.id.saveeidtNote);
        data= getIntent();
        Toolbar toolbar = findViewById(R.id.toolBarOfeditnote);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        msavededitnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "savebutton clicked", Toast.LENGTH_SHORT).show();
            }
        });

        String notetitle = data.getStringExtra("title");
        String notecontent= data.getStringExtra("content") ;
        meditcontentofnote.setText(notecontent);
        medittitleofnote.setText(notetitle);


    }
}