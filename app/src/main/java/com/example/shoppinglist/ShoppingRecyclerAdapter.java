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
    private int itemPosition;


    private ShoppingListListener shoppingListListener;
    //public final List<ShoppingListData> cartItemList = new ArrayList<>(); // Nova lista para guardar os produtos adicionados à Lista


    public ShoppingRecyclerAdapter(Context context,
                           List<ShoppingListData> itemList) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.mItemList = itemList; // Shopping Activity
        this.shoppingListListener = null;
        this.itemPosition = 0;

    }

    public void setShoppingListListener(ShoppingListListener shoppingListListener){
        this.shoppingListListener = shoppingListListener;
    }


    class ShoppingViewHolder extends RecyclerView.ViewHolder {
        public final TextView txtItemView;  // Shopping Activity
        public final TextView txtItemDescription;
        public final ImageButton btnAddToCart;  // Shopping Activity
        public int itemPosition;

        final ShoppingRecyclerAdapter mAdapter;


        public ShoppingViewHolder(View itemView, ShoppingRecyclerAdapter adapter) {
            super(itemView);
            txtItemView = itemView.findViewById(R.id.txtItem); // Shopping Activity
            txtItemDescription = itemView.findViewById(R.id.txtNote);
            itemPosition = 0;
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart); // Shopping Activity
            /*btnAddToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = v.getId();

                    if (id == R.id.btnAddToCart){
                        if (shoppingListListener != null){
                            shoppingListListener.onClickAddToCart();
                        }
                    }
                }
            });*/


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

        //final DataManager dm = DataManager.getInstance();
        //final ShoppingListData item = dm.items.get(position);

        final ShoppingListData currentItem = mItemList.get(position);
        holder.txtItemView.setText(currentItem.getTitle());
        //holder.txtItemDescription.setText(currentItem.getDescription());
        //holder.itemPosition = position;
        holder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();

                if (id == R.id.btnAddToCart){
                    if (shoppingListListener != null){
                        shoppingListListener.onClickAddToCart(position);
                    }
                }
            }

                //listItem.clear();
        });
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }


    public interface ShoppingListListener {
        void onClickAddToCart(int position);
    }

}



