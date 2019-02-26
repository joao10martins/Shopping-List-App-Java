package com.example.shoppinglist;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class Coverters {
    @TypeConverter
    public static List<ShoppingListData> fromArrayList(ArrayList<ShoppingListData> list){

        list.to
        Gson gson = new Gson();
        List<String> objStrings = new ArrayList<>();
        for(ShoppingListData obj : list){
            objStrings.add(gson.toJson(obj));
        }
        String[] myStringList = objStrings.toArray(new String[objStrings.size()]);
        return myStringList;
    }
}
