package css.com.fuck.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Constraints;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

public class TableConstraintLayout extends ConstraintLayout {
    private int width = 0;
    private int height = 0;

    private TableConstraintAdapter mAdapter;

    public TableConstraintLayout(Context context) {
        super(context);
        init();
    }

    public TableConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TableConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void init() {
    }

    private void setItemView(Display display) {
        System.out.println("TableConstraintLayout.setItemView");
        for (int row = 0; row < mAdapter.getRowCount(); row++) {
            for (int column = 0; column < mAdapter.getColumnCount(); column++) {
                int viewIndex = row * mAdapter.getColumnCount() + column;
                if (viewIndex < mAdapter.getCount()) {
                    System.out.println(viewIndex);
                    View childView;
                    childView = mAdapter.getView(viewIndex, getChildAt(viewIndex), this);
//                    Display display = getDisplay();
                    Point size = new Point();
                    display.getSize(size);
                    childView.measure(size.x, size.y);
                    int width = childView.getMeasuredWidth();
                    int height = childView.getMeasuredHeight();

                    ConstraintLayout.LayoutParams lp = new Constraints.LayoutParams(width, height);
                    lp.startToStart = 0;
                    lp.endToEnd = 0;
                    lp.bottomToBottom = 0;
                    lp.topToTop = 0;
                    lp.verticalBias = 1.0f / (mAdapter.getRowCount() + 1) * (row + 1);
                    lp.horizontalBias = 1.0f / (mAdapter.getColumnCount() + 1) * (column + 1);
                    childView.setLayoutParams(lp);
                    addView(childView);
                } else {
                    return;
                }
            }

        }
    }

    public void setAdapter(TableConstraintAdapter adapter,Display display) {
        mAdapter = adapter;
        removeAllViews();
        setItemView(display);
        postInvalidate();
    }

    public static abstract class TableConstraintAdapter {
        public abstract int getColumnCount();

        public abstract int getRowCount();


        public abstract int getCount();

        public abstract View getView(int position, View convertView, ViewGroup parent);
    }


}
