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

public class DialogSurgicalHist extends AppCompatDialogFragment
{
    private EditText editTextSurgicalHist;
    private DialogListenerSurgicalHist listener;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_surgicalhist, null);

        builder.setView(view);
        builder.setTitle("Add Surgical History");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                String surgicalHist = editTextSurgicalHist.getText().toString();
                int accepted = listener.appendSurgicalHist(surgicalHist);

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

        editTextSurgicalHist = view.findViewById(R.id.surgicalHistTBA);

        return builder.create();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            listener = (DialogListenerSurgicalHist) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListenerSurgicalHist
    {
        int appendSurgicalHist(String surgicalHist);
    }
}
