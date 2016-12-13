package com.kiranacustomerapp.Models;

/**
 * Created by Siddhi on 12/12/2016.
 */
public class Keywords {

    String keywordName,itemName;
    int id;

    public Keywords(String keywordName,String itemName)
    {
        this.keywordName = keywordName;
        this.itemName = itemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getKeywordName() {
        return keywordName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setKeywordName(String keywordName) {
        this.keywordName = keywordName;
    }
}
