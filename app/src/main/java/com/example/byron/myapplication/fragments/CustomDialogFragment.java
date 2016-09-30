package com.example.byron.myapplication.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.byron.myapplication.R;
import com.example.byron.myapplication.interfaces.ItemDialogListener;
import com.example.byron.myapplication.models.Item;

/**
 * Created by Byron on 9/29/2016.
 */
public class CustomDialogFragment extends DialogFragment {

    private static final String TITLE = "title";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    private static final String ID = "id";

    EditText editTextName;
    EditText editTextDescription;

    public CustomDialogFragment() {
        // Empty constructor required for DialogFragment
    }

    public static CustomDialogFragment newInstance(String title, Item item) {
        CustomDialogFragment frag = new CustomDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        if (item != null) {
            args.putString(DESCRIPTION, item.getDescription());
            args.putLong(ID, item.getId());
            args.putString(NAME, item.getName());
        }
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(TITLE);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        //set custom view
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.dialog_fragment, null);
        alertDialogBuilder.setView(dialogView);
        setupDialogData(dialogView);

        editTextDescription = (EditText) dialogView.findViewById(R.id.editTextItemDescription);
        editTextName = (EditText) dialogView.findViewById(R.id.editTextItemName);

        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Long itemId = getArguments().getLong(ID, -1);
                String name = editTextName.getText().toString();
                String description = editTextDescription.getText().toString();

                if (itemId != -1) {
                    Item item = Item.load(Item.class, itemId);
                    item.description = description;
                    item.save();
                } else {
                    Item item = new Item(name, description);
                    item.save();
                }

                ItemDialogListener listener = (ItemDialogListener) getActivity();
                listener.onFinishEditDialog();
            }
        });
        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        editTextName.requestFocus();

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return alertDialog;
    }

    private void setupDialogData(View view) {
        String itemName = getArguments().getString(NAME);
        String itemDescription = getArguments().getString(DESCRIPTION);
        EditText editTextName = (EditText) view.findViewById(R.id.editTextItemName);
        EditText editTextDescription = (EditText) view.findViewById(R.id.editTextItemDescription);
        editTextDescription.setText(itemDescription);
        editTextName.setText(itemName);
    }
}