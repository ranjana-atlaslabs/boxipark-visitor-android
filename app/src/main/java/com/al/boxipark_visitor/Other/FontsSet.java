package com.al.boxipark_visitor.Other;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.Locale;

public class FontsSet {

    public Typeface Light(Context context)
    {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),  "font/Gotham-Light.otf");
        return typeface;
    }
    public Typeface Bold(Context context)
    {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),  "font/GothamBold.ttf");
        return typeface;
    }
    public Typeface Book(Context context)
    {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),  "font/GothamBook.ttf");
        return typeface;
    }
    public Typeface Medium(Context context)
    {
        Typeface typeface = Typeface.createFromAsset(context.getAssets(),  "font/GothamMedium.ttf");
        return typeface;
    }
}
