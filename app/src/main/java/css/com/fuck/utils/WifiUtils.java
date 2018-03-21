package css.com.fuck.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.provider.Settings.Secure;
import android.util.Log;


import org.apache.http.conn.util.InetAddressUtils;

import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

import css.com.fuck.app.ApplicationInit;


public class WifiUtils {

	public static String getWifiSSID(Context context) {
		String ssid = null;
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Service.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		ssid = removeSSIDQuotes(wifiInfo.getSSID());
		return ssid;
	}
	
	public static String removeSSIDQuotes(String connectedSSID)
	{
		int currentVersion= Build.VERSION.SDK_INT;

		if (currentVersion >= 17)
		{
			if (connectedSSID.startsWith("\"") && connectedSSID.endsWith("\""))
			{
				connectedSSID = connectedSSID.substring(1, connectedSSID.length()-1);
			}
		}
		return connectedSSID;
	}
	
	public static boolean isWifiConnected(Context context)
	{
		NetworkInfo mWifi = getWifiNetworkInfo(context);
		Boolean isWifi = mWifi.isConnected();
		String ssid = WifiUtils.getWifiSSID(context);
		return isWifi && ssid != null && ssid.trim().length()>0;
	}

	public static void openWifiSettingActivity(Context context) {
		if (Build.VERSION.SDK_INT > 10) {
			// 3.0以上打开设置界面，也可以直接用ACTION_WIRELESS_SETTINGS打开到wifi界面
			context.startActivity(new Intent(
					Settings.ACTION_SETTINGS));
		} else {
			context.startActivity(new Intent(
					Settings.ACTION_WIRELESS_SETTINGS));
		}
	}
	
	public static String getMacAddress() {
		return getMacAddress(ApplicationInit.getInstance());
	}

	public static String getMacAddress(Context context) {
		if(Build.VERSION.SDK_INT >=23){
			return  getMac();
		}else {
			String android_id = Settings.System.getString(context.getContentResolver(),
					Secure.ANDROID_ID);
			String mac = "def-" + android_id;
			try {
				WifiManager wifiMgr = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
				WifiInfo info = (null == wifiMgr ? null : wifiMgr.getConnectionInfo());
				if (null != info) {
					String addr = info.getMacAddress();
					if (!StringUtils.isEmpty(addr)) {
						mac = addr;
						mac = mac.replaceAll(":", "");
					}
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
			if (mac.length() > 12) {
				mac = mac.substring(0, 12);
			} else {
				mac = mac + "000000000000";
				mac = mac.substring(0, 12);
			}
			if (mac.contains(":")){
				mac = mac.replaceAll(":","");
				mac = mac.toUpperCase();
			}
			return mac;
		}
	}

	/**
	 * 获取手机的MAC地址
	 * @return
	 */
	public static String getMac(){
		String str="";
		String macSerial="";
		try {
			Process pp = Runtime.getRuntime().exec(
					"cat /sys/class/net/wlan0/address ");
			InputStreamReader ir = new InputStreamReader(pp.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);

			for (; null != str;) {
				str = input.readLine();
				if (str != null) {
					macSerial = str.trim();// 去空格
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (macSerial == null || "".equals(macSerial)) {
			try {
				return loadFileAsString("/sys/class/net/eth0/address")
						.toUpperCase().substring(0, 17);
			} catch (Exception e) {
				e.printStackTrace();

			}

		}
		return macSerial;
	}
	public static String loadFileAsString(String fileName) throws Exception {
		FileReader reader = new FileReader(fileName);
		String text = loadReaderAsString(reader);
		reader.close();
		return text;
	}
	public static String loadReaderAsString(Reader reader) throws Exception {
		StringBuilder builder = new StringBuilder();
		char[] buffer = new char[4096];
		int readLength = reader.read(buffer);
		while (readLength >= 0) {
			builder.append(buffer, 0, readLength);
			readLength = reader.read(buffer);
		}
		return builder.toString();
	}
	public static String getWifiConnectedBssid(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Service.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String bssid = null;
		if (wifiInfo != null && isWifiNetworkConnected(context)) {
			bssid = wifiInfo.getBSSID();
		}
		return bssid;
	}
	
	private static boolean isWifiNetworkConnected(Context context) {
		NetworkInfo wiFiNetworkInfo = getWifiNetworkInfo(context);
		boolean isWifiConnected = false;
		if (wiFiNetworkInfo != null) {
			isWifiConnected = wiFiNetworkInfo.isConnected();
		}
		return isWifiConnected;
	}
	
	private static NetworkInfo getWifiNetworkInfo(Context context) {
		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wiFiNetworkInfo = mConnectivityManager
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return wiFiNetworkInfo;
	}

	private static String byte2hex(byte[] b) {
		StringBuffer hs = new StringBuffer(b.length);
		String stmp = "";
		int len = b.length;
		for (int n = 0; n < len; n++) {
			stmp = Integer.toHexString(b[n] & 0xFF);
			if (stmp.length() == 1) {
				hs = hs.append("0").append(stmp);
			} else {
				hs = hs.append(stmp);
			}
		}
		return String.valueOf(hs);
	}

	private static String getLocalIpAddress() {
		try {
			String ipv4;
			List<NetworkInterface> nilist = Collections.list(NetworkInterface
					.getNetworkInterfaces());
			for (NetworkInterface ni : nilist) {
				List<InetAddress> ialist = Collections.list(ni
						.getInetAddresses());
				for (InetAddress address : ialist) {
					if (!address.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(ipv4 = address
									.getHostAddress())) {
						return ipv4;
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("getLocalIpAddress", ex.toString());
		}
		return null;
	}
	
	public static String getCurrentIpAddressConnected(Context context)
	{
		WifiManager wifiManager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ipval=	wifiInfo.getIpAddress();
		String ipString = String.format("%d.%d.%d.%d", (ipval & 0xff),(ipval >> 8 & 0xff),(ipval >> 16 & 0xff),	(ipval >> 24 & 0xff));
		return ipString.toString();
	}
	
	public static boolean isOnline(Context context) {
		try {
			ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (cm != null && cm.getActiveNetworkInfo() != null
					&& cm.getActiveNetworkInfo().isAvailable()
					&& cm.getActiveNetworkInfo().isConnected()) {
				return true;
			}
		} catch (Throwable e) {
			return false;
		}

		return false;
	}

}
