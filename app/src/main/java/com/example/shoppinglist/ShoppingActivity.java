package com.example.shoppinglist;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.example.shoppinglist.Constants.POSITION_NOT_SET;

public class ShoppingActivity extends AppCompatActivity {
    private String LOG_TAG = this.getClass().getSimpleName();
    private int itemPosition = POSITION_NOT_SET;


    private RecyclerView mRecyclerView;
    private ShoppingRecyclerAdapter mAdapter;


    /* TODO: onCreate
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.rvShop);
        // Create an adapter and supply the data to be displayed.
        DataManager dm = DataManager.getInstance();
        mAdapter = new ShoppingRecyclerAdapter(this, dm.items);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }



/*    public void addToCart(View view){
        List<ShoppingListData> listItem = new ArrayList<>();
        DataManager dm = DataManager.getInstance();

        view.get


        ShoppingListData item = dm.items.get(itemPosition);
        listItem.add(new ShoppingListData(item.getId(), item.getTitle(), item.getDescription()));


        *//*int itemId = dm.items.get(itemPosition).getId();
        String itemTitle = dm.items.get(itemPosition).getTitle();
        String itemDescription = dm.items.get(itemPosition).getDescription();*//*

        //Intent addItem = new Intent(ShoppingActivity.this, ShoppingListActivity.class);


        Toast toast = Toast.makeText(this, "Product Added to Cart", Toast.LENGTH_LONG);
        toast.show();
        listItem.clear();
    }*/


    private void createNewItem(){
        DataManager dm = DataManager.getInstance();
        dm.items.add(new ShoppingListData());
        itemPosition = dm.items.size()-1; //lastIndex
    }


    // Populating view with items
    private void displayItem(){
        DataManager dm = DataManager.getInstance();
        if (itemPosition > dm.items.size()-1){
            showMessage("Item not found");
            Log.e(LOG_TAG, "Invalid item position" + itemPosition + ", max valid position " + (dm.items.size()-1));
            return;
        }

        Log.i(LOG_TAG, "Displaying item for position" + itemPosition);
        ShoppingListData item = dm.items.get(itemPosition);
        TextView txtItem = (TextView)findViewById(R.id.txtItem);
        txtItem.setText(item.getTitle());
    }

    private void showMessage(String message){
        Snackbar snackbar = Snackbar.make(findViewById(R.id.txtItem), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
