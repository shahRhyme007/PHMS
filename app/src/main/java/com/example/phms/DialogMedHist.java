package com.example.phms;

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

public class DialogMedHist extends AppCompatDialogFragment
{
    private EditText editTextMedHist;
    private DialogListenerMedHist listener;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_medhist, null);

        builder.setView(view);
        builder.setTitle("Add Medical History");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                String medHist = editTextMedHist.getText().toString();
                int accepted = listener.appendMedHist(medHist);

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

        editTextMedHist = view.findViewById(R.id.medhistTBA);

        return builder.create();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            listener = (DialogListenerMedHist) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListenerMedHist
    {
        int appendMedHist(String medHist);
    }
}
