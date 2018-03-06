package css.com.fuck.utils;

public class StringUtils {
	
	public static boolean isEmpty(String str) {
        if (str == null || str.trim().length() == 0)
            return true;
        else
            return false;
    }
	
}
