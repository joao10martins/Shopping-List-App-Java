package com.example.shoppinglist;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Parcelable;
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
    public List<ShoppingListData> cartItemList = new ArrayList<>();
    public List<ShoppingListData> existingCartItemList = new ArrayList<>();

    private ShoppingListViewModel mShoppingListViewModel;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);

        mShoppingListViewModel = ViewModelProviders.of(this).get(ShoppingListViewModel.class);
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.rvShop);
        // Create an adapter and supply the data to be displayed.
        final DataManager dm = DataManager.getInstance();
        mAdapter = new ShoppingRecyclerAdapter(this, dm.items);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        // If there is already an existing list, pick it up to continue
        // adding items/products.
        existingCartItemList = getIntent().getParcelableArrayListExtra("savedCartItemList");


        // Creating Shopping List(Cart) by adding each item onClick.
        // Custom listener is used to retrieve the position of each item.
        mAdapter.setShoppingListListener(new ShoppingRecyclerAdapter.ShoppingListListener() {
            @Override
            public void onClickAddToCart(int position) {
                final ShoppingListData currentItem = dm.items.get(position);
                if (existingCartItemList != null){
                    existingCartItemList.add(new ShoppingListData(currentItem.getId(), currentItem.getTitle(), currentItem.getDescription()));


                    Toast toast = Toast.makeText(ShoppingActivity.this, "Product Added to Cart", Toast.LENGTH_SHORT);
                    toast.show();

                    // Notify the adapter, that the data has changed so it can
                    // update the RecyclerView to display the data.
                    //mShoppingListViewModel.insert(existingCartItemList);
                    mShoppingListViewModel.insert(currentItem);
                    mAdapter.notifyDataSetChanged();


                    System.out.println("List:");
                    for (int i = 0; i < existingCartItemList.size(); i++) {
                        System.out.println(existingCartItemList.get(i).getTitle());
                    }
                }
                else {
                    cartItemList.add(new ShoppingListData(currentItem.getId(), currentItem.getTitle(), currentItem.getDescription()));


                    Toast toast = Toast.makeText(ShoppingActivity.this, "Product Added to Cart", Toast.LENGTH_SHORT);
                    toast.show();

                    // Notify the adapter, that the data has changed so it can
                    // update the RecyclerView to display the data.
                    mShoppingListViewModel.insert(currentItem);
                    mAdapter.notifyDataSetChanged();


                    System.out.println("List:");
                    for (int i = 0; i < cartItemList.size(); i++) {
                        System.out.println(cartItemList.get(i).getTitle());
                    }

                }
            }
        });


    }

    /*@Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("savedList", cartItemList);
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (existingCartItemList != null){
            // Put data in SharedPrefs database
            //TinyDB tinydb = new TinyDB(this);
            //tinydb.putListObject("currentList", existingCartItemList);

            // Sending the updated List of items/products in the database.
            Intent save = new Intent(ShoppingActivity.this, ShoppingListActivity.class);
            save.putExtra("savedList", (Parcelable) existingCartItemList);
            startActivity(save);

            // Sending the updated List of items/products to another Activity/Class.
            /*Intent intent = new Intent(ShoppingActivity.this, ShoppingListActivity.class);
            intent.putExtra("savedList", existingCartItemList);
            startActivity(intent);*/
        }
        else {
            // Put data in SharedPrefs database
            //TinyDB tinydb = new TinyDB(this);
            //tinydb.putListObject("currentList", cartItemList);

            // Sending the updated List of items/products in the database.
            Intent save = new Intent(ShoppingActivity.this, ShoppingListActivity.class);
            save.putExtra("savedList", (Parcelable) cartItemList);
            startActivity(save);

            // Sending the updated List of items/products to another Activity/Class.
            /*Intent intent = new Intent(ShoppingActivity.this, ShoppingListActivity.class);
            intent.putExtra("savedList", cartItemList);
            startActivity(intent);*/
        }

    }


   /* @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("key", cartItemList);
    }*/



    /*private void createNewItem() {
        DataManager dm = DataManager.getInstance();
        dm.items.add(new ShoppingListData());
        itemPosition = dm.items.size() - 1; //lastIndex
    }*/


    // Populating view with items
    private void displayItem() {
        DataManager dm = DataManager.getInstance();
        if (itemPosition > dm.items.size() - 1) {
            showMessage("Item not found");
            Log.e(LOG_TAG, "Invalid item position" + itemPosition + ", max valid position " + (dm.items.size() - 1));
            return;
        }

        Log.i(LOG_TAG, "Displaying item for position" + itemPosition);
        ShoppingListData item = dm.items.get(itemPosition);
        TextView txtItem = findViewById(R.id.txtItem);
        txtItem.setText(item.getTitle());
    }

    private void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.txtItem), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
