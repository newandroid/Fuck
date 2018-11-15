package com.ayzn.netlib.retrofit.global;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.ayzn.netlib.retrofit.utils.StechoUtil;
import com.facebook.stetho.Stetho;

/**
 * Created by ayzn on 2018/3/26.
 */

public class NetModuleInit {
    public static String BRO_ACTION_TOKEN_INVALID = "bro_action_token_invalid";
    static OnTokenInvalidListener onTokenInvalidListener;

    public static void init(Context context, OnTokenInvalidListener onTokenInvalidListener) {
        context = context.getApplicationContext();
        NetGlobal.setApplicationContext(context);
        NetModuleInit.onTokenInvalidListener = onTokenInvalidListener;
        context.registerReceiver(new TokenInvalidBroadcast(), new IntentFilter(BRO_ACTION_TOKEN_INVALID));
        if (StechoUtil.isNeedStetho())
            Stetho.initializeWithDefaults(context);
    }

    public interface OnTokenInvalidListener {
        public void onTokenInvalid();
    }

    private static class TokenInvalidBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (BRO_ACTION_TOKEN_INVALID.equals(intent.getAction())) {
                if (onTokenInvalidListener != null) onTokenInvalidListener.onTokenInvalid();

            }

        }
    }
}
