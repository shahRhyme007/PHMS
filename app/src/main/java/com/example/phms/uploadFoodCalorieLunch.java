package com.example.phms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class uploadFoodCalorieLunch extends AppCompatActivity
{
    Button saveButtonLunch;
    EditText foodLunch, calorieLunch;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food_calorie_lunch);

        calorieLunch = findViewById(R.id.uploadLunchCalorie);
        foodLunch = findViewById(R.id.uploadLunchFoodName);
        saveButtonLunch = findViewById(R.id.addLunchIcon);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        saveButtonLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                ///uploadData("PHMS");
                uploadData("lunch");
            }
        });
    }

    public void uploadData(String mealType)
    {
        String strfood = foodLunch.getText().toString();
        String strCalorie = calorieLunch.getText().toString();
        String uId = firebaseAuth.getCurrentUser().getUid();  // new code

        DataClass dataClass= new DataClass(strfood, strCalorie);

        String uniqueId = FirebaseDatabase.getInstance().getReference().push().getKey();

        FirebaseDatabase.getInstance().getReference("users").child(uId).child(mealType).child(uniqueId)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(uploadFoodCalorieLunch.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(uploadFoodCalorieLunch.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    }
                });
    }
}