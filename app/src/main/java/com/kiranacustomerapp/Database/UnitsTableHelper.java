package com.kiranacustomerapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kiranacustomerapp.Models.OrderItem;
import com.kiranacustomerapp.Models.Unit;
import com.kiranacustomerapp.helper.Constants;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/22/2016.
 */
public class UnitsTableHelper  extends SQLiteOpenHelper {

        private static final String UNITS_TABLE = "unitTable";
        private static final String KEY_UNIT_ID = "id";
        private static final String KEY_UNIT_NAME = "unitName";


        public UnitsTableHelper(Context context) {
            super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        }


        // Upgrading database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + UNITS_TABLE);

            // createTable(db);
            // onCreate(db);
        }

        public void addUnits(Unit item) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put(KEY_UNIT_ID, item.getId());
            values.put(KEY_UNIT_NAME, item.getUnit());

            db.insert(UNITS_TABLE, null, values);

            db.close();
        }

        public ArrayList<String> getAllUnits() {
            ArrayList<String> conList = new ArrayList<String>();

            String selectQuery = "SELECT unitName FROM " + UNITS_TABLE;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()) {
                do {

                    String unit;

                    unit = (cursor.getString(cursor.getColumnIndex("unitName")));

                    conList.add(unit);
                } while (cursor.moveToNext());
            }

            return conList;
        }

}
