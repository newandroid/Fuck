package css.com.fuck.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import css.com.fuck.utils.StringUtils;


public class MyTextView extends AppCompatTextView {

	public MyTextView(Context context) {
		this(context, null);
	}
	public MyTextView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	public MyTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		super.onLayout(changed, left+3, top, right+3, bottom);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (getText() == null || StringUtils.isEmpty(getText().toString())) {
			super.onDraw(canvas);
			return;
		}
		
		String text = getText().toString();
		int textWidth = (int) getPaint().measureText(text);
		int width = getWidth() - getPaddingLeft() - getPaddingRight();
		if (textWidth - width > 1) {
			int count = text.length();
			int lineTextWidth = 0;
			for(int end=count-1; end>=0; end--) {
				textWidth = (int) getPaint().measureText(text, 0, end);
				if (textWidth <= width) {
					if (width - textWidth < 2) {
						super.onDraw(canvas);
						return;
					}
					lineTextWidth = textWidth;
					break;
				}
			}
			int widthDelta = width - lineTextWidth;
			int ver = widthDelta/2;
			int paddingLeft = getPaddingLeft() + ver;
			int paddingRight = getPaddingRight() + (widthDelta - ver);
			setPadding(paddingLeft, getPaddingTop(), paddingRight, getPaddingBottom());
			requestLayout();
			return;
		}
		
		super.onDraw(canvas);
	}

}
