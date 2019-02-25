package com.example.shoppinglist;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

public class DataManager extends AppCompatActivity {
    private static DataManager ourInstance;

    public static DataManager getInstance() {
        if (ourInstance == null){
            ourInstance = new DataManager();
        }
        return ourInstance;
    }

    private DataManager() {
       items = initializeItems();
       database = initializeDatabase();
    }



    public List<ShoppingListData> items;
    public List<ShoppingListData> database;

    private List<ShoppingListData> initializeItems(){

        List<ShoppingListData> item = new ArrayList<>();
        /*ShoppingListData item = new ShoppingListData();
        item.setItem(0, "Pringles", "Source of Fat");*/
        item.add(new ShoppingListData(0, "Pringles", "Source of Fat"));
        item.add(new ShoppingListData(1, "Sweet Potatoes", "Source of Carbs"));
        item.add(new ShoppingListData(2, "Chicken", "Source of Protein"));
        item.add(new ShoppingListData(3, "Turkey", "Source of Protein"));
        item.add(new ShoppingListData(4, "Chocolate", "Source of Sugar"));
        item.add(new ShoppingListData(5, "Canned Tuna", "Source of Protein"));
        item.add(new ShoppingListData(6, "Bread", "Source of Carbs"));
        item.add(new ShoppingListData(7, "Butter", "Source of Fat"));
        item.add(new ShoppingListData(8, "Water", "Calories Free Hydration"));
        item.add(new ShoppingListData(9, "Toilet Paper", "Hygiene"));
        item.add(new ShoppingListData(10, "Coffee", "Source of Energy"));
        item.add(new ShoppingListData(11, "Fish", "Source of Protein"));
        item.add(new ShoppingListData(12, "Ham", "Source of Protein"));
        item.add(new ShoppingListData(13, "Chickpeas & Beans", "Source of Carbs & Fiber"));
        item.add(new ShoppingListData(14, "Olive oil", "Source of Fat"));
        item.add(new ShoppingListData(15, "Bananas", "Source of Protein"));
        item.add(new ShoppingListData(16, "Eggs", "Source of Protein"));
        item.add(new ShoppingListData(17, "Champoo", "Hygiene"));
        item.add(new ShoppingListData(18, "Shower Gel", "Hygiene"));
        item.add(new ShoppingListData(19, "Toothpaste", "Hygiene"));
        item.add(new ShoppingListData(20, "Listerine", "Hygiene"));
        item.add(new ShoppingListData(21, "Deodorant", "Hygiene"));
        item.add(new ShoppingListData(22, "Trash Bags", "Hygiene"));
        item.add(new ShoppingListData(23, "Condoms", "Contraceptive Methods"));
        item.add(new ShoppingListData(24, "White Wine", "Alcohol"));
        item.add(new ShoppingListData(25, "Hangers", "Home Accessories"));



        return item;
    }

    private List<ShoppingListData> initializeDatabase(){

        List<ShoppingListData> databaseList = new ArrayList<>();

        databaseList = getIntent().getParcelableArrayListExtra("saveToDatabase");

        return databaseList;
    }

}
