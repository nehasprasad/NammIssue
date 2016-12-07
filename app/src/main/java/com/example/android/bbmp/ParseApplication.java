package com.example.android.bbmp;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);      // Add your initialization code here
        Parse.initialize(this, "yzBeFBUXCKVgkzZ0VakJrIc2OKo6L63xaz4rd3Z4", "zZx3KiMnjEaPaFFSbST0oeOjHW04oiScAL9ouHb4");


    }

}