package com.kiranacustomerapp.helper;

import com.kiranacustomerapp.AsyncTasks.GetAllItemNamesAsyncTask;
import com.kiranacustomerapp.AsyncTasks.GetAllUnitsAsyncTask;
import com.kiranacustomerapp.Models.Item;

import java.util.ArrayList;

/**
 * Created by Siddhi on 12/23/2016.
 */
public class FilterString {


    public static String item = "", measuringUnit = "", quantity = "",mItemName;

    public static boolean findOut(String matching,ArrayList<String> mItemUnitsList,ArrayList<String> mItemNamesList) {


        String[] match = matching.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");

        for (int i = 0; i < mItemUnitsList.size(); i++) {

            for (String check : match) {
                String unit = mItemUnitsList.get(i).toLowerCase().trim();

                if (unit != null && !unit.equals("")) {

                    if (check.toLowerCase().trim().contains(unit)) {

                        measuringUnit = mItemUnitsList.get(i).trim();

                        break;
                    }
                }
            }
        }

        for (int i = 0; i < mItemNamesList.size(); i++) {
            for (String check : match) {

                String item1 = mItemNamesList.get(i).toLowerCase();

                if (item1 != null && !item1.equals("")) {
                    {

                        if (check.toLowerCase().contains(item1)) {
                            item = mItemNamesList.get(i).trim();
                            break;
                        }
                    }
                }

            }
        }

        if (matching != null) {
            String[] part = matching.split("(?<=\\D)(?=\\d)|(?<=\\d)(?=\\D)");
            for (int k = 0; k < part.length; k++) {
                try {
                    Integer.parseInt(part[k]);
                    quantity = part[k];
                    break;
                } catch (Exception ex) {
                    continue;
                }
            }
        }

        if(item != null && !item.equals("")) {
            char first = item.charAt(0);

            mItemName = item.replace(item.charAt(0), Character.toUpperCase(first));
        }


        if(measuringUnit.equals("") || item.equals(""))
        {

            return false;

            //showAlert("Unable to figure out what you typed, try search instead?");
        }
        else {
            return true;
        }
    }
}
