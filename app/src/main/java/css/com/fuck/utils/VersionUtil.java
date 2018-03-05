package css.com.fuck.utils;

import android.content.Context;
import android.os.Build;

import css.com.fuck.app.AppManager;


/**
 * 获取版本信息
 * @author LEON
 *
 */
public class VersionUtil {
	/**
	 * 获取软件的版本号
	 */
	public static int getVersionCode(){
		int code=0;		
		try {
			Context context= AppManager.getInstance().getApplicationContext();
			code=context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return code;
	}
	
	/**
	 * 获取软件版本名
	 *
	 */
	public static String getVersionName(){
		String versionName=null;
		try {
			Context context=AppManager.getInstance().getApplicationContext();
			versionName=context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	public static String getDeviceInfo() {
		String sperator = "; ";
		StringBuffer sbLog = new StringBuffer();
		
		sbLog.append("Brand: ");
		sbLog.append(Build.BRAND);
		sbLog.append(sperator);
		sbLog.append("Device: ");
		sbLog.append(Build.DEVICE);
		sbLog.append(sperator);
		sbLog.append("Model: ");
		sbLog.append(Build.MODEL);
		sbLog.append(sperator);
		sbLog.append("Id: ");
		sbLog.append(Build.ID);
		sbLog.append(sperator);
		sbLog.append("Product: ");
		sbLog.append(Build.PRODUCT);
		sbLog.append(sperator);
		sbLog.append("SDK: ");
		sbLog.append(Build.VERSION.SDK);
		sbLog.append(sperator);
		sbLog.append("Release: ");
		sbLog.append(Build.VERSION.RELEASE);
		sbLog.append(sperator);
		sbLog.append("Incremental: ");
		sbLog.append(Build.VERSION.INCREMENTAL);
		sbLog.append(sperator);
		sbLog.append("Version code:");
		sbLog.append(VersionUtil.getVersionCode());
		sbLog.append(sperator);
		sbLog.append("Version name:");
		sbLog.append(VersionUtil.getVersionName());
		
		return sbLog.toString();
	}

}
