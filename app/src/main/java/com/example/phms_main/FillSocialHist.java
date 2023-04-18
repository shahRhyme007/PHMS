package com.example.phms_main;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class FillSocialHist extends AppCompatActivity implements DialogSocialHist.DialogListenerSocialHist
{
    private ArrayAdapter<String> adapter;
    private ArrayList<String> socialHistList;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_socialhist);

        ListView socialHistLV = findViewById(R.id.socialhistLV);
        socialHistList = new ArrayList<>();
        FloatingActionButton fabAdd = findViewById(R.id.addSocialHist);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, socialHistList);
        socialHistLV.setAdapter(adapter);

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
        DialogSocialHist dialogSocialHist = new DialogSocialHist();
        dialogSocialHist.show(getSupportFragmentManager(), "show dialog");
    }

    @Override
    public int appendSocialHist(String socialHist)
    {
        int validAdd = 1;

        if (!socialHist.isEmpty())
        {
            for (String item : socialHistList)
            {
                if (socialHist.equalsIgnoreCase(item))
                {
                    validAdd = 0;
                    break;
                }
            }

            if (validAdd == 1)
            {
                socialHistList.add(socialHist);
                adapter.notifyDataSetChanged();
            }
        }
        else
            validAdd = -1;

        return validAdd;
    }
}