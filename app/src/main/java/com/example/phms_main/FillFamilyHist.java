package com.example.phms_main;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FillFamilyHist extends AppCompatActivity implements DialogFamHist.DialogListenerFamHist
{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> famHistList;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_family_hist);

        ListView famhistLV = findViewById(R.id.famhistLV);
        famHistList = new ArrayList<>();
        FloatingActionButton fabAdd = findViewById(R.id.addFamHist);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, famHistList);
        famhistLV.setAdapter(adapter);

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
        DialogFamHist dialogFamhist = new DialogFamHist();
        dialogFamhist.show(getSupportFragmentManager(), "show dialog");
    }

    @Override
    public int appendFamHist(String famHist)
    {
        int validAdd = 1;

        if (!famHist.isEmpty())
        {
            for (String item : famHistList)
            {
                if (famHist.equalsIgnoreCase(item))
                {
                    validAdd = 0;
                    break;
                }
            }

            if (validAdd == 1)
            {
                famHistList.add(famHist);
                adapter.notifyDataSetChanged();
            }
        }
        else
            validAdd = -1;

        return validAdd;
    }
}