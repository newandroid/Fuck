package css.com.fuck.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/13.
 */

public class MyCircleView extends View {
    private Paint paint;

    public MyCircleView(Context context) {
        super(context);
        init();
    }

    public MyCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);                       //设置画笔为无锯齿
        paint.setColor(Color.parseColor("#06a7f2"));                    //设置画笔颜色
        paint.setStyle(Paint.Style.FILL);

        RectF oval = new RectF();                     //RectF对象
        oval.left = 0;                              //左边
        oval.top = 0;                                   //上边
        oval.right = getWidth();                             //右边
        oval.bottom = getHeight();
        canvas.translate(0, -getHeight() / 2);//下边
        canvas.drawArc(oval, 0, 180, true, paint);    //绘制圆弧
        canvas.save();
    }

}
