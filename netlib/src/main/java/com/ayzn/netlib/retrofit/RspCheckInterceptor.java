package com.ayzn.netlib.retrofit;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import com.ayzn.netlib.retrofit.global.NetGlobal;
import com.ayzn.netlib.retrofit.global.NetModuleInit;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class RspCheckInterceptor implements Interceptor {
    private static final String TAG = RspCheckInterceptor.class.getSimpleName();
    private Context context;

    public RspCheckInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
            try {
                ResponseBody rspBody = response.body();
                JSONObject jsonObject = new JSONObject(InterceptorUtils.getRspData(rspBody));
                int status = jsonObject.getInt("status");
                String  code = jsonObject.getString("code");
                if(NetGlobal.DEBUG){
                    Log.e(TAG, "status= " + status + "");
                    Log.e(TAG, "code= " + code + "");
                    Log.i(TAG, jsonObject.toString());
                }
                //此处对返回码进行统一处理
                if (status == -2003){
                    //令牌无效或已过期,请重新登录
                    context.sendBroadcast(new Intent(NetModuleInit.BRO_ACTION_TOKEN_INVALID));
                }
            } catch (JSONException e) {
                e.printStackTrace();
                throw new IOException("parase data error");
            }catch (Exception e){
                e.printStackTrace();
                if (e instanceof IOException){
                    throw (IOException)e;
                }
            }

        return response;
    }



}
