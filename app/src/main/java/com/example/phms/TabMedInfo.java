package com.example.phms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TabMedInfo extends AppCompatActivity
{
    Button allergyBTN, familyBTN, medicalBTN, socialBTN, surgicalBTN;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_med_info);

        allergyBTN = findViewById(R.id.allergies);
        familyBTN = findViewById(R.id.family);
        medicalBTN = findViewById(R.id.medical);
        socialBTN = findViewById(R.id.social);
        surgicalBTN = findViewById(R.id.surgery);

        allergyBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMedInfo.this, FillAllergy.class));
            }
        });

        familyBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMedInfo.this, FillFamHist.class));
            }
        });

        medicalBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMedInfo.this, FillMedHist.class));
            }
        });

        socialBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMedInfo.this, FillSocialHist.class));
            }
        });

        surgicalBTN.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMedInfo.this, FillSurgicalHist.class));
            }
        });
    }
}