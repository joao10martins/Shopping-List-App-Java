package com.example.shoppinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ShoppingRecyclerAdapter extends RecyclerView.Adapter<ShoppingRecyclerAdapter.ShoppingViewHolder> {
    private final List<ShoppingListData> mItemList;
    private LayoutInflater mInflater;
    private Context context;


    public ShoppingRecyclerAdapter(Context context,
                           List<ShoppingListData> itemList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mItemList = itemList;
    }


    class ShoppingViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtItemView;
        public final ImageButton btnAddToCart;
        final ShoppingRecyclerAdapter mAdapter;


        public ShoppingViewHolder(View itemView, ShoppingRecyclerAdapter adapter) {
            super(itemView);
            txtItemView = itemView.findViewById(R.id.txtItem);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
            this.mAdapter = adapter;
        }
    }


    @NonNull
    @Override
    public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_shopping_add, parent, false);
        return new ShoppingViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingViewHolder holder, final int position) {
        ShoppingListData currentItem = mItemList.get(position);
        holder.txtItemView.setText(currentItem.getTitle());
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ShoppingListData> listItem = new ArrayList<>();
                DataManager dm = DataManager.getInstance();


                ShoppingListData item = dm.items.get(position);
                listItem.add(new ShoppingListData(item.getId(), item.getTitle(), item.getDescription()));

                Toast toast = Toast.makeText(context, "Product Added to Cart "+position, Toast.LENGTH_LONG);
                toast.show();
                listItem.clear();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


   /* public void addToCart(View view){
        List<ShoppingListData> listItem = new ArrayList<>();
        DataManager dm = DataManager.getInstance();


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

}



