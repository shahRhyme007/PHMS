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

public class DialogSocialHist extends AppCompatDialogFragment
{
    private EditText editTextSocialHist;
    private DialogListenerSocialHist listener;

    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_dialog_socialhist, null);

        builder.setView(view);
        builder.setTitle("Add Social History");
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int id)
            {
                String socialhist = editTextSocialHist.getText().toString();
                int accepted = listener.appendSocialHist(socialhist);

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

        editTextSocialHist = view.findViewById(R.id.socialhistTBA);

        return builder.create();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        try
        {
            listener = (DialogListenerSocialHist) context;
        }
        catch (ClassCastException e)
        {
            throw new ClassCastException(context.toString()
                    + " must implement NoticeDialogListener");
        }
    }

    public interface DialogListenerSocialHist
    {
        int appendSocialHist(String socialhist);
    }
}
