package com.kiranacustomerapp.app;


import com.kiranacustomerapp.helper.FontsOverride;

/**
 * Created by Owner on 19-11-2016.
 */
public class Application extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "CenturyGothic.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "CenturyGothic.ttf");
        FontsOverride.setDefaultFont(this, "SERIF", "CenturyGothic.ttf");
        FontsOverride.setDefaultFont(this, "SANS_SERIF", "CenturyGothic.ttf");

    }

}
