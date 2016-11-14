package helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Owner on 08-11-2016.
 */
public class SessionData {
    Context context;
    SharedPreferences pref;

    static final String PREF_NAME = "appdata";

    public SessionData(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_APPEND);

    }

    public boolean add(String name, String value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(name, value);
        return editor.commit();
    }


    public boolean add(String name, int value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(name, value);

        return editor.commit();
    }
    public boolean add(String name, long value) {
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(name, value);

        return editor.commit();
    }

    public String getString(String name, String defaultValue) {
        return pref.getString(name, defaultValue);
    }



    public long getLong(String name, long defultValue) {
        return Long.parseLong(getString(name,""+defultValue));
    }
    public long getLongEx(String name, long defultValue) {
        return pref.getLong(name,defultValue);
    }
    public boolean delete(String name) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(name);

        return editor.commit();
    }
    public void clearSession() {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    public void remove(String name) {
        SharedPreferences.Editor editor = pref.edit();
        editor.remove("name");
        editor.commit();

    }
}
