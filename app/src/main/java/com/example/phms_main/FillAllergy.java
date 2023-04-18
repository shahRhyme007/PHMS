package com.example.phms_main;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FillAllergy extends AppCompatActivity implements DialogAllergy.DialogListenerAllergy
{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> allergyList;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_allergy);

        ListView allergyLV = findViewById(R.id.allergyLV);
        allergyList = new ArrayList<>();
        FloatingActionButton fabAdd = findViewById(R.id.addAllergy);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, allergyList);
        allergyLV.setAdapter(adapter);

        fabAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog();
            }
        });
    }

    public void showDialog()
    {
        DialogAllergy dialogAllergy = new DialogAllergy();
        dialogAllergy.show(getSupportFragmentManager(), "show dialog");
    }

    @Override
    public int appendAllergy(String allergy)
    {
        int validAdd = 1;

        if (!allergy.isEmpty())
        {
            for (String item : allergyList)
            {
                if (allergy.equalsIgnoreCase(item))
                {
                    validAdd = 0;
                    break;
                }
            }

            if (validAdd == 1)
            {
                allergyList.add(allergy);
                adapter.notifyDataSetChanged();
            }
        }
        else
            validAdd = -1;

        return validAdd;
    }
}