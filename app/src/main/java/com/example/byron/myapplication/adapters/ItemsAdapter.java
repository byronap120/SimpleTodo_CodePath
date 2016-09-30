package com.example.byron.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.byron.myapplication.R;
import com.example.byron.myapplication.models.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron on 9/27/2016.
 */
public class ItemsAdapter extends ArrayAdapter<Item> {

    public ItemsAdapter(Context context, ArrayList<Item> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Item item = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_detail, parent, false);
        }

        TextView descriptionTextView = (TextView) convertView.findViewById(R.id.item_description);
        descriptionTextView.setText(item.getDescription());

        return convertView;
    }
}
