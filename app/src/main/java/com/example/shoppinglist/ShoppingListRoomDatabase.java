package com.example.shoppinglist;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;


@Database(entities = {ShoppingListData.class}, version = 1, exportSchema = false)
public abstract class ShoppingListRoomDatabase extends RoomDatabase {

    public abstract ShoppingListDao shoppingListDao();
    private static ShoppingListRoomDatabase INSTANCE;


    public static ShoppingListRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ShoppingListRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ShoppingListRoomDatabase.class, "shopping_list_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
