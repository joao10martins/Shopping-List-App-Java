package com.example.shoppinglist;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ShoppingListViewModel extends AndroidViewModel {

    private ShoppingListRepository mRepository;
    private LiveData<List<ShoppingListData>> mShoppingList;


    public ShoppingListViewModel (Application application) {
        super(application);
        mRepository = new ShoppingListRepository(application);
        mShoppingList = mRepository.getShoppingList();
    }


    LiveData<List<ShoppingListData>> getShoppingList() { return mShoppingList; }

    public void insert(ShoppingListData shoppingListData) { mRepository.insert(shoppingListData); }
}
