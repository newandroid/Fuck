package css.com.fuck.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import css.com.fuck.R;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ProgressView extends LinearLayout {
	
	private TextView progressText;
	private ProgressColor progressColor;
	private int width = 0;
	private int height = 0;
	private int progressTextLeft = 0;
	
	public ProgressView(Context context) {
		super(context);
		init();
	}
	
	public ProgressView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public ProgressView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	private void init() {
		setOrientation(LinearLayout.VERTICAL);
		progressText = new TextView(getContext());
		LayoutParams progressTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		progressText.setLayoutParams(progressTextParams);
		progressText.setBackgroundResource(R.drawable.download_progress);
		progressText.setGravity(Gravity.CENTER);
		progressText.setTextColor(Color.parseColor("#ffffff"));
		progressText.setTextSize(10);
		progressText.setText(0+"%");
		addView(progressText);

		progressColor = new ProgressColor(getContext());
		LayoutParams progressColorParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		progressColorParams.topMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8,
				getContext().getResources().getDisplayMetrics());
		progressColor.setLayoutParams(progressColorParams);
		addView(progressColor);
	}

	public void setProgress(int progress) {
		progressText.setText(progress+"%");
		progressColor.setProgress(progress);
		int width = progressColor.getProgressViewWidth();
		progressTextLeft = width;
		changeProgressTextLayout();
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		changeProgressTextLayout();
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		this.width = this.getMeasuredWidth();
		this.height = this.getMeasuredHeight();
		changeProgressTextLayout();
	}

	private void changeProgressTextLayout() {
		int width = progressText.getMeasuredWidth();
		int height = progressText.getMeasuredHeight();
		int top = progressText.getTop();
		int left = progressTextLeft - width / 2;
		left = Math.max(0, left);
		left = Math.min(left, this.width - width);
		progressText.layout(left, top, left+width, top+height);
	}

	class ProgressColor extends RelativeLayout {

		private TextView backgroudView;
		private TextView progressView;
		private int width = 0;
		private int height = 0;
		private int progress;

		private int viewHeight;

		public ProgressColor(Context context) {
			super(context);
			init();
		}

		public ProgressColor(Context context, AttributeSet attrs) {
			super(context, attrs);
			init();
		}

		public ProgressColor(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			init();
		}

		private void init() {
			viewHeight = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3,
					getContext().getResources().getDisplayMetrics());
			backgroudView = new TextView(getContext());
			LayoutParams backgroudParams = new LayoutParams(LayoutParams.MATCH_PARENT, viewHeight);
			backgroudView.setLayoutParams(backgroudParams);
			backgroudView.setBackgroundColor(Color.parseColor("#f2f1e9"));
			addView(backgroudView);

			progressView = new TextView(getContext());
			LayoutParams progressParams = new LayoutParams(LayoutParams.WRAP_CONTENT, viewHeight);
			progressView.setLayoutParams(progressParams);
			progressView.setBackgroundColor(Color.parseColor("#317ff5"));
			progressView.setVisibility(View.GONE);
			addView(progressView);
		}

		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {
			super.onSizeChanged(w, h, oldw, oldh);
			this.width = this.getMeasuredWidth();
			this.height = this.getMeasuredHeight();
			changeProgressWidth(this.progress, this.width);
		}

		public void setProgress(int progress) {
			this.progress = progress;
			if (this.width != 0) {
				changeProgressWidth(this.progress, this.width);
			}
		}

		public int getProgressViewWidth() {
			if (progressView != null) {
				return progressView.getMeasuredWidth();
			}
			return 0;
		}

		private void changeProgressWidth(int progress, int width) {
			if (progress == 0) {
				progressView.setVisibility(View.GONE);
				return;
			}
			int progressWidth = 0;
			if (this.progress == 100) {
				progressWidth = this.width;
			} else {
				progressWidth = (int) (this.progress / 100.0f * this.width);
			}
			progressView.setVisibility(View.VISIBLE);
			LayoutParams progressParams = new LayoutParams(progressWidth, viewHeight);
			progressView.setLayoutParams(progressParams);
		}
		
	}

}
