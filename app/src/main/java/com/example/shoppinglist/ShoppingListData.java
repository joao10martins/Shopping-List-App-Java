package com.example.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class ShoppingListData implements Parcelable {

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


    @Override
    public int describeContents() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int arg1) {
        // TODO Auto-generated method stub
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
    }

    public ShoppingListData(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
    }

    public static final Parcelable.Creator<ShoppingListData> CREATOR = new Parcelable.Creator<ShoppingListData>() {
        public ShoppingListData createFromParcel(Parcel in) {
            return new ShoppingListData(in);
        }

        public ShoppingListData[] newArray(int size) {
            return new ShoppingListData[size];
        }
    };


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
