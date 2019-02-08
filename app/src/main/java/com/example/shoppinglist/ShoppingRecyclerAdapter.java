package com.example.shoppinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

public class ShoppingRecyclerAdapter extends RecyclerView.Adapter<ShoppingRecyclerAdapter.ShoppingViewHolder> {
    private final List<ShoppingListData> mItemList;
    private LayoutInflater mInflater;


    public ShoppingRecyclerAdapter(Context context,
                           List<ShoppingListData> itemList) {
        mInflater = LayoutInflater.from(context);
        this.mItemList = itemList;
    }


    class ShoppingViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtItemView;
        final ShoppingRecyclerAdapter mAdapter;


        public ShoppingViewHolder(View itemView, ShoppingRecyclerAdapter adapter) {
            super(itemView);
            txtItemView = itemView.findViewById(R.id.txtItem);
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
    public void onBindViewHolder(@NonNull ShoppingViewHolder holder, int position) {
        ShoppingListData currentItem = mItemList.get(position);
        holder.txtItemView.setText(currentItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

}



