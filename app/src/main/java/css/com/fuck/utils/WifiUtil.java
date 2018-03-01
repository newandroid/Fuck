package css.com.fuck.utils;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.util.List;

/**
 * Created by simon on 2018/3/1 0001.
 */

public class WifiUtil {
    public static int getWifiFreq(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if (Build.VERSION.SDK_INT > 21)
            return wifiInfo.getFrequency();
        String tempSsidString = wifiInfo.getSSID();
        if (tempSsidString != null && tempSsidString.length() > 2) {
            String wifiSsid = tempSsidString.substring(1, tempSsidString.length() - 1);
            List<ScanResult> scanResults = wifiManager.getScanResults();
            for (ScanResult scanResult : scanResults) {
                if (scanResult.SSID.equals(wifiSsid)) {
                    return scanResult.frequency;
                }
            }
        }
        return 0;
    }

    public static boolean is5GWifi(Context context) {
        return getWifiFreq(context) > 5000;
    }


}
