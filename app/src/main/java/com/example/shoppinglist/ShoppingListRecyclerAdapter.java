package com.example.shoppinglist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class ShoppingListRecyclerAdapter extends RecyclerView.Adapter<com.example.shoppinglist.ShoppingListRecyclerAdapter.ShoppingListViewHolder> {

        private final List<ShoppingListData> mCartItemList;
        private LayoutInflater mInflater;
        private Context context;



        //private List<ShoppingListData> cartItemList = new ShoppingRecyclerAdapter().getShoppingList();
        //public final List<ShoppingListData> cartItemList = new ArrayList<>(); // Nova lista para guardar os produtos adicionados à Lista


        public ShoppingListRecyclerAdapter(Context context,
                                       List<ShoppingListData> itemCartList) {
            this.context = context;
            mInflater = LayoutInflater.from(context);
            this.mCartItemList = itemCartList; // Shopping Activity
        }


        class ShoppingListViewHolder extends RecyclerView.ViewHolder {
            public final TextView txtCartItemTitle;  // Shopping List Activity
            public final TextView txtCartItemDescription; // Shopping List Activity
            final com.example.shoppinglist.ShoppingListRecyclerAdapter mAdapter;


            public ShoppingListViewHolder(View itemView, com.example.shoppinglist.ShoppingListRecyclerAdapter adapter) {
                super(itemView);
                txtCartItemTitle = itemView.findViewById(R.id.txtItemTitle); // Shopping List Activity
                txtCartItemDescription = itemView.findViewById(R.id.txtNote); // Shopping List Activity
                this.mAdapter = adapter;
            }
        }


        @NonNull
        @Override
        public com.example.shoppinglist.ShoppingListRecyclerAdapter.ShoppingListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mItemView = mInflater.inflate(R.layout.item_shopping_list, parent, false);
            return new com.example.shoppinglist.ShoppingListRecyclerAdapter.ShoppingListViewHolder(mItemView, this);
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.shoppinglist.ShoppingListRecyclerAdapter.ShoppingListViewHolder holder, final int position) {

            //final DataManager dm = DataManager.getInstance();
            //final ShoppingListData item = dm.items.get(position);

            final ShoppingListData currentItem = mCartItemList.get(position);
            holder.txtCartItemTitle.setText(currentItem.getTitle());
            holder.txtCartItemDescription.setText(currentItem.getDescription());

        }

        @Override
        public int getItemCount() {
            return mCartItemList.size();
        }

}


