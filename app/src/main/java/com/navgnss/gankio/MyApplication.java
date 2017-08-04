package com.navgnss.gankio;

import android.app.Application;
import android.content.Context;

/**
 * Created by ZhuJinWei on 2017/8/3.
 */

public class MyApplication extends Application {

    public static String APP_NAME;
    public static boolean isDebug;
    public static Context mContext;

    @Override
    public void onCreate() {

        APP_NAME="干货集中营";
        isDebug=true;
        mContext=this;

        super.onCreate();
    }
}
