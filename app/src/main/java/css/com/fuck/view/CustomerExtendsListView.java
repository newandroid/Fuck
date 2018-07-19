package css.com.fuck.view;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.ListView;

/**
 * Created by css on 2018/7/11.
 * todo 待完成的view
 */
public class CustomerExtendsListView extends ListView {
    private static final String TAG = "CustomerExtendsListView";
    public CustomerExtendsListView(Context context) {
        super(context);
    }

    public CustomerExtendsListView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomerExtendsListView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        ListView listView = new ListView(getContext());
        ExpandableListView expandableListView = new ExpandableListView(getContext());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw() called with: canvas = [" + canvas + "]");
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d(TAG, "onScrollChanged() called with: l = [" + l + "], t = [" + t + "], oldl = [" + oldl + "], oldt = [" + oldt + "]");
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
