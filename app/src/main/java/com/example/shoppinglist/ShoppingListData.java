package com.example.shoppinglist;

public class ShoppingListData {

    private int id;
    private String title;
    private String description;

    public ShoppingListData() {
        this.id = 0;
        this.title="";
        this.description="";
    }

    public ShoppingListData(int id, String title, String description) {
        this.id = id;
        this.title=title;
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    /*public void setItem(int id, String title, String description){
        this.id = id;
        this.title=title;
        this.description=description;
    }


    public int getItemId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }*/


}
