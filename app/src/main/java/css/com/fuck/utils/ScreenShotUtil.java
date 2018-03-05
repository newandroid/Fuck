package css.com.fuck.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by maijunhong on 2015/11/5.
 */
public class ScreenShotUtil {
    private static final String TAG = ScreenShotUtil.class.getSimpleName();

    private static Bitmap takeScreenShot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap b1 = view.getDrawingCache();

        // get statusbar height
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;

        // get height and width of screen
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        int height = activity.getWindowManager().getDefaultDisplay()
                .getHeight();
        // remove titlebar
        Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
                - statusBarHeight);
        view.destroyDrawingCache();
        return b;
    }

    private static void savePic(Bitmap b, File filePath) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(filePath);
            if (null != fos) {
                b.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            // e.printStackTrace();
        } catch (IOException e) {
            // e.printStackTrace();
        }
    }

    public static File getShootFile(Activity activity) {
        File file = new File(activity.getExternalCacheDir(), "shoot.jpeg");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        savePic(takeScreenShot(activity), file);
        return file;
    }

    public static File getShareImage(Activity activity) {
        File shareFile = getShootFile(activity);
        return shareFile;
    }
}
