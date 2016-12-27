package com.kiranacustomerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kiranacustomerapp.Models.QueryOrderItem;
import com.kiranacustomerapp.helper.Constants;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class ItemsTableHelper  extends SQLiteOpenHelper {


    private static final String ITEMS_TABLE = "itemsTable";
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_QUERY_NAME = "itemName";


    public ItemsTableHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);

        // createTable(db);
        // onCreate(db);
    }

    public void addQueryItem(QueryOrderItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_QUERY_NAME, item.getQuery());

        db.insert(ITEMS_TABLE, null, values);

        db.close();
    }

    public ArrayList<String> getAllItemNames() {
        ArrayList<String> conList = new ArrayList<>();

        String selectQuery = "SELECT itemName FROM " + ITEMS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String unit;

                unit = (cursor.getString(cursor.getColumnIndex("itemName")));

                conList.add(unit);
            } while (cursor.moveToNext());
        }

        return conList;
    }

    public ArrayList<String> getAllItemUnits() {
        ArrayList<String> conList = new ArrayList<String>();

        String selectQuery = "SELECT itemUnit FROM " + ITEMS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String unit;

                unit = (cursor.getString(cursor.getColumnIndex("itemUnit")));

                conList.add(unit);
            } while (cursor.moveToNext());
        }

        return conList;
    }

}
