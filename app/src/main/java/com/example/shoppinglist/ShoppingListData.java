package com.example.shoppinglist;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;

public class ShoppingListData implements Parcelable {

    private int id;
    private String title;
    private String description;

    /*public ShoppingListData() {
        this.id = 0;
        this.title = "";
        this.description = "";
    }*/

    public ShoppingListData(int id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    protected ShoppingListData(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
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
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
    }

    public static final Creator<ShoppingListData> CREATOR = new Creator<ShoppingListData>() {
        @Override
        public ShoppingListData createFromParcel(Parcel in) {
            return new ShoppingListData(in);
        }

        @Override
        public ShoppingListData[] newArray(int size) {
            return new ShoppingListData[size];
        }
    };


}
