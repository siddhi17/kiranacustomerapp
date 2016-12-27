package com.kiranacustomerapp.Models;

/**
 * Created by Siddhi on 12/22/2016.
 */
public class Item {

    String id;
    String itemName;

    public Item(){}

    public Item(String id,String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }
}
