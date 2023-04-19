package com.example.phms;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TabMedication extends AppCompatActivity
{
    ArrayList<ModelMedication> medicationModels = new ArrayList<>();
    int image = R.drawable.control;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_med);

        RecyclerView recyclerView = findViewById(R.id.medicationView);

        setUpMedicationModels();

        RecyclerViewAdapter_Med adapter = new RecyclerViewAdapter_Med(this, medicationModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpMedicationModels()
    {
        String[] medicationNames = getResources().getStringArray(R.array.medication_names);
        String[] routes = getResources().getStringArray(R.array.routes);
        String[] lastTimeTaken = getResources().getStringArray(R.array.timings);
        int[] control = getResources().getIntArray(R.array.control);
        int[] dosage = getResources().getIntArray(R.array.dosage);
        int[] intervals = getResources().getIntArray(R.array.intervals);

        for (int i = 0; i < medicationNames.length; i++)
        {
            if (control[i] == 1)
            {
                medicationModels.add(new ModelMedication(medicationNames[i], routes[i],
                        lastTimeTaken[i], dosage[i], intervals[i]));
            }
            else
            {
                medicationModels.add(new ModelMedication(medicationNames[i], routes[i],
                        lastTimeTaken[i], image, dosage[i], intervals[i]));
            }


        }
    }
}

