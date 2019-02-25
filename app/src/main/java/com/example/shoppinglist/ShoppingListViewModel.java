package com.example.shoppinglist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.ArrayList;

public class ShoppingListViewModel extends AndroidViewModel {

    private ShoppingListRepository mRepository;
    private LiveData<ArrayList<ShoppingListData>> mShoppingList;


    public ShoppingListViewModel (Application application) {
        super(application);
        mRepository = new ShoppingListRepository(application);
        mShoppingList = mRepository.getShoppingList();
    }


    LiveData<ArrayList<ShoppingListData>> getShoppingList() { return mShoppingList; }

    public void insert(ShoppingListData shoppingListData) { mRepository.insert(shoppingListData); }
}
