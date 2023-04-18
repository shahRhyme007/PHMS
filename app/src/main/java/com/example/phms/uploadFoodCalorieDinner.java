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

public class uploadFoodCalorieDinner extends AppCompatActivity
{
    Button saveButtonDinner;
    EditText foodDinner, calorieDinner;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_food_calorie_dinner);

        calorieDinner = findViewById(R.id.uploadDinnerCalorie);
        foodDinner = findViewById(R.id.uploadDinnerFoodName);
        saveButtonDinner = findViewById(R.id.addDinnerIcon);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        saveButtonDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                uploadData("dinner");
            }
        });
    }

    public void uploadData(String mealType)
    {
        String strfood = foodDinner.getText().toString();
        String strCalorie = calorieDinner.getText().toString();
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
                            Toast.makeText(uploadFoodCalorieDinner.this, "Saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(uploadFoodCalorieDinner.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}