package com.kiranacustomerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import com.kiranacustomerapp.Models.Item;
import com.kiranacustomerapp.Models.Unit;
import com.kiranacustomerapp.helper.Constants;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/23/2016.
 */
public class ItemNamesTableHelper extends SQLiteOpenHelper{

        private static final String ITEM_NAMES_TABLE = "itemNamesTable";
        private static final String KEY_ITEM_ID = "id";
        private static final String KEY_ITEM_NAME = "itemName";


        public ItemNamesTableHelper(Context context) {
            super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }


        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + ITEM_NAMES_TABLE);

            // createTable(db);
            // onCreate(db);
        }

        public void addItem(Item item) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            String selectQuery = "SELECT itemName FROM " + ITEM_NAMES_TABLE;

            values.put(KEY_ITEM_ID, item.getId());
            values.put(KEY_ITEM_NAME, item.getItemName());

            db.insert(ITEM_NAMES_TABLE, null, values);

            db.close();
        }

        public ArrayList<String> getAllItems() {
            ArrayList<String> conList = new ArrayList<String>();

            String selectQuery = "SELECT itemName FROM " + ITEM_NAMES_TABLE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {

                    String itemName;

                    itemName = (cursor.getString(cursor.getColumnIndex("itemName")));

                    conList.add(itemName);
                } while (cursor.moveToNext());
            }

            return conList;
        }
}
