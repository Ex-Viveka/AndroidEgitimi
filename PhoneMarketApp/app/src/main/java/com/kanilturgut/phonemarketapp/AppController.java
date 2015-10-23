package com.kanilturgut.phonemarketapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Author   : kanilturgut
 * Date     : 23/10/15
 * Time     : 19:35
 */
public class AppController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(this, "vLncIdpyK4pJp6Tv0bVDb9cDqB83QhJBXXoeuPVU", "M0mtSAPr959me3MWPjwnAC8V4dYPBYlqCxaBgNX5");
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}
