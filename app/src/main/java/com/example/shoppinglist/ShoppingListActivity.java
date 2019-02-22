package com.example.shoppinglist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import static com.example.shoppinglist.Constants.SHARED_PREFS_FILENAME;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ShoppingListRecyclerAdapter mAdapter;

    private SharedPreferences mPreferences;



    public ArrayList<ShoppingListData> cartItemList = new ArrayList<ShoppingListData>();
    public ArrayList<ShoppingListData> savedCartItemList = new ArrayList<ShoppingListData>();

    // SharedPreferences Array for the current list of items.
    public ArrayList<ShoppingListData> sharedPrefsList = new ArrayList<ShoppingListData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TinyDB tinydb = new TinyDB(this);
        TinyDB tinydb = new TinyDB(ShoppingListActivity.this);
        sharedPrefsList = tinydb.getListObject("currentList");
        if (sharedPrefsList != null)
            cartItemList = sharedPrefsList;
        cartItemList = getIntent().getParcelableArrayListExtra("savedList");
        // Saving new list to a variable
        savedCartItemList = cartItemList;
        if (cartItemList != null) {
            //tinydb.putListObject("currentList", cartItemList);
            // Get a handle to the RecyclerView.
            mRecyclerView = findViewById(R.id.rvCartList);
            // Create an adapter and supply the data to be displayed.
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
                    shop.putExtra("test", savedCartItemList);
                    startActivity(shop);
                } else {
                    Intent shop = new Intent(ShoppingListActivity.this, ShoppingActivity.class);
                    startActivity(shop);
                }
            }
        });


    }


    @Override
    protected void onPause() {
        super.onPause();
        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        //SharedPreferences mPreferences = getSharedPreferences(SHARED_PREFS_FILENAME, MODE_PRIVATE);

        // Put data in database
        if (cartItemList != null){
            TinyDB tinydb = new TinyDB(ShoppingListActivity.this);
            tinydb.putListObject("currentList", cartItemList);
        }




        // Commit the edits!
        //editor.commit();
    }



}
