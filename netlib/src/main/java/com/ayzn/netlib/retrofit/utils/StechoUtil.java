package com.ayzn.netlib.retrofit.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.ayzn.netlib.retrofit.global.NetGlobal;

/**
 * Created by simon on 2018/3/1 0001.
 */

public class StechoUtil {
    public static boolean isNeedStetho() {
       Context context=  NetGlobal.getApplicationContext();
        if (context == null) return false;
        PackageManager packageManager = context.getPackageManager();
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = packageManager.getApplicationInfo(context
                    .getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo != null && applicationInfo.metaData != null
                    && applicationInfo.metaData.containsKey("stetho_debug")) {
                return true;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return false;
    }
}
