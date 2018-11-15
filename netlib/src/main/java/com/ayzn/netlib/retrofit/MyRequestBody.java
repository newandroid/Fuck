package com.ayzn.netlib.retrofit;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/1/10 0010.
 */

public class MyRequestBody {

    private static MediaType mediaType = MediaType.parse("application/json; charset=utf-8");

    public static RequestBody create(Object data){
        Gson gson = new Gson();
        return RequestBody.create(mediaType, gson.toJson(data));
    }

    public static RequestBody create(String data){
        return RequestBody.create(mediaType, data);
    }
}
