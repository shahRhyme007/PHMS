package com.example.phms_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class TabMed extends AppCompatActivity implements DialogMedicationAdd.DialogListenerMedication
{
    ArrayList<MedicationModel> medicationModels = new ArrayList<>();
    int image = R.drawable.control;

    FloatingActionButton home;
    Button add;
    Button remove;
    ImageButton search;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_med);

        home = findViewById(R.id.Home);
        add = findViewById(R.id.add);
        remove = findViewById(R.id.remove);
        search = findViewById(R.id.search);


        RecyclerView recyclerView = findViewById(R.id.medicationView);

        setUpMedicationModels();



        Med_RecyclerViewAdapter adapter = new Med_RecyclerViewAdapter(this, medicationModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        home.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMed.this, MainActivity.class));
            }
        });

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                addShowDialog();
            }
        });

        remove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                rmShowDialog();
            }
        });

        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(TabMed.this, MainActivity.class));
            }
        });


    }

    private void setUpMedicationModels()
    {
        String[] medicationNames = getResources().getStringArray(R.array.medication_names);
        String[] routes = getResources().getStringArray(R.array.routes);
        int[] control = getResources().getIntArray(R.array.control);
        int[] dosage = getResources().getIntArray(R.array.dosage);
        int[] intervals = getResources().getIntArray(R.array.intervals);

        for (int i = 0; i < medicationNames.length; i++)
        {
            if (control[i] == 1)
            {
                medicationModels.add(new MedicationModel(medicationNames[i], routes[i],
                                    image, dosage[i], intervals[i]));
            }
            else
            {
                medicationModels.add(new MedicationModel(medicationNames[i], routes[i],
                                    dosage[i], intervals[i]));
            }
        }
    }

    public void addShowDialog()
    {
        DialogMedicationAdd dialogMedicationAdd = new DialogMedicationAdd();
        dialogMedicationAdd.show(getSupportFragmentManager(), "show dialog");
    }

    public void rmShowDialog()
    {

    }

    @Override
    public int appendMedication(String medication, String route, String dosage, String intervals)
    {
        int validAdd = 1;

        if (!medication.isEmpty() || !route.isEmpty() || !dosage.isEmpty() || !intervals.isEmpty())
        {
            validAdd = -1;
        }
        else
        {
            ArrayList<String> medNames = new ArrayList<String>();

            for (int i = 0; i < medicationModels.size(); i++)
                medNames.add(medicationModels.get(i).getMedicationName());

            for (String med : medNames)
            {
                if (medication.equalsIgnoreCase(med))
                {
                    validAdd = 0;
                    break;
                }
            }
        }

        return validAdd;
    }
}

