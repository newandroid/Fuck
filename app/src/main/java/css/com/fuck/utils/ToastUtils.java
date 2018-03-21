package css.com.fuck.utils;

import android.content.Context;
import android.widget.Toast;

import css.com.fuck.app.ApplicationInit;


public class ToastUtils {
	
	private static Toast mToast = null;
	
	private static void initToast() {
		if (mToast == null) {
			synchronized (ToastUtils.class) {
				if (mToast == null) {
					mToast = Toast.makeText(ApplicationInit.getInstance().getApplicationContext(),
									"", Toast.LENGTH_SHORT);
				}
			}
		}
	}
	
	public static void showToast(int resId) {
		initToast();
		showToast(resId, mToast);
	}
	
	public static void showToast(Context context, int resId) {
		initToast();
		showToast(resId, mToast);
	}
	
	public static void showToast(String text) {
		initToast();
		showToast(text, mToast);
	}
	
	public static void showToast(Context context, String text) {
		initToast();
		showToast(text, mToast);
	}
	
	public static void showToast(String text, Toast toast) {
		if (toast != null) {
			toast.setText(text);
			toast.show();
		} else {
			Toast.makeText(ApplicationInit.getInstance().getApplicationContext(),
					text, Toast.LENGTH_SHORT).show();
		}
	}
	
	public static void showToast(int resId, Toast toast) {
		String text = ApplicationInit.getInstance().getApplicationContext()
				.getResources().getString(resId);
		if (toast != null) {
			toast.setText(text);
			toast.show();
		} else {
			Toast.makeText(ApplicationInit.getInstance().getApplicationContext(),
					text, Toast.LENGTH_SHORT).show();
		}
	}
	
}
