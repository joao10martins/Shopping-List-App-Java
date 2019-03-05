package com.example.shoppinglist;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import static com.example.shoppinglist.Constants.SHARED_PREFS_FILENAME;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ShoppingListRecyclerAdapter mAdapter;


    public List<ShoppingListData> cartItemList = new ArrayList<ShoppingListData>();
    public List<ShoppingListData> savedCartItemList = new ArrayList<>();
    //public List<ShoppingListData> sharedPrefsList = new ArrayList<>();
    private ShoppingListViewModel mShoppingListViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mShoppingListViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);


        //TinyDB tinydb = new TinyDB(this);
        //TinyDB tinydb = new TinyDB(this);
        // SharedPreferences Array for the current list of items.

        //sharedPrefsList = tinydb.getListObject("currentList");
        //if (sharedPrefsList != null)
            //cartItemList = sharedPrefsList;
        cartItemList = getIntent().getParcelableArrayListExtra("savedList");

        //DataManager dm = DataManager.getInstance();
        //if (dm.database != null)
            //cartItemList = dm.database;

        // Saving new list to a variable
        savedCartItemList = cartItemList;
        if (savedCartItemList != null)
            writeToDatabase(savedCartItemList);
        if (cartItemList != null) {
            //tinydb.putListObject("currentList", cartItemList);
            // Get a handle to the RecyclerView.
            mRecyclerView = findViewById(R.id.rvCartList);
            // Create an adapter and supply the data to be displayed.
            if (mShoppingListViewModel != null){
                mShoppingListViewModel.getShoppingList().observe(this, new Observer<List<ShoppingListData>>() {
                    @Override
                    public void onChanged(@Nullable final List<ShoppingListData> items) {
                        // Update the cached copy of the words in the adapter.
                        mAdapter.setItems(items);
                    }
                });
            }
            else
                mAdapter = new ShoppingListRecyclerAdapter(this, cartItemList);
            // Connect the adapter with the RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
            // Give the RecyclerView a default layout manager.
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        }


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedCartItemList != null) {
                    Intent shop = new Intent(ShoppingListActivity.this, ShoppingActivity.class);
                    shop.putExtra("savedCartItemList", (Parcelable) savedCartItemList);
                    startActivity(shop);
                } else {
                    Intent shop = new Intent(ShoppingListActivity.this, ShoppingActivity.class);
                    startActivity(shop);
                }
            }
        });


    }

    public void writeToDatabase(List<ShoppingListData> savedCartItemList){
            Intent saveToDB = new Intent(ShoppingListActivity.this, DataManager.class);
            saveToDB.putExtra("saveToDatabase", (Parcelable) savedCartItemList);
            startActivity(saveToDB);
    }

}
