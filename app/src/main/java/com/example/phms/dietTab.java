package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class dietTab extends AppCompatActivity
{
    FloatingActionButton fabMorning; // this is the main activity for diet
    FloatingActionButton fabLunch;
    FloatingActionButton fabDinner;
    RecyclerView recycleViewMorning;
    RecyclerView recyclerViewLunch;
    RecyclerView recyclerViewDinner;

    Button buttonToHome;
    Button buttonToHealthTips;

    List<DataClass> dataList;
    List<DataClass> dataList2;
    List<DataClass> dataList3;
    List<DataClass> dataList4;

    int totalCaloriesMorning = 0;
    int totalCaloriesLunch = 0 ;
    int totalCaloriesDinner = 0;
    int totalCaloriesForTheDay = 0 ;


    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;

    ValueEventListener valueEventListener;
    ValueEventListener valueEventListener2 ;
    ValueEventListener valueEventListener3;

    ValueEventListener valueEventListener4;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_tab);

        fabMorning = findViewById(R.id.breakfastFab);
        fabLunch = findViewById(R.id.lunchFab);
        fabDinner = findViewById(R.id.dinnerFab);

        buttonToHome = findViewById(R.id.backtoHOME);
        buttonToHealthTips = findViewById((R.id.buttonHealthtip));

        recycleViewMorning = findViewById(R.id.recyclerView1);
        recyclerViewLunch = findViewById(R.id.recyclerView2);
        recyclerViewDinner = findViewById(R.id.recyclerView3);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(dietTab.this, 1);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(dietTab.this, 1);
        GridLayoutManager gridLayoutManager3 = new GridLayoutManager(dietTab.this, 1);

        recycleViewMorning.setLayoutManager(gridLayoutManager);
        recyclerViewLunch.setLayoutManager(gridLayoutManager2);
        recyclerViewDinner.setLayoutManager(gridLayoutManager3);

        AlertDialog.Builder builder = new AlertDialog.Builder(dietTab.this);
        AlertDialog.Builder builder2 = new AlertDialog.Builder(dietTab.this);
        AlertDialog.Builder builder3 = new AlertDialog.Builder(dietTab.this);

        AlertDialog dialog = builder.create();
        AlertDialog dialog2 = builder2.create();
        AlertDialog dialog3 = builder3.create();

        dialog.show();
        dialog2.show();
        dialog3.show();

        dataList = new ArrayList<>();
        dataList2 = new ArrayList<>();
        dataList3 = new ArrayList<>();
        dataList4 = new ArrayList<>();

        MyAdapterRecyclerView adapter = new MyAdapterRecyclerView(dietTab.this, dataList);
        recycleViewMorning.setAdapter(adapter);

        MyAdapterRecyclerView adapterLunch = new MyAdapterRecyclerView(dietTab.this, dataList2);
        recyclerViewLunch.setAdapter(adapterLunch);

        MyAdapterRecyclerView adapterDinner = new MyAdapterRecyclerView(dietTab.this, dataList3);
        recyclerViewDinner.setAdapter(adapterDinner);


        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        //databaseReference2 =  FirebaseDatabase.getInstance().getReference("users");
        String uId= FirebaseAuth.getInstance().getCurrentUser().getUid();

        DatabaseReference phmsRef = databaseReference.child(uId).child("PHMS");

       // DatabaseReference phmsLunch = databaseReference2.child(uId).child("lunch");
        DatabaseReference databaseReferenceLunch = FirebaseDatabase.getInstance().getReference("users").child(uId).child("lunch");
        DatabaseReference databaseReferenceDinner = FirebaseDatabase.getInstance().getReference("users").child(uId).child("dinner");

        dialog.show();
        dialog2.show();
        dialog3.show();

        //for breakfast
        valueEventListener = phmsRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {

                dataList.clear();
                totalCaloriesMorning = 0;
                //dataList2.clear();
                for(DataSnapshot itemsnapshot : snapshot.getChildren())
                {
                    DataClass dataClass = itemsnapshot.getValue(DataClass.class);
                    dataList.add(dataClass);
                }

                for (DataClass data : dataList)
                {
                    totalCaloriesMorning += Integer.parseInt(data.getCalorieCount()) ;
                }
                TextView textViewMorning = findViewById(R.id.totalBreakfastCalorie);
                textViewMorning.setText("Total Calories for Breakfast: " + totalCaloriesMorning+"kcal");

                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                dialog.dismiss();
            }
        });
        //for lunch
        valueEventListener2 = databaseReferenceLunch.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList2.clear();
                totalCaloriesLunch = 0;
                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {
                    DataClass dataClass2 = itemsnapshot.getValue(DataClass.class);
                    dataList2.add(dataClass2);
                }

                for (DataClass data : dataList2)
                {
                    totalCaloriesLunch += Integer.parseInt(data.getCalorieCount()) ;
                }
                TextView textViewLunch = findViewById(R.id.totalLunchCalorie);
                textViewLunch.setText("Total Calories for Lunch: " + totalCaloriesLunch+" kcal");

                adapterLunch.notifyDataSetChanged();
                dialog2.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog2.dismiss();
            }
        });

        valueEventListener3 = databaseReferenceDinner.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList3.clear();
                totalCaloriesDinner  = 0;
                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {
                    DataClass dataClass3 = itemsnapshot.getValue(DataClass.class);
                    dataList3.add(dataClass3);
                }
                adapterDinner.notifyDataSetChanged();
                dialog3.dismiss();

                for (DataClass data : dataList3)
                {
                    totalCaloriesDinner += Integer.parseInt(data.getCalorieCount()) ;
                }
                TextView textViewDinner = findViewById(R.id.totalDinnerCalorie);
                textViewDinner.setText("Total Calories for dinner: " + totalCaloriesDinner +" kcal");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog3.dismiss();
            }
        });
        valueEventListener4 = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot itemsnapshot : snapshot.getChildren()) {
                    DataClass dataClass4 = itemsnapshot.getValue(DataClass.class);
                    dataList4.add(dataClass4);
                }

                totalCaloriesForTheDay = totalCaloriesMorning + totalCaloriesLunch + totalCaloriesDinner;
                TextView textViewTotalCal = findViewById(R.id.totalCalorie);
                textViewTotalCal.setText("Total Calories you ate: " + totalCaloriesForTheDay +" kcal");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        //going to the present main tab tot he upload tab for breakfast
        fabMorning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(dietTab.this, uploadFoodCalorie.class));

            }
        });
        //   //going to the present main tab tot he upload tab for lunch
        fabLunch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(dietTab.this, uploadFoodCalorieLunch.class));
            }
        });
        //going to the present main tab tot he upload tab for dinner
        fabDinner.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(dietTab.this, uploadFoodCalorieDinner.class));
            }
        });

        buttonToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(dietTab.this, HomeMenu.class));
            }
        });
        buttonToHealthTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(dietTab.this, HealthArticle.class));
            }
        });

    }
}