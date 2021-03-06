package com.kiranacustomerapp.Models;

/**
 * Created by Siddhi on 12/11/2016.
 */
public class OrderItem {

    String item_name,item_quantity,item_unit,item_order;
    int item_id;

    public OrderItem(){}

    public OrderItem(String itemName,String quantity,String unit)
    {
        this.item_name = itemName;
        this.item_quantity = quantity;
        this.item_unit = unit;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItemName() {
        return item_name;
    }

    public String getQuantity() {
        return item_quantity;
    }

    public String getUnit() {
        return item_unit;
    }

    public void setUnit(String unit) {
        this.item_unit = unit;
    }

    public void setItemName(String itemName) {
        this.item_name = itemName;
    }

    public void setQuantity(String quantity) {
        this.item_quantity = quantity;
    }
}
