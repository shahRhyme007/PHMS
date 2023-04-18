package com.example.phms_main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Med_RecyclerViewAdapter extends RecyclerView.Adapter<Med_RecyclerViewAdapter.MedViewHolder>
{
    Context context;
    ArrayList<MedicationModel> medicationModels;

    public Med_RecyclerViewAdapter(Context context, ArrayList<MedicationModel> medicationModels)
    {
        this.context = context;
        this.medicationModels = medicationModels;
    }

    @NonNull
    @Override
    public Med_RecyclerViewAdapter.MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_medication, parent, false);

        return new Med_RecyclerViewAdapter.MedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Med_RecyclerViewAdapter.MedViewHolder holder, int position)
    {
        holder.medTxt.setText(medicationModels.get(position).getMedicationName());
        holder.controlImg.setImageResource(medicationModels.get(position).getImage());
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
