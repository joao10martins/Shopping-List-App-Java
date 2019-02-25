package com.example.shoppinglist;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ShoppingListDao {

    @Insert
    void insert(ShoppingListData shoppingListData);

    @Query("DELETE FROM shopping_list_table")
    void deleteAll();

    @Query("SELECT * FROM shopping_list_table ORDER BY id ASC")
    LiveData<List<ShoppingListData>> getShoppingList();
}
