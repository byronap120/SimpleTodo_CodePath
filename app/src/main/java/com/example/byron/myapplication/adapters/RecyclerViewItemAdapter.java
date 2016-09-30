package com.example.byron.myapplication.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.byron.myapplication.R;
import com.example.byron.myapplication.activities.MainActivity;
import com.example.byron.myapplication.models.Item;

import java.util.ArrayList;

/**
 * Created by Byron on 9/30/2016.
 */
public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewItemAdapter.MyViewHolder> {

    private ArrayList<Item> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewDescription;
        TextView textViewName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            this.textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
        }
    }

    public RecyclerViewItemAdapter(ArrayList<Item> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);

         view.setOnLongClickListener(MainActivity.myLongOnClickListener);
         view.setOnClickListener(MainActivity.myOnClickListener);


        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewDescription = holder.textViewDescription;
        TextView textViewName = holder.textViewName;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewDescription.setText(dataSet.get(listPosition).getDescription());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public Item getItemAtPosition(int index) {
        return dataSet.get(index);
    }

    public void deleteItem(int index) {
        dataSet.remove(index);
        this.notifyItemRemoved(index);
    }
}