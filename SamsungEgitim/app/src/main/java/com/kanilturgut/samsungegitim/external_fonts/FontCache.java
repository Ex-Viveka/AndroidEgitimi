package com.kanilturgut.samsungegitim.external_fonts;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

/**
 * Author   : kanilturgut
 * Date     : 16/10/15
 * Time     : 12:07
 */
public class FontCache {

    public static final int REGULAR = 0;
    public static final int BOLD = 1;
    public static final int LIGHT = 2;


    private static final String REGULAR_PATH = "fonts/OpenSans-Regular.ttf";
    private static final String BOLD_PATH = "fonts/OpenSans-Semibold.ttf";
    private static final String LIGHT_PATH = "fonts/OpenSans-Light.ttf";

    // Android tries to load fonts from asset every single setTypeFace method calls.
    // We should cache the typeface, otherwise it may produce memory leaks
    private static Hashtable<String, Typeface> fontCache = new Hashtable<>();

    /**
     * @param fontType bold or regular
     * @param context  context of application
     * @return cached font's typeface
     */
    public static Typeface get(int fontType, Context context) {

        if (fontType == REGULAR) {

            Typeface tf = fontCache.get(REGULAR_PATH);
            if (tf == null) { // if it is not cached, load from asset
                try {
                    tf = Typeface.createFromAsset(context.getAssets(), REGULAR_PATH);
                } catch (Exception e) {
                    return null;
                }
                fontCache.put(REGULAR_PATH, tf);
            }
            return tf;
        } else if (fontType == BOLD) {

            Typeface tf = fontCache.get(BOLD_PATH);
            if (tf == null) { // if it is not cached, load from asset
                try {
                    tf = Typeface.createFromAsset(context.getAssets(), BOLD_PATH);
                } catch (Exception e) {
                    return null;
                }
                fontCache.put(BOLD_PATH, tf);
            }
            return tf;
        } else {

            Typeface tf = fontCache.get(LIGHT_PATH);
            if (tf == null) { // if it is not cached, load from asset
                try {
                    tf = Typeface.createFromAsset(context.getAssets(), LIGHT_PATH);
                } catch (Exception e) {
                    return null;
                }
                fontCache.put(LIGHT_PATH, tf);
            }
            return tf;
        }
    }
}
