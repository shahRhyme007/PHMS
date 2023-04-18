package com.example.phms_main;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogFamHist extends AppCompatDialogFragment
{
    private EditText editTextFamHist;
    private DialogListenerFamHist listener;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_famhist, null);

        builder.setView(view);
        builder.setTitle("Add Family History");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                String allergy = editTextFamHist.getText().toString();
                int accepted = listener.appendFamHist(allergy);

                if (accepted == 1)
                    Toast.makeText(getActivity(), "Added", Toast.LENGTH_SHORT).show();
                else if (accepted == 0)
                    Toast.makeText(getActivity(), "Not Added", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                // Does Nothing
            }
        });

        editTextFamHist = view.findViewById(R.id.famhistTBA);

        return builder.create();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            listener = (DialogListenerFamHist) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListenerFamHist
    {
        int appendFamHist(String famhist);
    }
}
