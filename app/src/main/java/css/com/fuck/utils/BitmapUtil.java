package css.com.fuck.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.ExifInterface;
import android.util.TypedValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtil {

    private static int MIN_WIDTH = 100;

    /**
     * 读取图片选择的角度
     *
     * @param path
     * @return
     */
    public static int getBitmapDegree(String path) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(path);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 图片旋转
     *
     * @param bitmap
     * @param degree
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Bitmap returnBm = null;

        if (bitmap == null) {
            return null;
        }

        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            returnBm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (OutOfMemoryError e) {
        }
        if (returnBm == null) {
            returnBm = bitmap;
        }
        if (bitmap != returnBm) {
            bitmap.recycle();
        }
        return returnBm;
    }

    public static Bitmap decode(String path, int width, int height) {
        Bitmap bmp = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            if (width > 0 && height > 0) {
                options.inJustDecodeBounds = true;
                bmp = BitmapFactory.decodeFile(path, options);
                int w = options.outWidth;
                int h = options.outHeight;
                int inSampleSize = 1;
                if (w > width && h > height) {
                    int wRatio = w / width;
                    int hRatio = h / height;
                    inSampleSize = Math.max(wRatio, hRatio);
                    options.inSampleSize = inSampleSize;
                }
            }
            options.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(path, options);
            int degree = BitmapUtil.getBitmapDegree(path);
            if (degree != 0) {
                bmp = BitmapUtil.rotateBitmap(bmp, degree);
            }
        } catch (Exception e) {
        } catch (OutOfMemoryError e) {
        }
        return bmp;
    }

    /**
     * 获得圆角图片
     *
     * @param bitmap  bitmap对象
     * @param roundPx 圆角大小
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(int width, int height, Bitmap bitmap, float roundPx) {
        Bitmap output = Bitmap.createBitmap(width,
                height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        final Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final Rect drc = new Rect(0, 0, width, height);
        final RectF rectF = new RectF(0, 0, width, height);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, drc, paint);
        return output;
    }

    /**
     * 将bitmap对象保存在sd卡上
     * @param bitmap
     * @param dirFile
     * @param fileName
     */
    public static void saveBitmap(Bitmap bitmap, File dirFile, String fileName) {
        try {
            boolean isPng = false;
            if (fileName.toLowerCase().endsWith("png")) {
                isPng = true;
            }
            fileName = MD5Utils.stringToMD5(fileName);
            Bitmap.CompressFormat format = Bitmap.CompressFormat.JPEG;
            if (isPng) {
                format = Bitmap.CompressFormat.PNG;
            }
            String filePath = dirFile.getAbsolutePath() + "/" + fileName;

            File file = new File(filePath);
            file.createNewFile();
            FileOutputStream out;
            try {
                out = new FileOutputStream(file);
                if (bitmap.compress(format, 100, out)) {
                    out.flush();
                    out.close();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public static StateListDrawable getStateListDrawable(Resources resources, String normalIconPath, String selectedIconPath) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        try {
            TypedValue typedValue = new TypedValue();
            typedValue.density = 480;
            if (!StringUtils.isEmpty(selectedIconPath)) {
                try {
                    InputStream inputStream = getFileString(resources, selectedIconPath);
                    Drawable selectedIcon = Drawable.createFromResourceStream(resources,
                            typedValue, inputStream, "selectedIcon");
                    stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, selectedIcon);
                } catch (Throwable e) {
                }
            }
            InputStream inputStreamNor = getFileString(resources, normalIconPath);
            Drawable icon = Drawable.createFromResourceStream(resources,
                    typedValue, inputStreamNor, "normalIcon");
            stateListDrawable.addState(new int[]{}, icon);
        } catch (Throwable e) {
        }
        return stateListDrawable;
    }

    public static InputStream getFileString(Resources resources, String path) {
        InputStream inputStream = null;
        try {
            if (path.startsWith("file:///android_asset/")) {
                path = path.substring("file:///android_asset/".length());
                inputStream = resources.getAssets().open(path);
            } else {
                inputStream = new FileInputStream(path);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static Drawable createFromResourceStream(Resources resources, String path) {
        Drawable drawable = null;
        try {
            TypedValue typedValue = new TypedValue();
            typedValue.density = 480;
            drawable = Drawable.createFromResourceStream(resources,
                    typedValue, new FileInputStream(path), "drawable-" + System.currentTimeMillis());
        } catch (Throwable e) {
        }
        return drawable;
    }

    /**
     * 按最大边按一定大小缩放图片
     *
     * @param resources
     * @param resId
     * @param maxSize   压缩后最大长度
     * @return
     */
    public static Bitmap scaleImage(Resources resources, int resId, int maxSize) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, resId, options);
        options.inSampleSize = calculateInSampleSize(options, maxSize, maxSize);
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(resources, resId, options);
    }

    /**
     * 计算inSampleSize
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        if (width < MIN_WIDTH) {
            return inSampleSize;
        } else {
            int heightRatio;
            if (width > height && reqWidth < reqHeight || width < height && reqWidth > reqHeight) {
                heightRatio = reqWidth;
                reqWidth = reqHeight;
                reqHeight = heightRatio;
            }

            if (height > reqHeight || width > reqWidth) {
                heightRatio = Math.round((float) height / (float) reqHeight);
                int widthRatio = Math.round((float) width / (float) reqWidth);
                inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
            }

            return inSampleSize;
        }
    }

}
