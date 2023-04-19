package com.example.phms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter_Med extends RecyclerView.Adapter<RecyclerViewAdapter_Med.MedViewHolder>
{
    Context context;
    ArrayList<ModelMedication> medicationModels;

    public RecyclerViewAdapter_Med(Context context, ArrayList<ModelMedication> medicationModels)
    {
        this.context = context;
        this.medicationModels = medicationModels;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter_Med.MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_medication, parent, false);

        return new RecyclerViewAdapter_Med.MedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter_Med.MedViewHolder holder, int position)
    {
        holder.medTxt.setText(medicationModels.get(position).getMedicationName());

        if (medicationModels.get(position).getControl() == 1)
        {
            holder.controlImg.setImageResource(medicationModels.get(position).getImage());
        }
    }

    @Override
    public int getItemCount()
    {
        return medicationModels.size();
    }

    public static class MedViewHolder extends RecyclerView.ViewHolder
    {
        ImageView controlImg;
        TextView medTxt;

        public MedViewHolder(@NonNull View itemView)
        {
            super(itemView);

            controlImg = itemView.findViewById(R.id.controlImg);
            medTxt = itemView.findViewById(R.id.medicationName);
        }
    }
}
