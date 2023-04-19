package com.example.phms;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FillMedHist extends AppCompatActivity implements DialogMedHist.DialogListenerMedHist
{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> medHistList;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_medhist);

        ListView medhistLV = findViewById(R.id.medhistLV);
        medHistList = new ArrayList<>();
        FloatingActionButton fabAdd = findViewById(R.id.addMedHist);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medHistList);
        medhistLV.setAdapter(adapter);

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
        DialogMedHist dialogMedHist = new DialogMedHist();
        dialogMedHist.show(getSupportFragmentManager(), "show dialog");
    }

    @Override
    public int appendMedHist(String medHist)
    {
        int validAdd = 1;

        if (!medHist.isEmpty())
        {
            for (String item : medHistList)
            {
                if (medHist.equalsIgnoreCase(item))
                {
                    validAdd = 0;
                    break;
                }
            }

            if (validAdd == 1)
            {
                medHistList.add(medHist);
                adapter.notifyDataSetChanged();
            }
        }
        else
            validAdd = -1;

        return validAdd;
    }
}