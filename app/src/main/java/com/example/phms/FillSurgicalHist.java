package com.example.phms;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FillSurgicalHist extends AppCompatActivity implements DialogSurgicalHist.DialogListenerSurgicalHist
{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> surgicalHistList;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_surgicalhist);

        ListView surgicalHistLV = findViewById(R.id.surgicalhistLV);
        surgicalHistList = new ArrayList<>();
        FloatingActionButton fabAdd = findViewById(R.id.addSurgicalHist);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, surgicalHistList);
        surgicalHistLV.setAdapter(adapter);

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
        DialogSurgicalHist dialogSurgicalHist = new DialogSurgicalHist();
        dialogSurgicalHist.show(getSupportFragmentManager(), "show dialog");
    }

    @Override
    public int appendSurgicalHist(String surgicalHist)
    {
        int validAdd = 1;

        if (!surgicalHist.isEmpty())
        {
            for (String item : surgicalHistList)
            {
                if (surgicalHist.equalsIgnoreCase(item))
                {
                    validAdd = 0;
                    break;
                }
            }

            if (validAdd == 1)
            {
                surgicalHistList.add(surgicalHist);
                adapter.notifyDataSetChanged();
            }
        }
        else
            validAdd = -1;

        return validAdd;
    }
}