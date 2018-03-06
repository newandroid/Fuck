package css.com.fuck.utils;

import android.content.Context;
import android.util.TypedValue;

public class Dp2Px {
	
	public static int dipToPixel(Context context, int value) {
		int pixel = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, 
				context.getResources().getDisplayMetrics());
		return pixel;
	} 
	
}
