package com.kiranacustomerapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kiranacustomerapp.helper.Constants;

/**
 * Created by Siddhi on 12/20/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String ITEM_NAMES_TABLE = "itemNamesTable";
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_ITEM_NAME = "itemName";


    private static final String UNITS_TABLE = "unitTable";
    private static final String KEY_UNIT_ID = "id";
    private static final String KEY_UNIT_NAME = "unitName";

    private static final String ITEMS_TABLE = "itemsTable";
    private static final String KEY_QUERY_ITEM_ID = "id";
    private static final String KEY_QUERY_NAME = "itemName";

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
        String CREATE_ITEM_NAMES_TABLE = "CREATE TABLE " + ITEM_NAMES_TABLE + "("
                + KEY_ITEM_ID + " TEXT,"
                + KEY_ITEM_NAME + " TEXT "
                + ")";

        db.execSQL(CREATE_ITEM_NAMES_TABLE);

        String CREATE_UNITS_TABLE = "CREATE TABLE " + UNITS_TABLE + "("
                + KEY_UNIT_ID + " TEXT,"
                + KEY_UNIT_NAME + " TEXT "
                + ")";

        db.execSQL(CREATE_UNITS_TABLE);


        String CREATE_ITEMS_TABLE = "CREATE TABLE " + ITEMS_TABLE + "("
                + KEY_QUERY_ITEM_ID + " TEXT,"
                + KEY_QUERY_NAME + " TEXT "
                + ")";

        db.execSQL(CREATE_ITEMS_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + ITEM_NAMES_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE);

        context.deleteDatabase(Constants.DATABASE_NAME + ".db");

        createTable(db);
        // Create tables again
        //onCreate(db);
    }

}
