/**
 *
 */
package css.com.fuck.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;


public class RippleView extends View {

    private int mScreenWidth;
    private int mScreenHeight;

    private Bitmap mRippleBitmap;
    private Paint mRipplePaint = new Paint();

    private int mBitmapWidth;
    private int mBitmapHeight;

    private boolean isStartRipple = false;

    private RectF mRect = new RectF();

    private int rippleFirstRadius = 0;
    private int rippleSecendRadius = -33;
    private int rippleThirdRadius = -66;

    private Paint textPaint = new Paint();
    private String mText = "开锁";

    private float length;
    private int height;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            invalidate();
            if (isStartRipple) {
                rippleFirstRadius++;
                if (rippleFirstRadius > 100) {
                    rippleFirstRadius = 0;
                }
                rippleSecendRadius++;
                if (rippleSecendRadius > 100) {
                    rippleSecendRadius = 0;
                }
                rippleThirdRadius++;
                if (rippleThirdRadius > 100) {
                    rippleThirdRadius = 0;
                }
                sendEmptyMessageDelayed(0, 20);
            }
        }
    };

    /**
     * @param context
     */
    public RippleView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public RippleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public RippleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        mRipplePaint.setColor(Color.parseColor("#06a7f2"));
        mRipplePaint.setAntiAlias(true);
        mRipplePaint.setStyle(Paint.Style.FILL);

        textPaint.setTextSize(30);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.WHITE);
        length = textPaint.measureText(mText);
        Rect rect = new Rect();
        textPaint.getTextBounds(mText, 0, mText.length(), rect);
        height = rect.height();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // TODO Auto-generated method stub
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mh = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        int mw = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        mBitmapWidth = mw;
        mBitmapHeight = mh;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        int result = Math.max(mBitmapWidth, mBitmapHeight);
        if (isStartRipple) {
            float f1 = 3 * mBitmapHeight / 10;
            mRipplePaint.setAlpha(255);
            canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2,
                    result / 4, mRipplePaint);
            int i1 = (int) (220.0F - (220.0F - 0.0F) / 100.0F
                    * rippleFirstRadius);
            mRipplePaint.setAlpha(i1);
            canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2,
                    result / 4 + f1 * rippleFirstRadius / 100.0F,
                    mRipplePaint);
            if (rippleSecendRadius >= 0) {
                int i3 = (int) (220.0F - (220.0F - 0.0F) / 100.0F
                        * rippleSecendRadius);
                mRipplePaint.setAlpha(i3);
                canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2,
                        result / 4 + f1 * rippleSecendRadius
                                / 100.0F, mRipplePaint);
            }
            if (rippleThirdRadius >= 0) {
                int i2 = (int) (220.0F - (220.0F - 0.0F) / 100.0F
                        * rippleThirdRadius);
                mRipplePaint.setAlpha(i2);
                canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2,
                        result / 4 + f1 * rippleThirdRadius / 100.0F,
                        mRipplePaint);
            }

        }
        mRipplePaint.setAlpha(255);
        canvas.drawCircle(mBitmapWidth / 2, mBitmapHeight / 2,
                result / 4, mRipplePaint);
        canvas.drawText(mText, (mBitmapWidth - length) / 2,
                (mBitmapHeight + height) / 2, textPaint);
    }

    public boolean isPippleStart() {
        return isStartRipple;
    }

    public void startRipple() {
        if (isStartRipple) return;
        isStartRipple = true;
        handler.sendEmptyMessage(0);
    }

    public void stopRipple() {
        isStartRipple = false;
        handler.removeMessages(0);
        invalidate();
    }

}
