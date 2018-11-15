package com.ayzn.netlib.retrofit.global;

import android.content.Context;

import com.ayzn.netlib.BuildConfig;

/**
 * Created by ayzn on 2018/3/26.
 */

public class NetGlobal {
    public static final String BASE_URL = "http://120.78.174.200/IOTServer/";

    /**
     * 是否显示日志 , 上线版本屏蔽
     */
    public static boolean DEBUG = BuildConfig.DEBUG;

    public static String RX_BASE_URL = "http://192.168.1.232:80/";
    public static final boolean IS_TEST_SERVER = BuildConfig.DEBUG;

//    static {
//        if (IS_TEST_SERVER) {
//            RX_BASE_URL = "http://test.tvc88.cn/";
//        } else {
//            RX_BASE_URL = "http://www.tvc88.cn/";
//        }
//    }

    /**
     * 安装包下载地址
     */
    public static String DOWN_URL = "http://www.tvc88.cn/resources/down/app/ayzn-app.apk";

    private static Context applicationContext;

    public static Context getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(Context applicationContex) {
        applicationContext = applicationContex;
    }

}
