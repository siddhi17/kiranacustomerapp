package com.kiranacustomerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kiranacustomerapp.Models.OrderItem;
import com.kiranacustomerapp.helper.Constants;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class ItemsTableHelper  extends SQLiteOpenHelper {


    private static final String ITEMS_TABLE = "itemsTable";
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_ITEM_NAME = "itemName";
    private static final String KEY_ITEM_UNIT = "itemUnit";
    private static final String KEY_ITEM_QTY = "itemQty";

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

    public void addItem(OrderItem item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_ITEM_NAME, item.getItemName());
        values.put(KEY_ITEM_QTY, item.getQuantity());
        values.put(KEY_ITEM_UNIT, item.getUnit());

        db.insert(ITEMS_TABLE, null, values);

        db.close();
    }

   /* public User getUser(String id) {

        SQLiteDatabase db = this.getReadableDatabase();

        User user = new User();


        Cursor cursor = db.query(USER_TABLE, new String[]{KEY_USER_ID,
                        KEY_USER_NAME, KEY_PASSWORD,KEY_MOBILE_NO,KEY_EMAIL_ID, KEY_PROFILE_IMAGE, KEY_FULL_NAME,
                        KEY_DEVICE_ID,KEY_JOB_TITLE,KEY_HOME_ADDRESS,KEY_WORK_ADDRESS,KEY_WORK_PHONE,KEY_COMPANY}, KEY_USER_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        //cursor.moveToFirst();
        if( cursor != null && cursor.moveToFirst() ) {

            user = new User(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5), cursor.getString(6),cursor.getString(7),
                    cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11),cursor.getString(12));
        }

        return user;
    }*/

    public ArrayList<OrderItem> getAllItemNames() {
        ArrayList<OrderItem> conList = new ArrayList<OrderItem>();

        String selectQuery = "SELECT itemName FROM " + ITEMS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                OrderItem orderItem = new OrderItem();

                orderItem.setItem_id(0);
                orderItem.setItemName(cursor.getString(1));

                conList.add(orderItem);
            } while (cursor.moveToNext());
        }

        return conList;
    }

    public ArrayList<OrderItem> getAllItemUnits() {
        ArrayList<OrderItem> conList = new ArrayList<OrderItem>();

        String selectQuery = "SELECT itemUnit FROM " + ITEMS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                OrderItem orderItem = new OrderItem();

                orderItem.setItem_id(0);
                orderItem.setUnit(cursor.getString(1));

                conList.add(orderItem);
            } while (cursor.moveToNext());
        }

        return conList;
    }


    public ArrayList<OrderItem> getAllItems() {
        ArrayList<OrderItem> conList = new ArrayList<OrderItem>();

        String selectQuery = "SELECT * FROM " + ITEMS_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                OrderItem orderItem = new OrderItem();

                orderItem.setItem_id(0);
                orderItem.setItemName(cursor.getString(1));
                orderItem.setUnit(cursor.getString(2));
                orderItem.setQuantity(cursor.getString(3));

                conList.add(orderItem);
            } while (cursor.moveToNext());
        }

        return conList;
    }



   /* public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TASK_TABLE, KEY_TASK_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
        db.close();
    }*/

}
