package com.example.shoppinglist;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;


@Database(entities = {ShoppingListData.class}, version = 1, exportSchema = false)
public abstract class ShoppingListRoomDatabase extends RoomDatabase {




    //DataManager dm = new DataManager().getInstance();
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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ShoppingListDao mDao;

        DataManager dm = DataManager.getInstance();
        //String[] words = {"dolphin", "crocodile", "cobra"};

        PopulateDbAsync(ShoppingListRoomDatabase db) {
            mDao = db.shoppingListDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();

            for (int i = 0; i <= dm.database.size() - 1; i++) {
                ShoppingListData item = new ShoppingListData(
                        dm.database.get(i).getId(),
                        dm.database.get(i).getTitle(),
                        dm.database.get(i).getDescription()
                                        );
                mDao.insert(item);
            }
            return null;
        }

        /*public ArrayList<Parcelable> getIntent() {
            return getIntent().getParcelableArrayListExtra("saveToDatabase");
        }*/


    }
}


