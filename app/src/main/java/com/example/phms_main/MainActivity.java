package com.example.phms_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    Button medinfo;
    Button meds;
    Spinner rand;
    TextView rtext;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        medinfo = findViewById(R.id.medinfo);
        meds = findViewById(R.id.meds);
        rand = findViewById(R.id.randSpinner);
        rtext = findViewById(R.id.randTV);

        ArrayList<String> randArr = new ArrayList<String>();

        randArr.add("Hello");
        randArr.add("Hell");
        randArr.add("Hel");
        randArr.add("He");
        randArr.add("H");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, randArr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        rand.setAdapter(adapter);

        medinfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, TabMedInfo.class));
            }
        });

        meds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, TabMed.class));
            }
        });



        rand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           rtext.setText(rand.getSelectedItem().toString());
       }

       @Override
       public void onNothingSelected(AdapterView<?> adapterView) {

       }
        });



    }
}
