package com.kanilturgut.externalfontdenemesi;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Author   : kanilturgut
 * Date     : 16/10/15
 * Time     : 17:52
 */
public class FontCache {

    public static final int REGULAR = 0;
    public static final int BOLD = 1;
    public static final int LIGHT = 2;

    private static final String REGULAR_PATH = "fonts/OpenSans-Regular.ttf";
    private static final String BOLD_PATH = "fonts/OpenSans-Semibold.ttf";
    private static final String LIGHT_PATH = "fonts/OpenSans-Light.ttf";

    private static Hashtable<String, Typeface> fontCacheTable = new Hashtable<>();

    public static Typeface get(int fontType, Context context) {

        if (fontType == REGULAR) {
            Typeface regularTypeface = fontCacheTable.get(REGULAR_PATH);
            if (regularTypeface == null) {
                try {
                    regularTypeface = Typeface.createFromAsset(context.getAssets(), REGULAR_PATH);
                    fontCacheTable.put(REGULAR_PATH, regularTypeface);
                } catch (Exception e) {
                    return null;
                }
            }

            return regularTypeface;
        } else if (fontType == BOLD) {
            Typeface boldTypeface = fontCacheTable.get(BOLD_PATH);
            if (boldTypeface == null) {
                try {
                    boldTypeface = Typeface.createFromAsset(context.getAssets(), BOLD_PATH);
                    fontCacheTable.put(BOLD_PATH, boldTypeface);
                } catch (Exception e) {
                    return null;
                }
            }

            return boldTypeface;
        } else {
            Typeface lightTypeface = fontCacheTable.get(LIGHT_PATH);
            if (lightTypeface == null) {
                try {
                    lightTypeface = Typeface.createFromAsset(context.getAssets(), LIGHT_PATH);
                    fontCacheTable.put(LIGHT_PATH, lightTypeface);
                } catch (Exception e) {
                    return null;
                }
            }

            return lightTypeface;
        }
    }
}
