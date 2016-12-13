package com.kiranacustomerapp.helper;

import android.content.Context;

import com.kiranacustomerapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Siddhi on 12/12/2016.
 */
public class ReadTextFiles {

    public static List<String> readItemNamesFile(Context context) {
        String sText = null;
        List<String> stringList;
        try{

            InputStream is = context.getResources().openRawResource(R.raw.item_names);
            //Use one of the above as per your file existing folder

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            sText = new String(buffer, "UTF-8");

            String[] sTextArray = sText.replace("\"", "").split(",");
            stringList = new ArrayList<String>(Arrays.asList(sTextArray));
            System.out.print(stringList);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return stringList;

    }

    public static List<String> readUnitsFile(Context context) {
        String sText = null;
        List<String> stringList;
        try{

            InputStream is = context.getResources().openRawResource(R.raw.units);
            //Use one of the above as per your file existing folder

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            sText = new String(buffer, "UTF-8");

            String[] sTextArray = sText.replace("\"", "").split(",");
            stringList = new ArrayList<String>(Arrays.asList(sTextArray));
            System.out.print(stringList);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return stringList;

    }
}
