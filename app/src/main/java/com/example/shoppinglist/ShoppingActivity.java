package com.example.shoppinglist;

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
    public ArrayList<ShoppingListData> cartItemList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);


        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.rvShop);
        // Create an adapter and supply the data to be displayed.
        final DataManager dm = DataManager.getInstance();
        mAdapter = new ShoppingRecyclerAdapter(this, dm.items);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Creating Shopping List(Cart) by adding each item onClick.
        // Custom listener is used to retrieve the position of each item.
        mAdapter.setShoppingListListener(new ShoppingRecyclerAdapter.ShoppingListListener() {
            @Override
            public void onClickAddToCart(int position) {
                final ShoppingListData currentItem = dm.items.get(position);

                cartItemList.add(new ShoppingListData(currentItem.getId(), currentItem.getTitle(), currentItem.getDescription()));

                Toast toast = Toast.makeText(ShoppingActivity.this, "Product Added to Cart", Toast.LENGTH_LONG);
                toast.show();

                // Notify the adapter, that the data has changed so it can
                // update the RecyclerView to display the data.
                mAdapter.notifyDataSetChanged();


                System.out.println("List:");
                for (int i = 0; i < cartItemList.size(); i++) {
                    System.out.println(cartItemList.get(i).getTitle());
                }

                // Sending the updated List of items/products to another Activity/Class.
                //Bundle b = new Bundle();
                //b.putStringArrayList("key", cartItemList);
                //b.putParcelableArrayList("key", cartItemList);//putStringArray(key, new String[]{value1, value2});





            }
        });

        Intent intent = new Intent(ShoppingActivity.this, ShoppingListActivity.class);
        intent.putExtra("key", cartItemList);
        startActivity(intent);




    }



   /* public void onClickAddToCart(View view, int position){

        mAdapter.


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
        TextView txtItem = (TextView) findViewById(R.id.txtItem);
        txtItem.setText(item.getTitle());
    }

    private void showMessage(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(R.id.txtItem), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
