package com.example.phms_main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogMedicationAdd extends AppCompatDialogFragment
{
    private Spinner spinnerMedName;
    private Spinner spinnerRoute;
    private Spinner spinnerDosage;
    private Spinner spinnerIntervals;
    private DialogMedicationAdd.DialogListenerMedication listener;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_medication, null);

        builder.setView(view);
        builder.setTitle("Add Medication");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                String medName = spinnerMedName.getSelectedItem().toString();
                String route = spinnerRoute.getSelectedItem().toString();
                String dosage = spinnerDosage.getSelectedItem().toString();
                String intervals = spinnerIntervals.getSelectedItem().toString();

                int accepted = listener.appendMedication(medName, route, dosage, intervals);

                if (accepted == 1)
                {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
                }
                else if (accepted == 0)
                {
                    dialog.dismiss();
                    Toast.makeText(getActivity(), "Not Added", Toast.LENGTH_SHORT).show();
                }
                else if (accepted == -1)
                {
                    Toast.makeText(getActivity(), "Enter All Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNeutralButton("Home", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                // TODO
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {

            }
        });

        spinnerMedName = view.findViewById(R.id.medicationSpinner);
        spinnerRoute = view.findViewById(R.id.routeSpinner);
        spinnerDosage = view.findViewById(R.id.dosageSpinner);
        spinnerIntervals = view.findViewById(R.id.intervalSpinner);

        return builder.create();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            listener = (DialogMedicationAdd.DialogListenerMedication) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListenerMedication
    {
        int appendMedication(String medication, String route, String dosage, String intervals);
    }
}
