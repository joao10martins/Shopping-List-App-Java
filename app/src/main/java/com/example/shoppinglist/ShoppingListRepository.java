package com.example.shoppinglist;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ShoppingListRepository {

    private ShoppingListDao mShoppingListDao;
    private LiveData<List<ShoppingListData>> mShoppingList;

    ShoppingListRepository(Application application) {
        ShoppingListRoomDatabase db = ShoppingListRoomDatabase.getDatabase(application);
        mShoppingListDao = db.shoppingListDao();
        mShoppingList = mShoppingListDao.getShoppingList();
    }


    LiveData<List<ShoppingListData>> getShoppingList() {
        return mShoppingList;
    }


    public void insert (ShoppingListData shoppingListData) {
        new insertAsyncTask(mShoppingListDao).execute(shoppingListData);
    }


    private static class insertAsyncTask extends AsyncTask<ShoppingListData, Void, Void> {

        private ShoppingListDao mAsyncTaskDao;

        insertAsyncTask(ShoppingListDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ShoppingListData... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

}
