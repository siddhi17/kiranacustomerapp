package com.kiranacustomerapp.Models;

/**
 * Created by Siddhi on 12/22/2016.
 */
public class Unit {

    String id;
    String unit;

    public Unit(){}

    public Unit(String id,String unit)
    {
        this.id = id;
        this.unit = unit;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getId() {
        return id;
    }

    public String getUnit() {
        return unit;
    }
}
