package com.kiranacustomerapp.Activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.kiranacustomerapp.AsyncTasks.AddItemAsyncTask;
import com.kiranacustomerapp.AsyncTasks.AddUnitAsyncTask;
import com.kiranacustomerapp.Database.DatabaseHelper;
import com.kiranacustomerapp.Models.Item;
import com.kiranacustomerapp.Models.Unit;
import com.kiranacustomerapp.helper.SessionData;

import java.util.ArrayList;


public class StartupActivity extends AppCompatActivity {


    private String  sessionUserId;
    private SessionData sessionData;
    private Boolean addItem = false,addUnit=false;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sessionData = new SessionData(StartupActivity.this);
        sessionUserId = sessionData.getString("user_id","-1");

        DatabaseHelper db = new DatabaseHelper(StartupActivity.this);
        db.createDatabase();

        SharedPreferences sharedPreferences = getSharedPreferences("checkData", Context.MODE_APPEND);
        editor = sharedPreferences.edit();

        addItem = sharedPreferences.getBoolean("availableItems",false);
        addUnit = sharedPreferences.getBoolean("availableUnits",false);

        if(!addItem) {
            addItemNames();
        }
        if(!addUnit) {
            addUnits();
        }
        //if user logs in first time

        if (sessionUserId.equals("-1")) {

            Intent i = (new Intent(StartupActivity.this, MainActivity.class));
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(i);
            finish();

        } else {

            int status = sessionData.getInt("status",0);

            if(status == 1)
            {
                Intent i = (new Intent(StartupActivity.this,HomeActivity.class));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }
            else {

                Intent i = (new Intent(StartupActivity.this, OtpConfirmation.class));
                i.putExtra("email_id",sessionData.getString("email_id",""));
                i.putExtra("phone_no",sessionData.getString("phone_no",""));
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
                finish();
            }
        }

    }


    public void addItemNames() {

        ArrayList<Item> itemNames = new ArrayList<>();

        Item item = new Item("1", "Potatoes");
        itemNames.add(item);
        item = new Item("2", "Potato");
        itemNames.add(item);
        item = new Item("3", "Milk");
        itemNames.add(item);
        item = new Item("4", "Dryfruits");
        itemNames.add(item);
        item = new Item("5", "Coconut");
        itemNames.add(item);
        item = new Item("6", "Wheat flour");
        itemNames.add(item);
        item = new Item("7", "Maza");
        itemNames.add(item);
        item = new Item("8", "Eggs");
        itemNames.add(item);
        item = new Item("9", "Chocolate");
        itemNames.add(item);
        item = new Item("10", "Chocolates");
        itemNames.add(item);
        item = new Item("11", "Rice");
        itemNames.add(item);
        item = new Item("12", "Cold drink");
        itemNames.add(item);
        item = new Item("13", "Catchup");
        itemNames.add(item);
        item = new Item("14", "oil");
        itemNames.add(item);
        item = new Item("15", "Maggi");
        itemNames.add(item);
        item = new Item("16", "Biscuits");
        itemNames.add(item);
        item = new Item("17", "Biscuit");
        itemNames.add(item);

        for (Item item1 : itemNames) {
            new AddItemAsyncTask(StartupActivity.this).execute(item1.getItemName());
        }
        addItem = true;
        editor.putBoolean("availableItems", addItem);
        editor.commit();
    }


    public void addUnits()
    {

        ArrayList<Unit> units = new ArrayList<>();

        Unit unit = new Unit("1","Kg");
        units.add(unit);
        unit = new Unit("2","Kilograms");
        units.add(unit);
        unit = new Unit("3","Grams");
        units.add(unit);
        unit = new Unit("4","Liter");
        units.add(unit);
        unit = new Unit("5","Liters");
        units.add(unit);
        unit = new Unit("6","Packet");
        units.add(unit);
        unit = new Unit("7","Packets");
        units.add(unit);
        unit = new Unit("8","Units");
        units.add(unit);
        unit = new Unit("9","Pieces");
        units.add(unit);
        unit = new Unit("10","Gms");
        units.add(unit);
        unit = new Unit("11","Half kg");
        units.add(unit);
        unit = new Unit("12","Kilogms");
        units.add(unit);
        unit = new Unit("13","Piece");
        units.add(unit);
        unit = new Unit("14","500gms");
        units.add(unit);
        unit = new Unit("15","500grams");
        units.add(unit);
        unit = new Unit("16","100grams");
        units.add(unit);

        for (Unit unit1 : units)
        {
            new AddUnitAsyncTask(StartupActivity.this).execute(unit1.getUnit());
        }
        addUnit = true;
        editor.putBoolean("availableUnits",addUnit);
        editor.commit();
    }

}
