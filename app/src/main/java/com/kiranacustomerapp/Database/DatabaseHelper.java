package com.kiranacustomerapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kiranacustomerapp.helper.Constants;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String ITEMS_TABLE = "itemsTable";
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_ITEM_NAME = "itemName";
    private static final String KEY_ITEM_UNIT = "itemUnit";
    private static final String KEY_ITEM_QTY = "itemQty";


    private Context context;

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
        this.context = context;
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        createTable(db);

    }

    public void createDatabase(){
        context.deleteDatabase(Constants.DATABASE_NAME + ".db");
        SQLiteDatabase db = this.getReadableDatabase();
    }


    public void createTable(SQLiteDatabase db){
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + ITEMS_TABLE + "("
                + KEY_ITEM_ID + " TEXT,"
                + KEY_ITEM_NAME + " TEXT,"
                + KEY_ITEM_QTY + " TEXT,"
                + KEY_ITEM_UNIT + " TEXT "
                + ")";

        db.execSQL(CREATE_ITEMS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ITEMS_TABLE);

        context.deleteDatabase(Constants.DATABASE_NAME + ".db");

        createTable(db);
        // Create tables again
        //onCreate(db);
    }

}
