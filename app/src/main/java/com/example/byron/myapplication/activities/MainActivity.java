package com.example.byron.myapplication.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.activeandroid.ActiveAndroid;
import com.example.byron.myapplication.R;
import com.example.byron.myapplication.adapters.RecyclerViewItemAdapter;
import com.example.byron.myapplication.fragments.CustomDialogFragment;
import com.example.byron.myapplication.interfaces.ItemDialogListener;
import com.example.byron.myapplication.models.Item;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ItemDialogListener {

    private static RecyclerViewItemAdapter adapter;
    private static RecyclerView recyclerView;
    public static View.OnLongClickListener myLongOnClickListener;
    public static View.OnClickListener myOnClickListener;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActiveAndroid.initialize(this);

        myOnClickListener = new MyOnClickListener(this);
        myLongOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new RecyclerViewItemAdapter(getSavedItems());
        recyclerView.setAdapter(adapter);

    }

    private void showEditDialog(Item item, String title) {
        FragmentManager fm = getSupportFragmentManager();
        CustomDialogFragment alertDialog = CustomDialogFragment.newInstance(title, item);
        alertDialog.show(fm, "fragment_alert");
    }

    private ArrayList<Item> getSavedItems() {
        return new ArrayList<Item>(Item.getAll());
    }

    public void onAddItem(View view) {
        showEditDialog(null, getResources().getString(R.string.dialog_create_title));
    }

    @Override
    public void onFinishEditDialog() {
        adapter = new RecyclerViewItemAdapter(getSavedItems());
        recyclerView.setAdapter(adapter);
    }

    //RecyclerAdapter call this class when a card is clicked or LongClicked
    private class MyOnClickListener implements View.OnLongClickListener, View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public boolean onLongClick(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            Item item = adapter.getItemAtPosition(selectedItemPosition);
            item.delete();
            adapter.deleteItem(selectedItemPosition);
            return false;
        }

        @Override
        public void onClick(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            Item item = adapter.getItemAtPosition(selectedItemPosition);
            showEditDialog(item, context.getResources().getString(R.string.dialog_edit_title));
        }
    }

}


