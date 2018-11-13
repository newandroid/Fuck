package css.com.fuck.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class Dp2Px {
	
	public static int dipToPixel(Context context, int value) {
		int pixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, 
				context.getResources().getDisplayMetrics());
		return pixel;
	}
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 *
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 *
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 *
	 * @param pxValue
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 *
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 按分辨率的高进行等比例缩放，标准尺寸是1280*720，像素密度为2
	 * @param context
	 * @param dipValue dp数值
	 * @return
	 */
	public static int dip2pxByHeight(Context context, float dipValue){
		float realheight = context.getResources().getDisplayMetrics().heightPixels;
		int originalPixels = (int) (dipValue * 2);
		return (int)(originalPixels * realheight / 1280);
	}

	/**
	 * 按分辨率的宽进行等比例缩放，标准尺寸是1280*720，像素密度为2
	 * @param context
	 * @param dipValue dp数值
	 * @return
	 */
	public static int dip2pxByWidth(Context context, float dipValue){
		float realWidth = context.getResources().getDisplayMetrics().widthPixels;
		int originalPixels = (int) (dipValue * 2 + 0.5f);
		return (int)(originalPixels * realWidth / 720);
	}

	/**
	 * 按分辨率的宽高进行等比例缩放，标准尺寸是1280*720，像素密度为2，选其中缩放后更小的值返回
	 * @param context
	 * @param dipValue dp数值
	 * @return
	 */
	public static int dip2pxByWidthAndHeight(Context context, float dipValue){
		int x1 = dip2pxByHeight(context, dipValue);
		int x2 = dip2pxByWidth(context, dipValue);
		return x1 < x2 ? x1 : x2;
	}

	/***
	 * 隐藏键盘
	 * @param context
	 * @param view
	 */
	public static void hideKeybroad(Context context, View view){
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
}
