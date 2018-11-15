package com.ayzn.netlib.retrofit;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.ayzn.netlib.BuildConfig;
import com.ayzn.netlib.retrofit.global.NetGlobal;
import com.ayzn.netlib.retrofit.utils.MD5Util;
import com.ayzn.netlib.retrofit.utils.StechoUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yangboy22 on 2018/1/19.
 */

public class RxJavaRetrofitManager {

    private static final String TAG = RxJavaRetrofitManager.class.getSimpleName();
    private static boolean debug = BuildConfig.DEBUG;
    private static PersistentCookieJar cookieJar;

    private static Context mContext;

    public static void setContent(Context context) {
        mContext = context;
        if (cookieJar == null) {
            cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(context));
        }
    }

    private static Gson gson = new Gson();

    private static Retrofit initRetrofit(Object body, final String token) {
//        Log.w(TAG, "initRetrofit: " + debug);
        setContent(NetGlobal.getApplicationContext());

        if (debug) {
            Log.i(TAG, "请求数据" + gson.toJson(body));
        }
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor();
        LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (StechoUtil.isNeedStetho())
            builder.addNetworkInterceptor(new StethoInterceptor());
        builder.addInterceptor(new RspCheckInterceptor(mContext));
        if (NetGlobal.DEBUG) {
            builder.addInterceptor(LoginInterceptor);
        }
        final long timestamp = System.currentTimeMillis();

        final String signure = MD5Util.md5(gson.toJson(body) + "|" + token + "|" + timestamp);

        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
                        .addHeader("signure_type", "md5")
                        .addHeader("timestamp", "" + timestamp)
                        .addHeader("signure", signure)
                        .addHeader("token", token)
                        .build();
                return chain.proceed(request);
            }
        });
        builder.cookieJar(cookieJar);
        builder.connectTimeout(15, TimeUnit.SECONDS);// 15
        builder.readTimeout(10, TimeUnit.SECONDS);// 10
        builder.writeTimeout(10, TimeUnit.SECONDS);// 10
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        return new Retrofit.Builder()
                .baseUrl(NetGlobal.RX_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build();
    }

    public static <T> T createReq(Object body, String token, Class<T> reqServer) {
        return initRetrofit(body, token).create(reqServer);
    }
}
