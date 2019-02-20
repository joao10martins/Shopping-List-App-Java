package com.example.shoppinglist;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ShoppingListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ShoppingListRecyclerAdapter mAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //if (savedInstanceState != null){
        // TODO
        ArrayList<ShoppingListData> cartItemList = getIntent().getParcelableArrayListExtra("key");
            if (cartItemList != null){
                // Get a handle to the RecyclerView.
                mRecyclerView = findViewById(R.id.rvCartList);
                // Create an adapter and supply the data to be displayed.
                mAdapter = new ShoppingListRecyclerAdapter(this, cartItemList);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            }
        //}

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shop = new Intent(ShoppingListActivity.this, ShoppingActivity.class);
                startActivity(shop);
            }
        });



    }

    /*@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("key", cartItemList);
    }*/


}
